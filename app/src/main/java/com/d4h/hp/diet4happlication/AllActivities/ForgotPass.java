package com.d4h.hp.diet4happlication.AllActivities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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

import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgotPass extends AppCompatActivity {

    RequestQueue requestQueue;
    EditText forgot_pass_mail;
    private Button btn_mail;
    String mailString;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_forgotpass);
        forgot_pass_mail=findViewById(R.id.forgot_pass);
        btn_mail=findViewById(R.id.mail_btn);
        toolbar=(Toolbar) findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailString=forgot_pass_mail.getText().toString();

                //https://www.diet4health.in/public/diet4health.in/d4h_api/index.php?module_name=reset_pass&user_email=ashish3.jain@gmail.com
                String change_passURL=
                        // http://uat.diet4health.in/public/diet4health.in/d4h_api/index.php?module_name=reset_pass&user_email=xxxxxxxxxx
                        "https://www.diet4health.in/public/diet4health.in/d4h_api/index.php?module_name=reset_pass"+"&user_email="+mailString;
                requestQueue=Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, change_passURL, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject out=response.getJSONObject("output");
                                    String msg=out.getString("message");
                                    if(msg.equals("Invalid Email")){
                                        forgot_pass_mail.setError("Invalid Email");
                                    }else {
                                        Toast.makeText(ForgotPass.this, "Your Password has been sent to your Email...", Toast.LENGTH_LONG).show();
                                        onBackPressed();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                               // Toast.makeText(ForgotPass.this, "success"+response.toString(), Toast.LENGTH_SHORT).show();
                                // startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                // finish();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(ForgotPass.this, "Forget Password Network Error", Toast.LENGTH_SHORT).show();
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

    }


}
