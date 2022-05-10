package com.d4h.hp.diet4happlication.AllActivities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.d4h.hp.diet4happlication.AllFragments.ReviewReportFragement;
import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddReview extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView Rev_strdate, Rev_enddate, conTime,ed_conDate,tvMensesTaken,tv_rg_fever,tvDietChange,tv_chused,complaint;
    Toolbar toolbar;
    String pt_id;
    int day,month,year;
    EditText rev_edstatus, ed_medicineconcern, healthcom, ed_reason_no, out_plan, edFeverDays,edFeverMed,mensesDay;
    RadioGroup rg_energy_level, rg_stress_level, rg_motion, rg_fever,rgFeverMed, rg_menses, rg_isSuitabel, rut_reason,rg_MensesMEd;
    private int mDay,mMonth,mYear;
    private RadioButton rbEnergy,rbFever,rbStress,rbMotion,rbDiet;
    Spinner digest_complaints;
    Button feedback_choosefiel, feedback_save;
    String  rgStress="";
    String  rgMotion="";
    String  rgMenses="";
    String  rgIsSuitable="";
    String  rgRutreason="";
    String  rgFever="";
    String  strWeight="";
    String  callbacktime="";
    String  strDietChange="";
    String  strOutfasting="";
    String  strConTime="";
    String  strFeverDays="";
    String  callbackdate="";
    String  strMensesDay="";

    String strHealthComplaint="";
    String strTime="";
    String endTme="";
    String str_ed_fever="";
    String strFevermed="";
    String strMotion="";
    String strMensesmed="";
    String strCAllDate="";
    String strRutreason="";
    String fevermedyes="";
    String rgEnergy="";
    int intSpinnerDig;
    RequestQueue mq;
    int IMG_REQUEST=1;
    Bitmap bitmap;
    String encodedImage="";
    Handler mHandler;
    String filename="";
    String sDate="";
    String eDate="";
    ProgressBar progressBar;
    Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_feedback);

        sharedPreferences = this.getSharedPreferences("UserData", 0);
        pt_id = sharedPreferences.getString("patient_id", "");

/*
        toolbar=(Toolbar)findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Review");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
*/
        mq=Volley.newRequestQueue(this);
        progressBar=findViewById(R.id.fb_progressbar);

        progressBar.setVisibility(View.GONE);
        ////radioButton
        rbEnergy=findViewById(R.id.energy_high);
        rbFever=findViewById(R.id.fever_yes);
        rbStress=findViewById(R.id.stress_high);
        rbMotion=findViewById(R.id.motion_normal);
        rbDiet=findViewById(R.id.suit_yes);
        ///////////textview
        Rev_strdate = findViewById(R.id.Rev_strdate);
        Rev_enddate = findViewById(R.id.Rev_enddate);
        ed_conDate = findViewById(R.id.ed_conDAte);
        tvMensesTaken=findViewById(R.id.tv_mensestaken);
        tv_rg_fever=findViewById(R.id.tv_rg_fever);
        tvDietChange=findViewById(R.id.tv_dietChange);
        tv_chused=findViewById(R.id.fb_chusedfile);
        complaint=findViewById(R.id.txtviewComplaint);
//////////////////////EditText
        rev_edstatus = findViewById(R.id.rev_edstatus);
        healthcom = findViewById(R.id.healthcom);
        ed_reason_no = findViewById(R.id.ed_reason_no);
        out_plan = findViewById(R.id.out_plan);
        conTime = findViewById(R.id.ed_contime);
        edFeverDays = findViewById(R.id.ed_feverdays);
        edFeverMed = findViewById(R.id.ed_fevermed);
        mensesDay= findViewById(R.id.mensesday);
//////////////////spinner
        digest_complaints = findViewById(R.id.digest_complaints);
        //spin_fever = findViewById(R.id.spin_fever);
        ///////////////////Button
        feedback_choosefiel = findViewById(R.id.feedback_choosefiel);
        feedback_save = findViewById(R.id.feedback_save);

//////////radiogroup
        rg_energy_level = findViewById(R.id.rg_energy_level);
        rg_stress_level = findViewById(R.id.rg_stress_level);
        rg_motion = findViewById(R.id.rg_motion);
        rg_fever = findViewById(R.id.rg_fever);
        rg_menses = findViewById(R.id.rg_menses);
        rg_isSuitabel = findViewById(R.id.rg_isSuitabel);
        rut_reason = findViewById(R.id.rut_reason);
        rgFeverMed = findViewById(R.id.rg_fevermed);
        rg_MensesMEd = findViewById(R.id.rg_mensesMEd);

        ////get information in string



        Calendar myCalendar=Calendar.getInstance();
        mDay=myCalendar.get(Calendar.DATE);
        mMonth=myCalendar.get(Calendar.MONTH);
        mYear=myCalendar.get(Calendar.YEAR);;

        edFeverDays.setVisibility(View.GONE);
        edFeverMed.setVisibility(View.GONE);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String curdate = sdf.format(new Date());

        ed_conDate.setText(curdate);
        Rev_strdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog  datePickerDialog = new DatePickerDialog(AddReview.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                Rev_strdate.setText(day + "-" + (month + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();


            }
        });
        Rev_enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog  datePickerDialog = new DatePickerDialog(AddReview.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                Rev_enddate.setText(day + "-" + (month + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        edFeverDays.setVisibility(View.GONE);
        edFeverMed.setVisibility(View.GONE);
        rgFeverMed.setVisibility(View.GONE);
        tv_rg_fever.setVisibility(View.GONE);

        rg_fever.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.fever_yes:
                        rgFever="YES";
                        edFeverDays.setVisibility(View.VISIBLE);
                        edFeverMed.setVisibility(View.VISIBLE);
                        rgFeverMed.setVisibility(View.VISIBLE);
                        tv_rg_fever.setVisibility(View.VISIBLE);
                        str_ed_fever=edFeverDays.getText().toString();
                        str_ed_fever= str_ed_fever.replace(" ", "+");
                        break;
                    case R.id.fever_no:
                        rgFever = "NO";
                        edFeverDays.setVisibility(View.GONE);
                        edFeverMed.setVisibility(View.GONE);
                        rgFeverMed.setVisibility(View.GONE);
                        tv_rg_fever.setVisibility(View.GONE);
                        break;
                }
            }
        });
        edFeverMed.setVisibility(View.GONE);
        rgFeverMed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.fevermed_yes:
                        fevermedyes = "YES";
                        edFeverMed.setVisibility(View.VISIBLE);
                        strFevermed=edFeverMed.getText().toString();
                        strFevermed=strFevermed.replace(" ", "+");

                        break;
                    case R.id.fevermed_no:
                        fevermedyes = "NO";
                        edFeverMed.setVisibility(View.GONE);
                        break;
                }
            }
        });
        rg_energy_level.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.energy_high:
                        rgEnergy = "HIGH";
                        break;
                    case R.id.energy_low:
                        rgEnergy = "LOW";
                        break;
                }
            }
        });
        rg_stress_level.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.stress_high:
                        rgStress = "HIGH";
                        break;
                    case R.id.stress_low:
                        rgStress = "LOW";
                        break;
                }
            }
        });
        rg_motion.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.motion_normal:
                        rgMotion = "NORMAL";
                        break;
                    case R.id.motion_Loose:
                        rgMotion = "LOOSE";
                        break;
                    case R.id.motion_Hard:
                        rgMotion = "HARD";
                        break;
                }
            }
        });

        mensesDay.setVisibility(View.GONE);
        rg_MensesMEd.setVisibility(View.GONE);
        tvMensesTaken.setVisibility(View.GONE);


        rg_menses.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.menses_yes:
                        rgMenses = "YES";
                        rg_MensesMEd.setVisibility(View.VISIBLE);
                        rg_MensesMEd.setVisibility(View.VISIBLE);
                        tvMensesTaken.setVisibility(View.VISIBLE);
                        mensesDay.setVisibility(View.VISIBLE);
                        break;
                    case R.id.menses_no:
                        rgMenses = "NO";
                        mensesDay.setVisibility(View.GONE);
                        rg_MensesMEd.setVisibility(View.GONE);
                        tvMensesTaken.setVisibility(View.GONE);
                        mensesDay.setVisibility(View.GONE);
                        break;
                }
            }
        });

        rg_MensesMEd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mensesMEd_yes:
                        strMensesmed = "YES";
                        break;
                    case R.id.mensesMED_no:
                        strMensesmed = "NO";
                        break;
                }
            }
        });
        rg_isSuitabel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.suit_yes:
                        rgIsSuitable = "YES";
                        break;
                    case R.id.suit_no:
                        rgIsSuitable = "NO";
                        break;
                }
            }
        });

        ed_reason_no.setVisibility(View.GONE);
        tvDietChange.setVisibility(View.GONE);

        rut_reason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.reason_yes:
                        rgRutreason = "YES";
                        ed_reason_no.setVisibility(View.GONE);
                        tvDietChange.setVisibility(View.GONE);
                        break;
                    case R.id.reason_no:
                        rgRutreason = "NO";
                        ed_reason_no.setVisibility(View.VISIBLE);
                        tvDietChange.setVisibility(View.VISIBLE);

                        if(strRutreason.isEmpty()){
                            ed_reason_no.setText("");
                        }
                        strRutreason= ed_reason_no.getText().toString().replace(" ", "+");
                        break;
                }
            }
        });

        digest_complaints.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                intSpinnerDig = digest_complaints.getSelectedItemPosition()+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }});

        conTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker();
            }
        });


        ed_conDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog  datePickerDialog = new DatePickerDialog(AddReview.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                ed_conDate.setText(day + "-" + (month + 1) + "-" + year);
                              //  strCAllDate=day + "-" + (month + 1) + "-" + year;
                                callbackdate=day + "-" + (month + 1) + "-" + year;
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-10000);
                //datePicker.setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });


        toolbar=findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Review");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        feedback_choosefiel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,IMG_REQUEST);*/
                Log.e(encodedImage,"msg");
                showFileChooser();


            }
        });


        feedback_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sDate=Rev_strdate.getText().toString();
                eDate=Rev_enddate.getText().toString();
                Toast.makeText(AddReview.this, ""+filename, Toast.LENGTH_SHORT).show();
                if (sDate.equals("")) {
                    progressBar.setVisibility(View.GONE);
                    Rev_strdate.setError("Please Enter start date ");
                    Toast.makeText(AddReview.this, "Please Enter start date", Toast.LENGTH_SHORT).show();

                }
                else if (eDate.equals("")) {
                     progressBar.setVisibility(View.GONE);
                    Rev_enddate.setError("Please Enter end date ");
                   Toast.makeText(AddReview.this, "Please Enter end date", Toast.LENGTH_SHORT).show();
                    }

                else if (rgEnergy.equals("")) {
                    progressBar.setVisibility(View.GONE);
                    rbEnergy.setError("Please Enter energy level ");
                    Toast.makeText(AddReview.this, "Please Enter energy level", Toast.LENGTH_SHORT).show();
                }
                else if (rgStress.equals("")) {
                    progressBar.setVisibility(View.GONE);
                    rbStress.setError(" ");
                    Toast.makeText(AddReview.this, "Please Enter stress", Toast.LENGTH_SHORT).show();
                }
                else if (rgMotion.equals("")) {
                    progressBar.setVisibility(View.GONE);
                    rbMotion.setError(" ");
                    Toast.makeText(AddReview.this, "Please Enter motion", Toast.LENGTH_SHORT).show();
                }
                else if (rgFever.equals("")) {
                    progressBar.setVisibility(View.GONE);
                    rbFever.setError(" ");
                    Toast.makeText(AddReview.this, "Please Enter fever", Toast.LENGTH_SHORT).show();
                }
                else if (rgIsSuitable.equals("")) {
                    progressBar.setVisibility(View.GONE);
                    rbDiet.setError(" ");
                    Toast.makeText(AddReview.this, "Please Enter diet suitable", Toast.LENGTH_SHORT).show();
                }
                else {
                     feedbacksave(encodedImage);
                 }

            }
        });

    }
    public void feedbacksave(final String IMG){

        progressBar.setVisibility(View.VISIBLE);
        feedback_save.setEnabled(false);
        feedback_save.setText("Saving...");

        String weekReport="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_review_report.php?";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, weekReport,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(AddReview.this, "Report successfully submited !!", Toast.LENGTH_SHORT).show();

                            onBackPressed();
                          /*  fragment = new ReviewReportFragement();
                            FragmentManager rvfms = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction7 = rvfms.beginTransaction();
                            fragmentTransaction7.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                            finish();*/



                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressBar.setVisibility(View.GONE);
                    // Toast.makeText(AddReview.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
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
            }) {
                @Override
                protected Map<String, String> getParams() {
                    strWeight = rev_edstatus.getText().toString().replace(" ", "+");
                    strHealthComplaint = healthcom.getText().toString().replace(" ", "+");
                    // Toast.makeText(AddReview.this, ""+strHealthComplaint, Toast.LENGTH_SHORT).show();
                    strDietChange = ed_reason_no.getText().toString().replace(" ", "+");
                    strOutfasting = out_plan.getText().toString().replace(" ", "+");
                    strFeverDays = edFeverDays.getText().toString().replace(" ", "+");
                    strConTime = conTime.getText().toString().replace(" ", "+");
                    strFevermed=edFeverMed.getText().toString();
                    strMensesDay= mensesDay.getText().toString();

                    strRutreason= ed_reason_no.getText().toString().replace(" ", "+");


                    Map<String, String> params = new HashMap<String, String>();
                    params.put("patient_id", pt_id);
                    params.put("start_date", sDate);
                    params.put("end_date", eDate);
                    params.put("weight_status", strWeight);
                    params.put("energy_level", rgEnergy);
                    params.put("stress_level", rgStress);
                    params.put("motion", rgMotion);

                    params.put("fever", rgFever);
                    params.put("fever_no_days", strFeverDays);
                    params.put("is_medicine_taken_in_fever", fevermedyes);
                    params.put("medicine_for_fever", strFevermed);

                    params.put("menses", rgMenses);
                    params.put("menses_no_of_days",strMensesDay);
                    params.put("is_medicine_taken_in_menses", strMensesmed);

                    params.put("digestive_complaints", String.valueOf(intSpinnerDig));
                    params.put("is_any_other_complains", strHealthComplaint);

                    params.put("is_diet_suitable_for_you", rgIsSuitable);
                    params.put("will_your_daily_routine_same", rgRutreason);

                    params.put("what_will_be_change",strRutreason);
                    params.put("eating_out", strOutfasting);
                    params.put("date_time_to_callback",  callbackdate+ "+" +callbacktime );
                    params.put("file_name", filename);
                    params.put("encoded_file", IMG);
                    return params;
                }
            };
            mq.add(stringRequest);


    }

                @Override
                protected void onActivityResult ( int requestCode, int resultCode, Intent data){
                    super.onActivityResult(requestCode, resultCode, data);

                    if (requestCode == IMG_REQUEST &&
                            resultCode == RESULT_OK &&
                            data != null &&
                            data.getData() != null) {
                        Uri filePath = data.getData();
                        filename = filePath.toString();
                        filename= filename.substring(filename.lastIndexOf("/") + 1);
                        tv_chused.setText(filename);


                        //String filename=path.substring(path.lastIndexOf("/")+1);
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                            Bitmap lastBitmap = null;
                            lastBitmap = bitmap;
                            //encoding image to string
                            //  String image = getStringImage(lastBitmap);
                            // Log.d("image",image);
                            //passing the image to volley
                            //getStringImage(bitmap);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            lastBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] imageBytes = baos.toByteArray();
                            encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                private void showFileChooser () {
                    Intent pickImageIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    pickImageIntent.setType("image/*");
                    pickImageIntent.putExtra("aspectX", 1);
                    pickImageIntent.putExtra("aspectY", 1);
                    pickImageIntent.putExtra("scale", true);
                    pickImageIntent.putExtra("outputFormat",
                            Bitmap.CompressFormat.JPEG.toString());

                    startActivityForResult(pickImageIntent, IMG_REQUEST);
                }
    public void timePicker() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                conTime.setText(selectedHour + ":" + selectedMinute);
                strTime=selectedHour + ":" + selectedMinute;
                endTme=selectedHour + ":" + selectedMinute;
                callbacktime=selectedHour + ":" + selectedMinute;

                //dt=selectedHour + ":" + selectedMinute;
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
        DatePicker mdatepick;
    }

}