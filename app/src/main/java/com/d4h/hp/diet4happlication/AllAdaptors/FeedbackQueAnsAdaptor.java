package com.d4h.hp.diet4happlication.AllAdaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.d4h.hp.diet4happlication.R;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class FeedbackQueAnsAdaptor extends RecyclerView.Adapter<FeedbackQueAnsAdaptor.ViewHolder> {


    private String[] question = new String[0];
    private String[] answer = new String[0];
    Context context;


    public FeedbackQueAnsAdaptor(String[] question, String[] answer, Context context) {
        this.question = question;
        this.answer = answer;
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_feedback_que_ans,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        viewHolder.setIsRecyclable(false);
        final String que = question[position];
        viewHolder.txtviewQuestion.setText(que);
        final String ans = answer[position];
        viewHolder.txtviewAnswer.setText(ans);

        /*for(int i=0;i<question.length;i++) {
            viewHolder.ivDownarrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.txtviewAnswer.setVisibility(View.VISIBLE);
                    viewHolder.ivDownarrow.setVisibility(View.GONE);
                    viewHolder.ivUparrow.setVisibility(View.VISIBLE);
                }
            });
            viewHolder.ivUparrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.txtviewAnswer.setVisibility(View.GONE);
                    viewHolder.ivDownarrow.setVisibility(View.VISIBLE);
                    viewHolder.ivUparrow.setVisibility(View.GONE);
                }
            });
        }*/



    }

    @Override
    public int getItemCount() {

        return question.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtviewAnswer, txtviewQuestion;
        ImageView ivUparrow, ivDownarrow;

        public ViewHolder(View view) {
            super(view);
            txtviewAnswer = view.findViewById(R.id.tv_answer);
            txtviewQuestion = view.findViewById(R.id.tv_question);
            ivDownarrow = view.findViewById(R.id.iv_down_arrow);
            ivUparrow = view.findViewById(R.id.iv_uparrow);
            //txtviewAnswer.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);


            ivDownarrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i=0;i<question.length;i++) {
                        txtviewAnswer.setVisibility(View.VISIBLE);
                        ivDownarrow.setVisibility(View.GONE);
                        ivUparrow.setVisibility(View.VISIBLE);
                    }
                }
            });
            ivUparrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int i=0;i<question.length;i++) {
                        txtviewAnswer.setVisibility(View.GONE);
                        ivDownarrow.setVisibility(View.VISIBLE);
                        ivUparrow.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}