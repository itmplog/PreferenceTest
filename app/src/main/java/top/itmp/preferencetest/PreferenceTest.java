package top.itmp.preferencetest;

import android.os.Bundle;
import android.support.v4.app.NavUtils;


//import top.itmp.preferencetest.R;

/**
 * Created by hz on 2015/12/14.
 */
public class PreferenceTest extends AppCompatPreferenceActivity{//PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  AppCompatDelegate.create(this, null).getSupportActionBar().setTitle(getString(R.string.settings));

        addPreferencesFromResource(R.xml.preference);
    }

    @Override
    public void setTheme(int resid) {
        super.setTheme(MainActivity.get_theme(this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NavUtils.navigateUpFromSameTask(this);
    }
}
