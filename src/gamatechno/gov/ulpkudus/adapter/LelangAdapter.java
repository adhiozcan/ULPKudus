package gamatechno.gov.ulpkudus.adapter;

import gamatechno.gov.ulpkudus.model.LelangItem;
import gamatechno.government.ulpkudus.R;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LelangAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater inflater;
	private List<LelangItem> lelangItem;

	public LelangAdapter(Context mContext, List<LelangItem> lelangItem) {
		this.mContext = mContext;
		this.lelangItem = lelangItem;
	}

	@Override
	public int getCount() {
		return lelangItem.size();
	}

	@Override
	public Object getItem(int position) {
		return lelangItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (inflater == null)
			inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.lelang_fragment_item, null);

		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView author = (TextView) convertView.findViewById(R.id.author);
		TextView date = (TextView) convertView.findViewById(R.id.date);
		WebView content = (WebView) convertView.findViewById(R.id.content);

		LelangItem item = lelangItem.get(position);

		title.setText(item.getTitle());
		author.setText(item.getAuthor());
		date.setText(item.getDate());

		return convertView;
	}
}
