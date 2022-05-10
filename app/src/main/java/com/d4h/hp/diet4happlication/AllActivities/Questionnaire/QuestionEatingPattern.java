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


import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class QuestionEatingPattern extends AppCompatActivity {

    Spinner spinnerDietType,spinnerCookingPref,spinnerCosusine;
    EditText edWater,edDislike,edFoodAlergy,edMilkYes,edCousinOther;
    RadioGroup rgMilk;
    RadioButton rbMilkYes,rbMilkNo;
    String strOther;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_questionnaire8);

        spinnerDietType=findViewById(R.id.spinner_diettype);
        spinnerCookingPref=findViewById(R.id.spinner_cooking);
        spinnerCosusine=findViewById(R.id.spinner_cousine);
        edWater=findViewById(R.id.ed_Dailywater);
        edDislike=findViewById(R.id.ed_dislike);
        edFoodAlergy=findViewById(R.id.ed_foodalergy);
        edMilkYes=findViewById(R.id.ed_milkdis);
        rgMilk=findViewById(R.id.rg_milk);
        rbMilkYes=findViewById(R.id.rb_milk_yes);
        rbMilkNo=findViewById(R.id.rb_milk_no);
        edCousinOther=findViewById(R.id.edittext_cousin_other);
        edMilkYes.setVisibility(View.GONE);
        toolbar=findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Eating Pattern");
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


        rgMilk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_milk_yes:
                        edMilkYes.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_milk_no:
                        edMilkYes.setVisibility(View.GONE);
                        break;
                }
            }
        });
        edCousinOther.setVisibility(View.GONE);
        spinnerCosusine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strOther=spinnerCosusine.getSelectedItem().toString().trim();
                if(strOther.equals("other")){
                    edCousinOther.setVisibility(View.VISIBLE);
                }else {
                    edCousinOther.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
