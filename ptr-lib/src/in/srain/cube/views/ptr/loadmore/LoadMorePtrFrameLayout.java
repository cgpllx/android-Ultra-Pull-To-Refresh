package in.srain.cube.views.ptr.loadmore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by cgpllx on 2016/6/15.
 */
public class LoadMorePtrFrameLayout extends PtrFrameLayout implements LoadMoreHandle {
    public LoadMorePtrFrameLayout(Context context) {
        super(context);
    }

    public LoadMorePtrFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadMorePtrFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onContentViewFinishInflate(View contentView) {
        super.onContentViewFinishInflate(contentView);
        if (contentView instanceof ILoadMoreView) {
            loadMoreView = (ILoadMoreView) contentView;
            DefaultLoadMoreFooter defaultLoadMoreFooter = new DefaultLoadMoreFooter(getContext());
            defaultLoadMoreFooter.setLayoutParams(new RecyclerView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
            setFooterView(defaultLoadMoreFooter);
            setLoadMoreHandle(defaultLoadMoreFooter);
        }
    }


    @Override
    public void showNormal() {
        loadMoreHandle.showNormal();
        loading=false;
    }

    private boolean loading = false;//加载中
    private boolean noMore=false;// 已经没有更多了

    private boolean isLoading() {
        return loading;
    }

    @Override
    public void loadingCompleted() {
        loadMoreHandle.loadingCompleted();
        loading=false;
    }

    @Override
    public void showLoading() {
        loading=true;
        loadMoreHandle.showLoading();
    }

    @Override
    public void showFail(Exception e) {
        loadMoreHandle.showFail(e);
        loading=false;
    }


    public void setLoadMoreHandle(LoadMoreHandle loadMoreHandle) {
        this.loadMoreHandle = loadMoreHandle;

    }

    View footerView;
    LoadMoreHandle loadMoreHandle;//控制显示footerView 的各种状态
    ILoadMoreView loadMoreView;//content要实现他才能添加loadmore

    public void setFooterView(View footer) {
        if (loadMoreView != null) {
            footerView = footer;
            loadMoreView.addFooterView(footer);
        }
    }

    public void setOnLoadMoreListener(final OnLoadMoreListener onLoadMoreListener) {
        loadMoreView.setOnScrollBottomListener(new LoadMoreOnScrollBottomListener(this, onLoadMoreListener));
        footerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLoading()){
                    onLoadMoreListener.onLoadMoreBegin(LoadMorePtrFrameLayout.this);
                }
            }
        });
    }

    OnClickListener footViewClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            showLoading();//LoadMorePtrFrameLayout 的方法
        }
    };

    private class LoadMoreOnScrollBottomListener implements OnScrollBottomListener {
        OnLoadMoreListener onLoadMoreListener;
        LoadMorePtrFrameLayout loadMorePtrFrameLayout;

        public LoadMoreOnScrollBottomListener(LoadMorePtrFrameLayout loadMorePtrFrameLayout, OnLoadMoreListener onLoadMoreListener) {
            this.onLoadMoreListener = onLoadMoreListener;
            this.loadMorePtrFrameLayout = loadMorePtrFrameLayout;
        }

        @Override
        public void onScorllBootom() {//回调给用户
            if (onLoadMoreListener != null) {
                if(!loadMorePtrFrameLayout.isLoading()){
                    onLoadMoreListener.onLoadMoreBegin(loadMorePtrFrameLayout);
                }

            }
        }
    }
}
