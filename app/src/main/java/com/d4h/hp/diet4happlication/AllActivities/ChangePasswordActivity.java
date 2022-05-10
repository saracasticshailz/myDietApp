package com.d4h.hp.diet4happlication.AllActivities;


import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ProgressBar;
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

import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText edittext_old_password,edittext_new_password,edittext_confirm_password;
    String strOldPass,strNewPass,strConPass,pt_id;
    RequestQueue mq;
    Button btn_changePass;
    SharedPreferences sharedPreferences;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_change_password);

        edittext_old_password=findViewById(R.id.edittext_old_password);
        edittext_new_password=findViewById(R.id.edittext_new_password);
        edittext_confirm_password=findViewById(R.id.edittext_confirm_password);

        progressBar =findViewById(R.id.nusta_passChange);

        sharedPreferences = this.getSharedPreferences("UserData", 0);
        pt_id = sharedPreferences.getString("patient_id", "");

         btn_changePass=findViewById(R.id.btn_changePass);

        mq=Volley.newRequestQueue(this);

        toolbar=findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Change Password");
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

        btn_changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* edittext_old_password.setText("");
                edittext_confirm_password.setText("");
                edittext_new_password.setText("");*/

                strOldPass=edittext_old_password.getText().toString();
                strNewPass=edittext_new_password.getText().toString();
                strConPass=edittext_confirm_password.getText().toString();
               // Toast.makeText(ChangePasswordActivity.this, ""+strOldPass, Toast.LENGTH_SHORT).show();
                if(strOldPass.equals("") ){
                    edittext_old_password.setError("ENTER CURRENT PASSWORD");
                }else if(strNewPass.equals("") ){
                    edittext_new_password.setError("ENTER CONFIRM PASSWORD");
                }else if(strConPass.equals("")){
                    edittext_confirm_password.setError("ENTER CONFIRM PASSWORD");

                }else if(!(strConPass.equals(strNewPass)))
                {
                    edittext_confirm_password .setError("New Password And Confirm Password Should be Same");
                }else {
                    changpas();
                }

            }
        });

}
public void changpas(){
progressBar.setVisibility(View.VISIBLE);
    String changeURL="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_change_pass.php?";
    final StringRequest request=new StringRequest(Request.Method.POST, changeURL, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            progressBar.setVisibility(View.GONE);
            try {

                JSONObject jsonObject=new JSONObject(response);
                JSONObject out=jsonObject.getJSONObject("output");
                String msg=out.getString("message");
                Toast.makeText(ChangePasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
                if(msg.equals("invalid current password")){

                    edittext_old_password.setError("Invalid Old Password");

                }else {

                    SharedPreferences preferences = getSharedPreferences("UserData", getApplicationContext().MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.commit();

                    Intent i = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
                //onBackPressed();
                  /*  String code=out.getString("code");
                    if(code=="200"){
                        onBackPressed();
                    }
*/

            } catch (JSONException e) {
                e.printStackTrace();
            }


                        /*  edittext_old_password.setText("");
                edittext_new_password.setText("");
                edittext_confirm_password.setText("");
                //Toast.makeText(ChangePasswordActivity.this, ""+response, Toast.LENGTH_SHORT).show();*/
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
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
    }){
        @Override
        protected Map<String, String> getParams() {
            Map<String, String> params = new HashMap<String, String>();
            params.put("current_pass",strOldPass);
            params.put("confirm_pass",strConPass);
            params.put("new_pass",strNewPass);
            params.put("patient_id",pt_id);
            return  params;
        }
    };
    mq.add(request);
}
}