package com.d4h.hp.diet4happlication.AllAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllDataModels.DietchartModel;
import com.d4h.hp.diet4happlication.R;


import java.util.List;

public class MydietAdapter extends RecyclerView.Adapter<MydietAdapter.ViewHolder> {

    private Context context;
    private List<DietchartModel> dietList;

    public MydietAdapter(Context context, List dietList) {
        this.context = context;
        this.dietList = dietList;
        // setHasStableIds(true);

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_mydiet_item,
                viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        DietchartModel dietchartModel=dietList.get(i);
        viewHolder.dc_mealtype.setText(dietchartModel.getDc_mealtype());
        viewHolder.dc_mealtime.setText(dietchartModel.getDc_mealtime());
        viewHolder.dc_mealname1.setText(dietchartModel.getDc_mealname1());
        viewHolder.dc_quantity1.setText(dietchartModel.getDc_quantity1());
        viewHolder.dc_remark1.setText(dietchartModel.getDc_remark1());

        if(!(dietchartModel.getDc_mealname2() ==null)){
            viewHolder.dc_quantity2.setVisibility(View.VISIBLE);
            viewHolder.dc_quantity3.setVisibility(View.VISIBLE);
            viewHolder.dc_quantity4.setVisibility(View.VISIBLE);
            viewHolder.dc_quantity5.setVisibility(View.VISIBLE);

            viewHolder.dc_remark2.setVisibility(View.VISIBLE);
            viewHolder.dc_remark3.setVisibility(View.VISIBLE);
            viewHolder.dc_remark4.setVisibility(View.VISIBLE);
            viewHolder.dc_remark5.setVisibility(View.VISIBLE);

            viewHolder.dc_mealname2.setVisibility(View.VISIBLE);
            viewHolder.dc_mealname4.setVisibility(View.VISIBLE);
            viewHolder.dc_mealname5.setVisibility(View.VISIBLE);
            viewHolder.dc_mealname3.setVisibility(View.VISIBLE);



            viewHolder.dc_mealname2.setText(dietchartModel.getDc_mealname2());
            viewHolder.dc_remark2.setText(dietchartModel.getDc_remark2());
            viewHolder.dc_quantity2.setText(dietchartModel.getDc_quantity2());

            viewHolder.dc_mealname3.setText(dietchartModel.getDc_mealname3());
            viewHolder.dc_remark3.setText(dietchartModel.getDc_remark3());
            viewHolder.dc_quantity3.setText(dietchartModel.getDc_quantity3());

            viewHolder.dc_mealname4.setText(dietchartModel.getDc_mealname4());
            viewHolder.dc_remark4.setText(dietchartModel.getDc_remark4());
            viewHolder.dc_quantity4.setText(dietchartModel.getDc_quantity4());

            viewHolder.dc_mealname5.setText(dietchartModel.getDc_mealname5());
            viewHolder.dc_remark5.setText(dietchartModel.getDc_remark5());
            viewHolder.dc_quantity5.setText(dietchartModel.getDc_quantity5());
        }

    }

    @Override
    public int getItemCount() {
        return dietList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dc_mealtype,dc_mealtime,
                dc_remark1,dc_mealname1,dc_quantity1,
                dc_remark2,dc_mealname2,dc_quantity2,
                dc_remark3,dc_mealname3,dc_quantity3,
                dc_remark4,dc_mealname4,dc_quantity4,
                dc_remark5,dc_mealname5,dc_quantity5;
        public ViewHolder( View itemView) {
            super(itemView);

            dc_mealtype=itemView.findViewById(R.id.dc_mealtype);
            dc_mealtime=itemView.findViewById(R.id.dc_mealtime);

            dc_quantity1=itemView.findViewById(R.id.dc_quantity1);
            dc_quantity2=itemView.findViewById(R.id.dc_quantity2);
            dc_quantity3=itemView.findViewById(R.id.dc_quantity3);
            dc_quantity4=itemView.findViewById(R.id.dc_quantity4);
            dc_quantity5=itemView.findViewById(R.id.dc_quantity5);
            dc_quantity2.setVisibility(View.GONE);
            dc_quantity3.setVisibility(View.GONE);
            dc_quantity4.setVisibility(View.GONE);
            dc_quantity5.setVisibility(View.GONE);

            dc_mealname2=itemView.findViewById(R.id.dc_mealname2);
            dc_mealname3=itemView.findViewById(R.id.dc_mealname3);
            dc_mealname4=itemView.findViewById(R.id.dc_mealname4);
            dc_mealname5=itemView.findViewById(R.id.dc_mealname5);
            dc_mealname1=itemView.findViewById(R.id.dc_mealname1);
            dc_mealname2.setVisibility(View.GONE);
            dc_mealname3.setVisibility(View.GONE);
            dc_mealname4.setVisibility(View.GONE);
            dc_mealname5.setVisibility(View.GONE);

            dc_remark1=itemView.findViewById(R.id.dc_remark1);
            dc_remark2=itemView.findViewById(R.id.dc_remark2);
            dc_remark3=itemView.findViewById(R.id.dc_remark3);
            dc_remark4=itemView.findViewById(R.id.dc_remark4);
            dc_remark5=itemView.findViewById(R.id.dc_remark5);
            dc_remark2.setVisibility(View.GONE);
            dc_remark3.setVisibility(View.GONE);
            dc_remark4.setVisibility(View.GONE);
            dc_remark5.setVisibility(View.GONE);



        }
    }
}