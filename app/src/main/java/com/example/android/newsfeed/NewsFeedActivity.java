package com.example.android.newsfeed;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    public static final String LOG_TAG = NewsFeedActivity.class.getName();

    /** URL to query the Guardian api dataset for news information */
    private static final String GUARDIAN_REQUEST_URL = "http://content.guardianapis.com/search?q=technology&api-key=test";

    private static final int News_LOADER_ID = 1;

    private NewsFeedAdapter mAdapter;

    private View circularProgressBar;
    private TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfeed);

        ListView newsListView = (ListView) findViewById(R.id.list);

        mAdapter = new NewsFeedAdapter(this, new ArrayList<News>());

        newsListView.setAdapter(mAdapter);

        emptyView = (TextView)findViewById(R.id.empty_view) ;

        circularProgressBar = findViewById(R.id.loading_spinner);

        newsListView.setEmptyView(emptyView);

        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected){
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(News_LOADER_ID, null, this);
        }
        else {
            circularProgressBar.setVisibility(View.GONE);
            emptyView.setText(R.string.no_internet_connection);
        }


    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        // Create a new loader for the given URL
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        circularProgressBar.setVisibility(View.GONE);
        // Set empty state text to display "No subjects found."
        emptyView.setText(R.string.no_subjects);
        Log.e("ONloadFinished", "loaded");
        // Clear the adapter of previous book data
        mAdapter.clear();
        // If there is a valid list of {@link News}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
