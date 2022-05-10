package com.d4h.hp.diet4happlication.AllAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllDataModels.NotificationModel;
import com.d4h.hp.diet4happlication.R;


import java.util.List;

public class NotificationAdaptor extends RecyclerView.Adapter<NotificationAdaptor.ViewHolder> {


    List<NotificationModel> list;
    Context context;

    public NotificationAdaptor(List<NotificationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_notification,
                viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NotificationModel model=list.get(i);
        viewHolder.notiContet.setText(model.getNotiContent());
        viewHolder.notiTime.setText(model.getNotiTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView notiHeader,notiContet,notiTime;
        ViewHolder(View view){
            super(view);
            notiContet=view.findViewById(R.id.txtview_noti_content);
            notiHeader=view.findViewById(R.id.txtview_noti_header);
            notiTime=view.findViewById(R.id.notf_time);
        }

    }
}