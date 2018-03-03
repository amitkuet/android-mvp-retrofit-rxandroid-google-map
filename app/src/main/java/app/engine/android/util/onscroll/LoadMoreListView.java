package app.engine.android.util.onscroll;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.List;

import app.engine.android.R;

public class LoadMoreListView {
    private Boolean resetLoadMore;
    private View layout;

    public LoadMoreListView(Activity activity, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.list_view_load_more, parent, false);
        this.resetLoadMore = true;
    }
    public <T> void addLoadMoreListener(final ListView listView, List<T> listData, final ListViewOnScrollLoadMoreListner listViewOnScrollLoadMoreListner) {
        listView.addFooterView(layout);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleitem, int visibleItemCount, int totalItemCount) {
                if(view.getLastVisiblePosition() == totalItemCount-1 && resetLoadMore) {
                    resetLoadMore = false;
                    layout.setVisibility(View.VISIBLE);
                    listViewOnScrollLoadMoreListner.onScrollLoadMore();
                }
            }
        });
    }

    public Boolean getResetLoadMore() {
        return resetLoadMore;
    }

    public void setResetLoadMore(Boolean resetLoadMore) {
        this.resetLoadMore = resetLoadMore;
        this.layout.setVisibility(View.INVISIBLE);
    }

    public View getLayout() {
        return layout;
    }

    public void setLayout(View layout) {
        this.layout = layout;
    }
}
