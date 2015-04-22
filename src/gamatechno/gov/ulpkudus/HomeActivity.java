package gamatechno.gov.ulpkudus;

import gamatechno.gov.ulpkudus.adapter.NavDrawerAdapter;
import gamatechno.gov.ulpkudus.fragment.Agenda_Frag;
import gamatechno.gov.ulpkudus.fragment.Berita_Frag;
import gamatechno.gov.ulpkudus.fragment.Common_Frag;
import gamatechno.gov.ulpkudus.fragment.Kontak_Frag;
import gamatechno.gov.ulpkudus.fragment.Lelang_Frag;
import gamatechno.gov.ulpkudus.fragment.Pelayanan_Frag;
import gamatechno.gov.ulpkudus.fragment.Pengumuman_Frag;
import gamatechno.gov.ulpkudus.fragment.Regulasi_Frag;
import gamatechno.gov.ulpkudus.model.NavDrawer_M;
import gamatechno.government.ulpkudus.R;

import java.util.ArrayList;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class HomeActivity extends ActionBarActivity {
	private ActionBarDrawerToggle mDrawerToggle;
	private ArrayList<NavDrawer_M> navDrawerItems;

	private DrawerLayout mDrawerLayout;
	private NavDrawerAdapter adapter;
	private LinearLayout slide_menu;
	private ListView mDrawerList;

	private String[] navMenuTitles;
	private String[] navMenuSubtitles;
	private TypedArray navMenuIcons;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity_layout);

		init_components();
		setup_toolbar();
		setup_navigation_drawer();

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id._container, Berita_Frag.newInstance(getBaseContext()));
		ft.commit();

	}

	private void init_components() {
		mDrawerList = (ListView) findViewById(R.id.nav_drawer_list);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	}

	private void setup_toolbar() {
		toolbar = (Toolbar) findViewById(R.id.action_bar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}

	/**
	 * (non-Javadoc) Setting Navigation Drawer
	 * ---------------------------------------------------------------------
	 **/
	private void setup_navigation_drawer() {
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
				R.string.drawer_open, R.string.drawer_close) {
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				syncState();
			}

			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				syncState();
			}
		};

		navDrawerItems = new ArrayList<NavDrawer_M>();
		navMenuTitles = getResources().getStringArray(R.array.nav_list_title);
		navMenuSubtitles = getResources().getStringArray(
				R.array.nav_list_subtitle);
		navMenuIcons = getResources().obtainTypedArray(R.array.nav_list_icons);

		for (int i = 0; i < navMenuTitles.length; i++) {
			navDrawerItems.add(new NavDrawer_M(navMenuTitles[i],
					navMenuSubtitles[i], navMenuIcons.getResourceId(i, -1)));
		}

		navMenuIcons.recycle();

		adapter = new NavDrawerAdapter(getApplicationContext(), navDrawerItems);
		mDrawerList.setAdapter(adapter);

		mDrawerList.setOnItemClickListener(new NavigationAction());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		mDrawerToggle.syncState();
		super.onPostCreate(savedInstanceState);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		mDrawerToggle.onConfigurationChanged(newConfig);
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.home:
			if (mDrawerLayout.isDrawerOpen(slide_menu)) {
				mDrawerLayout.closeDrawer(slide_menu);
			} else {
				mDrawerLayout.openDrawer(slide_menu);
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Slide menu item click listener
	 * */
	private class NavigationAction implements ListView.OnItemClickListener {
		Fragment mFragment;

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			mFragment = null;

			switch (position) {
			case 0:
				// mFragment = Berita_Frag.newInstance(getBaseContext());
				mFragment = Common_Frag.newInstance(getBaseContext());
				break;
			case 1:
				mFragment = Pengumuman_Frag.newInstance();
				break;
			case 2:
				mFragment = Lelang_Frag.newInstance(getBaseContext());
				break;
			case 3:
				mFragment = Agenda_Frag.newInstance();
				break;
			case 4:
				mFragment = Pelayanan_Frag.newInstance();
				break;
			case 5:
				mFragment = Regulasi_Frag.newInstance(getBaseContext());
				break;
			case 6:
				mFragment = Kontak_Frag.newInstance();
				break;
			}

			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					FragmentTransaction ft = getSupportFragmentManager()
							.beginTransaction();
					ft.add(R.id._container, mFragment);
					ft.commit();

					mDrawerLayout.closeDrawers();
				}
			}, 200);
		}
	}
}
