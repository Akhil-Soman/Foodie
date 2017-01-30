package com.fauxwit.instacuisine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.OrderAdapter;
import models.OrderModel;
import storage.Database;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    RecyclerView rcv_orders;
    OrderAdapter adapter;
    ArrayList<OrderModel> dummy=new ArrayList<>();
    ArrayList<OrderModel> arl_orders=new ArrayList<>();
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.this.registerReceiver(this.refreshListReceiver,new IntentFilter("refreshListReceiver"));

        rcv_orders= (RecyclerView) findViewById(R.id.rv_orders);
        db=new Database(MainActivity.this);
        arl_orders=db.getAllOrrders();

        adapter= new OrderAdapter(arl_orders);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcv_orders.setLayoutManager(mLayoutManager);
        rcv_orders.setItemAnimator(new DefaultItemAnimator());
        rcv_orders.setAdapter(adapter);


        rcv_orders.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rcv_orders, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(MainActivity.this,OrderDetailsActivity.class);
                intent.putExtra("ORDER_ID",arl_orders.get(position).getOrderID());
                startActivity(intent);

                //refreshRecyclerView();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //refreshRecyclerView();

    }


    public void refreshRecyclerView(){
        try {
            db = new Database(MainActivity.this);
            arl_orders.clear();
            arl_orders.addAll(db.getAllOrrders());
            //adapter.notifyDataSetChanged();
            rcv_orders.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainActivity.this.unregisterReceiver(refreshListReceiver);
    }

    BroadcastReceiver refreshListReceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            refreshRecyclerView();
        }
    };


}
