package com.d4h.hp.diet4happlication.AllFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.AllActivities.AddReview;
import com.d4h.hp.diet4happlication.AllActivities.NavigationDrawerActivity;
import com.d4h.hp.diet4happlication.AllAdaptors.ReviewReportAdaptor;
import com.d4h.hp.diet4happlication.AllDataModels.ReviewModel;
import com.d4h.hp.diet4happlication.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ReviewReportFragement  extends Fragment {

    private RecyclerView recyclerView;
    ReviewReportAdaptor reviewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    private List<ReviewModel>reviewModels;
    ReviewModel reviewModel;
    FloatingActionButton fab_Review;
    SharedPreferences loginPreffrences;
    String pt_id,strStartDate,strEndDate,strOverAll,strReviewId;
    Handler mHandler;

    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_reviewreport,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(
                "Review Report");


        recyclerView=view.findViewById(R.id.review_rc);
        fab_Review=view.findViewById(R.id.fab_reviewRep);

        layoutManager=new LinearLayoutManager(getContext());
        reviewModels=new ArrayList<>();
        reviewAdapter=new ReviewReportAdaptor(reviewModels,context);
        viewData();
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(2000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                viewData();
                                // Toast.makeText(ChatActivity.this, "refresh", Toast.LENGTH_SHORT).show();
                                // Write your code here to update the UI.
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();
        fab_Review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AddReview.class));
            }
        });



    }
    public void viewData(){

        final RequestQueue requestQueue=Volley.newRequestQueue(context);
        loginPreffrences = context.getSharedPreferences("UserData", 0);
        pt_id=loginPreffrences.getString("patient_id","");

        String url="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/fetch_review_report.php?patient_id="+pt_id;

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    reviewModels.clear();
                    JSONArray out=response.getJSONArray("output");
                    for (int i=0;i<out.length();i++){
                        JSONObject jsonObject=out.getJSONObject(i);

                        strStartDate=jsonObject.getString("start_date");
                        strEndDate=jsonObject.getString("end_date");
                        strOverAll=jsonObject.getString("overall_score");
                        strReviewId=jsonObject.getString("reviewId");

                        reviewModel=new ReviewModel();
                        reviewModel.setStartDt(strStartDate);
                        reviewModel.setEndDt(strEndDate);
                        reviewModel.setScore(strOverAll);
                        reviewModel.setViewrep(strReviewId);

                        reviewModels.add(reviewModel);
                    }
                }catch (JSONException e){

                }
                setRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null || error.networkResponse == null) {
                    return;
                }

                String body;
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    body = new String(error.networkResponse.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // exception
                }
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    public void setRecyclerView(){
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(reviewAdapter);
    }
}