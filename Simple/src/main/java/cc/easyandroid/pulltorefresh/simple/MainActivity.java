package cc.easyandroid.pulltorefresh.simple;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.loadmore.LoadMorePtrFrameLayout;
import in.srain.cube.views.ptr.loadmore.OnLoadMoreListener;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    LoadMorePtrFrameLayout ptr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        ptr= (LoadMorePtrFrameLayout) findViewById(R.id.store_house_ptr_frame);
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);
        header.setLastUpdateTimeKey(getClass().getName());
//        final StoreHouseHeader header = new StoreHouseHeader(this);
//        header.setBackgroundColor(Color.BLACK);
        header.setPadding(0, 50, 0, 50);
//        header.initWithString("Ultra PTR");

        ptr.setDurationToCloseHeader(500);
        ptr.setHeaderView(header);
        ptr.addPtrUIHandler(header);
//        ptr.addPtrUIHandler(new PtrClassicDefaultHeader(this));
        ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ptr.refreshComplete();
                    }
                }, 3000);
                System.out.println("onRefreshBegin=");
            }
        });
        listView.setAdapter(new MyAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position=" + position);
                if (toast == null) {
                    toast = Toast.makeText(getApplicationContext(), "position=" + position, Toast.LENGTH_SHORT);
                } else {
                    toast.setText("position=" + position);
                }
                toast.show();
            }
        });
        ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMoreBegin(final LoadMorePtrFrameLayout frame) {
                System.out.println("setOnLoadMoreListener=" +  1);
                frame.showLoading();
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.showNormal();
                    }
                }, 3000);
            }
        });
    }
    Toast toast;

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
            textview.setHeight(300);
            return textview;
        }
    }
}
