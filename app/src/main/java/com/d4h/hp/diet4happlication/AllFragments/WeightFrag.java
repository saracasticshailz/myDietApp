package com.d4h.hp.diet4happlication.AllFragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.d4h.hp.diet4happlication.AllAdaptors.WeightAdapter;
import com.d4h.hp.diet4happlication.AllDataModels.WeightModel;
import com.d4h.hp.diet4happlication.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeightFrag extends Fragment {
    EditText W_ed;
    Button W_sub,W_cal;
    SharedPreferences loginpreference;
    private Context context;
    private int mDay,mMonth,mYear;
    private List<WeightModel> weightmodels;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private WeightAdapter adapter;
    private WeightModel models;


    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_weight, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        W_cal=view.findViewById(R.id.btn_calender_weight);
        W_sub=view.findViewById(R.id.btn_weightlog);
        W_ed=view.findViewById(R.id.edittext_weight);
        recyclerView=view.findViewById(R.id.rc_weigh);
        layoutManager=new LinearLayoutManager(getContext());
        W_ed.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);





        // RequestQueue requestQueue=Volley.newRequestQueue(context);
        weightmodels=new ArrayList<>();
        Calendar myCalendar=Calendar.getInstance();
        mDay=myCalendar.get(Calendar.DATE);
        mMonth=myCalendar.get(Calendar.MONTH);
        mYear=myCalendar.get(Calendar.YEAR);
        adapter=new WeightAdapter(getContext(),weightmodels);
          getWeight();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String curd = sdf.format(new Date());

        W_cal.setText(curd);
        W_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date();
            }
        });
        W_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendweight();
            }
        });


    }
    public void sendweight(){
        loginpreference =getContext().getSharedPreferences("UserData", 0);
        String p=loginpreference.getString("patient_id","");

        /*http://uat.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_weight.php?action=add"+
        "&patient_id="+p+"&date="+W_cal.getText().toString()+"&weight="+W_ed.getText().toString();*/

        String WURL="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_weight.php?action=add"+
                "&patient_id="+p+"&date="+W_cal.getText().toString()+"&weight="+W_ed.getText().toString();

        // Toast.makeText(context, ""+p, Toast.LENGTH_SHORT).show();
        // Toast.makeText(context, ""+W_ed.getText().toString(), Toast.LENGTH_SHORT).show();
        // Toast.makeText(context, ""+p, Toast.LENGTH_SHORT).show();
        if(W_cal.getText().toString().equals("calender")){
            Toast.makeText(context, "please Enter appropriate date! ", Toast.LENGTH_SHORT).show();
        }else if(W_ed.getText().toString().equals("")){
            Toast.makeText(context, "please Enter Weight! ", Toast.LENGTH_SHORT).show();

        }else {


            JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, WURL,
                    null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    W_ed.setText("");
                    //Toast.makeText(getContext(), "" + response.toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Sucessfully Enter" , Toast.LENGTH_SHORT).show();
                    getWeight();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                   // Toast.makeText(context, "Weight Log NetWork Error" , Toast.LENGTH_SHORT).show();
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
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(jsonObjectRequest1);
        }
    }
    public void date() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        W_cal.setText(day + "-" + (month + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        //  datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    public void getWeight(){
        loginpreference =getContext().getSharedPreferences("UserData", 0);
        String p=loginpreference.getString("patient_id","");


       // http://uat.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_weight.php?action=view&patient_id=197";
        String GetW_Url="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_weight.php?action=view&"+"patient_id="+p;

        final JsonObjectRequest obj=new JsonObjectRequest(Request.Method.GET, GetW_Url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    weightmodels.clear();

                    JSONArray out=response.getJSONArray("output");

                    for(int i=0;i<out.length();i++){
                        JSONObject jsonObject=out.getJSONObject(i);

                        String bmi_count=jsonObject.getString("bmi");
                        String created_date=jsonObject.getString("date");
                        String weight=jsonObject.getString("weight");

                        models = new WeightModel();
                        models.setWbmi(bmi_count);
                        models.setWdate(created_date);
                        models.setWweight(weight);
                        weightmodels.add(models);
                    }

                    adapter.notifyDataSetChanged();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                setRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Weight log NetWork Error", Toast.LENGTH_SHORT).show();
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
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
        RequestQueue requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(obj);
    }
    public void setRecyclerView(){
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}