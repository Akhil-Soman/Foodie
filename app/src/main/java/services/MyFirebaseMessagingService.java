package services;

import android.content.Intent;
import android.widget.Toast;

import com.fauxwit.instacuisine.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;

import java.util.ArrayList;

import models.OrderModel;
import storage.Database;
import utility.MyNotificationManager;
import ws.ParserClass;
import ws.WebServices;

/**
 * Created by user on 16/1/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    /*{
            "orderId" : "123",
            "message" : "order recieved",
    }*/

    String s="[\n" +
            "    {\n" +
            "        \"order\": {\n" +
            "            \"id\": 7,\n" +
            "            \"user\": {\n" +
            "                \"user\": {\n" +
            "                    \"first_name\": \"shihabudheen\",\n" +
            "                    \"last_name\": \"Karanchery\",\n" +
            "                    \"username\": \"shihab\",\n" +
            "                    \"email\": \"shihanedumpura@gmail.com\"\n" +
            "                },\n" +
            "                \"address\": \"addresss of shihb\",\n" +
            "                \"mobile\": \"9747672617\",\n" +
            "                \"zip_code\": \"658245\",\n" +
            "                \"user_image\": \"http://127.0.0.1:8000/uploads/uploads/Customer/shihab/IMG_5935.JPG\"\n" +
            "            },\n" +
            "            \"created_date\": \"2017-01-15T10:01:51.544095Z\",\n" +
            "            \"order_id\": \"ORD001\",\n" +
            "            \"special_request\": \"some thing spectial requestted to you\",\n" +
            "            \"amount\": 100,\n" +
            "            \"description\": \"lsdkjslkjf\",\n" +
            "            \"address\": \"this is new address\",\n" +
            "            \"mobile\": \"656565656\",\n" +
            "            \"zip_code\": \"25632\",\n" +
            "            \"location\": \"this is location\",\n" +
            "            \"status\": \"4\"\n" +
            "        },\n" +
            "        \"item\": {\n" +
            "            \"name\": \"Chicken Pizza\",\n" +
            "            \"description\": \"Hot pizza veg toppins\",\n" +
            "            \"image\": \"http://127.0.0.1:8000/uploads/uploads/menuitem/ChickenPizza/food-salad-healthy-lunch.jpg\",\n" +
            "            \"id\": 5,\n" +
            "            \"ingredients\": \"veg curry\",\n" +
            "            \"price\": 500.0,\n" +
            "            \"is_available\": true,\n" +
            "            \"preparation_time\": \"00:20:00\",\n" +
            "            \"delivery_fee\": 500.0,\n" +
            "            \"featured\": false,\n" +
            "            \"created_date\": \"2017-01-04T18:09:39.331252Z\",\n" +
            "            \"sub_category\": {\n" +
            "                \"id\": 1,\n" +
            "                \"category\": {\n" +
            "                    \"name\": \"Chinees\",\n" +
            "                    \"id\": 2\n" +
            "                },\n" +
            "                \"name\": \"Pizza\"\n" +
            "            }\n" +
            "        },\n" +
            "        \"combo\": \"null\",\n" +
            "        \"item_qty\": 1,\n" +
            "        \"combo_qty\": \"null\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"order\": {\n" +
            "            \"id\": 7,\n" +
            "            \"user\": {\n" +
            "                \"user\": {\n" +
            "                    \"first_name\": \"shihabudheen\",\n" +
            "                    \"last_name\": \"Karanchery\",\n" +
            "                    \"username\": \"shihab\",\n" +
            "                    \"email\": \"shihanedumpura@gmail.com\"\n" +
            "                },\n" +
            "                \"address\": \"addresss of shihb\",\n" +
            "                \"mobile\": \"9747672617\",\n" +
            "                \"zip_code\": \"658245\",\n" +
            "                \"user_image\": \"http://127.0.0.1:8000/uploads/uploads/Customer/shihab/IMG_5935.JPG\"\n" +
            "            },\n" +
            "            \"created_date\": \"2017-01-15T10:01:51.544095Z\",\n" +
            "            \"order_id\": \"ORD001\",\n" +
            "            \"special_request\": \"some thing spectial requestted to you\",\n" +
            "            \"amount\": 100,\n" +
            "            \"description\": \"lsdkjslkjf\",\n" +
            "            \"address\": \"this is new address\",\n" +
            "            \"mobile\": \"656565656\",\n" +
            "            \"zip_code\": \"25632\",\n" +
            "            \"location\": \"this is location\",\n" +
            "            \"status\": \"4\"\n" +
            "        },\n" +
            "        \"item\": {\n" +
            "            \"name\": \"Veg Soup\",\n" +
            "            \"description\": \"veg soup sldfkjlksjslfj\",\n" +
            "            \"image\": \"http://127.0.0.1:8000/uploads/uploads/menuitem/VegSoup/salmon-dish-food-meal-46239.jpeg\",\n" +
            "            \"id\": 4,\n" +
            "            \"ingredients\": \"vegitalbes\",\n" +
            "            \"price\": 50.0,\n" +
            "            \"is_available\": true,\n" +
            "            \"preparation_time\": \"00:16:00\",\n" +
            "            \"delivery_fee\": 150.0,\n" +
            "            \"featured\": true,\n" +
            "            \"created_date\": \"2017-01-10T18:19:54.121610Z\",\n" +
            "            \"sub_category\": {\n" +
            "                \"id\": 3,\n" +
            "                \"category\": {\n" +
            "                    \"name\": \"Arabian\",\n" +
            "                    \"id\": 4\n" +
            "                },\n" +
            "                \"name\": \"soupes\"\n" +
            "            }\n" +
            "        },\n" +
            "        \"combo\": \"null\",\n" +
            "        \"item_qty\": 1,\n" +
            "        \"combo_qty\": \"null\"\n" +
            "    }\n" +
            "]";



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        System.out.println("FOOD :::: MESSAGE RM ::: "+remoteMessage);

        System.out.println("FOOD :::: Message From "+remoteMessage.getFrom());
        if (remoteMessage.getNotification() != null) {
            System.out.println("FOOD :::: MESSAGE " + remoteMessage.getNotification().getBody());
        }

        if (remoteMessage.getData().size() > 0) {
            System.out.println("FOOD :::: MESSAGE 2 " + remoteMessage.getData().toString());
        }

        try {
            MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());

            //creating an intent for the notification
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            String orderId="";
            String msg=remoteMessage.getData().get("orderId");
            System.out.println("FOOD :::: MSG "+msg);
            mNotificationManager.showSmallNotification("Instacuisine", "Order ID : "+msg, intent);
            JSONArray jsonArray=null;
            ArrayList<OrderModel> arl=null;
            try {
                arl=ParserClass.parser_orderDetails(WebServices.getOrderDetails(msg));
            }catch (Exception e) {
                e.printStackTrace();
                jsonArray = new JSONArray(s);
                arl=ParserClass.parser_orderDetails(jsonArray);
            }

            if (arl!=null) {
                Database db = new Database(getApplicationContext());
                for (OrderModel ob : arl) {
                    db.insertOrder(ob.getOrderID(), ob.getCREATED_DATE(), ob.getUsername(), ob.getUserFNme(), ob.getUserLName(),
                            ob.getUserImageUrl(), ob.getORDERID_IN_ORDER(), ob.getSPECIAL_REQ(), ob.getITEM_NAME(), ob.getDescription(),
                            ob.getIMAGE(), ob.getIdInItem(), ob.getCOMBO(), ob.getITEM_QTY(), ob.getCOMBO_QTY());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
