package top.itmp.preferencetest;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // ActionBar actionBar = getActionBar();
       //actionBar.setDisplayShowTitleEnabled(false);
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
            case R.id.menu1:
                break;
            case R.id.menu2:
                break;
            case R.id.menu3:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
