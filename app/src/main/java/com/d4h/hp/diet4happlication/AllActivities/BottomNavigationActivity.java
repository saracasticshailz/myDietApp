package com.d4h.hp.diet4happlication.AllActivities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.d4h.hp.diet4happlication.R;


public class BottomNavigationActivity extends AppCompatActivity {

    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bottomnavigation);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.id_bottomnavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

       //toolbar.setTitle("DashBoard");
        getSupportActionBar().setLogo(getResources().getDrawable(R.drawable.d4hlogo));
      // toolbar.setLogo(getResources().getDrawable(R.drawable.d4hlogo));

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    toolbar.setTitle("Dashboard");
                    return true;
                case R.id.nav_nutricoach:
                    toolbar.setTitle("NutriCoach");
                    return true;
                case R.id.nav_plus:
                    toolbar.setTitle("Plus");
                    return true;
                case R.id.nav_weight:
                    toolbar.setTitle("Weight");
                    return true;

            }
            return false;
        }
    };

   /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));*/

}
