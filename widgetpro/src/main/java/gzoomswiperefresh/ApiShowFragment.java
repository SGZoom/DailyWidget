package gzoomswiperefresh;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.widgetpro.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by gzoom on 2016/11/27.
 * 演示用fragment
 */

public class ApiShowFragment extends Fragment {

    @Bind(R.id.tempfrag_swiperefresh)
    GZoomSwifrefresh refreshLayout;

    @Bind(R.id.main_recycleview)
    RecyclerView recyclerView;
    /**context*/
    Context context;

    SwipeRefreshLayout s;

    /**标示个数*/
    private int index=0;

    /**数据源*/
    List<String>datas = new ArrayList<>();


    /**测试用*/
    TempAdapter tempAdapter;

    Handler hd = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            addData();
            cancleLoading();

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this.getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.temp_fag,container,false);
        ButterKnife.bind(this,view);
        initRefreshLayout();
        //初始化recycleview
        initRecycleView();
        return view;
    }



    /**初始化刷新控件*/
    private void initRefreshLayout() {
        refreshLayout.setColorSchemeColors(Color.YELLOW,Color.RED,Color.GREEN,Color.BLUE);
        refreshLayout.setOnRefreshListener(new GZoomSwifrefresh.OnRefreshListener() {
            @Override
            public void onRefresh() {
                hd.sendEmptyMessageDelayed(0,3000);
            }
        });

        refreshLayout.setBottomColorSchemeColors(Color.GREEN,Color.BLUE,Color.YELLOW,Color.RED);
        //在这里新增下拉刷新方法
        refreshLayout.setOnBottomRefreshListenrer(new GZoomSwifrefresh.OnBottomRefreshListener() {
            @Override
            public void onBottomRefresh() {
                Snackbar.make(recyclerView,"下拉刷新了",Snackbar.LENGTH_SHORT).show();
                hd.sendEmptyMessageDelayed(0,3000);
            }
        });
    }


    /**取消加载*/
    public void cancleLoading()
    {
        if(refreshLayout.isRefreshing())
            refreshLayout.setRefreshing(false);
        refreshLayout.setBottomRefreshing(false);
    }

    /**初始化recycleview*/
    private void initRecycleView() {
       // List<String>tempS = new ArrayList<>();
        for(int i = 0; i<10 ;i++)
        {
            datas.add(index+":GZ");
            index++;
        }
        tempAdapter = new TempAdapter(context,datas);
        recyclerView.setAdapter(tempAdapter);
        LinearLayoutManager man = new LinearLayoutManager(context);
        man.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(man);

    }
    /**新增数据*/
    private void addData()
    {
        for(int i = 0; i<5 ;i++)
        {
            datas.add(index+":GZ");
            index++;
        }
        tempAdapter.notifyDataSetChanged();
    }

}
