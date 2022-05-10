package com.d4h.hp.diet4happlication.AllAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllActivities.WorkOutCompletedActivity;
import com.d4h.hp.diet4happlication.AllDataModels.WorkCompletedModel;
import com.d4h.hp.diet4happlication.R;

import java.util.List;

public class WorkCompletedAdaptor extends RecyclerView.Adapter<WorkCompletedAdaptor.ViewHolder> {

    private List<WorkCompletedModel> itemlist;
    Context context;

    public WorkCompletedAdaptor(List<WorkCompletedModel> itemlist, Context context) {
        this.itemlist = itemlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_completed,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        WorkCompletedModel model=itemlist.get(i);
        viewHolder.textviewActivity.setText(model.getStrActivity());
        viewHolder.txtviewDate.setText(model.getStrDate());
        viewHolder.txtviewRepeation.setText(model.getStrRepetation());
    }

    @Override
    public int getItemCount()
    {
        return itemlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textviewActivity,txtviewDate,txtviewRepeation;
        public ViewHolder(View itemView) {
            super(itemView);
            textviewActivity=itemView.findViewById(R.id.txtview_activity);
            txtviewDate=itemView.findViewById(R.id.txtview_act_date);
            txtviewRepeation=itemView.findViewById(R.id.txtview_repetation);
        }
    }
}
