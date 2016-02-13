package com.example.philoniare.weather_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private static final String FORECAST_SHARE_HASHTAG = "#WeatherApp";
    private String mForecastStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            mForecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            TextView textView = (TextView) findViewById(R.id.detail_text);
            textView.setText(mForecastStr);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detailfragment, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        ShareActionProvider mShareActionProvider = new ShareActionProvider(this);
        MenuItemCompat.setActionProvider(menuItem, mShareActionProvider);

        if(mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(createShareForecastIntent());
        } else {
            Log.d("DetailActivity", "Share Provider is null!");
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent displaySettings = new Intent(this, SettingsActivity.class);
            startActivity(displaySettings);
        }

        return super.onOptionsItemSelected(item);
    }
    private Intent createShareForecastIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,
                mForecastStr + FORECAST_SHARE_HASHTAG);
        return shareIntent;
    }
}
