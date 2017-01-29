package com.fauxwit.instacuisine;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import storage.Database;
import ws.WebServices;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        TextView txt_appName = (TextView)findViewById(R.id.txt_appName);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/lobster.otf");

        txt_appName.setTypeface(custom_font);

        final EditText edt_username= (EditText) findViewById(R.id.edt_username);
        final EditText edt_password= (EditText) findViewById(R.id.edt_password);
        Button btn_login=(Button)findViewById(R.id.btn_login);

        new CallWebService().execute("nasar","123");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String username=edt_username.getText().toString();
                String password=edt_password.getText().toString();

                if (username.equals("")||password.equals("")) {
                    //SHOW DIALOG
                }else{
                    new CallWebService().execute(username,password);
                }*/
                startActivity(new Intent(LoginPage.this,MainActivity.class));
            }
        });
        Database db=new Database(LoginPage.this);
        System.out.println("FOOD :::: DB COUNT :::: "+db.getOrderCount());
        if (db.getOrderCount()==0){
            /*db.insertOrder("123","01-01-2017","Special Requirement","PIZZA","IMAGE_URL","7","NO","2","0");
            db.insertOrder("123","01-01-2017","Special Requirement","Chicken Biriyani","IMAGE_URL","8","NO","4","0");
            db.insertOrder("124","01-01-2017","Special Requirement","PIZZA","IMAGE_URL","7","NO","2","0");
            db.insertOrder("125","01-01-2017","Special Requirement","PIZZA","IMAGE_URL","7","NO","2","0");
            db.insertOrder("126","01-01-2017","Special Requirement","PIZZA","IMAGE_URL","7","NO","2","0");
            db.insertOrder("127","01-01-2017","Special Requirement","PIZZA","IMAGE_URL","7","NO","2","0");*/
        }
    }


    class CallWebService extends AsyncTask<String,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            //WebServices.login(params[0],params[1]);
            //WebServices.getOrderDetails("7");
            //HashMap<String,String> hm=new HashMap<>();
            //hm.put("registration_id","d2RCxMOg4Ik:APA91bHov55a3oSSorDJrfWckSN0uRacMwpgSmpIN0YUCGM6EhOs9vt9EEq_uCL6tUvALPDS_73m6lVuqHTcRmkwcGQlJ7nmxZqrBeoqquEl__NqD50xSfmnxiqCtS1KpZz-dDLEc0vX");
            //WebServices.makeHttpRequest("http://insta.pywarriors.com/restaurant/send_fcm/","POST",hm);
            //WebServices.sendFCM("d2RCxMOg4Ik:APA91bHov55a3oSSorDJrfWckSN0uRacMwpgSmpIN0YUCGM6EhOs9vt9EEq_uCL6tUvALPDS_73m6lVuqHTcRmkwcGQlJ7nmxZqrBeoqquEl__NqD50xSfmnxiqCtS1KpZz-dDLEc0vX");

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
