package com.d4h.hp.diet4happlication.AllActivities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.AllAdaptors.FoodDieryAdaptor;
import com.d4h.hp.diet4happlication.AllDataModels.FooddieryModel;

import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FoodDiary extends AppCompatActivity  {

    //display day date month in recycler view as per patient diet plan in recycler view,
    //display my workout fragment with recycler view
    //display my diet plan on fragment with recycler view
    //display gain calories and burn calories on clicking txtview show calories on button

    SharedPreferences loginPreferences;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<FooddieryModel> listFooddiery;
    private RecyclerView.Adapter adapter;
    private FooddieryModel model;
    private TextView txtviewDate;
    private FloatingActionButton floatingActionButton;
    Calendar myCalendar;
    private final int dialogue_id =0;
    private int mDay,mMonth,mYear;
    private ImageView iv_calender;
    TextView inputMealTime;
    Toolbar toolbar;
    TextView txtviewEmpty;
    String strMealDate;
    ProgressBar progressBar1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_food_diary);
        //Initialization
        myCalendar=Calendar.getInstance();
        mDay=myCalendar.get(Calendar.DATE);
        mMonth=myCalendar.get(Calendar.MONTH);
        mYear=myCalendar.get(Calendar.YEAR);

        txtviewDate=findViewById(R.id.txtview_date);
        iv_calender=findViewById(R.id.iv_calender);
        recyclerView=(RecyclerView)findViewById(R.id.rv_fooddiery);
        floatingActionButton=findViewById(R.id.fab_fooddiery);
        progressBar1=findViewById(R.id.id_progressbarFood);
        txtviewEmpty=findViewById(R.id.txtview_empty);
        toolbar=(Toolbar)findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Food Diary");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        layoutManager=new LinearLayoutManager(this);
        listFooddiery=new ArrayList<>();
        adapter=new FoodDieryAdaptor(getApplicationContext(),listFooddiery);

        //set adaptor to rv
        /*layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.getOrientation();*/

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String curdate = sdf.format(new Date());
        txtviewDate.setText(curdate);
        txtviewEmpty.setVisibility(View.VISIBLE);
        diet_view();
        txtviewEmpty.setVisibility(View.GONE);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        iv_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(dialogue_id);
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
    public void diet_view(){
        loginPreferences = getSharedPreferences("UserData", 0);
        String pt_id=loginPreferences.getString("patient_id","");
        //Toast.makeText(this, pt_id, Toast.LENGTH_SHORT).show();
        RequestQueue mq=Volley.newRequestQueue(this);


        // https://www.diet4health.in/public/diet4health.in/d4h_api/food_diary.php?module_name=view_diary&f_date=2018-12-10&patient_id=197

        String food_diary_url=
                "https://www.diet4health.in/public/diet4health.in/d4h_api/food_diary.php?module_name=view_diary&"+"f_date="
                        +txtviewDate.getText().toString()
                        +"&patient_id="+pt_id;


        // String p_id=loginPreferences.getString("patient_id","");
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, food_diary_url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listFooddiery.clear();


                //  Toast.makeText(FoodDiary.this, response.toString(), Toast.LENGTH_SHORT).show();
                try {

                    JSONArray out=response.getJSONArray("output");
                    for(int i=0;i<out.length();i++){
                        JSONObject data=out.getJSONObject(i);
                        model=new FooddieryModel();

                        String diet_date=data.getString("diet_date");
                        //  txtviewDate.setText(diet_date);
                        model.setMealQuantity(data.getString("amount"));
                        model.setMealType(data.getString("mealType"));
                        model.setMealTime(data.getString("diet_time"));
                        model.setMealName(data.getString("diet"));
                        listFooddiery.add(model);

                    }
                    adapter.notifyDataSetChanged();
                    //getting data from output oject
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Toast.makeText(FoodDiary.this,response.toString(), Toast.LENGTH_SHORT).show();
                setRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(FoodDiary.this, "Diet View Network Error", Toast.LENGTH_SHORT).show();
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    message = "The data could not be found. Please try again after some time!!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mq.add(jsonObjectRequest);

    }

    public void setRecyclerView(){
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public void buttonClicked(View view) {

        final Dialog dialog = new Dialog(FoodDiary.this);
        dialog.setContentView(R.layout.layout_dialog_fooddiery);
        dialog.setTitle("Food Diary");
        dialog.setCancelable(true);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimaryDark);
        dialog.getWindow().setTitleColor(getResources().getColor(R.color.colorPrimary));
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.myloginbutton));

        //dialog.getWindow().setLayout(500,400);
        dialog.getWindow();
       /* int dividerId = dialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = dialog.findViewById(dividerId);
        divider.setBackgroundColor(getResources().getColor(R.color.colorOrange));*/
        final ProgressBar progressBar;
        final EditText inputDiet,inputMealQuantity;
        final TextView inputMealDate;
        final Spinner inputMealType;

        inputMealDate=dialog.findViewById(R.id.edittext_mealDate);
        inputMealTime=dialog.findViewById(R.id.edittext_mealTime);
        inputDiet=dialog.findViewById(R.id.edittext_diet);
        inputMealQuantity=dialog.findViewById(R.id.edittext_meaquantity);
        inputMealType=dialog.findViewById(R.id.spinner_mealtype);
        progressBar=dialog.findViewById(R.id.fdd_progressbar);


        loginPreferences = getSharedPreferences("UserData", 0);
        final String pt_id=loginPreferences.getString("patient_id","");

        Button Add=dialog.findViewById(R.id.btn_add);

        inputMealDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog  datePickerDialog = new DatePickerDialog(FoodDiary.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                inputMealDate.setText(day + "-" + (month + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }

        });

        //final String strMealType= String.valueOf(inputMealType.getSelectedItemPosition());


        //dilog boxx button
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                strMealDate = inputMealDate.getText().toString();
                String strDiet = inputDiet.getText().toString();
                strDiet = strDiet.replace(" ", "+");
                String strMealTime = inputMealTime.getText().toString();
                String strMealqnt = inputMealQuantity.getText().toString();
                strMealqnt = strMealqnt.replace(" ", "+");
                String strMealType = String.valueOf(inputMealType.getSelectedItemPosition()+1);
                if (strMealDate.equals("")) {
                    inputMealDate.setError("Please Add Date");
                    // Toast.makeText(FoodDiary.this, "Please add diet", Toast.LENGTH_SHORT).show();
                } else if (strMealTime.equals("")) {
                    inputMealTime.setError("Please Add Time");
                } else if(strDiet.equals(""))  {
                    inputDiet.setError("Please Add Diet");
                } else if (strMealqnt.equals("")){
                    inputMealQuantity.setError("Please Add Quantity");
                } else {

                    // https://www.diet4health.in/public/diet4health.in/d4h_api/food_diary.php?module_name=Add_Diet&mealType=1&
                    // diet=bada_paav&quantity=10&diet_time=10:00&diet_date=2018-12-18&patient_id=197
                    final String durl =
                            "https://www.diet4health.in/public/diet4health.in/d4h_api/food_diary.php?module_name=Add_Diet&" + "patient_id="
                                    + pt_id + "&mealType=" + strMealType + "&diet=" + strDiet + "&quantity="
                                    + strMealqnt + "&diet_time=" + strMealTime + "&diet_date=" + strMealDate;

                    RequestQueue mq1 = Volley.newRequestQueue(getApplicationContext());

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, durl,
                            null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            progressBar.setVisibility(View.GONE);
                            //Toast.makeText(FoodDiary.this, response.toString(), Toast.LENGTH_SHORT).show();

                            txtviewDate.setText(strMealDate);
                            dialog.cancel();
                            diet_view();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(FoodDiary.this, "Food Diery Network Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                    mq1.add(jsonObjectRequest);
                }
            }
        });
        inputMealTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker();

            }
        });

    }
    public void timePicker(){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                inputMealTime.setText( selectedHour + ":" + selectedMinute);
                //dt=selectedHour + ":" + selectedMinute;
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id==dialogue_id)

            return new DatePickerDialog(this,dpickerListner,mYear,mMonth,mDay);
        return null;
    }

    private  DatePickerDialog.OnDateSetListener dpickerListner=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            // view.setMaxDate(System.currentTimeMillis());
            // Toast.makeText(FoodDiary.this, mDay + "/" + mMonth + "/" + mYear, Toast.LENGTH_SHORT).show();
            //txtviewDate.setText(mDay + "/" + mMonth + "/" + mYear);

            String generatedDate=mDay+ "-"+(month+1)+"-"+mYear;
            txtviewDate.setText(generatedDate);
            loginPreferences = getSharedPreferences("UserData", 0);
            String pt_id=loginPreferences.getString("patient_id","");
            RequestQueue mq = Volley.newRequestQueue(getApplicationContext());
            final String food_diary_url =
                    "https://www.diet4health.in/public/diet4health.in/d4h_api/food_diary.php?module_name=view_diary"+
                            "&f_date="+generatedDate+"&patient_id="+pt_id;
            JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, food_diary_url,
                    null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    listFooddiery.clear();
                    try {
                        JSONArray out = response.getJSONArray("output");
                        for (int i = 0; i < out.length(); i++) {
                            JSONObject data = out.getJSONObject(i);
                            String diet = data.getString("diet");
                            //amount is string value which we  have to parse in int for saving in mo del
                            // int amount = Integer.parseInt(data.getString("amount"));

                            String diet_date = data.getString("diet_date");
                            //txtviewDate.setText(diet_date);
                            String diet_time = data.getString("diet_time");
                            String mealType = data.getString("mealType");
                            final FooddieryModel model = new FooddieryModel();

                            model.setMealQuantity(data.getString("amount"));
                            model.setMealType(mealType);
                            model.setMealTime(diet_time);
                            model.setMealName(diet);
                            //  model.setMealQuantity(amount);
                            listFooddiery.add(model);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    setRecyclerView();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Toast.makeText(FoodDiary.this, "Food Diery NetWork Error", Toast.LENGTH_SHORT).show();
                    String message = null;
                    if (error instanceof NetworkError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ServerError) {
                        message = "The data could not be found. Please try again after some time!!";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    } else if (error instanceof AuthFailureError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ParseError) {
                        message = "Parsing error! Please try again after some time!!";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    } else if (error instanceof NoConnectionError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    } else if (error instanceof TimeoutError) {
                        message = "Connection TimeOut! Please check your internet connection.";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
            mq.add(jsonObjectRequest1);
            //  diet_view();
        }
    };
}