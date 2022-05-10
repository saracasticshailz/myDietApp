package com.d4h.hp.diet4happlication.AllFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.d4h.hp.diet4happlication.AllActivities.NavigationDrawerActivity;
import com.d4h.hp.diet4happlication.AllAdaptors.FeedbackQueAnsAdaptor;
import com.d4h.hp.diet4happlication.R;

import java.util.List;

public class FeedBackQueAnsFragement extends Fragment {

    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<String> question;
    List<String> answer;
    private RecyclerView.Adapter adapter;

    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_feedback_que_ans,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.rv_feedback_que_ans);
        String[] question={getResources().getString(R.string.que1),getResources().getString(R.string.que2),
                getResources().getString(R.string.que3),getResources().getString(R.string.que4),
                getResources().getString(R.string.que5),getResources().getString(R.string.que6),
                getResources().getString(R.string.que7),getResources().getString(R.string.que8),
                getResources().getString(R.string.que9),getResources().getString(R.string.que10),
                getResources().getString(R.string.que11),getResources().getString(R.string.que12),
                getResources().getString(R.string.que13),getResources().getString(R.string.que14),
                getResources().getString(R.string.que15),getResources().getString(R.string.que16),
                getResources().getString(R.string.que17),getResources().getString(R.string.que18),
                getResources().getString(R.string.que19),getResources().getString(R.string.que20),
                getResources().getString(R.string.que21),getResources().getString(R.string.que22),
                getResources().getString(R.string.que23),getResources().getString(R.string.que24),
                getResources().getString(R.string.que25),getResources().getString(R.string.que26),
                getResources().getString(R.string.que27),getResources().getString(R.string.que28),
                getResources().getString(R.string.que29),getResources().getString(R.string.que30),
                getResources().getString(R.string.que31), getResources().getString(R.string.que33),getResources().getString(R.string.que34),
                getResources().getString(R.string.que35),getResources().getString(R.string.que36),
                getResources().getString(R.string.que37),getResources().getString(R.string.que38),
                getResources().getString(R.string.que39),getResources().getString(R.string.que40),
                getResources().getString(R.string.que41)};
        String[] answer={getResources().getString(R.string.anw1),getResources().getString(R.string.anw2),
                getResources().getString(R.string.anw3),getResources().getString(R.string.anw4),
                getResources().getString(R.string.anw5),getResources().getString(R.string.anw6),
                getResources().getString(R.string.anw7),getResources().getString(R.string.anw8),
                getResources().getString(R.string.anw9),getResources().getString(R.string.anw10),
                getResources().getString(R.string.anw11),getResources().getString(R.string.anw12),
                getResources().getString(R.string.anw13),getResources().getString(R.string.anw14),
                getResources().getString(R.string.anw15),getResources().getString(R.string.anw16),
                getResources().getString(R.string.anw17),getResources().getString(R.string.anw18),
                getResources().getString(R.string.anw19),getResources().getString(R.string.anw20),
                getResources().getString(R.string.anw21),getResources().getString(R.string.anw22),
                getResources().getString(R.string.anw23),getResources().getString(R.string.anw24),
                getResources().getString(R.string.anw25),getResources().getString(R.string.anw26),
                getResources().getString(R.string.anw27),getResources().getString(R.string.anw28),
                getResources().getString(R.string.anw29),getResources().getString(R.string.anw30),
                getResources().getString(R.string.anw31), getResources().getString(R.string.anw33),getResources().getString(R.string.anw34),
                getResources().getString(R.string.anw35),getResources().getString(R.string.anw36),
                getResources().getString(R.string.anw37),getResources().getString(R.string.anw38),
                getResources().getString(R.string.anw39),getResources().getString(R.string.anw40),
                getResources().getString(R.string.anw41)};
        adapter=new FeedbackQueAnsAdaptor(question,answer,context);
        layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(
                "FAQ");
        setRecyclerView();

    }
    public void setRecyclerView(){
        recyclerView.setLayoutManager(layoutManager);

        // recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}