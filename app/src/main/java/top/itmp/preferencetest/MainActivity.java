package top.itmp.preferencetest;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.Locale;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static Integer[] preferences = {R.xml.pfrag0, R.xml.preference, R.xml.frag3};
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


        SharedPreferences sharedPreferences = getPreferences(MODE_WORLD_READABLE);//PreferenceManager.getDefaultSharedPreferences(this);
        if(sharedPreferences.getString("theme", null) != null)
            sharedPreferences.edit().putString("theme", "fuck").commit();

        String language ;
        if((language = PreferenceManager.getDefaultSharedPreferences(this).getString("language", null)) == null) {
            language = Locale.getDefault().getLanguage();
        }
        Locale locale = new Locale(language);
        locale.setDefault(locale);
        Configuration conf = new Configuration();
        conf.locale = locale;
        getResources().updateConfiguration(conf, getResources().getDisplayMetrics());
        for (Map.Entry<String,?> entry : PreferenceManager.getDefaultSharedPreferences(this).getAll().entrySet()) {
            System.out.println("key = " + entry.getKey() + " and value = " + entry.getValue());
        }
                        // getFragmentManager().beginTransaction().add(android.R.id.content, new PreferenceFragmentTest.PreferenceFragment0()).commit();

    }

    @Override
    public void setTheme(int resid) {
        super.setTheme(get_theme(this));
    }

    public static int get_theme(Context context){
        String theme =
                PreferenceManager.getDefaultSharedPreferences(context).getString("theme", null);
        if(theme == null){
            theme = context.getTheme().toString();
        }

        switch (theme) {
            case "dark":
                return R.style.DarkTheme;

            case "light":
                return R.style.LightTheme;

            default:
                return R.style.DarkTheme;

        }

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
                viewPager.setCurrentItem(0);
                break;
            case R.id.two:
                viewPager.setCurrentItem(1);
                break;
            case R.id.three:
                viewPager.setCurrentItem(2);
                break;
            case R.id.four:
                viewPager.setCurrentItem(3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /*
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
*/
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(final int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            // return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0:
                case 1:
                case 2:
                return new PreferenceFragment() {
                    @Override
                    public void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        addPreferencesFromResource(preferences[position]);


                  /*  String theme = null;
                    if((theme = getPreferenceManager().getSharedPreferences().getString("theme", null)) == null){
                        theme = getTheme().toString();
                        Log.d("get theme", theme);
                    }
                        switch(theme){
                            case "dark":
                                getActivity().setTheme(android.support.v7.appcompat.R.style.Theme_AppCompat);
                                Log.d("dark theme", theme);
                                break;
                            case "light":
                                getActivity().setTheme(android.support.v7.appcompat.R.style.Theme_AppCompat_Light);
                                Log.d("light theme", theme);
                                break;
                            default:
                                getActivity().setTheme(android.support.v7.appcompat.R.style.Theme_AppCompat);
                                break;
                        }

*/


                    }

                    @Override
                    public void onPause() {
                        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
                        super.onPause();
                    }

                    @Override
                    public void onResume() {
                        super.onResume();
                        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
                    }


                    SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                        @Override
                        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                            // Toast.makeText(getApplicationContext(), key + "  " + sharedPreferences.getString(key, "") ,Toast.LENGTH_SHORT ).show();

                            switch (key) {
                                case "language":
                                    //Snackbar.make(getView(), "Language is set to " + sharedPreferences.getString(key, ""), Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                                    getActivity().finish();
                                    Intent intent = new Intent(getActivity(), getActivity().getClass());
                                    startActivity(intent);
                                    break;
                                case "theme":
                                    getActivity().finish();
                                    Intent intent1 = new Intent(getActivity(), getActivity().getClass());
                                    startActivity(intent1);
                                    break;
                                default:
                                    break;
                            }
                        }
                    };
                };

                default:  // case 3:
                    return new Fragment(){
                        @Override
                        public void onCreate(Bundle savedInstanceState) {
                            super.onCreate(savedInstanceState);
                        }

                        @Nullable
                        @Override
                        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                            TextView textView = new TextView(getActivity());
                            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT);
                            textView.setLayoutParams(layoutParams);
                            textView.setText("Hello world!!");
                            //textView.setBackground(new ColorDrawable(Color.parseColor("#FF00ccaa")));
                            return textView;
                            //return super.onCreateView(inflater, container, savedInstanceState);
                        }
                    };
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.app_name);
                case 1:
                    return "Settings";
                case 2:
                    return "Fragments";
                case 3:
                    return "Key Values";
            }
            return null;
        }
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            getSupportActionBar().setTitle(fragmentPagerAdapter.getPageTitle(position));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        viewPager.removeOnPageChangeListener(onPageChangeListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }
}
