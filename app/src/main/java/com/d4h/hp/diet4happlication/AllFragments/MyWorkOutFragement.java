package com.d4h.hp.diet4happlication.AllFragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.AllActivities.NavigationDrawerActivity;
import com.d4h.hp.diet4happlication.AllActivities.WorkOutCompletedActivity;
import com.d4h.hp.diet4happlication.AllAdaptors.ReviewReportAdaptor;
import com.d4h.hp.diet4happlication.AllAdaptors.WorkSuggestedAdaptor;
import com.d4h.hp.diet4happlication.AllDataModels.ReviewModel;
import com.d4h.hp.diet4happlication.AllDataModels.WorkSuggestedModel;
import com.d4h.hp.diet4happlication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MyWorkOutFragement extends Fragment {

    private Context context;
    Button btnCompleted,btnSuggested;
    SharedPreferences loginPreffrences;
    private RecyclerView recyclerView;
    WorkSuggestedAdaptor workSuggestedAdaptor;
    private RecyclerView.LayoutManager layoutManager;
    private List<WorkSuggestedModel> itemList;
    WorkSuggestedModel workModel;
    String pt_id,strActvity,strRepetation;


    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_workout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnCompleted=(Button)view.findViewById(R.id.btn_completed);
        btnSuggested=(Button)view.findViewById(R.id.btn_suggested);
        recyclerView=view.findViewById(R.id.rv_suggested);

        layoutManager=new LinearLayoutManager(getContext());
        itemList=new ArrayList<>();
        workSuggestedAdaptor=new WorkSuggestedAdaptor(context,itemList);

       // Toast.makeText(context, "Work In Progress", Toast.LENGTH_LONG).show();
        displaySuggestedAct();
        btnSuggested.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(
                "My WorkOut");
        btnSuggested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSuggested.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                btnCompleted.setBackgroundColor(getResources().getColor(R.color.colorGray));
                displaySuggestedAct();

            }
        });
        btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCompleted.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                btnSuggested.setBackgroundColor(getResources().getColor(R.color.colorGray));
                startActivity(new Intent(getActivity(),WorkOutCompletedActivity.class));
            }
        });
    }
    public void displaySuggestedAct(){
        final RequestQueue requestQueue=Volley.newRequestQueue(context);
        loginPreffrences = context.getSharedPreferences("UserData", 0);
        pt_id=loginPreffrences.getString("patient_id","");

        String url="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/fetch_suggession_activity.php?patient_id="+pt_id;

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray out=response.getJSONArray("output");
                    for (int i=0;i<out.length();i++){
                        JSONObject jsonObject=out.getJSONObject(i);

                        strActvity=jsonObject.getString("activity");
                        strRepetation=jsonObject.getString("repetition");


                        workModel=new WorkSuggestedModel();
                        workModel.setStrActivity(strActvity);
                        workModel.setStrRepetation(strRepetation);

                        itemList.add(workModel);
                    }
                }catch (JSONException e){

                }
                setRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }



    public void setRecyclerView(){
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(workSuggestedAdaptor);
    }
}
