package com.example.y.mvp.activity;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.y.mvp.R;
import com.example.y.mvp.fragment.AboutFragment;
import com.example.y.mvp.fragment.ImageNewFragment;
import com.example.y.mvp.fragment.MainViewPagerFragment;
import com.example.y.mvp.mvp.presenter.MainViewPresenter;
import com.example.y.mvp.mvp.presenter.MainViewPresenterImpl;
import com.example.y.mvp.mvp.view.MainView;
import com.example.y.mvp.utils.UIUtils;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainView {

    @Bind(R.id.toolBar)
    Toolbar toolBar;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Bind(R.id.dl_layout)
    DrawerLayout drawerLayout;
    private MainViewPresenter mainViewPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    private void init() {
        setSupportActionBar(toolBar);
        setupDrawerContent(navigationView);
        mainViewPresenter = new MainViewPresenterImpl(this);
        switchImageClassification();
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mainViewPresenter.switchId(menuItem.getItemId());
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void switchImageClassification() {
        toolBar.setTitle(UIUtils.getString(R.string.toolbar_image_viewpager));
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new MainViewPagerFragment()).commit();
    }

    @Override
    public void switchNewImage() {
        toolBar.setTitle(UIUtils.getString(R.string.toolbar_image_image_new));
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new ImageNewFragment()).commit();
    }

    @Override
    public void switchAbout() {
        toolBar.setTitle(UIUtils.getString(R.string.toolbar_about));
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new AboutFragment()).commit();
    }
}
