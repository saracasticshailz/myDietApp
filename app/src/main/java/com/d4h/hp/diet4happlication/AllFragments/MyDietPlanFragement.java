package com.d4h.hp.diet4happlication.AllFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.AllActivities.FoodDiary;
import com.d4h.hp.diet4happlication.AllActivities.NavigationDrawerActivity;
import com.d4h.hp.diet4happlication.AllAdaptors.MydietAdapter;
import com.d4h.hp.diet4happlication.AllDataModels.DietchartModel;
import com.d4h.hp.diet4happlication.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyDietPlanFragement extends Fragment {

    Context context;
    Button btnDietChart,btnFoodDiery,sun,mon,tue,wed,thu,fri,sat;
    Toolbar toolbar;
    private DietchartModel model;
    private RecyclerView.Adapter adapter;
    private List<DietchartModel> dietchartModels;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    String day;
    SharedPreferences loginPreferences;

    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_mydietplan,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnFoodDiery=view.findViewById(R.id.btn_fooddiery);
        btnDietChart=view.findViewById(R.id.btn_dietchart);
        recyclerView=view.findViewById(R.id.rc_mydiet);
        sun=view.findViewById(R.id.sun);
        mon=view.findViewById(R.id.mon);
        tue=view.findViewById(R.id.tue);
        wed=view.findViewById(R.id.wed);
        thu=view.findViewById(R.id.thu);
        fri=view.findViewById(R.id.fri);
        sat=view.findViewById(R.id.sat);
        layoutManager=new LinearLayoutManager(getContext());
        dietchartModels=new ArrayList<>();
        adapter=new MydietAdapter(getContext(),dietchartModels);

        DirectMyPlan();
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDay="1";
                view_dietChart(selectedDay);
                sun.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                mon.setBackgroundColor(getResources().getColor(R.color.lightGray));
                tue.setBackgroundColor(getResources().getColor(R.color.lightGray));
                wed.setBackgroundColor(getResources().getColor(R.color.lightGray));
                thu.setBackgroundColor(getResources().getColor(R.color.lightGray));
                fri.setBackgroundColor(getResources().getColor(R.color.lightGray));
                sat.setBackgroundColor(getResources().getColor(R.color.lightGray));

            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDay="2";
                view_dietChart(selectedDay);
                sun.setBackgroundColor(getResources().getColor(R.color.lightGray));
                mon.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                tue.setBackgroundColor(getResources().getColor(R.color.lightGray));
                wed.setBackgroundColor(getResources().getColor(R.color.lightGray));
                thu.setBackgroundColor(getResources().getColor(R.color.lightGray));
                fri.setBackgroundColor(getResources().getColor(R.color.lightGray));
                sat.setBackgroundColor(getResources().getColor(R.color.lightGray));

            }
        });
        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDay="3";
                view_dietChart(selectedDay);
                sun.setBackgroundColor(getResources().getColor(R.color.lightGray));
                mon.setBackgroundColor(getResources().getColor(R.color.lightGray));
                tue.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                wed.setBackgroundColor(getResources().getColor(R.color.lightGray));
                thu.setBackgroundColor(getResources().getColor(R.color.lightGray));
                fri.setBackgroundColor(getResources().getColor(R.color.lightGray));
                sat.setBackgroundColor(getResources().getColor(R.color.lightGray));

            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDay="4";
                view_dietChart(selectedDay);
                sun.setBackgroundColor(getResources().getColor(R.color.lightGray));
                mon.setBackgroundColor(getResources().getColor(R.color.lightGray));
                tue.setBackgroundColor(getResources().getColor(R.color.lightGray));
                wed.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                thu.setBackgroundColor(getResources().getColor(R.color.lightGray));
                fri.setBackgroundColor(getResources().getColor(R.color.lightGray));
                sat.setBackgroundColor(getResources().getColor(R.color.lightGray));

            }
        });
        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDay="5";
                view_dietChart(selectedDay);
                sun.setBackgroundColor(getResources().getColor(R.color.lightGray));
                mon.setBackgroundColor(getResources().getColor(R.color.lightGray));
                tue.setBackgroundColor(getResources().getColor(R.color.lightGray));
                wed.setBackgroundColor(getResources().getColor(R.color.lightGray));
                thu.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                fri.setBackgroundColor(getResources().getColor(R.color.lightGray));
                sat.setBackgroundColor(getResources().getColor(R.color.lightGray));

            }
        });
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDay="6";
                view_dietChart(selectedDay);
                sun.setBackgroundColor(getResources().getColor(R.color.lightGray));
                mon.setBackgroundColor(getResources().getColor(R.color.lightGray));
                tue.setBackgroundColor(getResources().getColor(R.color.lightGray));
                wed.setBackgroundColor(getResources().getColor(R.color.lightGray));
                thu.setBackgroundColor(getResources().getColor(R.color.lightGray));
                fri.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                sat.setBackgroundColor(getResources().getColor(R.color.lightGray));

            }
        });
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDay="7";
                view_dietChart(selectedDay);
                sun.setBackgroundColor(getResources().getColor(R.color.lightGray));
                mon.setBackgroundColor(getResources().getColor(R.color.lightGray));
                tue.setBackgroundColor(getResources().getColor(R.color.lightGray));
                wed.setBackgroundColor(getResources().getColor(R.color.lightGray));
                thu.setBackgroundColor(getResources().getColor(R.color.lightGray));
                fri.setBackgroundColor(getResources().getColor(R.color.lightGray));
                sat.setBackgroundColor(getResources().getColor(R.color.colorOrange));

            }
        });

        btnFoodDiery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnFoodDiery.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                btnDietChart.setBackgroundColor(getResources().getColor(R.color.colorGray));
                Intent i=new Intent(context,FoodDiary.class);
                startActivity(i);
            }
        });


        btnDietChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String curdate = sdf.format(new Date());*/



                btnDietChart.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                btnFoodDiery.setBackgroundColor(getResources().getColor(R.color.colorGray));

            }
        });

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle("My Diet Plan");



    }
    public void view_dietChart(String day){
        btnFoodDiery.setBackgroundColor(getResources().getColor(R.color.colorGray));
        btnDietChart.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        this.day=day;
        loginPreferences = context.getSharedPreferences("UserData", 0);
        String pt_id=loginPreferences.getString("patient_id","");
        //  Toast.makeText(context, ""+day, Toast.LENGTH_SHORT).show();
//String get_dieturl="http://uat.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_dietchart.php?"+"patient_id="+pt_id+"&day="+day;


       // https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_dietchart.php?patient_id=197&day=1

        String get_dieturl=" https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_dietchart.php?"+"patient_id="+pt_id+"&day="+day;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, get_dieturl,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Toast.makeText(context, ""+response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    dietchartModels.clear();
                    JSONArray out=response.getJSONArray("output");
                    for (int i=0;i<out.length();i++){
                        JSONObject object=out.getJSONObject(i);
                        String diet_time=object.getString("diet_time");
                        String quantity=object.getString("quantity");
                        String remark=object.getString("remark");
                        String meal=object.getString("meal");
                        String mealType=object.getString("mealType");

                        model=new DietchartModel();
                        model.setDc_mealtime(diet_time);
                        model.setDc_mealtype(mealType);

                        model.setDc_mealname1(meal);
                        model.setDc_quantity1(quantity);
                        model.setDc_remark1(remark);
                        dietchartModels.add(model);
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(context, "Diet Plan Network Error", Toast.LENGTH_SHORT).show();
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    message = "The data could not be found. Please try again after some time!!";
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        RequestQueue
                requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }

    public void setRecyclerView(){
        recyclerView.setLayoutManager(layoutManager);
        // recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
    public void DirectMyPlan(){
        btnDietChart.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        btnFoodDiery.setBackgroundColor(getResources().getColor(R.color.colorGray));
        Calendar calendar = Calendar.getInstance();
        int cur_day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (cur_day) {
            case Calendar.SUNDAY:
                day="1";
                view_dietChart(day);
                // Toast.makeText(context, ""+day, Toast.LENGTH_SHORT).show();
                sun.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                mon.setBackgroundColor(getResources().getColor(R.color.lightGray));
                tue.setBackgroundColor(getResources().getColor(R.color.lightGray));
                wed.setBackgroundColor(getResources().getColor(R.color.lightGray));
                thu.setBackgroundColor(getResources().getColor(R.color.lightGray));
                fri.setBackgroundColor(getResources().getColor(R.color.lightGray));
                sat.setBackgroundColor(getResources().getColor(R.color.lightGray));

                break;
            case Calendar.MONDAY:
                day="2";
                view_dietChart(day);
                //Toast.makeText(context, ""+day, Toast.LENGTH_SHORT).show();
                sun.setBackgroundColor(getResources().getColor(R.color.lightGray));
                mon.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                tue.setBackgroundColor(getResources().getColor(R.color.lightGray));
                wed.setBackgroundColor(getResources().getColor(R.color.lightGray));
                thu.setBackgroundColor(getResources().getColor(R.color.lightGray));
                fri.setBackgroundColor(getResources().getColor(R.color.lightGray));
                sat.setBackgroundColor(getResources().getColor(R.color.lightGray));
                break;
            case Calendar.TUESDAY:
                day="3";
                view_dietChart(day);
                // Toast.makeText(context, ""+day, Toast.LENGTH_SHORT).show();
                sun.setBackgroundColor(getResources().getColor(R.color.lightGray));
                mon.setBackgroundColor(getResources().getColor(R.color.lightGray));
                tue.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                wed.setBackgroundColor(getResources().getColor(R.color.lightGray));
                thu.setBackgroundColor(getResources().getColor(R.color.lightGray));
                fri.setBackgroundColor(getResources().getColor(R.color.lightGray));
                sat.setBackgroundColor(getResources().getColor(R.color.lightGray));
                break;
            case Calendar.WEDNESDAY:
                day="4";
                view_dietChart(day);
                // Toast.makeText(context, ""+day, Toast.LENGTH_SHORT).show();
                sun.setBackgroundColor(getResources().getColor(R.color.lightGray));
                mon.setBackgroundColor(getResources().getColor(R.color.lightGray));
                tue.setBackgroundColor(getResources().getColor(R.color.lightGray));
                wed.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                thu.setBackgroundColor(getResources().getColor(R.color.lightGray));
                fri.setBackgroundColor(getResources().getColor(R.color.lightGray));
                sat.setBackgroundColor(getResources().getColor(R.color.lightGray));
                break;
            case Calendar.THURSDAY:
                day="5";
                view_dietChart(day);
                //Toast.makeText(context, ""+day, Toast.LENGTH_SHORT).show();
                sun.setBackgroundColor(getResources().getColor(R.color.lightGray));
                mon.setBackgroundColor(getResources().getColor(R.color.lightGray));
                tue.setBackgroundColor(getResources().getColor(R.color.lightGray));
                wed.setBackgroundColor(getResources().getColor(R.color.lightGray));
                thu.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                fri.setBackgroundColor(getResources().getColor(R.color.lightGray));
                sat.setBackgroundColor(getResources().getColor(R.color.lightGray));
                break;
            case Calendar.FRIDAY:
                day="6";
                view_dietChart(day);
                //Toast.makeText(context, ""+day, Toast.LENGTH_SHORT).show();
                sun.setBackgroundColor(getResources().getColor(R.color.lightGray));
                mon.setBackgroundColor(getResources().getColor(R.color.lightGray));
                tue.setBackgroundColor(getResources().getColor(R.color.lightGray));
                wed.setBackgroundColor(getResources().getColor(R.color.lightGray));
                thu.setBackgroundColor(getResources().getColor(R.color.lightGray));
                fri.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                sat.setBackgroundColor(getResources().getColor(R.color.lightGray));
                break;
            case Calendar.SATURDAY:
                day="7";
                view_dietChart(day);
                sun.setBackgroundColor(getResources().getColor(R.color.lightGray));
                mon.setBackgroundColor(getResources().getColor(R.color.lightGray));
                tue.setBackgroundColor(getResources().getColor(R.color.lightGray));
                wed.setBackgroundColor(getResources().getColor(R.color.lightGray));
                thu.setBackgroundColor(getResources().getColor(R.color.lightGray));
                fri.setBackgroundColor(getResources().getColor(R.color.lightGray));
                sat.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                // Toast.makeText(context, ""+day, Toast.LENGTH_SHORT).show();
                break;
        }
    }

}