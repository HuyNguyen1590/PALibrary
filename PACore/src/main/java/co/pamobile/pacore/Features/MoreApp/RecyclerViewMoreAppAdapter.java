package co.pamobile.pacore.Features.MoreApp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import co.pamobile.pacore.Entities.AppItem;
import co.pamobile.pacore.R;
import co.pamobile.pacore.Utilities.Constant;
import co.pamobile.pacore.Utilities.Convert;
import co.pamobile.pacore.Utilities.Utils;


/**
 * Created by Dev04 on 10/21/2016.
 */
public class RecyclerViewMoreAppAdapter extends RecyclerView.Adapter<RecyclerViewMoreAppAdapter.MyViewHolder> {
    private Activity mActivity;
    private List<AppItem> data = new ArrayList<>();


    public RecyclerViewMoreAppAdapter(Activity mActivity, List<AppItem> data) {
        this.mActivity = mActivity;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi = View.inflate(mActivity, R.layout.item_app, null);
        MyViewHolder holder = new MyViewHolder(vi);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final AppItem app = getItem(position);
        if (app != null) {

            holder.txtName.setText(app.getName());
            Glide
                    .with(mActivity)
                    .load(app.getIcon())
                    .apply(new RequestOptions())
                    .into(holder.imgIconApp);
        }
        boolean isTablet = mActivity.getResources().getBoolean(R.bool.isTablet);

        if (isTablet) {
            LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) holder.imgIconApp.getLayoutParams();
            params1.width = (int) Convert.convertDpToPixel(60);
            params1.height = (int) Convert.convertDpToPixel(60);
            holder.imgIconApp.setLayoutParams(params1);
            holder.txtName.setTextSize(16);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri;
                if (app.getPackageName().startsWith("http")) {
                    if (app.getPackageName().contains("facebook.com")) {
                        Utils.openFacebookURL(mActivity, app.getPackageName());
                    } else if (app.getPackageName().contains("youtube.com")) {
                        Utils.openYouTubeURL(mActivity, app.getPackageName());
                    } else {
                        try {
                            uri = Uri.parse(app.getPackageName()); //web URL
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            mActivity.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Intent intent = mActivity.getPackageManager().getLaunchIntentForPackage(app.getPackageName()); //launch intent

                    if (Constant.isAmazonDevice) {
                        try {
                            if (intent == null) { //try open amazon market if not installed
                                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.AMAZON_MARKET_URL + app.getPackageName()));
                            }
                            mActivity.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();

                            //open web url if fail
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.AMAZON_WEB_URL + app.getPackageName()));
                            mActivity.startActivity(intent);
                        }
                    } else {
                        try {
                            if (intent == null) { //try open android market if not installed
                                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.ANDROID_MARKET_URL + app.getPackageName()));
                            }
                            mActivity.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();

                            //open web url if fail
                            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.ANDROID_WEB_URL + app.getPackageName()));
                            mActivity.startActivity(intent);
                        }
                    }
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public AppItem getItem(int position) {
        return data.get(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageView imgIconApp;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            imgIconApp = (ImageView) itemView.findViewById(R.id.imgIconApp);
        }
    }

    public List<AppItem> getData() {
        return data;
    }

    public void setData(List<AppItem> data) {
        this.data = data;
    }
}
