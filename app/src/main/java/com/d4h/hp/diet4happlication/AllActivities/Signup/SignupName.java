package com.d4h.hp.diet4happlication.AllActivities.Signup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.AllActivities.LoginActivity;
import com.d4h.hp.diet4happlication.R;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupName extends AppCompatActivity {
    EditText signupFirstName,signupNum,signupLastName;
    SharedPreferences signUpprefer;
    Button signupnameBtn;
    RequestQueue requestQueue;
    ProgressBar progressBar;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        signUpprefer.edit().clear();

        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup_name);
        progressBar = (ProgressBar) findViewById(R.id.id_progressbarlogin);

        signupNum=findViewById(R.id.signupnum);
        signupFirstName=findViewById(R.id.signupFirstname);
        signupLastName=findViewById(R.id.signupLastname);
        signupnameBtn=findViewById(R.id.signupnameBtn);


        signUpprefer = getSharedPreferences("SignUpData", 0);


        signupnameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(signupFirstName.getText().toString()) ){
                    signupFirstName.setError("Your Name Please!");

                }else if( TextUtils.isEmpty(signupNum.getText().toString()))
                {
                    signupNum.setError("Your Number Please!");

                }else if(signupNum.getText().toString().length()< 10 || signupNum.getText().toString().length()>10){
                    signupNum.setError("Your valid number Please!");
                }
                else{
                    Toast.makeText(SignupName.this, "success", Toast.LENGTH_SHORT).show();
                    final SharedPreferences.Editor editor = signUpprefer.edit();

                    editor.putString("signupFirstName", signupFirstName.getText().toString());
                    editor.putString("signupLastName", signupLastName.getText().toString());
                    editor.putString("signupNum", signupNum.getText().toString());

                    editor.commit();
                    String url="http://192.168.163.1/d4h_api_v1.1/handler_signup.php?"+"name="+
                            signupFirstName.getText().toString()+"&number="+signupNum.getText().toString();
                    requestQueue=Volley.newRequestQueue(getApplicationContext());
                    JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, url,
                            null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Toast.makeText(SignupName.this, ""+response.toString(), Toast.LENGTH_SHORT).show();
                                JSONObject jsonObject=response.getJSONObject("output");
                                String signUpid=jsonObject.getString("signUpid");
                                editor.putString("signUpid",signUpid);
                                editor.commit();
                               startActivity(new Intent(getApplicationContext(),KnowYourBody.class));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    requestQueue.add(objectRequest);

                }

            }
        });



    }

}
