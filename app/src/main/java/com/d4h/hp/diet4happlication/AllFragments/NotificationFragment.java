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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.AllActivities.NavigationDrawerActivity;
import com.d4h.hp.diet4happlication.AllAdaptors.NotificationAdaptor;
import com.d4h.hp.diet4happlication.AllDataModels.NotificationModel;
import com.d4h.hp.diet4happlication.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment {


    private List<NotificationModel> itemList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    NotificationModel model;
    NotificationAdaptor notificationAdaptor;
    Context context;
    SharedPreferences sharedPreferences;
    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_notification,container,false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView=view.findViewById(R.id.rv_notification);
        itemList=new ArrayList<>();
        notificationAdaptor=new NotificationAdaptor(itemList,context);
        layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(
                "Notification");
        sharedPreferences = context.getSharedPreferences("UserData", 0);
        String pt_id=sharedPreferences.getString("patient_id","");
        String notURI="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/dietchart_notification.php?"+"patient_id="+pt_id;
        RequestQueue mqz = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, notURI,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(context, ""+response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONArray output=response.getJSONArray("output");
                    int i;
                    for( i=0;i<output.length();i++){
                        JSONObject jsonObject=output.getJSONObject(i);
                        String notification=jsonObject.getString("notification");
                        String notification_date=jsonObject.getString("notification_date");
                        String notification_time=jsonObject.getString("notification_time");
                        model=new NotificationModel();
                        model.setNotiContent(notification);
                        model.setNotiTime(notification_date);
                        itemList.add(model);
                    }

                    notificationAdaptor.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mqz.add(jsonObjectRequest);

    }



    public void setRecyclerView(){
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(notificationAdaptor);
    }
}