package com.d4h.hp.diet4happlication.AllAdaptors;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.d4h.hp.diet4happlication.AllDataModels.WeightModel;
import com.d4h.hp.diet4happlication.R;


import java.util.List;


public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.ViewHolder> {
    private Context context;
    private List<WeightModel> weightmodels;
    public WeightAdapter(Context context, List<WeightModel> weightmodel) {
        this.context = context;
        this.weightmodels = weightmodel;
        setHasStableIds(true);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_weight,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        WeightModel weightmodel=weightmodels.get(i);
        viewHolder.Wdate.setText(weightmodel.getWdate());
        viewHolder.Wweight.setText(weightmodel.getWweight());
        viewHolder.Wbmi.setText(weightmodel.getWbmi());
    }

    @Override
    public int getItemCount() {

        return weightmodels.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView Wdate,Wweight,Wbmi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Wdate=itemView.findViewById(R.id.txtview_date_weight);
            Wweight=itemView.findViewById(R.id.txtview_weight_weight);
            Wbmi=itemView.findViewById(R.id.txview_bmi_weight);

        }
    }
}