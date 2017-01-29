package models;

/**
 * Created by akhil_soman on 24/1/17.
 */

public class OrderModel {



    private String username;
    private String userFNme;
    private String userLName;
    private String description;
    private String idInItem;
    private String userImageUrl;

    private String CREATED_DATE;
    private String SPECIAL_REQ;
    private String ITEM_NAME;
    private String IMAGE;

    public String getORDERID_IN_ORDER() {
        return ORDERID_IN_ORDER;
    }

    public void setORDERID_IN_ORDER(String ORDERID_IN_ORDER) {
        this.ORDERID_IN_ORDER = ORDERID_IN_ORDER;
    }

    private String ORDERID_IN_ORDER;
    private String COMBO;
    private String ITEM_QTY;
    private String COMBO_QTY;

    private String orderID;
    private String paidOrNot;
    private String order;

    public String getCREATED_DATE() {
        return CREATED_DATE;
    }

    public void setCREATED_DATE(String CREATED_DATE) {
        this.CREATED_DATE = CREATED_DATE;
    }

    public String getSPECIAL_REQ() {
        return SPECIAL_REQ;
    }

    public void setSPECIAL_REQ(String SPECIAL_REQ) {
        this.SPECIAL_REQ = SPECIAL_REQ;
    }

    public String getITEM_NAME() {
        return ITEM_NAME;
    }

    public void setITEM_NAME(String ITEM_NAME) {
        this.ITEM_NAME = ITEM_NAME;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }





    public String getCOMBO() {
        return COMBO;
    }

    public void setCOMBO(String COMBO) {
        this.COMBO = COMBO;
    }

    public String getITEM_QTY() {
        return ITEM_QTY;
    }

    public void setITEM_QTY(String ITEM_QTY) {
        this.ITEM_QTY = ITEM_QTY;
    }

    public String getCOMBO_QTY() {
        return COMBO_QTY;
    }

    public void setCOMBO_QTY(String COMBO_QTY) {
        this.COMBO_QTY = COMBO_QTY;
    }




    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPaidOrNot() {
        return paidOrNot;
    }

    public void setPaidOrNot(String paidOrNot) {
        this.paidOrNot = paidOrNot;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserFNme() {
        return userFNme;
    }

    public void setUserFNme(String userFNme) {
        this.userFNme = userFNme;
    }

    public String getUserLName() {
        return userLName;
    }

    public void setUserLName(String userLName) {
        this.userLName = userLName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdInItem() {
        return idInItem;
    }

    public void setIdInItem(String idInItem) {
        this.idInItem = idInItem;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }


}
