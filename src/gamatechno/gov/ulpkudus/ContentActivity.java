package gamatechno.gov.ulpkudus;

import gamatechno.government.ulpkudus.R;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.TextView;

public class ContentActivity extends ActionBarActivity {
	private Toolbar toolbar;

	String cTitle, cAuthor, cDate, cContent;
	TextView mTitle, mAuthor, mDate;
	WebView mContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_detail_layout);

		setup_toolbar();

		cTitle = getIntent().getStringExtra("title");
		cAuthor = getIntent().getStringExtra("author");
		cDate = getIntent().getStringExtra("date");
		cContent = getIntent().getStringExtra("content");

		mTitle = (TextView) findViewById(R.id.judul);
		mAuthor = (TextView) findViewById(R.id.author);
		mDate = (TextView) findViewById(R.id.tanggal);
		mContent = (WebView) findViewById(R.id.content);

		mTitle.setText(cTitle);
		mAuthor.setText(cAuthor);
		mDate.setText(cDate);
		mContent.loadData(cContent, "text/html", "UTF-8");

	}

	private void setup_toolbar() {
		toolbar = (Toolbar) findViewById(R.id.action_bar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}
}
