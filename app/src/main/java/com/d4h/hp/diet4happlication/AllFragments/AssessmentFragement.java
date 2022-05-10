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
import com.d4h.hp.diet4happlication.AllAdaptors.AssessmentAdaptor;
import com.d4h.hp.diet4happlication.AllDataModels.AssesmentModel;
import com.d4h.hp.diet4happlication.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AssessmentFragement extends Fragment {

    private Context context;
    private String pt_id;
    SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<AssesmentModel> listAsseemnt;
    private RecyclerView.Adapter adapter;
    private AssesmentModel model;
    private String strDate,strWeight,strBodyFats,strBodyAge,strRest,strViscerl,strBmi,strWaist,strHips,strRemark;

    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_assesment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.rv_assesment);
        listAsseemnt=new ArrayList<>();
        adapter=new AssessmentAdaptor(listAsseemnt,context);
        layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(
                "Assesment");
        addAssesment();

    }
    public void addAssesment(){

        sharedPreferences = context.getSharedPreferences("UserData", 0);
        pt_id=sharedPreferences.getString("patient_id","");

       // http://uat.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_assestment.php?patient_id="+pt_id;
        String url=" https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_assestment.php?patient_id="+pt_id;

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                   //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray=response.getJSONArray("output");
                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        strDate=jsonObject.getString("Checkup_Date");
                        strWaist=jsonObject.getString("Waist");
                        strWeight=jsonObject.getString("weight");
                        strViscerl=jsonObject.getString("fat_level");
                        strRest=jsonObject.getString("metabolism");
                        strRemark=jsonObject.getString("remark");
                        strHips=jsonObject.getString("Hips");
                        strBodyAge=jsonObject.getString("body_age");
                        strBmi=jsonObject.getString("bmi");
                        strBodyFats=jsonObject.getString("body_fat");
                       // Toast.makeText(context, ""+strRemark, Toast.LENGTH_SHORT).show();

                       // strRemark=strRemark.replace(" ","+");
                        model=new AssesmentModel();
                        model.setAssBmi(strBmi);
                        model.setAssBodyFats(strBodyFats);
                        model.setAssBosyAge(strBodyAge);
                        model.setAssDate(strDate);
                        model.setAssHips(strHips);
                        model.setAssMetabolism(strRest);
                        model.setAssRemark(strRemark);
                        model.setAssVisceral(strViscerl);
                        model.setAssWaist(strWaist);
                        model.setAssWeight(strWeight);

                        listAsseemnt.add(model);
                    }
                }catch (JSONException e){


                }
                    setRecyclerView();
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Assesment Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(context);
        requestQueue.add(jsonObjectRequest);
    }

    public void setRecyclerView(){
        recyclerView.setLayoutManager(layoutManager);
        // recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
