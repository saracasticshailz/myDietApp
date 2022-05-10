package com.d4h.hp.diet4happlication.AllActivities;

import android.content.SharedPreferences;
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
import com.d4h.hp.diet4happlication.AllDataModels.EscallationAnswerModel;
import com.d4h.hp.diet4happlication.AllDataModels.EscallationModel;

import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

public class EscallationAnswerActivity extends AppCompatActivity {

    TextView txtviewSubject,txtviewIssue,txtviewAnswer;
    String pt_id;
    SharedPreferences sharedPreferences;
    EscallationAnswerModel escallationModel;
    EscallationModel escaModel;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_esc_answer);

        txtviewSubject=findViewById(R.id.txtview_esc_sub);
        txtviewIssue=findViewById(R.id.txtview_esc_issue);
        txtviewAnswer=findViewById(R.id.txtview_esc_ans);
        toolbar=(Toolbar)findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Answer By Admin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ecsDisplayData();

       /* escaModel=new EscallationModel();
        String id=escaModel.getEscId();*/
       // int  id =Integer.parseInt(escaModel.getEscId());

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
    }

    public void ecsDisplayData(){

        Bundle extras = getIntent().getExtras();
        String test=extras.getString("test_id");
       // int id=Integer.parseInt(test);
        sharedPreferences = this.getSharedPreferences("UserData", 0);
        pt_id=sharedPreferences.getString("patient_id","");


        //https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/view_escalation.php?testimonial_id=xxxxx

        String url="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/view_escalation.php?testimonial_id="+test;
        final RequestQueue requestQueue=Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                     //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                     JSONObject data = response.getJSONObject("output");
                       // String strTest_id = data.getString("testimonial_id");
                        String strIssue = data.getString("testimonial");
                        String strSubject = data.getString("subject");
                        String strAnswer = data.getString("reply_by");
                        escallationModel=new EscallationAnswerModel();

                        escallationModel.setStrSubject(strSubject);
                        escallationModel.setStrIssue(strIssue);
                        escallationModel.setStrAnswer(strAnswer);

                        txtviewSubject.setText(escallationModel.getStrSubject());
                        txtviewIssue.setText(escallationModel.getStrIssue());
                        txtviewAnswer.setText(escallationModel.getStrAnswer());


                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(), "Escallation Network Error", Toast.LENGTH_SHORT).show();
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
        requestQueue.add(jsonObjectRequest);
    }
}
