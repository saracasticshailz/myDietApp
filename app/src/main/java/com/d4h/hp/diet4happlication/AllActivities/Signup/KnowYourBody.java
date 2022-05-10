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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.R;

import org.json.JSONObject;

public class KnowYourBody extends AppCompatActivity {


    EditText kyb_height, kyb_weight, kyb_age;
    RadioGroup rg;
    RadioButton rbYes,rbNo;
    Button submitkyb;
    String gender="";
    String zz;
    private String strheight, strweight, strAge, strName, strnumber;
    SharedPreferences signUpprefer;
    float gen=0;
    RadioButton malebtn;
    float idealwt;
    float weight;
    String x;
    float loosewt=0;
    RequestQueue requestQueue;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        signUpprefer.edit().clear();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_know_ur_body);
        kyb_height = findViewById(R.id.kyb_height);
        kyb_weight = findViewById(R.id.kyb_weight);
        kyb_age = findViewById(R.id.kyb_age);
        malebtn=findViewById(R.id.female);
        rg = findViewById(R.id.rg);

        submitkyb = findViewById(R.id.submitkyb);


        strAge = kyb_age.getText().toString();

        signUpprefer = getSharedPreferences("SignUpData", 0);


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.male:
                        gender = "Male";
                        gen=100;
                        break;
                    case R.id.female:
                        gender = "Female";
                        gen=105;
                        break;
                }
            }
        });


        submitkyb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strheight = "";
                strweight = "";
                if(TextUtils.isEmpty(kyb_height.getText())){
                    kyb_height.setError("Please Enter height");

                }else if(TextUtils.isEmpty(kyb_weight.getText())){
                    kyb_weight.setError("Please Enter Weight");
                }else
                if(gender.equals("")){
                    malebtn.setError("please select gender!");

                }else {
                    float height = Float.valueOf(kyb_height.getText().toString());
                    weight = Float.valueOf(kyb_weight.getText().toString());

                    //getting bmi
                    float bmi = (100 * 100 * weight) / (height * height);
                    String StrBmi = String.format("%.2f", bmi);
                    //gettin interpret bmi
                    final String result = interpretBMI(bmi);
                    //getting l
                    idealwt = (idealwts(height, gen));
                    float lwt = loose(weight, idealwt);

                    SharedPreferences.Editor editor = signUpprefer.edit();
                    editor.putString("signupidl", String.valueOf(idealwt));
                    editor.putString("signupBmi", StrBmi);
                    editor.putString("SignupResult", result);
                    editor.putString("loosewtString", x);
                    editor.putString("loosewtVAl", String.valueOf(lwt));
                    editor.putString("gender", gender);
                    editor.putString("height", kyb_height.getText().toString());
                    editor.putString("age", strAge);
                    editor.putString("weight", String.valueOf(weight));
                    editor.commit();
                    requestQueue=Volley.newRequestQueue(getApplicationContext());

                    String url="http://192.168.163.1/d4h_api_v1.1/handler_signup.php?"+
                            "bmi="+StrBmi+
                            "&weight="+String.valueOf(lwt)+
                            "&height="+kyb_height.getText().toString()+
                            "&age=" +kyb_age.getText().toString()+
                            "&gender="+gender+
                            "&signUpid="+signUpprefer.getString("signUpid","");

                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(KnowYourBody.this, ""+result.toString(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), SignUpResult.class);
                                    startActivity(intent);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                    requestQueue.add(jsonObjectRequest);




                }
            }
        });


    }


    private String interpretBMI(float bmiValue) {

        if (bmiValue < 16) {
            return "Severely underweight";

        } else if (bmiValue < 18.5) {

            return "Underweight";
        } else if (bmiValue < 25) {

            return "Normal";
        } else if (bmiValue < 30) {

            return "Overweight";
        } else if (bmiValue < 35) {

            return "Obese Class 1 ";
        } else if (bmiValue < 40) {

            return "Obese Class 2";
        } else  {

            return "obese Class 3";
        }
    }

    private  float idealwts(float height,float genwise){
        float Imb=height-genwise;
        return Imb;

    }
    private float loose(float weight,float idealwt){
        if(weight<idealwt){
            loosewt=idealwt-weight;
            x="You have to gain";
            return loosewt;
        }else if(weight>idealwt){
            loosewt=weight-idealwt;
            x="You have to loose";
            return  loosewt;

        }else{
            x="Congratulation! You are Fit!";
            return loosewt;
        }
    }
}