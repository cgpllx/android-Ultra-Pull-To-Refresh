package in.srain.cube.views.ptr.loadmore;

/**
 * Created by cgpllx on 2016/6/15.
 */
public interface LoadMoreHandle {
    /**
     * 显示普通布局
     */
    void showNormal();

    /**
     * 显示已经加载完成，没有更多数据的布局
     */
    void loadingCompleted();

    /**
     * 显示正在加载中的布局
     */
    void showLoading();

    /**
     * 显示加载失败的布局
     *
     * @param e error
     */
    void showFail(Exception e);
}
