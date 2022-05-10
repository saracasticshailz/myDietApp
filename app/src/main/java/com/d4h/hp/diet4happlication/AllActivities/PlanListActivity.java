package com.d4h.hp.diet4happlication.AllActivities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.d4h.hp.diet4happlication.AllActivities.Plan.BridalPlanActivity;
import com.d4h.hp.diet4happlication.AllActivities.Plan.ClinicalPlanActivity;
import com.d4h.hp.diet4happlication.AllActivities.Plan.DetoxPlanActivity;
import com.d4h.hp.diet4happlication.AllActivities.Plan.DiabeticPlanActivity;
import com.d4h.hp.diet4happlication.AllActivities.Plan.KetogenicPlanActivity;
import com.d4h.hp.diet4happlication.AllActivities.Plan.KidsPlanActivity;
import com.d4h.hp.diet4happlication.AllActivities.Plan.PregencyPlanActivity;
import com.d4h.hp.diet4happlication.AllActivities.Plan.SportsPlanActivity;
import com.d4h.hp.diet4happlication.AllActivities.Plan.WeightGainActivity;
import com.d4h.hp.diet4happlication.AllActivities.Plan.WeightLossActivity;
import com.d4h.hp.diet4happlication.AllFragments.DashBoardFragment;
import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class PlanListActivity extends AppCompatActivity {
    private Button wtGain,wtLoss,diabetic,bridal,preg,kids,clinical,detox,keto,sports;

    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_plan_list);
        wtGain=findViewById(R.id.wtgain);
        wtLoss=findViewById(R.id.wtloss);
        diabetic=findViewById(R.id.wtdiabeticpro);
        bridal=findViewById(R.id.wtbridal);
        preg=findViewById(R.id.wtPregDiet);
        kids=findViewById(R.id.wtkidDiet);
        clinical=findViewById(R.id.wtclinicalnutri);
        detox=findViewById(R.id.wtDetoxdiet);
        keto=findViewById(R.id.wtketo);
        sports=findViewById(R.id.sportDiet);

        toolbar=(Toolbar)findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Plans");
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


        wtGain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlanListActivity.this,WeightGainActivity.class);
                startActivity(i);
            }
        });
        wtLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlanListActivity.this,WeightLossActivity.class);
                startActivity(i);
            }
        });
        diabetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlanListActivity.this,DiabeticPlanActivity.class);
                startActivity(i);
            }
        });
        bridal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlanListActivity.this,BridalPlanActivity.class);
                startActivity(i);
            }
        });
        preg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlanListActivity.this,PregencyPlanActivity.class);
                startActivity(i);
            }
        });
        kids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlanListActivity.this,KidsPlanActivity.class);
                startActivity(i);
            }
        });
        clinical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlanListActivity.this,ClinicalPlanActivity.class);
                startActivity(i);
            }
        });
        detox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlanListActivity.this,DetoxPlanActivity.class);
                startActivity(i);
            }
        });
        keto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlanListActivity.this,KetogenicPlanActivity.class);
                startActivity(i);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(PlanListActivity.this,SportsPlanActivity.class);
                startActivity(i);
            }
        });


    }
}