package gamatechno.gov.ulpkudus.fragment;

import gamatechno.gov.ulpkudus.ContentActivity;
import gamatechno.gov.ulpkudus.adapter.RegulasiAdapter;
import gamatechno.gov.ulpkudus.config.Service_URL;
import gamatechno.gov.ulpkudus.lib.AppController;
import gamatechno.gov.ulpkudus.model.Regulasi_M;
import gamatechno.government.ulpkudus.R;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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

public class Regulasi_Frag extends Fragment implements OnItemClickListener {
	private static final String TAG = "Regulasi Fragment";

	private ActionBar actionBar;

	private LinearLayout spin_container;
	private ProgressBar spin_loading;
	private ListView listView;
	private RegulasiAdapter listAdapter;
	private List<Regulasi_M> regulasiItem;
	private static Context sContext;

	private boolean dataIsCompleted = false;

	public static Regulasi_Frag newInstance(Context context) {
		Regulasi_Frag mFragment = new Regulasi_Frag();
		sContext = context;
		return mFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.regulasi_fragment_layout,
				container, false);

		actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
		actionBar.setTitle("Regulasi");

		spin_container = (LinearLayout) rootView
				.findViewById(R.id.spin_container);
		spin_loading = (ProgressBar) rootView.findViewById(R.id.spin_loading);

		listView = (ListView) rootView.findViewById(R.id.list);

		if (dataIsCompleted == false) {

			regulasiItem = new ArrayList<Regulasi_M>();
			listAdapter = new RegulasiAdapter(sContext, regulasiItem);
			listView.setAdapter(listAdapter);

			loadingStart();
			getRegulasiContent();

			listView.setOnItemClickListener(this);

		} else {
			loadingStop();
		}

		return rootView;
	}

	private void getRegulasiContent() {
		JsonArrayRequest jsonReq = new JsonArrayRequest(
				new Service_URL().get_address(2),
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						try {
							for (int i = 0; i < response.length(); i++) {

								JSONObject berita = (JSONObject) response
										.get(i);

								String title = berita.getString("title");
								Log.d(TAG, title);
								String author = berita.getString("author");
								Log.d(TAG, author);
								String date = berita.getString("date");
								Log.d(TAG, date);
								String content = berita.getString("content");

								Regulasi_M data = new Regulasi_M();
								data.setTitle(title);
								data.setAuthor(author);
								data.setDate(date);
								data.setContent(content);

								regulasiItem.add(data);
								listAdapter.notifyDataSetChanged();
							}

							// Data Lengkap
							dataIsCompleted = true;
							spin_container.setVisibility(View.GONE);
							spin_loading.setVisibility(View.GONE);

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

		Regulasi_M item = regulasiItem.get(position);

		Intent intent = new Intent(sContext, ContentActivity.class);
		intent.putExtra("title", item.getTitle());
		intent.putExtra("author", item.getAuthor());
		intent.putExtra("date", item.getDate());
		intent.putExtra("content", item.getContent());
		startActivity(intent);

	}
}
