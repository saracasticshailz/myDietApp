package com.d4h.hp.diet4happlication.AllAdaptors;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllDataModels.ReviewModel;
import com.d4h.hp.diet4happlication.R;

import java.util.List;

public class WalletAdaptor extends RecyclerView.Adapter<WalletAdaptor.ViewHolder> {

    List<ReviewModel> itemList;
    Context context;

    public WalletAdaptor(List<ReviewModel> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_wallet,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        //if amount credited
        viewHolder.ivRs.setColorFilter(context.getResources().getColor(R.color.colorGreen));
        viewHolder.ivPlusMinus.setBackground(context.getResources().getDrawable(R.drawable.walletadd));
        viewHolder.tvRupees.setTextColor(context.getResources().getColor(R.color.colorGreen));

        //if amount debited
        viewHolder.ivRs.setColorFilter(context.getResources().getColor(R.color.colorRed));
        viewHolder.ivPlusMinus.setBackground(context.getResources().getDrawable(R.drawable.walletminus));
        viewHolder.tvRupees.setTextColor(context.getResources().getColor(R.color.colorRed));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivRs,ivPlusMinus;
        TextView tvRupees;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRs = (ImageView) itemView.findViewById(R.id.iv_rs);
            ivPlusMinus=itemView.findViewById(R.id.iv_plus_minus);
            tvRupees=itemView.findViewById(R.id.tv_rupees);

        }
    }


}