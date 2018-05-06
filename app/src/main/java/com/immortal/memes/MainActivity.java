package com.immortal.memes;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import com.immortal.memes.server.Mem;
import com.immortal.memes.server.ServerAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener{
    private SuperRecyclerView main_RecyclerView;
    private MemesAdapter mAdapter;
    ServerAPI serverAPI;
    ArrayList<Mem> memes_list = new ArrayList<Mem>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MemesAdapter(memes_list);

        main_RecyclerView = (SuperRecyclerView) findViewById(R.id.main_RecyclerView);
        main_RecyclerView.setLayoutManager(getLayoutManager());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverAPI = retrofit.create(ServerAPI.class);

//        main_RecyclerView.setAdapter(mAdapter);

        Call<ArrayList<Mem>> memes = serverAPI.getMemes(0);

        memes.enqueue(new Callback<ArrayList<Mem>>() {
            @Override
            public void onResponse(Call<ArrayList<Mem>> call, Response<ArrayList<Mem>> response) {
                memes_list = response.body();

//                mAdapter.addAll(response.body());
                for(int i = 0; i < 10; i++)
                    Log.d("MyLog", "response " + response.body().get(i).getId());
            }

            @Override
            public void onFailure(Call<ArrayList<Mem>> call, Throwable t) {
                Log.d("MyLog", "Failure calling API: " + t);
            }
        });

//        main_RecyclerView.setAdapter(mAdapter);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        main_RecyclerView.setAdapter(mAdapter);
                    }
                });

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            mAdapter.addAll(memes_list);
                    }
                });
            }
        });

        thread.start();
        main_RecyclerView.setRefreshListener(this);
        main_RecyclerView.setupMoreListener(this,1);
    }

    protected RecyclerView.LayoutManager getLayoutManager(){
        return new LinearLayoutManager(this);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "Refresh", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        Toast.makeText(this, "More", Toast.LENGTH_LONG).show();
        Call<ArrayList<Mem>> memes = serverAPI.getMemes(maxLastVisiblePosition+1);

        memes.enqueue(new Callback<ArrayList<Mem>>() {
            @Override
            public void onResponse(Call<ArrayList<Mem>> call, Response<ArrayList<Mem>> response) {
                mAdapter.addAll(response.body());
                Log.d("MyLog", "response " + response.body().size());
            }

            @Override
            public void onFailure(Call<ArrayList<Mem>> call, Throwable t) {
                Log.d("MyLog", "Failure calling API: " + t);
            }
        });
    }
}
