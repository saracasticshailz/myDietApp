package com.d4h.hp.diet4happlication.AllAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllDataModels.AssesmentModel;
import com.d4h.hp.diet4happlication.R;


import java.util.List;

public class AssessmentAdaptor extends RecyclerView.Adapter<AssessmentAdaptor.ViewHolder> {

    List<AssesmentModel> itemList;
    Context context;

    public AssessmentAdaptor(List<AssesmentModel> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_assement,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdaptor.ViewHolder viewHolder, int position) {
                AssesmentModel model=itemList.get(position);
                viewHolder.txtviewassDate.setText(model.getAssDate());
                viewHolder.txtviewassWeight.setText(model.getAssWeight());
                viewHolder.txtviewassBodyage.setText(model.getAssBosyAge());
                viewHolder.txtviewassBodyfats.setText(model.getAssBodyFats());
                viewHolder.txtviewassMeta.setText(model.getAssMetabolism());
                viewHolder.txtviewassVisce.setText(model.getAssVisceral());
                viewHolder.txtviewassBmi.setText(model.getAssBmi());
                viewHolder.txtviewassWaist.setText(model.getAssWaist());
                viewHolder.txtviewassHips.setText(model.getAssHips());
                viewHolder.txtviewassRemark.setText(model.getAssRemark());
    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtviewassDate,txtviewassWeight,txtviewassBodyfats,txtviewassBodyage;
        TextView txtviewassMeta,txtviewassVisce,txtviewassBmi,txtviewassWaist,txtviewassHips,txtviewassRemark;
        ViewHolder(View view){
            super(view);
            txtviewassDate=view.findViewById(R.id.txtview_ass_date);
            txtviewassWeight=view.findViewById(R.id.txtview_ass_weight);
            txtviewassBodyfats=view.findViewById(R.id.txtview_ass_bodyfats);
            txtviewassBodyage=view.findViewById(R.id.txtview_ass_bodyage);
            txtviewassMeta=view.findViewById(R.id.txtview_ass_resting);
            txtviewassVisce=view.findViewById(R.id.txtview_ass_viscrel);
            txtviewassBmi=view.findViewById(R.id.txtview_ass_bmi);
            txtviewassWaist=view.findViewById(R.id.txtview_ass_waist);
            txtviewassHips=view.findViewById(R.id.txtview_ass_hips);
            txtviewassRemark=view.findViewById(R.id.txtview_ass_r);
        }
    }

}
