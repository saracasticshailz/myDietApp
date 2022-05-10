package com.d4h.hp.diet4happlication.AllAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllDataModels.FooddieryModel;
import com.d4h.hp.diet4happlication.R;


import java.util.List;

public class FoodDieryAdaptor extends RecyclerView.Adapter<FoodDieryAdaptor.ViewHolder> {

    private Context context;
    private List<FooddieryModel> itemList;

    public FoodDieryAdaptor(Context context, List itemList) {
        this.context = context;
        this.itemList = itemList;
        setHasStableIds(true);

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_fooddiery,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder myViewHolder, int position) {
        FooddieryModel fooddieryModel=itemList.get(position);

        myViewHolder.txtViewMealname.setText(fooddieryModel.getMealName());
        myViewHolder.txtviewMealType.setText(fooddieryModel.getMealType());
        myViewHolder.txtviewMealTime.setText(fooddieryModel.getMealTime());

        if(fooddieryModel.getMealType().equals("1")){
            myViewHolder.txtviewMealType.setText("Other");
        }
        else if(fooddieryModel.getMealType().equals("2")){
            myViewHolder.txtviewMealType.setText("onrising");
        }
        else if(fooddieryModel.getMealType().equals("3")){
            myViewHolder.txtviewMealType.setText("BreakFast");

        }
        else if(fooddieryModel.getMealType().equals("4")){
            myViewHolder.txtviewMealType.setText("MidMorning");
        }
        else if(fooddieryModel.getMealType().equals("5")){
            myViewHolder.txtviewMealType.setText("Lunch");
        }
        else if(fooddieryModel.getMealType().equals("6")){
            myViewHolder.txtviewMealType.setText("EveningSnacks");
        }
        else if(fooddieryModel.getMealType().equals("7")){
            myViewHolder.txtviewMealType.setText("Dinner");
        }
        else if(fooddieryModel.getMealType().equals("8")){
            myViewHolder.txtviewMealType.setText("Bedtime");
        }
        else if(fooddieryModel.getMealType().equals("9")){
            myViewHolder.txtviewMealType.setText("EatingOut");
        }
        else if(fooddieryModel.getMealType().equals("10")){
            myViewHolder.txtviewMealType.setText("PreWorkout");
        }
        else if(fooddieryModel.getMealType().equals("11")){
            myViewHolder.txtviewMealType.setText("LateEvening");
        }
        myViewHolder.txtviewMealQuantity.setText(fooddieryModel.getMealQuantity());
        // FeedViewHolder.getTitle().setText(items.get(position).getTitle());
    }



    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtviewMealType,txtViewMealname,txtviewMealQuantity,txtviewMealTime;

        ViewHolder(View view){
            super(view);
            txtviewMealType=view.findViewById(R.id.txtview_mealtype);
            txtViewMealname=view.findViewById(R.id.txtview_mealname);
            txtviewMealQuantity=view.findViewById(R.id.txtview_quantity);
            txtviewMealTime=view.findViewById(R.id.txtview_mealtime);
        }

    }
}