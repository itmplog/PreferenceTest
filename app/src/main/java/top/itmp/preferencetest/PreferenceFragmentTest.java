package top.itmp.preferencetest;

import android.app.Fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;

import android.preference.PreferenceFragment;

import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;



/**
 * Created by hz on 2015/12/14.
 */
public class PreferenceFragmentTest extends AppCompatActivity {
    private static final int CONTENT_VIEW_ID = 10101010;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setId(CONTENT_VIEW_ID);
        setContentView(frameLayout);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(frameLayout.getId(), new PreferenceFragment0());
        fragmentTransaction.commit();


    }



    public static class PreferenceFragment0 extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferencefrag);
        }

        @Override
        public void addPreferencesFromResource(int preferencesResId) {
            super.addPreferencesFromResource(preferencesResId);
        }
    }
}