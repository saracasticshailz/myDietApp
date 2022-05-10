package com.d4h.hp.diet4happlication.AllActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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
import com.d4h.hp.diet4happlication.AllActivities.Signup.SignupName;
import com.d4h.hp.diet4happlication.Mysingleton;

import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnLogin,signup;
    private EditText inputEmail,inputDialogemail;
    private EditText inputPassword;
    private TextView txtViewForgot,zz;
    //private ProgressBar progressBar;
    private AlertDialog.Builder builder;
    String username;
    String password;
    SharedPreferences loginPreferences;
    RequestQueue requestQueue;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        loginPreferences = getSharedPreferences("UserData", 0);

        inputEmail = (EditText) findViewById(R.id.edittext_login);
        inputPassword = (EditText) findViewById(R.id.edittextpassword);
        progressBar = (ProgressBar) findViewById(R.id.id_progressbarlogin);
        btnLogin = (Button) findViewById(R.id.btn_login);
        signup = (Button) findViewById(R.id.signup);
        txtViewForgot=(TextView) findViewById(R.id.txtview_forgot_pass);

        // zz=(TextView) findViewById(R.id.zz);
        //inputEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.person,0,0,0);
        // inputPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lock, 0, 0, 0);
        // requestQueue=Volley.newRequestQueue(this);
        loginPreferences= getSharedPreferences("UserData", 0);

        signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),SignupName.class));
        //startActivity(new Intent(getApplicationContext(),KetogenicPlanActivity.class));


    }
});
        txtViewForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,ForgotPass.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                isLogin(username,password);

            /*Intent i=new Intent(LoginActivity.this,TemporaryActivity.class);
            startActivity(i);*/
            }
        });
        String username1 = loginPreferences.getString("email",null);
        if(username1 != null){
            Intent i=new Intent(LoginActivity.this,NavigationDrawerActivity.class);
            startActivity(i);
            finish();

        }else{
            getApplicationContext();
        }


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

    public void isLogin(String usrname, final String pasword) {

   /*     final ArrayList<String> weightDate = new ArrayList<String>();
        final ArrayList<String> weightWeight = new ArrayList<String>();
        final ArrayList<String> BmiDate = new ArrayList<String>();
        final ArrayList<String> Bmicount = new ArrayList<String>();*/
        final String strEmail, strPass;

        strEmail = inputEmail.getText().toString().trim();
        strPass = inputPassword.getText().toString().trim();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String curdate = sdf.format(new Date());
        if (strEmail.equals("")) {
            inputEmail.setError("Please Enter Email");
            progressBar.setVisibility(View.GONE);
        } else if (strPass.equals("")) {
            inputPassword.setText("");
            inputPassword.setError("Please Enter Password");
            progressBar.setVisibility(View.GONE);
        }else if(!(isValidEmail(strEmail))){
            inputEmail.setText("");
            inputEmail.setError("Please Enter Valid Email");
            progressBar.setVisibility(View.GONE);
        }
        else {
        String url= "https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_login.php?" + "username=" + strEmail +"&password=" + strPass +"&date=" + curdate;

            progressBar.setVisibility(View.VISIBLE);
            // Toast.makeText(this, "Date"+date, Toast.LENGTH_SHORT).show();
            JsonObjectRequest objectRequest =
                    new JsonObjectRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {

                                        JSONObject profile = response.getJSONObject("profile");
                                       String code= profile.getString("code");
                                        Toast.makeText(LoginActivity.this, ""+code, Toast.LENGTH_LONG).show();
                                            String UserId = profile.getString("user_id");
                                            String STATUS = profile.getString("STATUS");
                                            String name = profile.getString("name");
                                            String gender = profile.getString("gender");
                                            String email = profile.getString("email");
                                            String contact = profile.getString("contact");
                                            String city = profile.getString("city");
                                            String state = profile.getString("state");
                                            String date_of_birth = profile.getString("date_of_birth");
                                            String profile_img = profile.getString("profile_img");

                                            String quote = response.getString("quote");

                                            loginPreferences.edit().putString("patient_id", UserId).apply();
                                            loginPreferences.edit().putString("STATUS", STATUS).apply();
                                            loginPreferences.edit().putString("name", name).apply();
                                            // Toast.makeText(LoginActivity.this, "name"+data.getString("name"), Toast.LENGTH_SHORT).show();
                                            loginPreferences.edit().putString("gender", gender).apply();
                                            loginPreferences.edit().putString("email", email).apply();
                                            loginPreferences.edit().putString("contact", contact).apply();
                                            loginPreferences.edit().putString("city", city).apply();
                                            loginPreferences.edit().putString("state", state).apply();
                                            loginPreferences.edit().putString("date_of_birth", date_of_birth).apply();
                                            loginPreferences.edit().putString("quote", quote).apply();
                                            loginPreferences.edit().putString("profile_img", profile_img).apply();
                                            progressBar.setVisibility(View.GONE);
                                            loginPreferences.edit().putBoolean("is_loggedin", true).apply();
                                            //Toast.makeText(LoginActivity.this, "weightDate"+weightDate, Toast.LENGTH_SHORT).show();

                                            //  loginPreferences.edit().putString("Bmi_Array",bmi).apply();
                                        loginPreferences.edit().commit();


                                            Intent login = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
                                            startActivity(login);
                                            finish();

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressBar.setVisibility(View.GONE);
                           // Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            String message = null;
                            if (error instanceof NetworkError) {
                                message = "Cannot connect to Internet...Please check your connection!";
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
                                //Toast.makeText(getApplicationContext(), PlaybackStateCompat.ErrorCode.errorCodeMap.get(errorCode), Toast.LENGTH_SHORT).show();
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
            Mysingleton.getMysingleton(this).addToRequestque(objectRequest);
        }
    }

      /*  JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d("LoginActivity", "login Response: " + response.toString());
              //Toast.makeText(LoginActivity.this, "cmn "+response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONObject output=response.getJSONObject("output");
                    int statuscode =output.getInt("code");
                   // Toast.makeText(LoginActivity.this, "status is "+statuscode, Toast.LENGTH_SHORT).show();
                    *//*if (username.equals("")) {
                        Toast.makeText(LoginActivity.this, "Please Enter Email Address", Toast.LENGTH_SHORT).show();
                    }//
                   // else if(pasword.equals("")){
                        Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    }else if(pasword.length()<7){
                        Toast.makeText(LoginActivity.this, "Password should be greter than seven number", Toast.LENGTH_SHORT).show();
                    }//
                     if(statuscode==0){
                        //Toast.makeText(LoginActivity.this, "status is "+statuscode, Toast.LENGTH_SHORT).show();
                        }
                    else if(statuscode==2){
                       // Toast.makeText(LoginActivity.this, "status is "+statuscode, Toast.LENGTH_SHORT).show();

                    }else if(statuscode==1){
                        JSONObject data=output.getJSONObject("data");
                        Toast.makeText(LoginActivity.this, "status is "+statuscode, Toast.LENGTH_SHORT).show();
                        loginPreferences.edit().putString("patient_id", data.getString("user_id")).apply();
                        loginPreferences.edit().putString("STATUS", data.getString("STATUS")).apply();
                        loginPreferences.edit().putString("name", data.getString("name")).apply();
                       // Toast.makeText(LoginActivity.this, "name"+data.getString("name"), Toast.LENGTH_SHORT).show();
                        loginPreferences.edit().putString("gender", data.getString("gender")).apply();
                        loginPreferences.edit().putString("email", data.getString("email")).apply();
                        loginPreferences.edit().putString("contact", data.getString("contact")).apply();
                        loginPreferences.edit().putString("city", data.getString("city")).apply();
                        loginPreferences.edit().putString("state", data.getString("state")).apply();
                        loginPreferences.edit().commit();



                        progressBar.setVisibility(View.GONE);
                       // loginPreferences.edit().putBoolean("is_loggedin", true).apply();
                       // Toast.makeText(LoginActivity.this, "the result is"+response.toString(), Toast.LENGTH_SHORT).show();
                       // Toast.makeText(LoginActivity.this, "status is "+statuscode, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, NavigationDrawerActivity.class);
                        startActivity(i);
                       finish();
                    }
                    } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("LOGIN_ACT", "Error: " + error.getMessage());
                //Toast.makeText(LoginActivity.this, "somwrong"+error.toString(), Toast.LENGTH_SHORT).show();
                }
                });
        Mysingleton.getMysingleton(this).addToRequestque(objectRequest);*/

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


   /*
    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 7) {
            return true;
        }
        return false;
    }*/
}

















