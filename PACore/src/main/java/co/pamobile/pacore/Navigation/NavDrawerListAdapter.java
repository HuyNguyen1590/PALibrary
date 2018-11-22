package co.pamobile.pacore.Navigation;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import co.pamobile.pacore.R;

public class NavDrawerListAdapter extends BaseAdapter {
    private Activity mActivity;
    private ArrayList<NavDrawerItem> navDrawerItems;

    public NavDrawerListAdapter(Activity mActivity, ArrayList<NavDrawerItem> navDrawerItems) {
        this.mActivity = mActivity;
        this.navDrawerItems = navDrawerItems;

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

        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        txtTitle.setText(navDrawerItems.get(position).getTitle());
        //overrideFonts(mActivity, convertView,"fonts/Supercell-magic-webfont.ttf");
        return convertView;
    }

}
