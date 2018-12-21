package co.pamobile.pacore.MoreApp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import co.pamobile.pacore.Dialog.AppItem;
import co.pamobile.pacore.R;

/**
 * Created by Dev04 on 9/29/2016.
 */

public class MoreAppFragment extends Fragment {
    List<AppItem> lisAllApp = new ArrayList<>();
    public RecyclerView rvMoreApp;
    RecyclerViewMoreAppAdapter adapter;
    Activity activity;
    public void Config(Activity activity,List<AppItem> lisAllApp){
        this.activity = activity;
        this.lisAllApp = lisAllApp;
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    public void setBgImage(int bgImage) {
        this.bgImage = bgImage;
    }



    int bgImage = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more_app, container, false);
        if(bgImage != -1){
            Glide.with(this).load(bgImage).apply(new RequestOptions().centerCrop()).into((ImageView) view.findViewById(R.id.imgBackground));
        }
        rvMoreApp = view.findViewById(R.id.rvMoreApp);
        if(lisAllApp == null){
            lisAllApp = new ArrayList<>();
        }
        getMoreApp();

        return view;
    }

    public void getMoreApp() {
        if(lisAllApp != null){
            List<AppItem> list = new LinkedList<>(lisAllApp);
            for (AppItem item : list){
                if (activity.getPackageName().equals(item.getPackageName()))
                {
                    list.remove(item);
                    break;
                }
            }
           lisAllApp = list;
        }else{
            lisAllApp = new ArrayList<>();
        }
        GridLayoutManager layoutManager;
        boolean isTablet = activity.getResources().getBoolean(R.bool.isTablet);
        if (isTablet) {
            layoutManager = new GridLayoutManager(activity, 6);
        } else {
            layoutManager = new GridLayoutManager(activity, 4);
        }
        adapter = new RecyclerViewMoreAppAdapter(activity, lisAllApp);
        rvMoreApp.setLayoutManager(layoutManager);
        rvMoreApp.setAdapter(adapter);
    }
}
