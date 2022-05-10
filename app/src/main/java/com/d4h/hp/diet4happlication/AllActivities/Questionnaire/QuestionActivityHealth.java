package com.d4h.hp.diet4happlication.AllActivities.Questionnaire;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class QuestionActivityHealth extends AppCompatActivity {

    Spinner spinnerMedicine,spinnerDigestive;
    EditText edittextMedical,edittextOther,edittextDigestive,edittextMHistory,editetxtBlood,edittextSurgery,edittextReamrk;
    Button btnNext;
    String strspinner,strspinner2,str;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_questionnaire3);

        spinnerMedicine=findViewById(R.id.spinner_health_medicine);
        spinnerDigestive=findViewById(R.id.spinner_health_desease);
        edittextMedical=findViewById(R.id.editext_health_suffer);
        edittextOther=findViewById(R.id.edittext_health_dother);
        edittextDigestive=findViewById(R.id.editext_health_invisti);
        edittextMHistory=findViewById(R.id.edittxt_health_mhistory);
        editetxtBlood=findViewById(R.id.edittext_health_blood);
        edittextSurgery=findViewById(R.id.editext_health_surgery);
        edittextReamrk=findViewById(R.id.editext_health_remark);
        btnNext=findViewById(R.id.btn_health_next);
        toolbar=findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Health");
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


        edittextOther.setVisibility(View.GONE);
        spinnerMedicine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(
                    AdapterView<?> adapterView, View view,
                    int i, long l) {
                strspinner = spinnerMedicine.getSelectedItem().toString().trim();
                if(strspinner.equals("None")){
                    edittextMedical.setVisibility(View.GONE);
                }else{
                    edittextMedical.setVisibility(View.VISIBLE);
                }
                //   Toast.makeText(context, "spinner"+strTime, Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

       spinnerDigestive.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               strspinner2=spinnerDigestive.getSelectedItem().toString().trim();
               if(strspinner2.equals("Other")){
                    edittextOther.setVisibility(View.VISIBLE);
               }else{
                   edittextOther.setVisibility(View.GONE);
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

    }
}
