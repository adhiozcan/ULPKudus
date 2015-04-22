package gamatechno.gov.ulpkudus.config;

import gamatechno.gov.ulpkudus.adapter.CommonAdapter;
import gamatechno.gov.ulpkudus.lib.AppController;
import gamatechno.gov.ulpkudus.model.Common_M;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

public class JsonFeed {
	private CommonAdapter listAdapter;
	private List<Common_M> beritaItem;
	private Context mContext;

	public JsonFeed(CommonAdapter listAdapter, List<Common_M> beritaItem,
			Context mContext) {
		this.listAdapter = listAdapter;
		this.beritaItem = beritaItem;
		this.mContext = mContext;
	}

	public void getBeritaContent() {
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

								Common_M data = new Common_M();
								data.setTitle(title);
								data.setAuthor(author);
								data.setDate(date);
								data.setContent(content);

								beritaItem.clear();
								beritaItem.add(data);
								listAdapter.notifyDataSetChanged();
							}

						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(mContext,
									"Error: " + e.getMessage(),
									Toast.LENGTH_LONG).show();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
					}
				});

		AppController.getInstance().addToRequestQueue(jsonReq);
	}
}
