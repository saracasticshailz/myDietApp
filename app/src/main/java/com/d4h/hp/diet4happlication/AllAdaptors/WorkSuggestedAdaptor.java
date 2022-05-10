package com.d4h.hp.diet4happlication.AllAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllDataModels.WorkSuggestedModel;
import com.d4h.hp.diet4happlication.R;

import java.util.List;

public class WorkSuggestedAdaptor extends RecyclerView.Adapter<WorkSuggestedAdaptor.ViewHolder> {

    private Context context;
    private List<WorkSuggestedModel> itemList;

    public WorkSuggestedAdaptor(Context context, List<WorkSuggestedModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_suggested,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        WorkSuggestedModel model=itemList.get(i);
        viewHolder.txtviewActivity.setText(model.getStrActivity());
        viewHolder.txtviewRepetation.setText(model.getStrRepetation());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtviewActivity,txtviewRepetation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtviewActivity=itemView.findViewById(R.id.txtview_activity);
            txtviewRepetation=itemView.findViewById(R.id.txtview_repetation);
        }
    }


}
