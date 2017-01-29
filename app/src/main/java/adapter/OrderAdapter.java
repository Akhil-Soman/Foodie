package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fauxwit.instacuisine.R;

import java.util.ArrayList;

import models.OrderModel;

/**
 * Created by akhil_soman on 27/1/17.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    ArrayList<OrderModel> arl_orders;

    public OrderAdapter(ArrayList<OrderModel> arl){
        arl_orders=arl;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_rv_custom_row,parent,false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        OrderModel ob=arl_orders.get(position);
        holder.txt_paidOrNot.setText("PAID");
        String text="";
        int size=ob.getITEM_NAME().split(",").length;
        for (int i=0;i<size;i++){
            text=text+ob.getITEM_NAME().split(",")[i]+"            x"+ob.getITEM_QTY().split(",")[i]+"\n";
        }
        holder.txt_order.setText(text);
        holder.txt_orderPName.setText(ob.getUsername());
    }

    @Override
    public int getItemCount() {

        System.out.println("FOOD :::: ARL SIZE :: "+arl_orders.size());
        return arl_orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imv_photo;
        TextView txt_orderPName;
        TextView txt_paidOrNot;
        TextView txt_order;

        public ViewHolder(View itemView) {
            super(itemView);
            imv_photo=(ImageView)itemView.findViewById(R.id.imv_orderPerson);
            txt_orderPName=(TextView) itemView.findViewById(R.id.txt_orderPName);
            txt_paidOrNot=(TextView)itemView.findViewById(R.id.txt_orderPaid);
            txt_order=(TextView)itemView.findViewById(R.id.txt_order);
        }
    }

}
