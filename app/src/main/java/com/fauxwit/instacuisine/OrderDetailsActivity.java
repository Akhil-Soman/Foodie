package com.fauxwit.instacuisine;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import storage.Database;

public class OrderDetailsActivity extends AppCompatActivity {
    String text="";
    String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        try {
            Intent intent = getIntent();
            String orderID = intent.getStringExtra("ORDER_ID");

            Database db = new Database(OrderDetailsActivity.this);
            Cursor c = db.getOrder(orderID);

            if (c.getCount()>0){
                c.moveToFirst();
                username=c.getString(c.getColumnIndex(Database.CL_USERNAME));
                String itemName=c.getString(c.getColumnIndex(Database.CL_ITEM_NAME));
                String itemQty=c.getString(c.getColumnIndex(Database.CL_ITEM_QTY));
                String descriptions=c.getString(c.getColumnIndex(Database.CL_DESCRIPTION));
                String combos=c.getString(c.getColumnIndex(Database.CL_COMBO));
                String comboQty=c.getString(c.getColumnIndex(Database.CL_COMBO_QTY));
                if (itemName.contains(",")){
                    String[] ar=itemName.split(",");
                    text="";
                    int size=ar.length;
                    System.out.println("FOOD :::: ITEM AR SIZE "+size);
                    for (int i=0;i<size;i++){

                        text=text+ar[i]+"      x"+itemQty.split(",")[i];
                        text=text+"\n"+descriptions.split(",")[i];
                        text=text+"\nCombo : "+combos.split(",")[i]+"      x"+comboQty.split(",")[i];

                        if (i!=size-1){
                            text=text+"\n\n";
                        }
                    }
                }
            }




        }catch (Exception e){
            e.printStackTrace();
        }

        TextView txt_orderDetails=(TextView)findViewById(R.id.txt_orderDetails);
        TextView txt_username=(TextView)findViewById(R.id.txt_username);
        Button btn_accept= (Button) findViewById(R.id.btn_accept);

        txt_username.setText(username);
        txt_orderDetails.setText(text);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderDetailsActivity.this, "Accepted", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
