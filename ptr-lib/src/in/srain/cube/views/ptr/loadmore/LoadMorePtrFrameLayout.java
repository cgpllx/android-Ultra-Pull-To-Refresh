package in.srain.cube.views.ptr.loadmore;

import android.content.Context;
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
            setFooterView(defaultLoadMoreFooter);
            setLoadMoreHandle(defaultLoadMoreFooter);
        }
    }


    @Override
    public void showNormal() {
        loadMoreHandle.showNormal();
    }

    @Override
    public void loadingCompleted() {
        loadMoreHandle.loadingCompleted();
    }

    @Override
    public void showLoading() {
        loadMoreHandle.showLoading();
    }

    @Override
    public void showFail(Exception e) {
        loadMoreHandle.showFail(e);
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
                onLoadMoreListener.onLoadMoreBegin(LoadMorePtrFrameLayout.this);
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
                onLoadMoreListener.onLoadMoreBegin(loadMorePtrFrameLayout);
            }
        }
    }
}
