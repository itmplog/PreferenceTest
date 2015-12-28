package top.itmp.preferencetest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Map;


public class KeyValueList extends AppCompatActivity {

    private RecyclerView mRecyclerView = null;
    private RecyclerView.Adapter mAdapter = null;
    String[] keys = new String[20];
    private  static String[] values = new String[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_value_list);

        mRecyclerView = (RecyclerView)findViewById(R.id.item_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Map<String, ?> map = PreferenceManager.getDefaultSharedPreferences(this).getAll();

        int i = 0;
        for(Map.Entry<String,?> entry : map.entrySet()){
            keys[i] = entry.getKey();
            values[i++] = entry.getValue() + "";
        }
        mAdapter = new CustomAdapter(keys, this);

        mRecyclerView.setAdapter(mAdapter);

    }
    @Override
    public void setTheme(int resid) {
        super.setTheme(MainActivity.get_theme(this));
    }

    public static class CustomAdapter extends  RecyclerView.Adapter<CustomAdapter.ViewHolder>{
        private String[] mDataset;
        public Context ct;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView;

            public ViewHolder(TextView v) {
                super(v);
                mTextView = v;
            }
        }

        public CustomAdapter(String[] myDataset, Context context) {
            mDataset = myDataset;
            ct = context;
        }


        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            // set the view's size, margins, paddings and layout parameters

            ViewHolder vh = new ViewHolder((TextView)v);
            return vh;
        }

        public void onBindViewHolder(ViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset[position]);

            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("values", values[position] + "");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ct);
                    builder.setMessage(values[position])
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // FIRE ZE MISSILES!
                                }
                            })
                            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            });
                        builder.create();
                }
            });

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }



}
