package gamatechno.gov.ulpkudus.fragment;

import gamatechno.government.ulpkudus.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Pelayanan_Frag extends Fragment {
	private ActionBar actionBar;

	public static Pelayanan_Frag newInstance() {
		Pelayanan_Frag mFragment = new Pelayanan_Frag();
		return mFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.pelayanan_fragment_layout, container,
				false);

		actionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
		actionBar.setTitle("Pelayanan");

		return rootView;
	}

}
