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
import android.widget.EditText;


import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class QuestionDietory extends AppCompatActivity {

    EditText edMeal1,edMeal2,edMeal3,edMeal4,edMeal5,edQty1,edQty2,edQty3,edQty4,edQty5;
    Toolbar toolbar;
    String strMeal1,strMeal2,strMeal3,strMeal4,strMeal5,strQty1,strQty2,strQty3,strQty4,strQty5,strDate1,strDate2,strDate3,strDate4,strDate5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_questionnaire6);

        edMeal1=findViewById(R.id.ed_meal1);
        edMeal2=findViewById(R.id.ed_meal2);
        edMeal3=findViewById(R.id.ed_meal3);
        edMeal4=findViewById(R.id.ed_meal4);
        edMeal5=findViewById(R.id.ed_meal5);
        edQty1=findViewById(R.id.ed_qty1);
        edQty2=findViewById(R.id.ed_qty2);
        edQty3=findViewById(R.id.ed_qty3);
        edQty4=findViewById(R.id.ed_qty4);
        edQty5=findViewById(R.id.ed_qty5);
        toolbar=findViewById(R.id.toolbar_all);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dietory");
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



    }


}
