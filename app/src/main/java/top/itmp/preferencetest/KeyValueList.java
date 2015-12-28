package top.itmp.preferencetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class KeyValueList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_value_list);
    }
    @Override
    public void setTheme(int resid) {
        super.setTheme(MainActivity.get_theme(this));
    }
}
