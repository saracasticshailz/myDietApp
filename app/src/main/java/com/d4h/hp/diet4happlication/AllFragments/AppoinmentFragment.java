package com.d4h.hp.diet4happlication.AllFragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.d4h.hp.diet4happlication.AllActivities.NavigationDrawerActivity;
import com.d4h.hp.diet4happlication.AllDataModels.BookApponmentModel;
import com.d4h.hp.diet4happlication.R;


import org.json.JSONObject;

import java.util.Calendar;

public class AppoinmentFragment extends Fragment {

    private Context context;
    private Spinner spinnerAppTime;
    private RadioGroup rgAppType, rgOnline;
    private RadioButton rbOnline, rbOffline, rbAudio, rbVideo;
    private TextView txtviewOnline, txtviewOffline, txtviewAppType, txtviewAppDate;
    private EditText edittextComment;
    private int mDay,mMonth,mYear;
    private Button btnSubmit,btnCancle;
    String  strDate="", strTime="", strComment="",strLocation="",strType="";
    String pt_id;
    private BookApponmentModel bookApponmentModel;
    private Calendar myCalendar;
    SharedPreferences sharedPreferences;


    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_appoinment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnerAppTime = view.findViewById(R.id.spinner_appo_time);
        rgAppType = view.findViewById(R.id.rg_apptype);
        rgOnline = view.findViewById(R.id.rg_oltype);
        rbOnline = view.findViewById(R.id.rb_online);
        rbOffline = view.findViewById(R.id.rb_offline);
        rbAudio = view.findViewById(R.id.rb_audio);
        rbVideo = view.findViewById(R.id.rb_video);
        txtviewOnline = view.findViewById(R.id.txtview_olType);
        txtviewAppType = view.findViewById(R.id.txtview_apptype);
        txtviewAppDate = view.findViewById(R.id.edittext_appo_date);
        //edittextComment = view.findViewById(R.id.edittext_appocomment);
        btnSubmit=view.findViewById(R.id.btn_appsubmit);
        bookApponmentModel=new BookApponmentModel();
        btnCancle=view.findViewById(R.id.btn_app_cancle);

        myCalendar=Calendar.getInstance();
        mDay=myCalendar.get(Calendar.DATE);
        mMonth=myCalendar.get(Calendar.MONTH);
        mYear=myCalendar.get(Calendar.YEAR);

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(
                "Appoinment Request");
        txtviewAppDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                txtviewAppDate.setText(year + "-" + (month + 1) + "-" + day);

                               // Toast.makeText(context, strDate, Toast.LENGTH_SHORT).show();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();

            }
        });
        spinnerAppTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(
                    AdapterView<?> adapterView, View view,
                    int i, long l) {
                 strTime = spinnerAppTime.getSelectedItem().toString().trim();


             //   Toast.makeText(context, "spinner"+strTime, Toast.LENGTH_SHORT).show();
            }

            public void onNothingSelected(
                    AdapterView<?> adapterView) {

            }
        });

        rgAppType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_offline:
                        txtviewOnline.setVisibility(View.GONE);
                        rgOnline.setVisibility(View.GONE);
                            strType="offline";
                           strLocation="vashi";
                       // Toast.makeText(context, strLocation, Toast.LENGTH_SHORT).show();
                        break;

                        case R.id.rb_online:
                        txtviewOnline.setVisibility(View.VISIBLE);
                        rgOnline.setVisibility(View.VISIBLE);
                        strType="online";
                }
            }
        });
        rgOnline.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    case R.id.rb_audio:
                        strLocation="audio";
                       // Toast.makeText(context, strLocation, Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.rb_video:
                        strLocation="Video";
                       // Toast.makeText(context, strLocation, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new DashBoardFragment();
                FragmentManager homefm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction9 = homefm.beginTransaction();
                fragmentTransaction9.replace(R.id.frame_container, fragment, null).commit();
            }
        });
    }
    public void submitData(){

        sharedPreferences = context.getSharedPreferences("UserData", 0);
        pt_id=sharedPreferences.getString("patient_id","");
        strComment="";
       // strComment = edittextComment.getText().toString().replaceAll("","%20");
        strDate=txtviewAppDate.getText().toString().trim();

        if(strType.equals(""))//(!rbAudio.isChecked()||!rbVideo.isChecked())
        {
            rbOnline.setError("Please Add Appoinment Type");
        }
        else if(strLocation.equals("")){
            rbAudio.setError("Please Add Appoinment Online Type");
        }else if(strDate.equals("")) {
            txtviewAppDate.setError("Please Add Date");
        }else {

            /*"http://uat.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_appointment.php?" + "type=" + strType +
                    "&location=" + strLocation + "&appointment_time=" + strTime + "&patient_id=" + pt_id + "&comment=" + strComment
                    + "&appointment_date=" + strDate;
*/
            String url =
                    "https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_appointment.php?" + "type=" + strType +
                            "&location=" + strLocation + "&appointment_time=" + strTime + "&patient_id=" + pt_id + "&comment=" + strComment
                            + "&appointment_date=" + strDate;


            RequestQueue mqz = Volley.newRequestQueue(context);
            JsonObjectRequest objectRequestz = new JsonObjectRequest(Request.Method.GET, url,
                    null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(context, "Appointment Request Accepted", Toast.LENGTH_SHORT).show();

                    Fragment fragment = new DashBoardFragment();
                    FragmentManager homefm = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction9 = homefm.beginTransaction();
                    fragmentTransaction9.replace(R.id.frame_container, fragment, null).commit();


                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // Toast.makeText(context, "Appoinment Network Error", Toast.LENGTH_SHORT).show();
                    String message = null;
                    if (error instanceof NetworkError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ServerError) {
                        message = "The data could not be found. Please try again after some time!!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    } else if (error instanceof AuthFailureError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ParseError) {
                        message = "Parsing error! Please try again after some time!!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    } else if (error instanceof NoConnectionError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    } else if (error instanceof TimeoutError) {
                        message = "Connection TimeOut! Please check your internet connection.";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }

                }
            });
            mqz.add(objectRequestz);
        }

    }


}
