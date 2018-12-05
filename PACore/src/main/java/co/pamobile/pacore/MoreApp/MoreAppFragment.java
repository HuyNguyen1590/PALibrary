package co.pamobile.pacore.MoreApp;

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
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import co.pamobile.pacore.Dialog.AppItem;
import co.pamobile.pacore.R;

/**
 * Created by Dev04 on 9/29/2016.
 */

public class MoreAppFragment extends Fragment {
    List<AppItem> lisAllApp = new ArrayList<>();
    public RecyclerView rvMoreApp;

    public List<AppItem> getLisAllApp() {
        return lisAllApp;
    }

    public void setListMoreApp(List<AppItem> lisAllApp) {
        this.lisAllApp = lisAllApp;
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
        lisAllApp = new ArrayList<>();
        getMoreApp();

        return view;
    }

    public void getMoreApp() {
        if(lisAllApp != null){
            ArrayList<AppItem> listApp = new ArrayList<>(lisAllApp);
            Iterator<AppItem> iterator = listApp.iterator();
            while(iterator.hasNext())
            {
                AppItem value = iterator.next();
                if (getContext().getPackageName().equals(value.getPackageName()))
                {
                    iterator.remove();
                    break;
                }
            }

        }else{
            lisAllApp = new ArrayList<>();
        }
        GridLayoutManager layoutManager;
        boolean isTablet = getContext().getResources().getBoolean(R.bool.isTablet);
        if (isTablet) {
            layoutManager = new GridLayoutManager(getActivity(), 6);
        } else {
            layoutManager = new GridLayoutManager(getActivity(), 4);
        }
        RecyclerViewMoreAppAdapter adapter = new RecyclerViewMoreAppAdapter(getActivity(), lisAllApp);
        rvMoreApp.setLayoutManager(layoutManager);
        rvMoreApp.setAdapter(adapter);
    }
}
