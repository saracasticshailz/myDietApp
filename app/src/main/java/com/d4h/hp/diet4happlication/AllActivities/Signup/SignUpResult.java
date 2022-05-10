package com.d4h.hp.diet4happlication.AllActivities.Signup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllActivities.Plan.ClinicalPlanActivity;
import com.d4h.hp.diet4happlication.AllActivities.Plan.WeightGainActivity;
import com.d4h.hp.diet4happlication.AllActivities.Plan.WeightLossActivity;
import com.d4h.hp.diet4happlication.AllActivities.PlanListActivity;
import com.d4h.hp.diet4happlication.AllActivities.Signup.SignupName;
import com.d4h.hp.diet4happlication.R;

public class SignUpResult extends AppCompatActivity {
    TextView extraWt,IdealWt,bmi,Tv_havto,txtview_bmi_hi,txtview_bmi_underweight;
    SharedPreferences signUpprefer;
    Button getplanbtn;
    ImageView Iv_Result;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        signUpprefer.edit().clear();
        startActivity(new Intent(this,SignupName.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup_result);
        extraWt=findViewById(R.id.extraWt);
        IdealWt=findViewById(R.id.IdealWt);
        bmi=findViewById(R.id.bmi);
        Tv_havto=findViewById(R.id.Tv_havto);
        Iv_Result=findViewById(R.id.Iv_Result);
        txtview_bmi_hi=findViewById(R.id.txtview_bmi_hi);
        txtview_bmi_underweight=findViewById(R.id.txtview_bmi_underweight);

        signUpprefer = getSharedPreferences("SignUpData", 0);
        //String b=signUpprefer.getString("SignupResult","");
        getplanbtn=findViewById(R.id.getplan);
        String wt=signUpprefer.getString("loosewtString","");

        IdealWt.setText(signUpprefer.getString("signupidl",""));
        bmi.setText(signUpprefer.getString("signupBmi",""));
        extraWt.setText(signUpprefer.getString("loosewtVAl","")+"kgs");
        Tv_havto.setText(signUpprefer.getString("loosewtString",""));
        txtview_bmi_hi.setText("Hi..."+signUpprefer.getString("signupName","")+" you look");
        txtview_bmi_underweight.setText(signUpprefer.getString("SignupResult",""));

        getplanbtn.setText("Dear "+signUpprefer.getString("signupName","")+"...Get All Plans Here!");






        if(wt.equals("You have to gain")){
            //for resultd gain

            Iv_Result.setImageResource(R.drawable.bannerweightgain);
            Iv_Result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),WeightGainActivity.class));
                }
            });
        }else

        if(wt.equals("You have to loose")){
            //for results for gain
            Iv_Result.setImageResource(R.drawable.bannerweightloss);
            Iv_Result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),WeightLossActivity.class));
                }
            });

        }else

        if(wt.equals("Congratulation! You are Fit!")){
            Iv_Result.setImageResource(R.drawable.bannerclinical);
            Iv_Result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),ClinicalPlanActivity.class));
                }
            });
        }






        getplanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PlanListActivity.class));
            }
        });






    }

}