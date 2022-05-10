package com.d4h.hp.diet4happlication.AllAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllDataModels.RecieverModel;
import com.d4h.hp.diet4happlication.R;


import java.util.ArrayList;
import java.util.List;

public class RecieverAdapter extends RecyclerView.Adapter<RecieverAdapter.ViewHolder> {

    private Context context;
    private List<RecieverModel> itemList;
    int px;


    public RecieverAdapter(Context context, List itemList) {
        this.context = context;
        this.itemList = itemList;
        setHasStableIds(false);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_chat_received,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        RecieverModel recieverModel=itemList.get(position);

        viewHolder.recvd_message_body.setText(recieverModel.getRecievermsg());
        viewHolder.recvd_message_time.setText(recieverModel.getRecievertime());
        viewHolder.recvd_message_date.setText(recieverModel.getRecvd_message_date());

        viewHolder.send_message_date.setText(recieverModel.getSend_message_date());
        viewHolder.send_message_body.setText(recieverModel.getSend_message_body());
        viewHolder.send_message_time.setText(recieverModel.getSend_message_time());


        if(!(recieverModel.getRecievermsg() ==null)&&(recieverModel.getSend_message_body() ==null)){

            // viewHolder.recivedrelativeLayout.setVisibility(RelativeLayout.VISIBLE);
            viewHolder.recvd_message_body.setText(recieverModel.getRecievermsg());
            viewHolder.recvd_message_time.setText(recieverModel.getRecievertime());
            viewHolder.recvd_message_date.setText(recieverModel.getRecvd_message_date());

            viewHolder.sendrelative.setVisibility(RelativeLayout.GONE);
        }
        if(!(recieverModel.getSend_message_body() ==null) && (recieverModel.getRecievermsg() ==null)){
            //  viewHolder.sendrelative.setVisibility(RelativeLayout.VISIBLE);

            viewHolder.send_message_date.setText(recieverModel.getSend_message_date());
            viewHolder.send_message_body.setText(recieverModel.getSend_message_body());
            viewHolder.send_message_time.setText(recieverModel.getSend_message_time());
            viewHolder.recivedrelativeLayout.setVisibility(RelativeLayout.GONE);
        }

    }
    @NonNull
    @Override
    public int getItemCount() {
        if (itemList == null)
            return 0;
        else
            return  itemList.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView recvd_message_body,recvd_message_time,recvd_message_date,send_message_time,send_message_body,send_message_date;
        RelativeLayout sendrelative,recivedrelativeLayout,main;
        ViewHolder(View view){
            super(view);
            recvd_message_time=view.findViewById(R.id.recvd_message_time);
            recvd_message_body=view.findViewById(R.id.recvd_message_body);
            send_message_time=view.findViewById(R.id.send_message_time);
            send_message_body=view.findViewById(R.id.send_message_body);
            recvd_message_date=view.findViewById(R.id.recv_date);
            send_message_date=view.findViewById(R.id.send_message_date);

            recivedrelativeLayout=view.findViewById(R.id.hh);
            sendrelative=view.findViewById(R.id.hhsend);

            //  recivedrelativeLayout.setVisibility(RelativeLayout.GONE);
            // sendrelative.setVisibility(RelativeLayout.GONE);
            // main=view.findViewById(R.id.chat_main);


            /* RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams)view.getLayoutParams();
            params.height=RelativeLayout.LayoutParams.WRAP_CONTENT;
            params.width=RelativeLayout.LayoutParams.WRAP_CONTENT;
            recivedrelativeLayout.setVisibility(View.GONE);*/

        }
    }
    public void swap(ArrayList<RecieverModel> itemList)
    {
        if(itemList == null || itemList.size()==0)
            return;
        if (itemList != null && itemList.size()>0)
            itemList.clear();
        itemList.addAll(itemList);
        notifyDataSetChanged();

    }
}