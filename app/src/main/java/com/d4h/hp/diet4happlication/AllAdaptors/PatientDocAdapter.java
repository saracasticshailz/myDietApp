package com.d4h.hp.diet4happlication.AllAdaptors;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllActivities.SecondScreen;
import com.d4h.hp.diet4happlication.AllDataModels.GetDocModel;
import com.d4h.hp.diet4happlication.R;

import java.util.List;

public class PatientDocAdapter extends RecyclerView.Adapter<com.d4h.hp.diet4happlication.AllAdaptors.PatientDocAdapter.ViewHolder> {

        List<GetDocModel> itemList;
        Context context;
    public PatientDocAdapter(List<GetDocModel> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }    @NonNull
    @Override
    public PatientDocAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_patdoc,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientDocAdapter.ViewHolder viewHolder, int i) {
        GetDocModel model=itemList.get(i);
        viewHolder.patdoc_date.setText(model.getDate());
        viewHolder.patdoc_name.setText(model.getFilename());
        final String view_id=model.getFilelink();
        viewHolder.patdoc_viewdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(context,SecondScreen.class);
                in.putExtra("view_id",view_id);
                in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView patdoc_date,patdoc_name;
        ImageView patdoc_viewdoc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patdoc_date=itemView.findViewById(R.id.patdoc_date);
            patdoc_name=itemView.findViewById(R.id.patdoc_name);
            patdoc_viewdoc=itemView.findViewById(R.id.patdoc_viewdoc);

        }
    }
}
