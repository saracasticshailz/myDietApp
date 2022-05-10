package com.d4h.hp.diet4happlication.AllActivities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.AllAdaptors.FoodDieryAdaptor;
import com.d4h.hp.diet4happlication.AllAdaptors.WorkCompletedAdaptor;
import com.d4h.hp.diet4happlication.AllDataModels.FooddieryModel;
import com.d4h.hp.diet4happlication.AllDataModels.WorkCompletedModel;
import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.jar.JarException;

public class WorkOutCompletedActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    int  mYear, mMonth, mDay;
    SharedPreferences loginPreferences;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    WorkCompletedModel workCompletedModel;
    private List<WorkCompletedModel> listWorkout;
    private RecyclerView.Adapter adapter;
    String pt_id,patient_id;
    Toolbar toolbar;
    private String strActivity,strActivityDate,strRepetation;
    private String strAName,strADate,strARepetation;
    String pt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_completed);

        recyclerView=(RecyclerView)findViewById(R.id.rv_workcompled);
        floatingActionButton =(FloatingActionButton)findViewById(R.id.fab_workcompleted);
        toolbar=(Toolbar)findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Completed Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        layoutManager=new LinearLayoutManager(this);
        listWorkout=new ArrayList<>();
        adapter=new WorkCompletedAdaptor(listWorkout,getApplicationContext());
        displayActivtiy();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCompleted(v);
            }
        });

        Calendar myCalendar=Calendar.getInstance();
        mDay=myCalendar.get(Calendar.DATE);
        mMonth=myCalendar.get(Calendar.MONTH);
        mYear=myCalendar.get(Calendar.YEAR);

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
    public void displayActivtiy(){

        loginPreferences=getSharedPreferences("UserData",0);
        pt=loginPreferences.getString("patient_id","");
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
       // Toast.makeText(this, ""+pt, Toast.LENGTH_SHORT).show();
        String url="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/fetch_completed_activity.php?patient_id="+pt;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray out=response.getJSONArray("output");
                    for (int i=0;i<out.length();i++) {
                        JSONObject jsonObject = out.getJSONObject(i);

                        strAName = jsonObject.getString("activity_type");
                       // Toast.makeText(WorkOutCompletedActivity.this, ""+strAName, Toast.LENGTH_SHORT).show();
                        strADate = jsonObject.getString("activity_date");
                        strARepetation = jsonObject.getString("repetition");
                        patient_id = jsonObject.getString("patient_id");

                        workCompletedModel=new WorkCompletedModel();
                        workCompletedModel.setStrActivity(strAName);
                        workCompletedModel.setStrDate(strADate);
                        workCompletedModel.setStrRepetation(strARepetation);
                        listWorkout.add(workCompletedModel);

                    }
                    //adapter.notifyDataSetChanged();


                }catch (JSONException e){

                }
                setRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WorkOutCompletedActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    public void setRecyclerView(){
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
    public void addCompleted (View view){
        final Dialog dialog = new Dialog(WorkOutCompletedActivity.this);
        dialog.setContentView(R.layout.layout_dialog_workcompleted);
        dialog.setCancelable(true);
        dialog.setTitle("Add Workout");
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.myloginbutton));
        dialog.getWindow().setTitleColor(getResources().getColor(R.color.colorPrimary));
        dialog.getWindow();
       /* int dividerId = dialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = dialog.findViewById(dividerId);
        divider.setBackgroundColor(getResources().getColor(R.color.colorOrange));*/

        Button btnAddActivity;
        final EditText editTextRepetation,edittextOther;
        final TextView editTextDate;
        final Spinner spinnerActivity;

        btnAddActivity=dialog.findViewById(R.id.btn_add_activity);
        editTextDate=dialog.findViewById(R.id.ed_activity_date);
        spinnerActivity=dialog.findViewById(R.id.spinner_activity);
        editTextRepetation=dialog.findViewById(R.id.ed_repetation);
        edittextOther=dialog.findViewById(R.id.ed_otheractivity);

        spinnerActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(
                    AdapterView<?> adapterView, View view,
                    int i, long l) {
                strActivity = spinnerActivity.getSelectedItem().toString().trim();
                if(strActivity.equals("Others")){

                    edittextOther.setVisibility(View.VISIBLE);
                    strActivity=edittextOther.getText().toString().trim();
                }else{
                    edittextOther.setVisibility(View.GONE);
                    strActivity = spinnerActivity.getSelectedItem().toString().trim();
                }
                //   Toast.makeText(context, "spinner"+strTime, Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog  datePickerDialog = new DatePickerDialog(WorkOutCompletedActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                editTextDate.setText(day + "-" + (month + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }

        });


       // strActivity = String.valueOf(spinnerActivity.getSelectedItemPosition()+1);

        btnAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                strRepetation = editTextRepetation.getText().toString().trim();
                strRepetation = strRepetation.replace(" ", "+");
                strActivityDate = editTextDate.getText().toString().trim();
                strActivity = spinnerActivity.getSelectedItem().toString().trim();
                if (strRepetation.equals("")) {
                    editTextRepetation.setError("Required");
                } else if (strActivityDate.equals("")) {
                    editTextDate.setError("Required");
                } else if (strActivity.equals("Others")) {
                    edittextOther.setVisibility(View.VISIBLE);
                    strActivity = edittextOther.getText().toString().trim();
                } else if (strActivity.equals("Select Activity type")) {
                    Toast.makeText(WorkOutCompletedActivity.this, "Please Enter Activity Name", Toast.LENGTH_SHORT).show();
                } else {

                    strActivity = spinnerActivity.getSelectedItem().toString().trim();
                    loginPreferences = getSharedPreferences("UserData", 0);
                    pt_id = loginPreferences.getString("patient_id", "");
                    String url ="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/add_activity.php?patient_id="+pt_id+"&activity_type="+strActivity+"&no_of_repeation="+strRepetation+"&activity_date="+strActivityDate;
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(WorkOutCompletedActivity.this, "Activity Inserted Successfully", Toast.LENGTH_SHORT).show();
                            displayActivtiy();
                            dialog.dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    requestQueue.add(jsonObjectRequest);
                }
            }
        });





    }
}
