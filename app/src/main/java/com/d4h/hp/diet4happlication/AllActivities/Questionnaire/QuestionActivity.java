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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class QuestionActivity extends AppCompatActivity {

    RadioGroup rgActivity,rgPhysical,rgSuggestion;
    RadioButton rbAYes,rbANo,rbPYes,rbPNo,rbSYes,rbSno;
    Button btnNext;
    EditText editextSuggetion,edittextActivity,edittextPhysical;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_questionnaire7);

        rgActivity=findViewById(R.id.rg_act_activity);
        rgPhysical=findViewById(R.id.rg_physicalchal);
        rgSuggestion=findViewById(R.id.rg_act_docsug);
        rbAYes=findViewById(R.id.rb_act_yes);
        rbANo=findViewById(R.id.rb_act_no);
        rbPYes=findViewById(R.id.rb_act_phsical_yes);
        rbPNo=findViewById(R.id.rb_act_phyasical_no);
        rbSYes=findViewById(R.id.rb_docsug_yes);
        rbSno=findViewById(R.id.rb_docsug_no);
        editextSuggetion=findViewById(R.id.ed_docsug);
        edittextActivity=findViewById(R.id.edittext_act_activity);
        btnNext=findViewById(R.id.btn_act_next);
        edittextPhysical=findViewById(R.id.ed_phy_cha);
        edittextActivity.setVisibility(View.GONE);
        editextSuggetion.setVisibility(View.GONE);
        toolbar=findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Activity");
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

        rgActivity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rb_act_yes:
                        edittextActivity.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_act_no:
                        edittextActivity.setVisibility(View.GONE);
                        break;
                }
            }
        });

        rgSuggestion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_docsug_yes:
                        editextSuggetion.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_docsug_no:
                        editextSuggetion.setVisibility(View.GONE);
                        break;
                }
            }
        });
        edittextPhysical.setVisibility(View.GONE);
        rgPhysical.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rb_act_phsical_yes:
                        edittextPhysical.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_act_phyasical_no:
                        edittextPhysical.setVisibility(View.GONE);
                        break;
                }
            }
        });

    }
}
