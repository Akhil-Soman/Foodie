package storage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fauxwit.instacuisine.MainActivity;

import java.util.ArrayList;

import models.OrderModel;

/**
 * Created by user on 18/1/17.
 */

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME="DATABASE";
    private static final int DB_VERSION=1;

    private static final String TB_NAME_ORDER="ORDERS";
    public static final String CL_ORDER_ID="ORDER_ID";
    public static final String CL_CREATED_DATE="CREATED_DATE";
    public static final String CL_USERNAME="USERNAME";
    public static final String CL_USER_F_NAME="USER_F_NAME";
    public static final String CL_USER_L_NAME="USER_L_NAME";
    public static final String CL_USER_IMAGEURL="USER_IMAGEURL";
    public static final String CL_ORDERID_IN_ORDER="ORDERID_IN_ORDER";
    public static final String CL_SPECIAL_REQ="SPECIAL_REQ";
    public static final String CL_ITEM_NAME="ITEM_NAME";
    public static final String CL_DESCRIPTION="DESCRIPTION";
    public static final String CL_IMAGE="IMAGE";
    public static final String CL_ITEM_ID ="ITEM_ID";
    public static final String CL_COMBO="COMBO";
    public static final String CL_ITEM_QTY="ITEM_QTY";
    public static final String CL_COMBO_QTY="COMBO_QTY";
    public static final String CL_ACCEPTED="ACEEPTED";

    private static final String TB_ORDER_CL=CL_ORDER_ID+","+CL_CREATED_DATE+","+CL_USERNAME+","+CL_USER_F_NAME
            +","+CL_USER_L_NAME+","+CL_USER_IMAGEURL+","+CL_ORDERID_IN_ORDER+","+CL_SPECIAL_REQ+","+CL_ITEM_NAME
            +","+CL_DESCRIPTION+","+CL_IMAGE+","+ CL_ITEM_ID +","+CL_COMBO+","+CL_ITEM_QTY+","+CL_COMBO_QTY;

    SQLiteDatabase trialdb;
Context context;

    public Database(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context=context;
        trialdb=this.getWritableDatabase();
    }

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String NEW_TB_ORDER_CLS=TB_ORDER_CL.replace(","," text,");
        System.out.println("FOOD ::: DB COL ::: "+NEW_TB_ORDER_CLS);
        //NEW_TB_ORDER_CLS=NEW_TB_ORDER_CLS+" text";
        String query="create table "+TB_NAME_ORDER+"("+NEW_TB_ORDER_CLS+" text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertOrder(String id,String date,String username,String fname,String lname,String userImageUrl,String orderID,String specialReq,String itemName,String description,String image,String itemId,String combo,String itemQty,String comboQty){
        String query="insert into "+TB_NAME_ORDER+"("+TB_ORDER_CL+") values('"+id+"','"+date+"','"+username+"','"+fname+"','"+lname+"','"+userImageUrl+"','"+orderID+"','"+specialReq+"','"+itemName+"','"+description+"','"+image+"','"+itemId+"','"+combo+"','"+itemQty+"','"+comboQty+"')";
        trialdb.execSQL(query);
    }

    public Cursor getOrder(String orderId){
        String query="select * from "+TB_NAME_ORDER+" where "+CL_ORDER_ID+"='"+orderId+"'";
        return trialdb.rawQuery(query,null);
    }

    public ArrayList<OrderModel> getAllOrrders(){
        ArrayList<OrderModel> arl=new ArrayList<>();;
        String query="select * from "+TB_NAME_ORDER;
        Cursor c=trialdb.rawQuery(query,null);
        if (c.getCount()>0){
            c.moveToFirst();
            arl.clear();
            do {
                try {

                    OrderModel ob = new OrderModel();

                    ob.setOrderID(c.getString(c.getColumnIndex(CL_ORDER_ID)));
                    ob.setUserFNme(c.getString(c.getColumnIndex(CL_USER_F_NAME)));
                    ob.setUserLName(c.getString(c.getColumnIndex(CL_USER_L_NAME)));
                    ob.setUsername(c.getString(c.getColumnIndex(CL_USERNAME)));
                    ob.setUserImageUrl(c.getString(c.getColumnIndex(CL_USER_IMAGEURL)));
                    ob.setORDERID_IN_ORDER(c.getString(c.getColumnIndex(CL_ORDERID_IN_ORDER)));
                    ob.setSPECIAL_REQ(c.getString(c.getColumnIndex(CL_SPECIAL_REQ)));
                    ob.setITEM_NAME(c.getString(c.getColumnIndex(CL_ITEM_NAME)));
                    ob.setDescription(c.getString(c.getColumnIndex(CL_DESCRIPTION)));
                    ob.setIdInItem(c.getString(c.getColumnIndex(CL_ITEM_ID)));
                    ob.setCOMBO(c.getString(c.getColumnIndex(CL_COMBO)));
                    ob.setITEM_QTY(c.getString(c.getColumnIndex(CL_ITEM_QTY)));
                    ob.setCOMBO_QTY(c.getString(c.getColumnIndex(CL_COMBO_QTY)));
                    ob.setCREATED_DATE(c.getString(c.getColumnIndex(CL_CREATED_DATE)));

                    /*ob.setOrderID(c.getString(c.getColumnIndex(CL_ORDER_ID)));
                    ob.setOrderID(c.getString(c.getColumnIndex(CL_ORDER_ID)));
                    ob.setOrderID(c.getString(c.getColumnIndex(CL_ORDER_ID)));*/

                    arl.add(ob);

                    System.out.println("FOOD :::: DB arlADD");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }while (c.moveToNext());
        }
        return arl;
    }


    public int getOrderCount(){
        String query="select * from "+TB_NAME_ORDER;
        Cursor c= trialdb.rawQuery(query,null);
        return c.getCount();
    }
}
