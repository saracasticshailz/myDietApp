package com.d4h.hp.diet4happlication.AllActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.AllAdaptors.RecieverAdapter;
import com.d4h.hp.diet4happlication.AllDataModels.RecieverModel;

import com.d4h.hp.diet4happlication.AllFragments.DashBoardFragment;
import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class ChatActivity extends AppCompatActivity {

    RecyclerView rv_chat_sender,rv_chat_reciever;

    SharedPreferences loginPreferences;
    // private RecyclerView.LayoutManager layoutManager,layoutManagers;
    // private List<SendchatModel> sendchatModel;
    private List<RecieverModel> recieverModel;
    private RecyclerView.Adapter sendadapter;
    private RecyclerView.Adapter recvadapter;
    // private SendchatModel sendmodel;
    private RecieverModel model;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    Button chatBtn;
    EditText ed_chat;
    String pt_id;
    Toolbar toolbar;
    RequestQueue mqz;
    LinearLayoutManager  layoutManager;
    private Parcelable recyclerViewState;
    Handler mHandler = new Handler();
    int delay = 1000; // delay for 1 sec.
    int period = 10000; // repeat every 10 sec.


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chat_screen);
        rv_chat_reciever=findViewById(R.id.rv_chat_reciever);
        //  rv_chat_sender=findViewById(R.id.rv_chat_sender);

        progressBar =findViewById(R.id.pb_chat);

        chatBtn=findViewById(R.id.button_chat_send);
        ed_chat=findViewById(R.id.edittext_chat);

        toolbar=findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ask Your Query");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NavigationDrawerActivity.class));

            }
        });


           /* new MaterialTapTargetPrompt.Builder(this)
                    .setTarget(R.id.toolbar_all)
                    .setAnimationInterpolator(new FastOutSlowInInterpolator())
                    .setPrimaryText("Wish you best luck..!")
                    .setSecondaryText("Lets start a new Health journey!")
                    .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                    {
                        @Override
                        public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state)
                        {
                            if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                            {
                                //showOverflowPrompt(view);

                            *//*    loginPreferences.edit().putString("tag","1").apply();
                                loginPreferences.edit().commit();*//*
                                Fragment fragment = new DashBoardFragment();
                                FragmentManager homefm = getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction9 = homefm.beginTransaction();
                                fragmentTransaction9.replace(R.id.frame_container, fragment).addToBackStack("back").commit();


                            }
                        }
                    })
                    .show();
*/
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setStackFromEnd(true);

        layoutManager.setReverseLayout(false);

        recieverModel=new ArrayList<>();
        recvadapter=new RecieverAdapter(this,recieverModel);

        sharedPreferences = getSharedPreferences("UserData", 0);
        pt_id=sharedPreferences.getString("patient_id","");
        mqz =Volley.newRequestQueue(getApplicationContext());

        ViewChat();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                ViewChat();
                               // Toast.makeText(ChatActivity.this, "refresh", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (Exception e) {
                    }
                }
            }
        }).start();

        //   sendchatModel=new ArrayList<>();
        //  sendadapter=new SenderAdapter(this,  sendchatModel);
        // rv_chat_reciever.addFocusables(recieverModel,,);
        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // progressBar.setVisibility(View.VISIBLE);
                InsertChat();

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

    public void ViewChat(){

        //https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_chat.php?action=Fetch&sender_id=197

       // progressBar.setVisibility(View.VISIBLE);
        String uri="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_chat.php?" +
                "action=Fetch" +
                "&sender_id="+pt_id;
 /* String getchaturl="http://diet4health.org/public/diet4health.in/diet-panel/d4h_api_v1.1/handle_chat.php?"+
  "action=Fetch"
             +"sender_id="+pt_id+"&message=" +msg+"&date="+curd+"&time="+curtime;*/
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, uri,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                recieverModel.clear();
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray output=response.getJSONArray("output");
                    for(int i=0;i<=output.length();i++){
                        JSONObject jsonObject=output.getJSONObject(i);
                        String senderId=jsonObject.getString("sender_id");
                        String message=jsonObject.getString("message");
                        String showDate=jsonObject.getString("showDate");
                        String chat_time=jsonObject.getString("chat_time");

                        if(!senderId.equals(pt_id)){
                            model=new RecieverModel();
                            model.setRecievermsg(message);
                            model.setRecievertime(chat_time);
                            model.setRecvd_message_date(showDate);


                        }else {
                            model=new RecieverModel();
                            model.setSend_message_body(message);
                            model.setSend_message_time(chat_time);
                            model.setSend_message_date(showDate);

                        }
                        recieverModel.add(model);

                    }

                    recvadapter.notifyDataSetChanged();
                    recvadapter.notifyItemRangeChanged(0, recvadapter.getItemCount());


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setRecyclerViewReciever();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null || error.networkResponse == null) {
                    return;
                }

                String body;
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    body = new String(error.networkResponse.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // exception
                }
            }
        });

        mqz.add(jsonObjectRequest);
        /*new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ViewChat();
            }
        }, 0, 10);*/
    }
    public void InsertChat(){

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        String curtime=hour+":"+minute;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String curd = sdf.format(new Date());

        String msg=ed_chat.getText().toString();
        msg=msg.replace(" ","+");
        progressBar.setVisibility(View.VISIBLE);

       // https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_chat.php?action=Add&sender_id=197&message=Hi&date=2018-01-01&time=11:00
        String insertchatURI="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_chat.php?action=Add" +
                "&sender_id=" +pt_id+
                "&message=" +msg+
                "&date=" +curd+
                "&time="+curtime;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, insertchatURI,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                ed_chat.setText("");
                //recieverModel.clear();
                ViewChat();
                //progressBar.setVisibility(View.GONE);
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
        });
        mqz.add(jsonObjectRequest);


    }

    public void setRecyclerViewReciever(){
        rv_chat_reciever.setLayoutManager(layoutManager);
        rv_chat_reciever.setHasFixedSize(true);
        rv_chat_reciever.setAdapter(recvadapter);
        recyclerViewState = rv_chat_reciever.getLayoutManager().onSaveInstanceState();
        rv_chat_reciever.getLayoutManager().onRestoreInstanceState(recyclerViewState);


        // rv_chat_reciever.smoothScrollToPosition(rv_chat_reciever.getAdapter().getItemCount() - 1);
    }
   /* public void setRecyclerViewSender(){
        rv_chat_sender.setLayoutManager(layoutManagers);
        rv_chat_sender.setHasFixedSize(true);
        rv_chat_sender.setAdapter(sendadapter);
    }*/
}