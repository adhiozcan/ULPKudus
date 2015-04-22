package gamatechno.gov.ulpkudus.adapter;

import gamatechno.gov.ulpkudus.model.Common_M;
import gamatechno.government.ulpkudus.R;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommonAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater inflater;
	private List<Common_M> listItem;

	public CommonAdapter(Context mContext, List<Common_M> listItem) {
		this.mContext = mContext;
		this.listItem = listItem;
	}

	@Override
	public int getCount() {
		return listItem.size();
	}

	@Override
	public Object getItem(int position) {
		return listItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (inflater == null)
			inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.berita_fragment_item, null);

		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView author = (TextView) convertView.findViewById(R.id.author);
		TextView date = (TextView) convertView.findViewById(R.id.date);
		WebView content = (WebView) convertView.findViewById(R.id.content);

		Common_M item = listItem.get(position);

		title.setText(item.getTitle());
		author.setText(item.getAuthor());
		date.setText(item.getDate());
		return convertView;
	}

}
