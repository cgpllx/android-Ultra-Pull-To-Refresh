package in.srain.cube.views.ptr.loadmore;

import android.view.View;

/**
 * Created by cgpllx on 2016/6/15.
 */
public interface ILoadMoreView {
    void addFooterView(View view);

    void setOnScrollBottomListener(OnScrollBottomListener onScrollBottomListener);

}
