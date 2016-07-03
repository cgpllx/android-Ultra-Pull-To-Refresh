package cc.easyandroid.pulltorefresh.simple;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cc.easyandroid.pulltorefresh.simple.dummy.DummyContent;
import cc.easyandroid.pulltorefresh.simple.dummy.DummyContent.DummyItem;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.loadmore.LoadMorePtrFrameLayout;
import in.srain.cube.views.ptr.loadmore.OnLoadMoreListener;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link  }
 * interface.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
//        if (view instanceof RecyclerView) {
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        Easyadapter    easyada =new Easyadapter( );
        ArrayList<String> lists=new ArrayList<>();
        for(int i=0;i<50;i++){
            lists.add("测试"+i);
        }
         easyada.addDatas(lists);
        recyclerView.setAdapter(easyada);
//        }
        return view;
    }

    LoadMorePtrFrameLayout ptr;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ptr = (LoadMorePtrFrameLayout) view.findViewById(R.id.store_house_ptr_frame);
        PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getContext());
        header.setLastUpdateTimeKey(getClass().getName());
//        final StoreHouseHeader header = new StoreHouseHeader(this);
//        header.setBackgroundColor(Color.BLACK);
        header.setPadding(0, 50, 0, 50);
//        header.initWithString("Ultra PTR");

        ptr.setDurationToCloseHeader(500);
        ptr.setHeaderView(header);
        ptr.addPtrUIHandler(header);
//        ptr.addPtrUIHandler(new PtrClassicDefaultHeader(this));
        ptr.setPtrHandler(new PtrDefaultHandler<LoadMorePtrFrameLayout>() {
            @Override
            public void onRefreshBegin(final LoadMorePtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();

                    }
                }, 3000);
                System.out.println("onRefreshBegin=");
            }
        });
        ptr.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMoreBegin(final LoadMorePtrFrameLayout frame) {
                System.out.println("onLoadMoreBeginonLoadMoreBegin=");
                System.out.println("setOnLoadMoreListener=" +  1);
                frame.showLoading();
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        frame.showNormal();
                        frame.showFail(null);
                    }
                }, 3000);
            }
        });
    }


}
