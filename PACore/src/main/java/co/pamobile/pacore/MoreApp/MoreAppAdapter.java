package co.pamobile.pacore.MoreApp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import co.pamobile.pacore.Dialog.AppItem;
import co.pamobile.pacore.R;

public class MoreAppAdapter extends BaseAdapter {
    protected Context context;
    protected List<AppItem> MenuItems;
    protected LayoutInflater inflater;
    public MoreAppAdapter(Context mainActivity, List<AppItem> MenuItems) {
        // TODO Auto-generated constructor stub
        this.MenuItems = MenuItems;
        context=mainActivity;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return MenuItems.size();
    }

    @Override
    public AppItem getItem(int position) {

        // TODO Auto-generated method stub
        return MenuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View rowView;
        final AppItem mItem = getItem(position);
        rowView = inflater.inflate(R.layout.item_more_app, null);
        ImageView img= rowView.findViewById(R.id.img_icon);
        TextView tv = rowView.findViewById(R.id.txt_name);
        tv.setText(mItem.getName());
        Glide.with(context)
                .load(mItem.getIcon()).apply(new RequestOptions().fitCenter())
                .into(img);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + mItem.getPackageName())));
            }
        });
        return rowView;
    }

}
