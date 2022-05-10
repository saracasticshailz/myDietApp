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

public class QuestionGeneralInfo extends AppCompatActivity {

    EditText editTextGenOccupation,editTextGenBloodGrp,editTextGendesease,editTextGenother;
    RadioGroup rgMarried;
    RadioButton rbUnmarried,rbMarried;
    Spinner spinnerObjective;
    String strSpinner;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_questionnaire1);
        editTextGenOccupation=findViewById(R.id.edittext_gen_occupation);
        editTextGenBloodGrp=findViewById(R.id.edittext_gen_bloodg);
        editTextGendesease=findViewById(R.id.edittext_gen_desease);
        editTextGenother=findViewById(R.id.editext_gen_other);
        rgMarried=findViewById(R.id.rg_gen_married);
        rbMarried=findViewById(R.id.rb_gen_married);
        rbUnmarried=findViewById(R.id.rb_gen_unmarried);
        spinnerObjective=findViewById(R.id.spinner_que_obj);
        editTextGendesease.setVisibility(View.GONE);
        editTextGenother.setVisibility(View.GONE);
        toolbar=findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("General Information");
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


        spinnerObjective.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(
                    AdapterView<?> adapterView, View view,
                    int i, long l) {
                strSpinner = spinnerObjective.getSelectedItem().toString().trim();
                if(strSpinner.equals("Other")){
                    editTextGenother.setVisibility(View.VISIBLE);
                    editTextGendesease.setVisibility(View.GONE);
                }
                else if(strSpinner.equals("Manage My Diseases")){
                    editTextGendesease.setVisibility(View.VISIBLE);
                    editTextGenother.setVisibility(View.GONE);
                }
                else {
                    editTextGendesease.setVisibility(View.GONE);
                    editTextGenother.setVisibility(View.GONE);
                }
                //   Toast.makeText(context, "spinner"+strTime, Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });



    }
}
