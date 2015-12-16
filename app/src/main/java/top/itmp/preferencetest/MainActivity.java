package top.itmp.preferencetest;




import android.app.FragmentManager;
import android.content.Intent;
import android.preference.PreferenceFragment;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends AppCompatActivity {

    private static Integer[] preferences = {R.xml.preference, R.xml.preferencefrag, R.xml.preferencefrag};
    private ViewPager viewPager = null;
    private FragmentPagerAdapter fragmentPagerAdapter = null;
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

        viewPager = (ViewPager)findViewById(R.id.container);
        fragmentPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(3);

       // getFragmentManager().beginTransaction().add(android.R.id.content, new PreferenceFragmentTest.PreferenceFragment0()).commit();

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

    public static class PlaceholderFragment extends PreferenceFragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        private  int i= 0;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            if((i = getArguments().getInt(ARG_SECTION_NUMBER)) != 0) {
                addPreferencesFromResource(preferences[i-1]);
            }
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public PreferenceFragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
