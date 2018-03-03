package app.engine.android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import app.engine.android.AppEngine;
import app.engine.android.R;
import app.engine.android.activity.navigation.NavigationDrawerMenuView;
import app.engine.android.util.NetworkUtilsInterface;
import app.engine.android.util.onscroll.ListViewOnScrollLoadMoreListner;
import app.engine.android.util.onscroll.LoadMoreListView;

import java.util.List;

/**
 * Created by kud-wtag on 1/8/18.
 */

public class BaseUIController extends AppCompatActivity implements View.OnClickListener, NetworkUtilsInterface {
    private String layoutType;

    private DrawerLayout navDrawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar navToolBar;
    private ImageView userImage;
    private ImageView messageBtn;
    public LinearLayout viewContainer;
    private TextView navBarTitle;
    private ImageButton backButton;
    private LinearLayout loadingLayout;
    private NavigationDrawerMenuView navigationDrawerMenuView;
    private LoadMoreListView loadMoreListView;
    private boolean disableKeyBoardHide;
    public ConstraintLayout noInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.disableKeyBoardHide = false;
        super.onCreate(savedInstanceState);

        if(layoutType.equals(AppEngine.getInstance().constants.DRAWER_LAYOUT)) {
            this.drawerLayout();
        } else if(layoutType.equals(AppEngine.getInstance().constants.TOPBAR_LAYOUT)) {
            this.topBarLayout();
        } else if(layoutType.equals(AppEngine.getInstance().constants.EMPTY_LAYOUT)) {
            this.emptyLayout();
        }

        this.loadingLayout = (LinearLayout) this.findViewById(R.id.loadingLayout);
        AppEngine.getInstance().blurEditText.setDisable(false);

    }
    @Override
    public void onClick(View view) {
        if(view.equals(userImage)) {
//            AppEngine.getInstance().androidIntent.startActivity(this, EditProfileMenuActivity.class);
        }
        else if(view.equals(messageBtn)) {
//            AppEngine.getInstance().androidIntent.startActivity(this, MessageBoardActivity.class);
        }
        else if(view.equals(this.backButton)) {
            this.finish();
        }
        if(navDrawerLayout!=null) navDrawerLayout.closeDrawers();
    }
    public void addLayout(int id) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(id, null, false);
        this.viewContainer.addView(layout);
    }

    public void setNavBarTitle(String title) {
        navBarTitle.setText(title);
    }

    private void drawerLayout() {
        setContentView(R.layout.base_layout_drawer);

        viewContainer = (LinearLayout) findViewById(R.id.mainViewContainer);
        noInternet = (ConstraintLayout) findViewById(R.id.noInternet);

        navToolBar = (Toolbar) findViewById(R.id.navbar);
        setSupportActionBar(navToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        navDrawerLayout = (DrawerLayout) findViewById(R.id.navDrawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, navDrawerLayout, navToolBar, 0, 0);
        navDrawerLayout.setScrimColor(ContextCompat.getColor(this, android.R.color.transparent));

        navDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        userImage = (ImageView) findViewById(R.id.userImage);
        userImage.setOnClickListener(this);

        messageBtn = (ImageView) findViewById(R.id.messageBtn);
        messageBtn.setOnClickListener(this);

        navigationDrawerMenuView = new NavigationDrawerMenuView();
        navigationDrawerMenuView.setView(this, this, (LinearLayout) this.findViewById(R.id.menuItemList), navDrawerLayout);

        navBarTitle = (TextView) findViewById(R.id.navBarTitle);

        AppEngine.getInstance().networkUtils.setNetworkUtilsInterface(this);
    }

    private void topBarLayout() {
        setContentView(R.layout.base_layout_topbar);
        viewContainer = (LinearLayout) findViewById(R.id.mainViewContainer);
        noInternet = (ConstraintLayout) findViewById(R.id.noInternet);
        navBarTitle = (TextView) findViewById(R.id.navbarTitle);
        this.backButton = (ImageButton) findViewById(R.id.moduleTopBarBackBtn);
        this.backButton.setOnClickListener(this);
        AppEngine.getInstance().networkUtils.setNetworkUtilsInterface(this);
    }

    private void emptyLayout() {
        setContentView(R.layout.base_layout_empty);
        viewContainer = (LinearLayout) findViewById(R.id.mainViewContainer);
        noInternet = (ConstraintLayout) findViewById(R.id.noInternet);
        AppEngine.getInstance().networkUtils.setNetworkUtilsInterface(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(AppEngine.getInstance().blurEditText.setContext(this).blurEvent(event));
    }

    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }

    public ImageButton getBackButton() {
        return backButton;
    }

    public void showloading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    public void hideloading() {
        loadingLayout.setVisibility(View.INVISIBLE);
    }

    public <T> void setLoadMoreListView(ListView listView, List<T> dataList, ListViewOnScrollLoadMoreListner listViewOnScrollLoadMoreListner) {
        loadMoreListView = new LoadMoreListView(this, listView);
        loadMoreListView.addLoadMoreListener(listView, dataList, listViewOnScrollLoadMoreListner);
        this.resetLoadMore();
    }

    public void resetLoadMore() {
        loadMoreListView.setResetLoadMore(true);
    }

    public boolean isDisableKeyBoardHide() {
        return disableKeyBoardHide;
    }

    public void setDisableKeyBoardHide() {
        AppEngine.getInstance().blurEditText.setDisable(true);
    }

    @Override
    public void networkDisconnect() {
        this.noInternet.setVisibility(View.VISIBLE);
    }

    @Override
    public void networkConnect() {
        this.noInternet.setVisibility(View.INVISIBLE);
    }
}
