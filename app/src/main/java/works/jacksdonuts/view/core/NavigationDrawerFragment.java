package works.jacksdonuts.view.core;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import works.jacksdonuts.R;
import works.jacksdonuts.model.NavigationItem;
import works.jacksdonuts.model.NavigationDrawerListAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    @Nullable
    private NavigationDrawerCallback callback;

    private ListView listView;

    public final void setNavigationDrawerCallback(@Nullable NavigationDrawerCallback callback) {
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.nav_bar_list_view);
        loadListView();
    }

    private void loadListView() {
        NavigationDrawerListAdapter adapter = new NavigationDrawerListAdapter(getActivity(), new NavigationDrawerListAdapter.Delegate() {
            @Override
            public void onNavigationItemSelected(NavigationItem item) {
                if (callback != null) {
                    callback.onNavigationItemSelected(item);
                }
            }
        });
        listView.setAdapter(adapter);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }


    public interface NavigationDrawerCallback {

        void onNavigationItemSelected(@NonNull NavigationItem item);
    }

}
