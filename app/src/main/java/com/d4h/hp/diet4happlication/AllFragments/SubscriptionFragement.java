package com.d4h.hp.diet4happlication.AllFragments;

import android.content.Context;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.AllActivities.NavigationDrawerActivity;
import com.d4h.hp.diet4happlication.AllAdaptors.SubcriptionAdaptor;
import com.d4h.hp.diet4happlication.AllDataModels.SubcriptionModel;
import com.d4h.hp.diet4happlication.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionFragement extends Fragment {

    private Context context;
    private String pt_id;
    SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<SubcriptionModel> listSubcription;
    private RecyclerView.Adapter adapter;
    private SubcriptionModel model;
    private String strPlanName,strDuration,strSubOver,strPaidAmt,strPayId;


    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_subcription,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(
                "SubCription");

        recyclerView=view.findViewById(R.id.rv_subcription);
        listSubcription=new ArrayList<>();
        adapter=new SubcriptionAdaptor(listSubcription,context);
        layoutManager=new LinearLayoutManager(context);

        showSubcription();
    }

    public void showSubcription(){

        sharedPreferences = context.getSharedPreferences("UserData", 0);
        pt_id=sharedPreferences.getString("patient_id","");

       // https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_subscription.php?patient_id=197
        String url="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_subscription.php?patient_id="+pt_id;

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Toast.makeText(context, ""+response.toString(), Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray=response.getJSONArray("output");
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        strPlanName=jsonObject.getString("plan_name");
                        strDuration=jsonObject.getString("duration");
                        strSubOver=jsonObject.getString("plan_end_date");
                        strPaidAmt=jsonObject.getString("paid_amount");
                        strPayId=jsonObject.getString("payment_id");

                        model = new SubcriptionModel();
                        model.setSubPaidAmt(strPaidAmt);
                        model.setSubDuration(strDuration);
                        model.setSubOver(strSubOver);
                        model.setSubPlanName(strPlanName);
                        model.setSubPayId(strPayId);
                        listSubcription.add(model);

                    } adapter.notifyDataSetChanged();
                }catch (JSONException e){

                }
                setRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Subcription Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }

    public void setRecyclerView(){
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
