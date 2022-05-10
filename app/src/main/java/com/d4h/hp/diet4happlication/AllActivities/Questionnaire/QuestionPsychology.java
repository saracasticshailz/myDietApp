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

public class QuestionPsychology extends AppCompatActivity {

    EditText editextPscyother,editextHabit;
    Spinner spinnerMotivation,spinnerHabit;
    Button btnNext;
    String strSpinner,strSpinner2;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_questionnaire4);
        editextPscyother=findViewById(R.id.editext_motivation);
        editextHabit=findViewById(R.id.edittext_pyco_habit);
        spinnerMotivation=findViewById(R.id.spinner_pscho_motivation);
        spinnerHabit=findViewById(R.id.spinner_pysco_habit);
        btnNext=findViewById(R.id.btn_psycho_next);
        toolbar=findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Psychology");
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

        editextPscyother.setVisibility(View.GONE);
        editextHabit.setVisibility(View.GONE);
        spinnerMotivation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(
                    AdapterView<?> adapterView, View view,
                    int i, long l) {
                strSpinner = spinnerMotivation.getSelectedItem().toString().trim();
                if(strSpinner.equals("Other")){
                    editextPscyother.setVisibility(View.VISIBLE);
                }
                else {
                    editextPscyother.setVisibility(View.GONE);
                }
                //   Toast.makeText(context, "spinner"+strTime, Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });
        spinnerHabit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(
                    AdapterView<?> adapterView, View view,
                    int i, long l) {
                strSpinner2 = spinnerHabit.getSelectedItem().toString().trim();
                if(strSpinner2.equals("Other")){
                    editextHabit.setVisibility(View.VISIBLE);
                }
                else {
                    editextHabit.setVisibility(View.GONE);
                }
                //   Toast.makeText(context, "spinner"+strTime, Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });



    }
}
