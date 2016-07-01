package in.srain.cube.views.ptr.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;


public class ListViewHandler extends ListView implements ILoadMoreView {
    public ListViewHandler(Context context) {
        super(context);
    }

    public ListViewHandler(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewHandler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 滚动到最底部后调用  onScrollBottomListener.onScorllBootom() 方法
     *
     * @param onScrollBottomListener
     */
    @Override
    public void setOnScrollBottomListener(OnScrollBottomListener onScrollBottomListener) {

        setOnScrollListener(new ListViewOnScrollListener(onScrollBottomListener));
    }

    /**
     * 滚动到底部自动加载更多数据
     */
    private static class ListViewOnScrollListener implements OnScrollListener {
        private OnScrollBottomListener onScrollBottomListener;

        public ListViewOnScrollListener(OnScrollBottomListener onScrollBottomListener) {
            super();
            this.onScrollBottomListener = onScrollBottomListener;
        }

        @Override
        public void onScrollStateChanged(AbsListView listView, int scrollState) {
            if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && listView.getLastVisiblePosition() + 1 == listView.getCount()) {// 如果滚动到最后一行
                if (onScrollBottomListener != null) {
                    onScrollBottomListener.onScorllBootom();
                }
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    }
}
