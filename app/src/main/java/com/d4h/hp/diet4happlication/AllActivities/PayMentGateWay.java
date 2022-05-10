package com.d4h.hp.diet4happlication.AllActivities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.AllActivities.Signup.SignupName;
import com.d4h.hp.diet4happlication.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.json.JSONException;
import org.json.JSONObject;


public class PayMentGateWay extends PayMentGateWays implements PaymentResultWithDataListener {


    private static final String TAG = PayMentGateWay.class.getSimpleName();

    WebView webView;
    RazorpayClient razorpayClient;

    static String FIrstname,Lastname, getPaybaleAmount, getUserId, price,username,userMob;
    int txnid;

    SharedPreferences signUpprefer;
    RequestQueue requestQueue;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
            signUpprefer.edit().clear();
            startActivity(new Intent(this,SignupName.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Checkout.preload(getApplicationContext());
         startPayment();
    }

    public void startPayment() {
        signUpprefer = getSharedPreferences("SignUpData", 0);
        price=signUpprefer.getString("plan_cost","");
        userMob=signUpprefer.getString("signupNum","");
        username=signUpprefer.getString("dialogemail","");
     /*
       You need to pass current activity in order to let Razorpay create CheckoutActivity
      */
        final Activity activity = this;

        final Checkout co = new Checkout();
        co.setFullScreenDisable(true);
        try {
             razorpayClient = new RazorpayClient("key_id", "key_secret");
        } catch (RazorpayException e) {
            e.printStackTrace();
        }


        try {
            JSONObject options = new JSONObject();
            options.put("name", "Diet4Health");
            options.put("description", "Health Diet Plan");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://www.diet4health.in/public/images/resources/logo.png");
            options.put("currency", "INR");
            options.put("payment_capture", "1");
            options.put("amount",1000 );

          /*  options.put("razorpay_payment_id",signUpprefer.getString("paymentId","") );
            options.put("signature",signUpprefer.getString("signature",""));
            options.put("orderId",signUpprefer.getString("orderId",""));*/

        //   options.put("amount",Integer.parseInt(price)*100 );

            JSONObject preFill = new JSONObject();
            preFill.put("email", username);
            preFill.put("contact", userMob);

            options.put("prefill", preFill);
          //  razorpayClient.Payments.capture("payment_id", options);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }

    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */



    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
       /* Toast.makeText(PayMentGateWay.this, "Welcome to diet4health..." +
                "we have sent your user credentials to your email..!Thank You", Toast.LENGTH_LONG).show();*/
        successdiloague();

        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
              /*  String paymentId = paymentData.getPaymentId();
        String signature = paymentData.getSignature();
        String orderId = paymentData.getOrderId();

        signUpprefer.edit().putString("paymentId",paymentId).apply();
        signUpprefer.edit().putString("signature",signature).apply();
        signUpprefer.edit().putString("orderId",orderId).apply();
        signUpprefer.edit().commit();
        try {



            ///////calling api for patient registeration
            String url="http://192.168.163.1/d4h_api/handle_signup.php?"+
                    "txn_id="+paymentId+
                    "&p_status=success"+
                    "&signUpid="+signUpprefer.getString("signUpid","")+
                    "&email="+signUpprefer.getString("dialogemail","")+
                    "&fname"+signUpprefer.getString("signupFirstName","")+
                    "&lname"+signUpprefer.getString("signupLastName","")+
                    "&mob"+signUpprefer.getString("signupNum","")+
                    "&gender"+signUpprefer.getString("gender","")+
                    "&height"+signUpprefer.getString("height","")+
                    "&age"+signUpprefer.getString("age","")+
                    "&weight"+signUpprefer.getString("weight","")+
                    "&plan_info"+signUpprefer.getString("plan_info","")+
                    "&plan_due"+signUpprefer.getString("plan_due","")+
                    "&plan_cost"+price;
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject oout=response.getJSONObject("output");
                                Toast.makeText(PayMentGateWay.this, "Welcome to diet4health..." +
                                        "we have sent your user credentials to your email..!Thank You", Toast.LENGTH_LONG).show();
                                signUpprefer.edit().clear();
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(PayMentGateWay.this, "payment failed", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue=Volley.newRequestQueue(this);
            requestQueue.add(jsonObjectRequest);
            /////////////////////here reg ends

        } catch (Exception e) {
            //  Log.e(TAG, "Exception in onPaymentSuccess", e);
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }*/



    }
/*  @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {



            ///////calling api for patient registeration
            String url="http://192.168.163.1/d4h_api/handle_signup.php?"+
                    "txn_id="+razorpayPaymentID+
                    "&p_status=success"+
                    "&signUpid="+signUpprefer.getString("signUpid","")+
                    "&email="+signUpprefer.getString("dialogemail","")+
                    "&fname"+signUpprefer.getString("signupFirstName","")+
                    "&lname"+signUpprefer.getString("signupLastName","")+
                    "&mob"+signUpprefer.getString("signupNum","")+
                    "&gender"+signUpprefer.getString("gender","")+
                    "&height"+signUpprefer.getString("height","")+
                    "&age"+signUpprefer.getString("age","")+
                    "&weight"+signUpprefer.getString("weight","")+
                    "&plan_info"+signUpprefer.getString("plan_info","")+
                    "&plan_due"+signUpprefer.getString("plan_due","")+
                    "&plan_cost"+price;
           JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                   new Response.Listener<JSONObject>() {
               @Override
               public void onResponse(JSONObject response) {
                   try {
                       JSONObject oout=response.getJSONObject("output");
                       Toast.makeText(PayMentGateWay.this, "Welcome to diet4health..." +
                               "we have sent your user credentials to your email..!Thank You", Toast.LENGTH_LONG).show();
                       signUpprefer.edit().clear();
                       startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                   } catch (JSONException e) {
                       e.printStackTrace();
                   }

               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   Toast.makeText(PayMentGateWay.this, "payment failed", Toast.LENGTH_SHORT).show();
               }
           });
           requestQueue=Volley.newRequestQueue(this);
           requestQueue.add(jsonObjectRequest);
           /////////////////////here reg ends

        } catch (Exception e) {
          //  Log.e(TAG, "Exception in onPaymentSuccess", e);
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }

    *//**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     *//*
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {

            try {
                String url="http://192.168.163.1/d4h_api/handle_signup.php?"+"txn_id="+""
                        +"&p_status=failed"+"&signUpid="+signUpprefer.getString("signUpid","")+
                        "&email="+signUpprefer.getString("dialogemail","");
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                signUpprefer.edit().clear();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue=Volley.newRequestQueue(this);
                requestQueue.add(jsonObjectRequest);

            } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }*/
    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {

    }
    public void successdiloague(){
        final Dialog dialog = new Dialog(PayMentGateWay.this);
        dialog.setContentView(R.layout.layout_success_payment);
        dialog.setTitle("Payment successful");
        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimaryDark);
        dialog.getWindow().setTitleColor(getResources().getColor(R.color.colorPrimary));
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.myloginbutton));

        //dialog.getWindow().setLayout(500,400);
        dialog.getWindow();
      /*  int dividerId = dialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = dialog.findViewById(dividerId);*/
        Button button=dialog.findViewById(R.id.ps);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }

}
