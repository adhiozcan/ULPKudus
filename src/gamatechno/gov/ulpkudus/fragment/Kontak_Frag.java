package gamatechno.gov.ulpkudus.fragment;

import gamatechno.government.ulpkudus.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Kontak_Frag extends Fragment {
	private ActionBar actionBar;

	public static Kontak_Frag newInstance() {
		Kontak_Frag mFragment = new Kontak_Frag();

		return mFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.kontak_fragment_layout,
				container, false);
		actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
		actionBar.setTitle("Kontak");
		return rootView;
	}
}
