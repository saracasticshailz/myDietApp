package com.d4h.hp.diet4happlication.AllActivities;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
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

import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Feedback extends AppCompatActivity {

    TextView fbEndDate,fbStartDate,fbweightStatus,fb_energyLvl,fb_stress,fb_motion,fb_digCom,fb_fevr,fb_medName,fb_menses,
            fe_NoMenses,fb_suit,fb_dailyrtnChange,fb_weightCompare,fb_score,fb_NFD,fb_NDE,fb_view_meal_time,
            fb_view_meal_date,fb_view_meal
            ,fb_nuticoach_name,fb_doc_com;
    String viewString;
    String ned_status;
    String question3;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_view_feedback);

        //Toast.makeText(this, ""+viewString, Toast.LENGTH_SHORT).show();


        fbEndDate = findViewById(R.id.fb_endDt);
        fbStartDate = findViewById(R.id.fb_startDt);
        fbweightStatus = findViewById(R.id.fb_weightStatus);
        fb_energyLvl = findViewById(R.id.fb_energyLvl);
        fb_stress = findViewById(R.id.fb_stress);
        fb_motion = findViewById(R.id.fb_motion);
        fb_digCom = findViewById(R.id.fb_digCom);
        fb_fevr = findViewById(R.id.fb_fevr);
        fb_medName = findViewById(R.id.fb_medName);
        fb_menses = findViewById(R.id.fb_menses);
        fe_NoMenses = findViewById(R.id.fe_NoMenses);
        fb_suit = findViewById(R.id.fb_suit);
        fb_dailyrtnChange = findViewById(R.id.fb_dailyrtnChange);
        fb_weightCompare = findViewById(R.id.fb_weightCompare);
        fb_score = findViewById(R.id.fb_score);
        fb_NFD = findViewById(R.id.fb_NFD);
        fb_NDE = findViewById(R.id.fb_NDE);
        fb_view_meal_time = findViewById(R.id.fb_view_meal_time);
        //fb_view_meal_date=findViewById(R.id.fb_view_meal_date);
        fb_view_meal = findViewById(R.id.fb_view_meal);
        fb_nuticoach_name = findViewById(R.id.fb_nuticoach_name);
        fb_doc_com = findViewById(R.id.fb_doc_com);

        toolbar=findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Weekely Review");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setTintColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }


        viewData();
    }
    public void viewData(){

        Bundle extras = getIntent().getExtras();
        viewString = extras.getString("view_id");
         final RequestQueue requestQueue=Volley.newRequestQueue(this);
        String getFeed="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/fetch_review_report.php?"
                +"reviewId="+viewString;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, getFeed,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(Feedback.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONArray output=response.getJSONArray("output");
                    for(int i=0;i<output.length();i++){
                        JSONObject jsonObject=output.getJSONObject(i);

                        String start_date=jsonObject.getString("start_date");
                        fbStartDate.setText(start_date);

                        String end_date=jsonObject.getString("end_date");
                        fbEndDate.setText(end_date);

                        String weight_status=jsonObject.getString("weight_status");
                        fbweightStatus.setText(weight_status);

                        String energy_level=jsonObject.getString("energy_level");
                        fb_energyLvl.setText(energy_level);

                        String stress=jsonObject.getString("stress");
                        fb_stress.setText(stress);

                        String motion=jsonObject.getString("motion");
                        fb_motion.setText(motion);

                        String digestive_complaints=jsonObject.getString("digestive_complaints");
                        fb_digCom.setText(digestive_complaints);

                        String is_fever=jsonObject.getString("is_fever");
                        fb_fevr.setText(is_fever);

                        String fever_no_days=jsonObject.getString("fever_no_days");

                        String is_medicine_taken_in_fever=jsonObject.getString("is_medicine_taken_in_fever");

                        String medicine_in_fever=jsonObject.getString("medicine_in_fever");
                        fb_medName.setText(medicine_in_fever);

                        String is_menses=jsonObject.getString("is_menses");
                        fb_menses.setText(is_menses);

                        String menses_days=jsonObject.getString("menses_days");
                        fe_NoMenses.setText(menses_days);

                        String is_diet_suitable_for_you=jsonObject.getString("is_diet_suitable_for_you");
                        fb_suit.setText(is_diet_suitable_for_you);

                        String will_your_daily_routine_same=jsonObject.getString("will_your_daily_routine_same");


                        String daily_routine_chang=jsonObject.getString("daily_routine_chang");
                        fb_dailyrtnChange.setText(daily_routine_chang);

                        String question1=jsonObject.getString("question1");

                        String question2=jsonObject.getString("question2");

                         question3=jsonObject.getString("weight_compare");
                        fb_weightCompare.setText(question3);

                        String meal_qty=jsonObject.getString("meal_qty");

                        String nfd_status=jsonObject.getString("nfd_status");
                        fb_NFD.setText(nfd_status);

                         ned_status=jsonObject.getString("ned_status");
                        fb_NDE.setText(ned_status);


                        String overall_score=jsonObject.getString("overall_score");
                        fb_score.setText(overall_score);

                        String meal=jsonObject.getString("meal");
                        fb_view_meal.setText(meal);

                        String meal_time=jsonObject.getString("meal_time");
                        fb_view_meal_time.setText(meal_time);

                        String nutricoach=jsonObject.getString("nutricoach");
                        fb_nuticoach_name.setText(nutricoach);

                        String doctor_comment=jsonObject.getString("doctor_comment");
                        fb_doc_com.setText(doctor_comment);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    message = "The data could not be found. Please try again after some time!!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Toast.makeText(this, ""+question3, Toast.LENGTH_SHORT).show();
        requestQueue.add(jsonObjectRequest);



    }
}
