package cc.easyandroid.pulltorefresh.simple;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import in.srain.cube.views.ptr.PtrFrameLayout;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    PtrFrameLayout ptr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new MyAdapter(this));
        ptr= (PtrFrameLayout) findViewById(R.id.store_house_ptr_frame);
    }

    private class MyAdapter extends BaseAdapter {
        Context context;

        public MyAdapter(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textview = new TextView(context);
            textview.setTextColor(Color.BLACK);
            textview.setTextSize(32);
            textview.setText("text----" + position);
            textview.setHeight(100);
            return textview;
        }
    }
}
