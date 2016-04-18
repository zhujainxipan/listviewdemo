package com.example.niehongtao.listviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by niehongtao on 16/4/18.
 */
public class AutoLoadMoreListView extends ListView {
    // 这个布尔变量是必须使用的，因为不然会访问多次网络，这个可以控制，防止访问多次网络
    private boolean mIsLoading = false;
    private OnLoadMoreListener mOnLoadMoreListener;
    private View mHeadViewContainer;

    public AutoLoadMoreListView(Context context) {
        super(context);
        init(context);
    }

    public AutoLoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View headerView = View.inflate(context, R.layout.autoloadmorelistview_header, null);
        super.addHeaderView(headerView);
        mHeadViewContainer = headerView.findViewById(R.id.ll_header_container);
        hideHeadView();
        super.setOnScrollListener(mScrollListener);
    }

    private void hideHeadView() {
        mIsLoading = false;
        mHeadViewContainer.setVisibility(View.GONE);
    }

    private void showHeadView() {
        mIsLoading = true;
        mHeadViewContainer.setVisibility(View.VISIBLE);
    }

    /**
     * 加载完数据，供外界调用
     */
    public void onLoadMoreComplete() {
        mIsLoading = false;
        hideHeadView();
    }


    private OnScrollListener mScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (firstVisibleItem == 0 && !mIsLoading && mOnLoadMoreListener != null) {
                showHeadView();
                mOnLoadMoreListener.onLoadMore();
            }
        }
    };


    /**
     * 加载更多数据的接口
     */
    interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

}
