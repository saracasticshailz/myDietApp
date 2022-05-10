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

import com.d4h.hp.diet4happlication.AllActivities.EscallationAnswerActivity;
import com.d4h.hp.diet4happlication.AllDataModels.EscallationModel;
import com.d4h.hp.diet4happlication.R;


import java.util.List;

public class EscallationAdaptor extends RecyclerView.Adapter<EscallationAdaptor.ViewHolder> {

    private Context context;
    private List<EscallationModel> itemList;


    public EscallationAdaptor(Context context, List itemList) {
        this.context = context;
        this.itemList = itemList;
       // setHasStableIds(true);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_escallation,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        EscallationModel model=itemList.get(position);
        viewHolder.txtviewEscdate.setText(model.getEscDate());
        if(model.getEscStatus().equals("1"))
        {
            viewHolder.txtviewEscstatus.setText("Yes");
        }
        if(model.getEscStatus().equals("0"))
        {
            viewHolder.txtviewEscstatus.setText("No");
        }
        viewHolder.txviewEscsubject.setText(model.getEscSubject());
        //int strTest=Integer.parseInt(model.getEscId());
        final String str=model.getEscId();
        viewHolder.txtviewEscview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,EscallationAnswerActivity.class);
                intent.putExtra("test_id",str);
                context.startActivity(intent);
            }
        });


    }
    @NonNull
    @Override
    public int getItemCount() {
        if (itemList == null)
            return 0;
        else
            return  itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtviewEscdate,txviewEscsubject,txtviewEscstatus;
        ImageView txtviewEscview;
        ViewHolder(View view){
            super(view);
            txtviewEscdate=view.findViewById(R.id.txtview_escdate);
            txviewEscsubject=view.findViewById(R.id.txtview_escsubject);
            txtviewEscstatus=view.findViewById(R.id.txtview_escstatus);
            txtviewEscview=view.findViewById(R.id.txtview_escview);

        }
    }
}
