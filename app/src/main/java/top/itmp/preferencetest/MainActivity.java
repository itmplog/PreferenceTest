package top.itmp.preferencetest;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.preference.PreferenceFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*RelativeLayout relativeLayout = new RelativeLayout(this);
       relativeLayout.setId(10111001);
        setContentView(relativeLayout);*/
       // ActionBar actionBar = getActionBar();
       //actionBar.setDisplayShowTitleEnabled(false);
       // final ViewPager viewPager = (ViewPager)findViewById(R.id.container);
        getFragmentManager().beginTransaction().add(R.id.container, new PreferenceFragmentTest.PreferenceFragment0()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.add(Menu.NONE, Menu.FIRST+1, 100, R.string.settings).setIcon(R.mipmap.ic_launcher).setShowAsAction(1);
        // 手动添加的Menu， 更新一些操作
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.preferenceFragment:
                Intent intentFrag = new Intent(MainActivity.this, PreferenceFragmentTest.class);
                startActivity(intentFrag);
                break;
            case Menu.FIRST + 1:
                Intent intent = new Intent(MainActivity.this, PreferenceTest.class);
                startActivity(intent);
                break;
            case R.id.one:
            case R.id.two:
            case R.id.three:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
