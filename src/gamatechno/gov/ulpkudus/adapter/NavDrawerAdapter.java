package gamatechno.gov.ulpkudus.adapter;

import gamatechno.gov.ulpkudus.model.NavDrawer_M;
import gamatechno.government.ulpkudus.R;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NavDrawerAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<NavDrawer_M> navDrawerItem;

	public NavDrawerAdapter(Context context,
			ArrayList<NavDrawer_M> navDrawerItem) {
		this.context = context;
		this.navDrawerItem = navDrawerItem;
	}

	@Override
	public int getCount() {
		return navDrawerItem.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.list_navigation_drawer,
					null);
		}

		TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
		TextView txtSubtitle = (TextView) convertView
				.findViewById(R.id.subtitle);
		ImageView iconView = (ImageView) convertView.findViewById(R.id.icon);

		txtTitle.setText(navDrawerItem.get(position).getTitle());
		txtSubtitle.setText(navDrawerItem.get(position).getSubtitle());
		iconView.setImageResource(navDrawerItem.get(position).getIcon());

		return convertView;
	}

}
