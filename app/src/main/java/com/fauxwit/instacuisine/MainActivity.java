package com.fauxwit.instacuisine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }



}
