package co.pamobile.pacore.Navigation;

import android.app.Activity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import co.pamobile.pacore.Dialog.AppItem;
import co.pamobile.pacore.MoreApp.MoreAppAdapter;
import co.pamobile.pacore.R;

public class NavDrawerListAdapter extends BaseAdapter {
    protected Activity mActivity;
    protected ArrayList<NavDrawerItem> navDrawerItems;
    protected ArrayList<AppItem> moreAppItems = new ArrayList<>();
    public NavDrawerListAdapter(Activity mActivity, ArrayList<NavDrawerItem> navDrawerItems) {
        this.mActivity = mActivity;
        this.navDrawerItems = navDrawerItems;

    }

    public NavDrawerListAdapter(Activity mActivity, ArrayList<NavDrawerItem> navDrawerItems,ArrayList<AppItem> moreAppItems) {
        this.mActivity = mActivity;
        this.navDrawerItems = navDrawerItems;
        this.moreAppItems = moreAppItems;
    }

    public void setMoreAppItems(ArrayList<AppItem> moreAppItems) {
        this.moreAppItems = moreAppItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    mActivity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }
        ImageView imgIcon =  convertView.findViewById(R.id.icon);
        TextView txtTitle =  convertView.findViewById(R.id.title);
        final ListView lvMoreApps = convertView.findViewById(R.id.moreApp);

        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        txtTitle.setText(navDrawerItems.get(position).getTitle());
        if (navDrawerItems.get(position).getTitle().equals("More Apps")) {
            MoreAppAdapter appAdapter = new MoreAppAdapter(mActivity, moreAppItems);
            lvMoreApps.setAdapter(appAdapter);
            lvMoreApps.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) lvMoreApps.getLayoutParams();
            int heightpx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 43, mActivity.getResources().getDisplayMetrics());
            params.height = (moreAppItems.size() * heightpx);
            lvMoreApps.setLayoutParams(params);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lvMoreApps.setClickable(true);
                    if (lvMoreApps.getVisibility() == View.VISIBLE) {
                        lvMoreApps.setVisibility(View.GONE);
                    } else {
                        lvMoreApps.setVisibility(View.VISIBLE);
                    }
                }
            });


        }
        return convertView;
    }

}
