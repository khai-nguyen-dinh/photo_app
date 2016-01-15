package com.snapsoft.khain.photo_app.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.snapsoft.khain.photo_app.MainActivity;
import com.snapsoft.khain.photo_app.R;
import com.snapsoft.khain.photo_app.objects.Data;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by khain on 09/01/2016.
 */
public class Grid_adapter extends BaseAdapter{
    ArrayList<Data> data;
    MainActivity activity;

    public Grid_adapter(ArrayList<Data> data, MainActivity activity) {
        this.data = data;
        this.activity = activity;
    }

    @Override

    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Data temp = data.get(position);
        View view = convertView;
        if(view==null){
            view = LayoutInflater.from(activity).inflate(R.layout.row_grid,null);
        }

        ImageView imv1 = (ImageView)view.findViewById(R.id.imv1);
        TextView txtcontent = (TextView)view.findViewById(R.id.download1);
        if(temp.getPhotoUrl().isEmpty()){
            imv1.setImageResource(R.drawable.non);
        }else {
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.displayImage(temp.getPhotoUrl(),imv1);
        }
        txtcontent.setText(""+temp.getDownloadCount());
        return view;
    }
}
