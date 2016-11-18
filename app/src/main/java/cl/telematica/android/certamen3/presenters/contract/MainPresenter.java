package cl.telematica.android.certamen3.presenters.contract;

import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Erlend on 18.11.2016.
 */

public interface MainPresenter {
    void inflateList(RecyclerView mAdapter);
    void inflateCreateOptionsMenu(Menu menu);
    boolean onOptionsItemSelected(MenuItem item);
}
