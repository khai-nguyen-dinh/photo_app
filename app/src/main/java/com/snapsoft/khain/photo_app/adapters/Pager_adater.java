package com.snapsoft.khain.photo_app.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.snapsoft.khain.photo_app.R;
import com.snapsoft.khain.photo_app.Viewpager_activity;
import com.snapsoft.khain.photo_app.objects.Data;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by khain on 09/01/2016.
 */
public class Pager_adater extends PagerAdapter {
    ArrayList<Data> item;
    Viewpager_activity activity;

    public Pager_adater(ArrayList<Data> item, Viewpager_activity activity) {
        this.item = item;
        this.activity = activity;
    }

    @Override

    public int getCount() {
        return item.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(activity).inflate(R.layout.view_pager,null);
        container.addView(view);
        Data i = item.get(position);
        ImageView imv = (ImageView)view.findViewById(R.id.imv2);
        TextView txt1 = (TextView) view.findViewById(R.id.title2);
        TextView txt2 = (TextView)view.findViewById(R.id.download2);
        Button btn = (Button)view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        if(i.getPhotoUrl()==null){
            imv.setImageResource(R.drawable.nen);
        }else {
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(i.getPhotoUrl(),imv);
        }
        txt1.setText(i.getTitle());
        txt2.setText(""+i.getDownloadCount());

        return view;
    }
}