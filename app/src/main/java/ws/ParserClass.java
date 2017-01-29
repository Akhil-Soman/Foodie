package ws;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import models.OrderModel;

/**
 * Created by akhil_soman on 24/1/17.
 */

public class ParserClass {


    public static ArrayList<OrderModel> parser_orderDetails(JSONArray initialObject){
        int size=initialObject.length();
        OrderModel ob=new OrderModel();
        String orderID="",userFirstName="",userLastName="",username="",userImageUrl="",order_id="",specialRequest="",
                itemName="",idInItem="",item_qty="",descriptions="",comboName="",combo_qty="";
        ArrayList<OrderModel> arl=new ArrayList<>();
        for (int i=0;i<size;i++){
            try {

                JSONObject jsonObject = initialObject.getJSONObject(i);
                JSONObject jsonOrder=jsonObject.getJSONObject("order");

                orderID=jsonOrder.getString("id");
                ob.setOrderID(orderID);

                JSONObject jsonFirstUser=jsonOrder.getJSONObject("user");
                JSONObject jsonSecondUser=jsonFirstUser.getJSONObject("user");

                userFirstName=jsonSecondUser.getString("first_name");
                userLastName=jsonSecondUser.getString("last_name");
                username=jsonSecondUser.getString("username");

                ob.setUserFNme(userFirstName);
                ob.setUserLName(userLastName);
                ob.setUsername(username);

                userImageUrl=jsonFirstUser.getString("user_image");
                ob.setUserImageUrl(userImageUrl);

                order_id=jsonOrder.getString("order_id");
                specialRequest=specialRequest+","+jsonOrder.getString("special_request");

                ob.setORDERID_IN_ORDER(order_id);
                ob.setSPECIAL_REQ(specialRequest);

                JSONObject jsonItem=jsonObject.getJSONObject("item");

                itemName=itemName+","+jsonItem.getString("name");
                descriptions=descriptions+","+jsonItem.getString("description");
                idInItem=idInItem+","+jsonItem.getString("id");
                item_qty=item_qty+","+jsonObject.getString("item_qty");

                try {
                    comboName = comboName + "," + jsonObject.getString("combo");
                    combo_qty = combo_qty + "," + jsonObject.getString("combo_qty");
                }catch (Exception e){
                    e.printStackTrace();
                }

                if (itemName.startsWith(",")){
                    itemName=itemName.substring(1,itemName.length());
                }
                if (descriptions.startsWith(",")){
                    descriptions=descriptions.substring(1,descriptions.length());
                }
                if (idInItem.startsWith(",")){
                    idInItem=idInItem.substring(1,idInItem.length());
                }
                if (comboName.startsWith(",")){
                    comboName=comboName.substring(1,comboName.length());
                }
                if (item_qty.startsWith(",")){
                    item_qty=item_qty.substring(1,item_qty.length());
                }
                if (combo_qty.startsWith(",")){
                    combo_qty=combo_qty.substring(1,combo_qty.length());
                }

                ob.setITEM_NAME(itemName);
                ob.setDescription(descriptions);
                ob.setIdInItem(idInItem);
                ob.setCOMBO(comboName);
                ob.setITEM_QTY(item_qty);
                ob.setCOMBO_QTY(combo_qty);



                System.out.println("FOOD ::: PARSER ::: ARLADD");

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        arl.add(ob);
        return arl;
    }
}
