package co.pamobile.pacore;

import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.pamobile.pacore.Navigation.NavDrawerItem;
import co.pamobile.pacore.Navigation.NavDrawerListAdapter;

/**
 * Created by admin on 4/3/18.
 */

public abstract class BaseLeftMenuActivity extends AppCompatActivity {

    @BindView(R2.id.drawer_layout)
    protected
    DrawerLayout drawer;

    @BindView(R2.id.toolbar)
    protected
    Toolbar toolbar;

    @BindView(R2.id.nav_view)
    protected
    NavigationView navigationView;

    @BindView(R2.id.list_slidermenu)
    protected
    ListView mDrawerList;

    protected ActionBarDrawerToggle toggle;

    protected abstract int setViewLayoutId();


    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    protected ArrayList<NavDrawerItem> navDrawerItems;
    protected NavDrawerListAdapter navDrawerListAdapter;

    public void setOnNavItemClick(ListView.OnItemClickListener onNavItemClick) {
        this.onNavItemClick = onNavItemClick;
    }

    ListView.OnItemClickListener onNavItemClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setViewLayoutId());
        ButterKnife.bind(this);
        settingUI();
    }

    protected void settingUI() {
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, 0, 0);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });


    }

    protected void loadNavigation(int title_array_id,int icons_array_id) {
        navMenuTitles = getResources().getStringArray(title_array_id);
        navMenuIcons = getResources().obtainTypedArray(icons_array_id);
        navDrawerItems = new ArrayList<NavDrawerItem>();
        // adding nav drawer totalItems to array
        addNavigationItem();
        // Recycle the typed array
        navMenuIcons.recycle();
        mDrawerList.setOnItemClickListener(onNavItemClick);
        navDrawerListAdapter = new NavDrawerListAdapter(this, navDrawerItems);
        mDrawerList.setAdapter(navDrawerListAdapter);
    }

    protected void addNavigationItem() {
        for (int i = 0; i < navMenuTitles.length; i++) {
            navDrawerItems.add(new NavDrawerItem(navMenuTitles[i], navMenuIcons.getResourceId(i, -1)));
        }
    }
}

