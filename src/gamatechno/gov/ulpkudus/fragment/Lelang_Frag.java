package gamatechno.gov.ulpkudus.fragment;

import gamatechno.gov.ulpkudus.ContentActivity;
import gamatechno.gov.ulpkudus.adapter.LelangAdapter;
import gamatechno.gov.ulpkudus.config.Service_URL;
import gamatechno.gov.ulpkudus.lib.AppController;
import gamatechno.gov.ulpkudus.model.Lelang_M;
import gamatechno.government.ulpkudus.R;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

public class Lelang_Frag extends Fragment implements OnItemClickListener {
	private static final String TAG = "LelangFragment";

	private ActionBar actionBar;
	private ArrayList<Lelang_M> lelangItem;
	private ListView listView;
	private LinearLayout spin_container;
	private ProgressBar spin_loading;
	private LelangAdapter listAdapter;
	private static Context sContext;

	private boolean dataIsCompleted = false;

	public static Lelang_Frag newInstance(Context Context) {
		Lelang_Frag mFragment = new Lelang_Frag();
		sContext = Context;
		return mFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.lelang_fragment_layout,
				container, false);

		actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
		actionBar.setTitle("Informasi Lelang");

		spin_container = (LinearLayout) rootView
				.findViewById(R.id.spin_container);
		spin_loading = (ProgressBar) rootView.findViewById(R.id.spin_loading);

		listView = (ListView) rootView.findViewById(R.id.list_lelang);

		if (dataIsCompleted == false) {

			lelangItem = new ArrayList<Lelang_M>();
			listAdapter = new LelangAdapter(sContext, lelangItem);
			listView.setAdapter(listAdapter);

			loadingStart();
			getLelangContent();

			listView.setOnItemClickListener(this);

		} else {
			loadingStop();
		}

		return rootView;
	}

	private void getLelangContent() {
		JsonArrayRequest jsonReq = new JsonArrayRequest(
				new Service_URL().get_address(1),
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						try {
							for (int i = 0; i < response.length(); i++) {

								JSONObject lelang = (JSONObject) response
										.get(i);

								String title = lelang.getString("title");
								String author = lelang.getString("author");
								String date = lelang.getString("date");
								String content = lelang.getString("content");

								Lelang_M data = new Lelang_M();
								data.setTitle(title);
								data.setAuthor(author);
								data.setDate(date);
								data.setContent(content);

								lelangItem.add(data);
								listAdapter.notifyDataSetChanged();
							}

							dataIsCompleted = true;
							loadingStop();

						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(sContext,
									"Error: " + e.getMessage(),
									Toast.LENGTH_LONG).show();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						Toast.makeText(sContext, error.getMessage(),
								Toast.LENGTH_SHORT).show();
					}
				});

		// Adding request to volley request queue
		AppController.getInstance().addToRequestQueue(jsonReq);
	}

	private void loadingStart() {
		spin_loading.setVisibility(View.VISIBLE);
	}

	private void loadingStop() {
		spin_container.setVisibility(View.GONE);
		spin_loading.setVisibility(View.GONE);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Lelang_M item = lelangItem.get(position);

		Intent intent = new Intent(sContext, ContentActivity.class);
		intent.putExtra("title", item.getTitle());
		intent.putExtra("author", item.getAuthor());
		intent.putExtra("date", item.getDate());
		intent.putExtra("content", item.getContent());
		startActivity(intent);
	}

}
