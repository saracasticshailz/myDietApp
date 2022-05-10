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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class QuestionLifeStyle extends AppCompatActivity {

    Spinner spinnerLifeStyle,spinnerReasonAlcho,spinnerSleepPattern,spinnerStress,spinnerReasonSmoke;
    EditText editTextAlchoYes,editTextalchoOther,editTextSleepCyle,editTextNightShift,editTextNightShiftYes,editTextStressYes,
    editTextTravelling,editTextTravellingLocation,editTextSmoke,editTextSmokeReason;
    RadioButton radioButtonAlhoYes,radioButtonAlchoNo,radioButtonShiftYes,radioButtonShiftNo,radioButtonTravellingYes,radioButtonTravellingNo,
    radioButtonSmokeYes,radioButtonSmokeNo;
    RadioGroup radioGroupAlcho,radioGroupShift,radioGroupTravelling,radioGroupSmoke;
    TextView txtviewNightShift,txtviewTravelling,txtviewSmoke,txtviewAlcho,txtviewSmokes;
    String strReasonAlcho,strSleepPattern,strStress,strReasonSmoke;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_questionnaire5);
        spinnerLifeStyle=findViewById(R.id.spinner_life_style);
        spinnerReasonAlcho=findViewById(R.id.spinner_life_reasonalcho);
        spinnerSleepPattern=findViewById(R.id.spinner_life_sleeping_pattern);
        spinnerStress=findViewById(R.id.spinner_life_stress);
        spinnerReasonSmoke=findViewById(R.id.spinner_reason_smoke);
        editTextAlchoYes=findViewById(R.id.edittext_life_alcho);
        editTextalchoOther=findViewById(R.id.edittext_life_alcho_other);
        editTextSleepCyle=findViewById(R.id.edittext_life_sleepingtime);
        editTextNightShiftYes=findViewById(R.id.edittext_life_night_yes);
        editTextNightShift=findViewById(R.id.edittext_night_shift);
        editTextStressYes=findViewById(R.id.edittext_office_time);
        editTextTravelling=findViewById(R.id.edittext_travelling);
        editTextTravellingLocation=findViewById(R.id.edittext_travelling_location);
        editTextSmoke=findViewById(R.id.edittext_frequently_use);
        editTextSmokeReason=findViewById(R.id.edittext_other_reason);
        radioGroupAlcho=findViewById(R.id.rg_life_alchohol);
        radioGroupShift=findViewById(R.id.rg_life_night_shift);
        radioGroupTravelling=findViewById(R.id.rg_life_travelling_issue);
        radioGroupSmoke=findViewById(R.id.rg_smoke);
        radioButtonAlhoYes=findViewById(R.id.rb_life_alcho_yes);
        radioButtonAlchoNo=findViewById(R.id.rb_life_alcho_no);
        radioButtonShiftYes=findViewById(R.id.rb_life_night_yes);
        radioButtonShiftNo=findViewById(R.id.rb_life_night_no);
        radioButtonTravellingYes=findViewById(R.id.rb_life_travelling_yes);
        radioButtonTravellingNo=findViewById(R.id.rb_life_travelling_no);
        radioButtonSmokeYes=findViewById(R.id.rb_smoke_yes);
        radioButtonSmokeNo=findViewById(R.id.rb_smoke_no);
        txtviewNightShift=findViewById(R.id.txtview_night_shift);
        txtviewTravelling=findViewById(R.id.txtview_travelling);
        txtviewSmoke=findViewById(R.id.txtview_smoke);
        txtviewAlcho=findViewById(R.id.txtview_alcho);
        txtviewSmokes=findViewById(R.id.txtview_smokes);
        toolbar=findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Life Style");
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


        editTextAlchoYes.setVisibility(View.GONE);
        spinnerReasonAlcho.setVisibility(View.GONE);
        txtviewAlcho.setVisibility(View.GONE);
        radioGroupAlcho.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_life_alcho_yes:
                        editTextAlchoYes.setVisibility(View.VISIBLE);
                        spinnerReasonAlcho.setVisibility(View.VISIBLE);
                        txtviewAlcho.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_life_alcho_no:
                        editTextAlchoYes.setVisibility(View.GONE);
                        spinnerReasonAlcho.setVisibility(View.GONE);
                        txtviewAlcho.setVisibility(View.GONE);
                        break;
                }
            }
        });
        editTextNightShiftYes.setVisibility(View.GONE);
        editTextNightShift.setVisibility(View.GONE);
        txtviewNightShift.setVisibility(View.GONE);
        radioGroupShift.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_life_night_yes:
                        editTextNightShiftYes.setVisibility(View.VISIBLE);
                        editTextNightShift.setVisibility(View.VISIBLE);
                        txtviewNightShift.setVisibility(View.VISIBLE);
                        break;
                    case  R.id.rb_life_night_no:
                        editTextNightShiftYes.setVisibility(View.GONE);
                        editTextNightShift.setVisibility(View.GONE);
                        txtviewNightShift.setVisibility(View.GONE);
                        break;
                }
            }
        });
        editTextTravelling.setVisibility(View.GONE);
        editTextTravellingLocation.setVisibility(View.GONE);
        txtviewTravelling.setVisibility(View.GONE);
        radioGroupTravelling.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_life_travelling_yes:
                        editTextTravelling.setVisibility(View.VISIBLE);
                        editTextTravellingLocation.setVisibility(View.VISIBLE);
                        txtviewTravelling.setVisibility(View.VISIBLE);
                        break;
                    case  R.id.rb_life_travelling_no:
                        editTextTravelling.setVisibility(View.GONE);
                        editTextTravellingLocation.setVisibility(View.GONE);
                        txtviewTravelling.setVisibility(View.GONE);
                        break;
                }
            }
        });
        editTextSmoke.setVisibility(View.GONE);
        txtviewSmoke.setVisibility(View.GONE);
        txtviewSmokes.setVisibility(View.GONE);
        spinnerReasonSmoke.setVisibility(View.GONE);
        radioGroupSmoke.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_smoke_yes:
                        editTextSmoke.setVisibility(View.VISIBLE);
                        txtviewSmoke.setVisibility(View.VISIBLE);
                        txtviewSmokes.setVisibility(View.VISIBLE);
                        spinnerReasonSmoke.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_smoke_no:
                        editTextSmoke.setVisibility(View.GONE);
                        txtviewSmoke.setVisibility(View.GONE);
                        txtviewSmokes.setVisibility(View.GONE);
                        spinnerReasonSmoke.setVisibility(View.GONE);
                        break;
                }
            }
        });
        editTextalchoOther.setVisibility(View.GONE);
        spinnerReasonAlcho.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strReasonAlcho=spinnerReasonAlcho.getSelectedItem().toString().trim();
                if(strReasonAlcho.equals("Other")){
                    editTextalchoOther.setVisibility(View.VISIBLE);
                }else{
                    editTextalchoOther.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editTextStressYes.setVisibility(View.GONE);
        spinnerStress.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strStress=spinnerStress.getSelectedItem().toString().trim();
                if(strStress.equals("NO")){
                    editTextStressYes.setVisibility(View.GONE);
                }else{
                    editTextStressYes.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editTextSmokeReason.setVisibility(View.GONE);
        spinnerReasonSmoke.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strReasonSmoke=spinnerReasonSmoke.getSelectedItem().toString().trim();
                if(strReasonSmoke.equals("other")){
                    editTextSmokeReason.setVisibility(View.VISIBLE);
                }else{
                    editTextSmokeReason.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
