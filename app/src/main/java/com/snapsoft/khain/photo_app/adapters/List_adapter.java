package com.snapsoft.khain.photo_app.adapters;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.snapsoft.khain.photo_app.MainActivity;
import com.snapsoft.khain.photo_app.R;
import com.snapsoft.khain.photo_app.objects.Data;
import com.snapsoft.khain.photo_app.utils.commonUtils;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by khain on 09/01/2016.
 */
public class List_adapter extends BaseAdapter {
        ArrayList<Data> data;
        MainActivity activity;


    public List_adapter(ArrayList<Data> data, MainActivity activity) {
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
                view = LayoutInflater.from(activity).inflate(R.layout.row_list,null);
            }

            final ImageView imv1 = (ImageView)view.findViewById(R.id.imv);
            TextView txtcontent = (TextView)view.findViewById(R.id.content);
            TextView txttitle = (TextView)view.findViewById(R.id.title);
            TextView txtdown = (TextView)view.findViewById(R.id.download);
            TextView txtdate = (TextView)view.findViewById(R.id.date);

            if(temp.getPhotoUrl()==null){
                imv1.setImageResource(R.drawable.non);
            }else {
                ImageLoader imageLoader = ImageLoader.getInstance();
                imageLoader.displayImage(temp.getPhotoUrl(),imv1);
            }
            txttitle.setText(temp.getTitle());
            txtcontent.setText("dfggfg");
            txtdown.setText(""+temp.getDownloadCount());
            Date date = new Date(temp.getCreatedTime());
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss, yyy-dd-MM ");
            String dateFormatted = formatter.format(date);

            txtdate.setText(dateFormatted);
            return view;
        }

    }
