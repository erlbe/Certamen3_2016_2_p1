package cl.telematica.android.certamen3.presenters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.android.certamen3.DataAdapter;
import cl.telematica.android.certamen3.helpers.DBHelper;
import cl.telematica.android.certamen3.helpers.MyAsyncTaskExecutor;
import cl.telematica.android.certamen3.R;
import cl.telematica.android.certamen3.models.Feed;
import cl.telematica.android.certamen3.presenters.contract.MainPresenter;
import cl.telematica.android.certamen3.views.MainView;

/**
 * Created by Erlend on 18.11.2016.
 */

public class MainPresenterImpl implements MainPresenter {

    private final Activity activity;
    private final MainView mainView;
    private DataAdapter mAdapter;


    public MainPresenterImpl(Activity activity, MainView mainView) {
        this.activity = activity;
        this.mainView = mainView;
        mainView.createMyRecyclerView();
        DBHelper.getInstance().createDatabase(activity);
        mAdapter = new DataAdapter(activity, new ArrayList<Feed>());
    }

    @Override
    public void inflateList(RecyclerView mRecyclerView) {
        MyAsyncTaskExecutor.getInstance().executeMyAsynctask(activity, mRecyclerView, mAdapter);
    }

    @Override
    public void inflateCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        activity.getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Toast.makeText(activity, "Showing only favorites", Toast.LENGTH_LONG).show();

        List<String> favoriteLinks = DBHelper.getInstance().getFavorites();
        List<Feed> favoriteFeeds = new ArrayList<Feed>();
        for (Feed feed: mAdapter.getDataset()) {
            if(favoriteLinks.contains(feed.getLink())){
                favoriteFeeds.add(feed);
            }
        }
        mAdapter.setDataset(favoriteFeeds);

        if (id == R.id.action_settings) {
            /**
             * You should manage the action to show the favorite items saved by the user
             */
            return true;
        }
        return false;
    }
}
