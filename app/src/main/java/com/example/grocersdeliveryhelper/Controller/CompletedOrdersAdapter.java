package com.example.grocersdeliveryhelper.Controller;

import android.content.Context;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.grocersdeliveryhelper.Model.CompletedOrdersModel;
import com.example.grocersdeliveryhelper.R;
import com.example.grocersdeliveryhelper.Response.CompletedOrdersResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CompletedOrdersAdapter extends RecyclerView.Adapter<CompletedOrdersAdapter.CompletedOrdersViewHolder> {

    private Context ctx;
    private ArrayList<CompletedOrdersResponse.CompletedOrder> completedOrdersList;
    private int  mExpandedPosition=-1;

    public CompletedOrdersAdapter(Context ctx, ArrayList<CompletedOrdersResponse.CompletedOrder> completedOrdersList) {
        this.ctx = ctx;
        this.completedOrdersList = completedOrdersList;
    }



    @NonNull
    @Override
    public CompletedOrdersAdapter.CompletedOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_completed_orders, parent, false);
        return new CompletedOrdersAdapter.CompletedOrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedOrdersAdapter.CompletedOrdersViewHolder holder, final int position) {
        final CompletedOrdersResponse.CompletedOrder completedOrdersModel=completedOrdersList.get(position);

        holder.txtorderid.setText("Order ID:"+completedOrdersModel.getId());
        holder.txtdate.setText(completedOrdersModel.getDate());
        holder.txtName.setText(completedOrdersModel.getFullname());
        holder.txtPhone.setText(completedOrdersModel.getContact());
        holder.txtAddress.setText(completedOrdersModel.getAddress());
        holder.txtAmount.setText(completedOrdersModel.getTotal()+"");
        holder.txttime.setText(completedOrdersModel.getTime());


        final boolean isExpanded = position==mExpandedPosition;
        holder.subItem.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1:position;
                notifyItemChanged(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return completedOrdersList.size();
    }

    public class CompletedOrdersViewHolder extends RecyclerView.ViewHolder {

        private TextView txtorderid;
        private TextView txtdate;
        private TextView txtName,txtPhone,txtAmount,txtAddress,txttime;
        private View subItem;
        public CompletedOrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            txtorderid= itemView.findViewById(R.id.order_id);
            txtdate= itemView.findViewById(R.id.txt_date);
            txtName=itemView.findViewById(R.id.name);
            txtPhone=itemView.findViewById(R.id.phone);
            txtAmount=itemView.findViewById(R.id.amount);
            txtAddress=itemView.findViewById(R.id.address);
            txttime=itemView.findViewById(R.id.txt_time);
            subItem = itemView.findViewById(R.id.sub_item);


        }

    }
}
