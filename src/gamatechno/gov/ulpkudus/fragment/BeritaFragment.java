package gamatechno.gov.ulpkudus.fragment;

import gamatechno.gov.ulpkudus.ContentActivity;
import gamatechno.gov.ulpkudus.adapter.BeritaAdapter;
import gamatechno.gov.ulpkudus.config.Service_URL;
import gamatechno.gov.ulpkudus.lib.AppController;
import gamatechno.gov.ulpkudus.model.BeritaItem;
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
import android.support.v4.widget.SwipeRefreshLayout;
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

public class BeritaFragment extends Fragment implements OnItemClickListener {
	private static final String TAG = "Berita Fragment";

	private ActionBar actionBar;
	private BeritaAdapter listAdapter;
	private ListView listView;
	private List<BeritaItem> beritaItem;
	private LinearLayout spin_container;
	private ProgressBar spin_loading;
	private static Context sContext;

	private SwipeRefreshLayout mSwipeRefreshLayout = null;

	private boolean dataIsCompleted = false;

	public static BeritaFragment newInstance(Context context) {
		BeritaFragment mFragment = new BeritaFragment();
		sContext = context;
		return mFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.berita_fragment_layout,
				container, false);

		actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
		actionBar.setTitle("Berita");

		spin_container = (LinearLayout) rootView
				.findViewById(R.id.spin_container);
		spin_loading = (ProgressBar) rootView.findViewById(R.id.spin_loading);

		listView = (ListView) rootView.findViewById(R.id.list_berita);

		if (dataIsCompleted == false) {

			beritaItem = new ArrayList<BeritaItem>();

			listAdapter = new BeritaAdapter(sContext, beritaItem);
			listView.setAdapter(listAdapter);

			loadingStart();
			getBeritaContent();

			listView.setOnItemClickListener(this);

		} else {
			loadingStop();
		}

		mSwipeRefreshLayout = (SwipeRefreshLayout) rootView
				.findViewById(R.id.swipeRefreshLayout);
		mSwipeRefreshLayout
				.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
					@Override
					public void onRefresh() {
						getBeritaContent();
					}
				});

		return rootView;
	}

	private void getBeritaContent() {
		JsonArrayRequest jsonReq = new JsonArrayRequest(
				new Service_URL().get_address(0),
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						try {
							for (int i = 0; i < response.length(); i++) {

								JSONObject berita = (JSONObject) response
										.get(i);

								String title = berita.getString("title");
								String author = berita.getString("author");
								String date = berita.getString("date");
								String content = berita.getString("content");

								BeritaItem data = new BeritaItem();
								data.setTitle(title);
								data.setAuthor(author);
								data.setDate(date);
								data.setContent(content);

								beritaItem.clear();
								beritaItem.add(data);
								listAdapter.notifyDataSetChanged();
							}

							dataIsCompleted = true;
							loadingStop();

							updateList();

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

		AppController.getInstance().addToRequestQueue(jsonReq);
	}

	private void updateList() {
		listAdapter = new BeritaAdapter(sContext, beritaItem);
		listView.setAdapter(listAdapter);

		if (mSwipeRefreshLayout.isRefreshing()) {
			mSwipeRefreshLayout.setRefreshing(false);
		}
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
		
		BeritaItem item = beritaItem.get(position);

		Intent intent = new Intent(sContext, ContentActivity.class);
		intent.putExtra("title", item.getTitle());
		intent.putExtra("author", item.getAuthor());
		intent.putExtra("date", item.getDate());
		intent.putExtra("content", item.getContent());
		startActivity(intent);
	}

}
