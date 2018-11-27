package co.pamobile.pacore.Dialog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import co.pamobile.pacore.R;

/**
 * Created by Dev04 on 10/21/2016.
 */
public class FeatureAppAdapter extends RecyclerView.Adapter<FeatureAppAdapter.MyViewHolder>{
    private Context mContext;
    private List<AppItem> data = new ArrayList<>();
    final String URL_GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=";
    public FeatureAppAdapter(Context context, List<AppItem> data) {
        this.mContext = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi = View.inflate(mContext, R.layout.layout_item_app, null);
        MyViewHolder holder = new MyViewHolder(vi);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final AppItem app = getItem(position);
        if (app != null) {
            holder.txtName.setText(app.getName());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.ic_app_default)
                    .error(R.drawable.ic_app_default)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .dontAnimate()
                    .dontTransform();

            Glide.with(mContext)
                    .load(app.getIcon())
                    .apply(options)
                    .into(holder.imgIconApp);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL_GOOGLE_PLAY + app.getPackageName()));
                mContext.startActivity(intent);
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
