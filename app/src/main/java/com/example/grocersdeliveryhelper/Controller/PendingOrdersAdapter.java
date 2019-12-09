package com.example.grocersdeliveryhelper.Controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grocersdeliveryhelper.Model.CompletedOrdersModel;
import com.example.grocersdeliveryhelper.R;
import com.example.grocersdeliveryhelper.Response.PendingOrdersResponse;
import com.example.grocersdeliveryhelper.Views.Home;
import com.example.grocersdeliveryhelper.Views.UpdateStatus;
import com.example.grocersdeliveryhelper.fragments.PendingOrders;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class PendingOrdersAdapter extends RecyclerView.Adapter<PendingOrdersAdapter.PendingOrdersViewHolder> {
    ArrayList<PendingOrdersResponse.PendingOrder> completedOrdersList;
    private Context mcontext;
    private int mExpandedPosition=-1;

    public PendingOrdersAdapter(ArrayList<PendingOrdersResponse.PendingOrder> completedOrdersList, Fragment fragment) {
        this.completedOrdersList = completedOrdersList;
        mcontext = fragment.getActivity();
    }


    @NonNull
    @Override
    public PendingOrdersAdapter.PendingOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pending_orders, parent, false);
        return new PendingOrdersAdapter.PendingOrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PendingOrdersAdapter.PendingOrdersViewHolder holder, final int position) {
        final PendingOrdersResponse.PendingOrder pendingOrdersModel=completedOrdersList.get(position);

        holder.txtorderid.setText(pendingOrdersModel.getId()+"");
        holder.txtdate.setText(pendingOrdersModel.getDate()+"");
        holder.txtName.setText(pendingOrdersModel.getFullname());
        holder.txtPhone.setText(pendingOrdersModel.getContact());
        holder.txtAddress.setText(pendingOrdersModel.getAddress());
        holder.txtAmount.setText(pendingOrdersModel.getTotal()+"");
        holder.txttime.setText(pendingOrdersModel.getTime());

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



        final String orderid=String.valueOf(pendingOrdersModel.getId());
        holder.btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent(mcontext, UpdateStatus.class);
              intent.putExtra("orderid",holder.txtorderid.getText());
                intent.putExtra("custname",holder.txtName.getText());

                mcontext.startActivity(intent);

            }
        });


    }



    @Override
    public int getItemCount() {
        return completedOrdersList.size();

    }

    public class PendingOrdersViewHolder extends RecyclerView.ViewHolder  {

        private TextView txtorderid;
        private TextView txtdate;
        private Button btnupdate;
        private TextView txtName,txtPhone,txtAmount,txtAddress,txttime;
        private View subItem;
        public PendingOrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            txtorderid= itemView.findViewById(R.id.order_id);
            txtdate= itemView.findViewById(R.id.txt_date);
            txtName=itemView.findViewById(R.id.name);
            btnupdate=itemView.findViewById(R.id.btn_update);
            txtPhone=itemView.findViewById(R.id.phone);
            txtAmount=itemView.findViewById(R.id.amount);
            txtAddress=itemView.findViewById(R.id.address);
            subItem = itemView.findViewById(R.id.sub_item);
            txttime =itemView.findViewById(R.id.txt_time);


        }



    }


}
