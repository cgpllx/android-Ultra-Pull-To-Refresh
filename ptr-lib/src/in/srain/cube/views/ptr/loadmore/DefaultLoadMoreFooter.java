/*
Copyright 2015 Chanven

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package in.srain.cube.views.ptr.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import in.srain.cube.views.ptr.R;

/**
 * default load more view
 */
public class DefaultLoadMoreFooter extends FrameLayout implements LoadMoreHandle {


    public DefaultLoadMoreFooter(Context context) {
        super(context);
        initView(context);

    }

    public DefaultLoadMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DefaultLoadMoreFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    TextView footerTv;
    ProgressBar footerBar;

    private void initView(Context context) {
        View footView = LayoutInflater.from(context).inflate(R.layout.loadmore_default_footer, this);
        footerTv = (TextView) footView.findViewById(R.id.loadmore_default_footer_tv);
        footerBar = (ProgressBar) footView.findViewById(R.id.loadmore_default_footer_progressbar);
    }


    OnClickListener onClickRefreshListener;

    public void setOnClickRefreshListener(OnClickListener onClickRefreshListener) {
        this.onClickRefreshListener = onClickRefreshListener;
    }

    @Override
    public void showNormal() {
        footerTv.setText("点击加载更多");
        footerBar.setVisibility(View.GONE);
//        setOnClickListener(onClickRefreshListener);
    }

    @Override
    public void showLoading() {
        footerTv.setText("正在加载中...");
        footerBar.setVisibility(View.VISIBLE);
//        setOnClickListener(null);
    }

    @Override
    public void showFail(Exception exception) {
        footerTv.setText("加载失败，点击重新");
        footerBar.setVisibility(View.GONE);
//        setOnClickListener(onClickRefreshListener);
    }

    @Override
    public void loadingCompleted() {
        footerTv.setText("已经加载完毕");
        footerBar.setVisibility(View.GONE);
//        footerTv.setOnClickListener(null);
    }
}
