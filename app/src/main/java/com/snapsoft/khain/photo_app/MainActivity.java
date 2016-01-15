package com.snapsoft.khain.photo_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.snapsoft.khain.photo_app.adapters.Grid_adapter;
import com.snapsoft.khain.photo_app.adapters.List_adapter;
import com.snapsoft.khain.photo_app.constants.Constants;
import com.snapsoft.khain.photo_app.databases.DatabaseHandler;
import com.snapsoft.khain.photo_app.objects.Data;
import com.snapsoft.khain.photo_app.objects.ObjectData;
import com.snapsoft.khain.photo_app.server.UrlServer;
import com.snapsoft.khain.photo_app.utils.Alert;
import com.snapsoft.khain.photo_app.utils.commonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    GridView gridView;
    public static ArrayList<Data> data;
    RadioGroup group1,group2;
    RelativeLayout layoutGrid,layoutList;
    SharedPreferences preferences;
    DatabaseHandler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

         handler = new DatabaseHandler(this);
        ImageLoader.getInstance().init(config);
        data = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listview);
        gridView = (GridView)findViewById(R.id.gridview);
        group1 = (RadioGroup)findViewById(R.id.group1);
        group2 = (RadioGroup)findViewById(R.id.group2);
        layoutList = (RelativeLayout)findViewById(R.id.layoutList);
        layoutGrid = (RelativeLayout)findViewById(R.id.layoutGrid);
        preferences = getSharedPreferences(Constants.PREFERENCES,MODE_PRIVATE);
        //check_first_use();
        getData();
        setListView();
        setGridView();


        group2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn3:
                        layoutList.setVisibility(View.VISIBLE);
                        layoutGrid.setVisibility(View.GONE);
                        break;
                    case R.id.btn4:
                        layoutList.setVisibility(View.GONE);
                        layoutGrid.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        group1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn1:
                        sortDate();
                        listView.invalidateViews();
                        gridView.invalidateViews();
                        break;
                    case R.id.btn2 :
                       sortDate2();
                        listView.invalidateViews();
                        gridView.invalidateViews();
                        break;
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Viewpager_activity.class);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Viewpager_activity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    public void addSqlite(){
        for(Data d : data){
            handler.insertDB(d);
        }

    }
    public void getData(){
        data = handler.selectDB();
        System.out.println(handler.selectDB());
    }
    public void check_first_use(){
        String id = preferences.getString(Constants.kRegister,null);
        if(id==null){
            first_use();
        }else{
            getListWalls();
        }

    }
    public void sortDate(){
        Collections.sort(data, new Comparator<Data>() {
            @Override
            public int compare(Data lhs, Data rhs) {
                return (int) (lhs.getCreatedTime() - rhs.getCreatedTime());
            }
        });
    }

    public void sortDate2(){
        Collections.sort(data, new Comparator<Data>() {
            @Override
            public int compare(Data lhs,Data rhs) {
                return (int) (rhs.getDownloadCount()-lhs.getDownloadCount());
            }
        });
    }

    public void first_use(){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        JSONObject jsonObject = new JSONObject();
        String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        final ProgressDialog progressDialog = commonUtils.showProgressDialog(MainActivity.this, "dang dang ki!");

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        try {
            jsonObject.put("deviceId", android_id);
            jsonObject.put("deviceType", "android");
            jsonObject.put("screenWidth", metrics.widthPixels);
            jsonObject.put("screenHeight", metrics.heightPixels);
            jsonObject.put("pushToken", "abc");
            jsonObject.put("deviceName", Build.MODEL);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringEntity entity = new StringEntity(jsonObject.toString(),"UTF-8");
        System.out.println(jsonObject.toString());
        httpClient.post(MainActivity.this, UrlServer.url("register-device"), entity, "application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Alert.showAlert(MainActivity.this, "Server dang ban!");
                progressDialog.dismiss();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    JSONObject object = new JSONObject(responseString);
                    if (object.getInt("success") == 1) {
                        String register = object.getString("data");
                        preferences.edit().putString(Constants.kRegister, register).commit();
                        getListWalls();
                    } else {
                        Alert.showAlert(MainActivity.this, "error!");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        });
    }
    public void getListWalls(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ProgressDialog progressDialog = commonUtils.showProgressDialog(MainActivity.this,"dang lay du lieu!");
        RequestParams params = new RequestParams();
        String registerid = preferences.getString(Constants.kRegister,"");
        params.put("registerId", registerid);
        System.out.println(registerid);
        params.put("type", 0);
        client.get(UrlServer.url("list-walls"), params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {
                    JSONObject jsonObject = new JSONObject(responseString);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Log.e(null, jsonObject.toString());
                    for (Integer i = 0; i < jsonArray.length(); i++) {
                        data.add(ObjectData.getData(jsonArray.get(i).toString()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                listView.invalidateViews();
                progressDialog.dismiss();
            }
        });
        addSqlite();
    }
    public void setListView(){
        List_adapter listAdapter = new List_adapter(data,MainActivity.this);
        listView.setAdapter(listAdapter);
    }
    public void setGridView(){
        Grid_adapter gridAdapter = new Grid_adapter(data,MainActivity.this);
        gridView.setAdapter(gridAdapter);

    }
}
