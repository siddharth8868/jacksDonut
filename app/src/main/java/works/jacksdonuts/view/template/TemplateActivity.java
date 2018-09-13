package works.jacksdonuts.view.template;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import works.jacksdonuts.R;
import works.jacksdonuts.core.NavigationDrawerActivity;

public class TemplateActivity extends NavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TemplateFragment fragment = new TemplateFragment();

        setFragment(fragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(R.string.contact_us);
    }
}
