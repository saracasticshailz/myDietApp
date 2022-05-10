package com.d4h.hp.diet4happlication.AllActivities;



import android.app.Dialog;
import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
//import com.ashish.hp.diet4happlication.AllFragments.NotificationFragement;
import com.bumptech.glide.Glide;
import com.d4h.hp.diet4happlication.AllFragments.AppoinmentFragment;
import com.d4h.hp.diet4happlication.AllFragments.AssessmentFragement;
import com.d4h.hp.diet4happlication.AllFragments.ContactUsFragement;
import com.d4h.hp.diet4happlication.AllFragments.DashBoardFragment;
import com.d4h.hp.diet4happlication.AllFragments.DisclaimerFragement;
import com.d4h.hp.diet4happlication.AllFragments.EscallationFragement;
import com.d4h.hp.diet4happlication.AllFragments.FeedBackQueAnsFragement;
import com.d4h.hp.diet4happlication.AllFragments.LabFragement;
import com.d4h.hp.diet4happlication.AllFragments.NotificationFragment;
import com.d4h.hp.diet4happlication.AllFragments.ProfileFragment;
import com.d4h.hp.diet4happlication.AllFragments.ReviewReportFragement;
import com.d4h.hp.diet4happlication.AllFragments.SubscriptionFragement;
import com.d4h.hp.diet4happlication.AllFragments.TermsAndConditionFragement;
import com.d4h.hp.diet4happlication.AllFragments.WeightFrag;
import com.d4h.hp.diet4happlication.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DrawerLayout drawer;
    Fragment fragment;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    SharedPreferences loginpreference;
    TextView txtviewEmail,txtviewName,txtviewNotification,txtview_notification;
    SharedPreferences loginPrefferences;
    ImageView imageView,ivBell,ivNoti;
    String pt_id;
    Handler mHandler = new Handler();
    private Menu menu;
    NotificationCompat.Builder builder;
     RequestQueue requestQueue;
     String  curtime,updatingTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nav_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DashBoard");

         builder =
                new NotificationCompat.Builder(this);

        //Initialization
        ivBell=findViewById(R.id.iv_bell);
        ivNoti=findViewById(R.id.iv_nofication);
        txtviewNotification=findViewById(R.id.txtview_noti);
        txtview_notification=findViewById(R.id.txtview_notification);
        bottomNavigationView=findViewById(R.id.id_bottomnavigation);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        imageView=headerView.findViewById(R.id.imageView);
        txtviewEmail=headerView.findViewById(R.id.textView);
        txtviewName=headerView.findViewById(R.id.txtview_name);

        navigationView.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        headerView.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        headerView.getBackground().setAlpha(150);
        navigationView.getBackground().setAlpha(150);

        requestQueue=Volley.newRequestQueue(this);

        loginpreference =getApplicationContext().getSharedPreferences("UserData", 0);
        String img=loginpreference.getString("profile_img","");
        String name=(loginpreference.getString("name", ""));
        String email=(loginpreference.getString("email",""));

        txtviewName.setText(name);
        txtviewEmail.setText(email);
        Glide.with(NavigationDrawerActivity.this)
                .load(Uri.parse("https://diet4health.in/public/diet4health.in/diet-panel/"+img)).into(imageView);
       /* toolbar.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlack));*/

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        Thread.sleep(10000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                addNocationCount();
                                getNote();
                                // Toast.makeText(ChatActivity.this, "refresh", Toast.LENGTH_SHORT).show();
                                // Write your code here to update the UI.
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();

       ///////for Deactivated status
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
         curtime=hour+":"+minute;
         updatingTime="18:20";

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                        mHandler.post(new Runnable() {

                            @Override
                            public void run() {
                              //  if(updatingTime=="18:20"){

                                    DeactivatedStatus();
                             //   }
                            }
                        });
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }).start();

        ivNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancleNotification();
                Intent i=new Intent(NavigationDrawerActivity.this,ChatActivity.class);
                startActivity(i);
            }
        });
        ivBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReadNote();
                fragment = new NotificationFragment();
                FragmentManager rvfm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction7 = rvfm.beginTransaction();
                fragmentTransaction7.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_reviewreport:
                        fragment = new ReviewReportFragement();
                        FragmentManager rvfm = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction7 = rvfm.beginTransaction();
                        fragmentTransaction7.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                        break;
                    case R.id.nav_home:
                        fragment = new DashBoardFragment();
                        FragmentManager homefm = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction9 = homefm.beginTransaction();
                        fragmentTransaction9.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                        break;
                    case R.id.nav_plus:
                        linksDialog();
                        break;
                    case R.id.nav_weight:
                        fragment = new WeightFrag();
                        FragmentManager wtfm = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction8 = wtfm.beginTransaction();
                        fragmentTransaction8.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                        break;
                    case R.id.nav_nutricoach:
                        txtviewNotification.setVisibility(View.GONE);
                        cancleNotification();
                        Intent i=new Intent(NavigationDrawerActivity.this,ChatActivity.class);
                        startActivity(i);
                }
                return true;

            }
        });
        txtviewNotification.setVisibility(View.GONE);
        txtview_notification.setVisibility(View.GONE);
        // navigationView.getBackground().setColorFilter(0x80000000, PorterDuff.Mode.MULTIPLY);
        //headerView.getBackground().setColorFilter(0x80000000, PorterDuff.Mode.MULTIPLY);

        fragment=new DashBoardFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragment).addToBackStack("back").commit();


    }
    @Override
    public void onBackPressed() {

      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_pdashboard:
                // Toast.makeText(this, "hiiiii", Toast.LENGTH_SHORT).show();
                fragment = new DashBoardFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                break;
            case R.id.nav_profile:
                fragment = new ProfileFragment();
                // Toast.makeText(this, "hiiiii", Toast.LENGTH_SHORT).show();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fm.beginTransaction();
                fragmentTransaction1.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                break;
            case R.id.nav_termsconditions:
                // Toast.makeText(this, "hiiiii", Toast.LENGTH_SHORT).show();
                fragment = new TermsAndConditionFragement();
                FragmentManager tcfm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = tcfm.beginTransaction();
                fragmentTransaction2.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                break;

            /*case R.id.nav_bookappoinment:
                fragment = new AppoinmentFragment();
                FragmentManager appfm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction3 = appfm.beginTransaction();
                fragmentTransaction3.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                break;*/

            case R.id.nav_disclaimer:
                fragment = new DisclaimerFragement();
                FragmentManager disfm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction4 = disfm.beginTransaction();
                fragmentTransaction4.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                break;

            case R.id.nav_contactus:
                fragment = new ContactUsFragement();
                FragmentManager confm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction5 = confm.beginTransaction();
                fragmentTransaction5.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                break;

            /*case R.id.nav_assessment:
                fragment = new AssessmentFragement();
                FragmentManager assfm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction6 = assfm.beginTransaction();
                fragmentTransaction6.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                break;
*/
            case R.id.nav_escalation:
                fragment = new EscallationFragement();
                FragmentManager escfm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction7 = escfm.beginTransaction();
                fragmentTransaction7.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                break;

            case R.id.nav_subscription:
                fragment = new SubscriptionFragement();
                FragmentManager subfm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction8 = subfm.beginTransaction();
                fragmentTransaction8.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                break;


            case R.id.nav_logout:
                SharedPreferences preferences =getSharedPreferences("UserData",getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent i=new Intent(NavigationDrawerActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.nav_lab:
                fragment = new LabFragement();
                FragmentManager labfm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction9 = labfm.beginTransaction();
                fragmentTransaction9.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                break;
            case R.id.nav_feedback:
                fragment = new FeedBackQueAnsFragement();
                FragmentManager faqfm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction10 = faqfm.beginTransaction();
                fragmentTransaction10.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
                break;
            /*case R.id.nav_doc:
              startActivity(new Intent(this,PatientDoc.class));
                break;*/
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void linksDialog(){
        final Dialog dialog = new Dialog(NavigationDrawerActivity.this);
        dialog.setContentView(R.layout.layout_links);
        dialog.setCancelable(true);
        dialog.setTitle("Follow Us");
        dialog.show();
        Window window = dialog.getWindow();
        dialog.getWindow().setTitleColor(getResources().getColor(R.color.colorPrimary));
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.myloginbutton));
        dialog.getWindow();
        /*int dividerId = dialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = dialog.findViewById(dividerId);
        divider.setBackgroundColor(getResources().getColor(R.color.colorOrange));*/

        final ImageView ivFac,ivTwitt,ivGoogle,ivYou,ivInsta,ivPin;


        ivFac=dialog.findViewById(R.id.iv_facebook);
        ivTwitt=dialog.findViewById(R.id.iv_twitter);
        ivGoogle=dialog.findViewById(R.id.iv_google);
        ivYou=dialog.findViewById(R.id.iv_youtube);
        ivInsta=dialog.findViewById(R.id.iv_insta);
        ivPin=dialog.findViewById(R.id.iv_pinterest);

        /*ivFac.setImageResource(R.drawable.facebook);
        ivGoogle.setImageResource(R.drawable.google);
        ivTwitt.setImageResource(R.drawable.twitter);
        ivYou.setImageResource(R.drawable.youtube);
        ivInsta.setImageResource(R.drawable.instagram);
        ivPin.setImageResource(R.drawable.pinterest);*/

        ivFac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.facebook.com/diet4health";
                Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(i);
            }
        });
        ivInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.instagram.com/diet4health/";
                Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(i);
            }
        });
        ivGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://plus.google.com/u/0/+DietitianArtiJain";
                Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(i);
            }
        });
        ivPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://in.pinterest.com/diet4health/overview/";
                Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(i);
            }
        });
        ivTwitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://twitter.com/Diet4H";
                Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(i);
            }
        });
        ivYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://www.youtube.com/diet4healthmantra";
                Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(i);
            }
        });
    }

    public void addNocationCount(){

        loginpreference= this.getSharedPreferences("UserData", 0);
        pt_id=loginpreference.getString("patient_id","");
        String url="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/get_notification.php?patinet_id="+pt_id;
        final RequestQueue requestQueue=Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    //Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                    String chat_count=response.getString("chat_count");
                    txtviewNotification.setVisibility(View.VISIBLE);
                    if(!chat_count.equals("0")){
                        txtviewNotification.setText(chat_count);
                      /*  Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        builder.setSound(alarmSound);*/
                        Notification notification = builder.build();
                        notification.defaults |= Notification.DEFAULT_SOUND;
                        notification.defaults |= Notification.DEFAULT_VIBRATE;


                    }else {
                        txtviewNotification.setVisibility(View.GONE);
                    }


                } catch (JSONException e) {

                    e.printStackTrace();
                }

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
        requestQueue.add(jsonObjectRequest);
    }

    public void cancleNotification(){

        loginpreference= this.getSharedPreferences("UserData", 0);
        pt_id=loginpreference.getString("patient_id","");
        String url="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/read_message.php?patinet_id="+pt_id;

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                txtviewNotification.setVisibility(View.GONE);

                }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void DeactivatedStatus(){

        loginpreference= this.getSharedPreferences("UserData", 0);
        pt_id=loginpreference.getString("patient_id","");

        String getState="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/get_user_status.php?"+"patient_id="+pt_id;

        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, getState, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    String live=response.getString("live");
//                    Toast.makeText(NavigationDrawerActivity.this, ""+live, Toast.LENGTH_SHORT).show();
                    if(live.equals("0")){
                      //  Toast.makeText(NavigationDrawerActivity.this, "deactivated", Toast.LENGTH_SHORT).show();
                       // should be here//Intent
                        Intent i=new Intent(NavigationDrawerActivity.this,DeactiveUserActivity.class);
                        startActivity(i);
                        finish();
                        SharedPreferences preferences =getSharedPreferences("UserData",getApplicationContext().MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.commit();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(objectRequest);
    }

    public void getNote(){
        if(txtview_notification.getText().toString()=="0"){
            txtview_notification.setVisibility(View.GONE);
        }
        String url="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/get_diet_notification.php?"+"patinet_id="+pt_id;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //  Toast.makeText(NavigationDrawerActivity.this, ""+response.toString(), Toast.LENGTH_SHORT).show();

                try {
                    String note=response.getString("notification");


                    if(!(note.equals("0"))){
                        txtview_notification.setVisibility(View.VISIBLE);
                        txtview_notification.setText(note);
                        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        builder.setSound(uri);

                    }else {
                        txtview_notification.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }
    public void ReadNote(){
        String url="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/read_dietchart.php?"+"patinet_id="+pt_id;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //  Toast.makeText(NavigationDrawerActivity.this, ""+response.toString(), Toast.LENGTH_SHORT).show();

                txtview_notification.setVisibility(View.GONE);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }



}

