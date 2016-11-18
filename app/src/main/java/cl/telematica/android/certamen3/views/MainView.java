package cl.telematica.android.certamen3.views;

import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Erlend on 18.11.2016.
 */

public interface MainView {
    void createMyRecyclerView();
    boolean onCreateOptionsMenu(Menu menu);
    boolean onOptionsItemSelected(MenuItem item);


}
