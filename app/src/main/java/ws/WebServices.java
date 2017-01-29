package ws;

import android.icu.util.Output;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by user on 20/1/17.
 */

public class WebServices {


    private static final String baseUrl="http://insta.pywarriors.com";


    public static void sendFCM(String regToken){
        String str="http://insta.pywarriors.com/restaurant/send_fcm/";
        try{
            URL url=new URL(str);

            JSONObject dataParams=new JSONObject();
            dataParams.put("registration_id",regToken);

            System.out.println("FOOD :::: JSON LOGIN ::: "+dataParams.toString());

            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream outputStream=conn.getOutputStream();
            outputStream.write(dataParams.toString().getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
            /*BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            writer.write(dataParams.toString().getBytes("UTF-8"));
            writer.flush();
            writer.close();
            outputStream.close();*/

            int responseCode=conn.getResponseCode();
            if (responseCode==HttpURLConnection.HTTP_OK){
                BufferedReader in=new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";
                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }
                in.close();
                System.out.println("FOOD :::: JSON FCM RESPONSE :::: "+sb.toString());
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static JSONObject login(String username,String password){
        try {
            String methodUrl = "/general/api/v1/login/";
            //String methodUrl="/Customer_api/order_item/?orderID=7";
            URL url=new URL(baseUrl+methodUrl);

            JSONObject dataParams=new JSONObject();
            dataParams.put("Username",username);
            dataParams.put("Password",password);
            System.out.println("FOOD :::: JSON LOGIN ::: "+dataParams.toString());

            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream outputStream=conn.getOutputStream();
            outputStream.write(dataParams.toString().getBytes("UTF-8"));
            outputStream.flush();
            /*BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            writer.write(getPostDataString(dataParams));
            writer.flush();
            writer.close();*/
            outputStream.close();

            int responseCode=conn.getResponseCode();
            if (responseCode==HttpURLConnection.HTTP_OK){
                BufferedReader in=new BufferedReader(
                        new InputStreamReader(
                                conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";
                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }
                in.close();
                System.out.println("FOOD :::: JSON LOGIN RESPONSE :::: "+sb.toString());
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static JSONArray getOrderDetails(String orderID){

        try {
            String methodUrl = "/Customer_api/order_item/?orderID=" + orderID;
            //String methodUrl="/Customer_api/order_item/?orderID=7";
            URL url = new URL(baseUrl + methodUrl);

            URLConnection conn = url.openConnection();
            //conn.setDoOutput(true);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "");
            }

            // Append Server Response To Content String
            String Content = sb.toString();
            System.out.println("FOOD :::: ORDER DETAILS :::: "+Content);
            JSONArray rJson=new JSONArray(Content);
            return rJson;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    public static void sendDevToken(String token){

    }








    public static String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }





    //
    //
    //
    //



    /*public static JSONObject makeHttpRequest(String url, String method,
                                      HashMap<String, String> params) {
        String charset = "UTF-8";
        HttpURLConnection conn;
        DataOutputStream wr;
        StringBuilder result=null;
        URL urlObj;
        JSONObject jObj = null;
        StringBuilder sbParams;
        String paramsString;

        sbParams = new StringBuilder();
        int i = 0;
        for (String key : params.keySet()) {
            try {
                if (i != 0){
                    sbParams.append("&");
                }
                sbParams.append(key).append("=")
                        .append(URLEncoder.encode(params.get(key), charset));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }

        if (method.equals("POST")) {
            // request method is POST
            try {
                urlObj = new URL(url);

                conn = (HttpURLConnection) urlObj.openConnection();

                conn.setDoOutput(true);

                conn.setRequestMethod("POST");

                conn.setRequestProperty("Content-Type", "application/json");

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);

                conn.connect();

                paramsString = sbParams.toString();

                wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(paramsString);
                wr.flush();
                wr.close();


                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                Log.d("JSON Parser", "result: " + result.toString());
                System.out.println("FOOD :::: HTTP POST:::: "+result.toString());
                conn.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("GET")){
            // request method is GET

            if (sbParams.length() != 0) {
                url += "?" + sbParams.toString();
            }

            try {
                urlObj = new URL(url);

                conn = (HttpURLConnection) urlObj.openConnection();

                conn.setDoOutput(false);

                conn.setRequestMethod("GET");

                conn.setRequestProperty("Accept-Charset", charset);

                conn.setConnectTimeout(15000);

                conn.connect();

                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                Log.d("JSON Parser", "result: " + result.toString());
                System.out.println("FOOD :::: HTTP GET:::: "+result.toString());
                conn.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        *//*try {
            //Receive the response from the server


        } catch (IOException e) {
            e.printStackTrace();
        }
*//*


        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(result.toString());
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON Object
        return jObj;
    }*/



}
