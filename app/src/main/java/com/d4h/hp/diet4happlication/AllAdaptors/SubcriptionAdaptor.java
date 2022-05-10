package com.d4h.hp.diet4happlication.AllAdaptors;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllActivities.SubcriptionInvoiceActivity;
import com.d4h.hp.diet4happlication.AllDataModels.SubcriptionModel;
import com.d4h.hp.diet4happlication.R;


import java.util.List;

public class SubcriptionAdaptor extends RecyclerView.Adapter<SubcriptionAdaptor.ViewHolder> {

    List<SubcriptionModel> itemlist;
    Context context;
    String str;

    public SubcriptionAdaptor(List<SubcriptionModel> itemlist, Context context) {
        this.itemlist = itemlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_subcription,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            final SubcriptionModel model=itemlist.get(position);
            viewHolder.txtviewPlanName.setText(model.getSubPlanName());
            viewHolder.txtviewDuration.setText(model.getSubDuration());
            viewHolder.txtviewPaidAmt.setText(model.getSubPaidAmt());
            viewHolder.txtviewSubcriptionOver.setText(model.getSubOver());


            viewHolder.btnViewInvoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    str=model.getSubPayId();
                    Intent i=new Intent(context,SubcriptionInvoiceActivity.class);
                    i.putExtra("pay_id",str);
                    //Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                    context.startActivity(i);
                }
            });
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtviewPlanName,txtviewDuration,txtviewSubcriptionOver,txtviewPaidAmt;
        Button btnViewInvoice;
        ViewHolder(View view){
            super(view);
            txtviewPlanName=view.findViewById(R.id.txtview_sub_plan);
            txtviewDuration=view.findViewById(R.id.txtview_sub_duration);
            txtviewSubcriptionOver=view.findViewById(R.id.txtview_sub_subover);
            txtviewPaidAmt=view.findViewById(R.id.txtview_sub_paidamt);
            btnViewInvoice=view.findViewById(R.id.btn_sub_invoice);

        }
    }
}
