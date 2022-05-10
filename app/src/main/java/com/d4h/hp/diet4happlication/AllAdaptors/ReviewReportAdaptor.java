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

import com.d4h.hp.diet4happlication.AllActivities.Feedback;
import com.d4h.hp.diet4happlication.AllDataModels.ReviewModel;
import com.d4h.hp.diet4happlication.R;


import java.util.List;

public class ReviewReportAdaptor  extends RecyclerView.Adapter<ReviewReportAdaptor.ViewHolder> {


    List<ReviewModel> itemList;
    Context context;

    public ReviewReportAdaptor(List<ReviewModel> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewReportAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_reviewreport,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ReviewReportAdaptor.ViewHolder viewHolder, int i) {
        final ReviewModel model=itemList.get(i);
        viewHolder.txtviewStartDate.setText(model.getStartDt());
        viewHolder.txtviewEndDate.setText(model.getEndDt());
        viewHolder.txtviewOverallScore.setText(model.getScore());
        final String viewString=model.getViewrep();

        viewHolder.txtviewView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Feedback.class);
                intent.putExtra("view_id",viewString);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtviewStartDate,txtviewEndDate,txtviewOverallScore;
        ImageView txtviewView;

        ViewHolder(View view){
            super(view);
            txtviewStartDate=view.findViewById(R.id.txtview_startdt);
            txtviewEndDate=view.findViewById(R.id.txtview_enddt);
            txtviewOverallScore=view.findViewById(R.id.txtview_overallscore);
            txtviewView=view.findViewById(R.id.txtview_viewreport);
        }
    }

}