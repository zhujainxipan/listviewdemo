package com.example.niehongtao.listviewdemo;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private AutoLoadMoreListView listview;
    private MyAdapter adapter;
    private List<String>  contents;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        listview = (AutoLoadMoreListView) findViewById(R.id.listview);
        adapter = new MyAdapter(contents, this);
        listview.setAdapter(adapter);
        listview.setOnLoadMoreListener(new AutoLoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        load();
                        adapter.notifyDataSetChanged();
                        listview.setAdapter(adapter);
                        listview.setSelection(20);
                        listview.onLoadMoreComplete();
                    }
                }, 1000);
            }
        });
    }


    protected void load() {
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < 20; i++) {  //每次加载20条数据
            list.add("加载数据:::" + i);
        }
        contents.addAll(0, list);
    }
    private void initData() {
        contents = new ArrayList<String>();
        for(int i = 0; i < 20; i++){
            contents.add("加载数据:::" + i);
        }
    }

}