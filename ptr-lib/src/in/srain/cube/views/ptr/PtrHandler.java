package in.srain.cube.views.ptr;

import android.view.View;

import in.srain.cube.views.ptr.loadmore.LoadMorePtrFrameLayout;

public interface PtrHandler<T extends PtrFrameLayout> {

    /**
     * Check can do refresh or not. For example the content is empty or the first child is in view.
     * <p/>
     * {@link in.srain.cube.views.ptr.PtrDefaultHandler#checkContentCanBePulledDown}
     */
    boolean checkCanDoRefresh(final T frame, final View content, final View header);

    /**
     * When refresh begin
     *
     * @param frame
     */
    void onRefreshBegin(final T frame);

}