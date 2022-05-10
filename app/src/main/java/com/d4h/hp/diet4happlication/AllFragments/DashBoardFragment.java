package com.d4h.hp.diet4happlication.AllFragments;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.bumptech.glide.Glide;
import com.d4h.hp.diet4happlication.AllActivities.NavigationDrawerActivity;
import com.d4h.hp.diet4happlication.AllActivities.PatientDoc;
import com.d4h.hp.diet4happlication.AllActivities.PlanListActivity;
import com.d4h.hp.diet4happlication.AllActivities.ReferFriendActivity;
import com.d4h.hp.diet4happlication.AllActivities.Signup.SignupName;
import com.d4h.hp.diet4happlication.AllActivities.WalletActivity;
import com.d4h.hp.diet4happlication.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;


public class DashBoardFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener
{

    Context context;
    ImageView ivDietplan,ivWorkout;
    SlidemyAdapter slidemyAdapter;
    ViewPager pager;
    int page_position=0;
    SharedPreferences loginPreferences;
    TextView tv_qt;
    ArrayList<String> weightDate=new ArrayList<String>();
    ArrayList<String> weightWeight=new ArrayList<String>();
    ArrayList<String> BmiDate=new ArrayList<String>();
    ArrayList<String> Bmicount=new ArrayList<String>();
    GraphView graph;
    LineGraphSeries<DataPoint> series;
    Button bmi,weight;
    Date date=null;
    RequestQueue mq1;
    TextView txtviewWeight,txtviewBmi,txtviewKg,txtviewKgm,txtviewStatus;
    private Double dweight,dbmi;
    private ImageView ivSadFace,ivBmiFace,ivRefer,ivWallet,ivDocuments,ivAssement,ivAppoinment;
    Fragment fragment;
    SharedPreferences.Editor editor;
    Button getplan;


    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }
    @Override
    public void onDestroy() {
        getActivity().finish();
        super.onDestroy();
    }

  /*  @Override
    public void onStop() {
        super.onStop();
        getActivity().finish();
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.layout_dashboardactivity,container,false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager = view.findViewById(R.id.iv_dashboard);
        ivDietplan = (ImageView) view.findViewById(R.id.iv_dietplan);
        ivWorkout = (ImageView) view.findViewById(R.id.iv_weight);
        txtviewWeight = view.findViewById(R.id.txtview_dash_weight);
        txtviewBmi = view.findViewById(R.id.txtview_dash_bmi);
        txtviewKgm = view.findViewById(R.id.txtview_kgm);
        txtviewKg = view.findViewById(R.id.txtview_kg);
        txtviewStatus = view.findViewById(R.id.txtview_status);
        ivSadFace = view.findViewById(R.id.iv_dash_weight);
        ivBmiFace = view.findViewById(R.id.iv_bmi);
        ivRefer=view.findViewById(R.id.iv_refer);
        ivWallet=view.findViewById(R.id.iv_wallet);
        ivAppoinment=view.findViewById(R.id.iv_appoinment);
        ivAssement=view.findViewById(R.id.iv_assesment);
        ivDocuments=view.findViewById(R.id.iv_document);
        getplan=view.findViewById(R.id.getplan);

        slidemyAdapter = new SlidemyAdapter(getContext());
        pager.setAdapter(slidemyAdapter);
        tv_qt = (TextView) view.findViewById(R.id.btn_slide);
        //graph=view.findViewById(R.id.graph);


        mq1 = Volley.newRequestQueue(getContext());
        loginPreferences = context.getSharedPreferences("UserData", 0);
        String qt = loginPreferences.getString("quote", "");
        String usid = loginPreferences.getString("patient_id", "");

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(
                "DashBoard");
        tv_qt.append(qt);

        getplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),PlanListActivity.class));
            }
        });
        ivAssement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new AssessmentFragement();
                FragmentManager assfm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction6 = assfm.beginTransaction();
                fragmentTransaction6.replace(R.id.frame_container, fragment).addToBackStack("back").commit();

            }
        });
        ivDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,PatientDoc.class));
            }
        });
        ivAppoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new AppoinmentFragment();
                FragmentManager appfm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction3 = appfm.beginTransaction();
                fragmentTransaction3.replace(R.id.frame_container, fragment).addToBackStack("back").commit();

            }
        });
        ivRefer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,ReferFriendActivity.class));
            }
        });
        ivWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,WalletActivity.class));
            }
        });
       //////////////Tap target


        if(!(loginPreferences.getString("tap","").equals("1"))){



                new MaterialTapTargetPrompt.Builder(getActivity())
                        .setTarget(view.findViewById(R.id.iv_dietplan))
                        .setPrimaryText("Hi.."+loginPreferences.getString("name","")+" Welcome to Diet4Health...Get Your Diet Chart Here !!")
                        .setSecondaryText("Tap the My Diet Plan for Diet Chart Suggestion & Food Dairy Submission..!")
                        .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                        {
                            @Override
                            public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state)
                            {
                                if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED )
                                {
                                    // User has pressed the prompt target
                                   // ivDietplan.setClickable(false);

                                    workout(view);
                                }
                            }
                        })
                        .show();


        }else {

        ivSadFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new WeightFrag();
                FragmentManager wtfm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction8 = wtfm.beginTransaction();
                fragmentTransaction8.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
            }
        });
        ivBmiFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new WeightFrag();
                FragmentManager wtfm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction8 = wtfm.beginTransaction();
                fragmentTransaction8.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
            }
        });
        txtviewStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new WeightFrag();
                FragmentManager wtfm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction8 = wtfm.beginTransaction();
                fragmentTransaction8.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
            }
        });
        // Toast.makeText(context, ""+qt, Toast.LENGTH_SHORT).show();
        // Weightdata();
        /*bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graph.removeAllSeries();
                Bmidata();
            }
        });*/
        /*weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graph.removeAllSeries();
                Weightdata();
            }
        });*/


        getCurrentDateBmi();

        //timer and swiper
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == 4) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                pager.setCurrentItem(page_position, true);
            }
        };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 100, 5000);

//dietview listner
        ivDietplan.setClickable(true);

        ivDietplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                fragment = new MyDietPlanFragement();
                FragmentManager dtfm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction7 = dtfm.beginTransaction();
                fragmentTransaction7.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
            }
        });


//workout listener
        ivWorkout.setClickable(true);
        ivWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment;
                fragment = new MyWorkOutFragement();
                FragmentManager wtfm = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction7;
                fragmentTransaction7 = wtfm.beginTransaction();
                fragmentTransaction7.replace(R.id.frame_container, fragment).addToBackStack("back").commit();
            }
        });

        String grapURL = "https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/dashboard.php?"
                + "patinet_id=" + usid;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, grapURL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int i = 0;
                        JSONArray Weight = null;
                        try {
                            Weight = response.getJSONArray("Weight");


                            for (i = 0; i < Weight.length(); i++) {
                                JSONObject jsonObject = Weight.getJSONObject(i);
                                String date = jsonObject.getString("date");
                                String weight = jsonObject.getString("weight");

                                weightDate.add(date);
                                weightWeight.add(weight);
                            }

                            JSONArray Bmi = response.getJSONArray("Bmi");
                            for (i = 0; i < Bmi.length(); i++) {
                                JSONObject jsonObject = Bmi.getJSONObject(i);
                                String date = jsonObject.getString("date");
                                String bmi_count = jsonObject.getString("bmi_count");
                                BmiDate.add(date);
                                Bmicount.add(bmi_count);


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
        mq1.add(jsonObjectRequest);
    }
    }
           ActionMode mActionMode;
         ActionMode.Callback mActionModeCallback = new ActionMode.Callback()
        {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu)
            {
                MenuInflater inflater = mode.getMenuInflater();
               // inflater.inflate(R.id.iv_dietplan, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu)
            {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item)
            {
                mActionMode.finish();
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode)
            {
                mActionMode = null;
            }



        };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    class SlidemyAdapter extends PagerAdapter {


        private ArrayList<Integer> image_resource = new ArrayList<>();
        private Context ctx;
        private LayoutInflater layoutInflater;
        public SlidemyAdapter(Context ctx) {
            this.ctx = ctx;
           image_resource.add(R.drawable.b3);image_resource.add(R.drawable.b2);
            image_resource.add(R.drawable.b1);
        }

        @Override
        public int getCount() {
            return image_resource.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == (LinearLayout)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item_view = layoutInflater.inflate(R.layout.slide, container, false);
            ImageView imageview = (ImageView) item_view.findViewById(R.id.image);

            Glide.with(ctx)
                    .load(image_resource.get(position)).into(imageview);
            container.addView(item_view);
            return item_view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }



    }



    public  void getCurrentDateBmi(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String curdate = sdf.format(new Date());
        String pt_id;
        loginPreferences = context.getSharedPreferences("UserData", 0);
        pt_id = loginPreferences.getString("patient_id", "");
        RequestQueue requestQueue=Volley.newRequestQueue(context);
        String url="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/weight_bmi.php?patient_id="
                +pt_id+"&date="+curdate;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray output=response.getJSONArray("output");
                    for (int i=0;i<output.length();i++){

                        if(output.length()>0){
                            JSONObject jsonObject=output.getJSONObject(0);

                            String bmi =jsonObject.getString("bmi_count");

                            JSONObject jsonObject2=output.getJSONObject(1);

                            String weight=jsonObject2.getString("weight");
                            //Toast.makeText(context, ""+bmi, Toast.LENGTH_SHORT).show();


                            dweight = Double.parseDouble(weight);
                            dbmi = Double.parseDouble(bmi);
                            txtviewWeight.setText(String.valueOf(dweight));
                            txtviewBmi.setText(String.valueOf(dbmi));
                            ivSadFace.setVisibility(View.GONE);
                            ivBmiFace.setVisibility(View.GONE);
                            txtviewKgm.setVisibility(View.VISIBLE);
                            txtviewKg.setVisibility(View.VISIBLE);
                            txtviewStatus.setText("My Today Status");


                        }else {
                            ivSadFace.setVisibility(View.VISIBLE);
                            ivBmiFace.setVisibility(View.VISIBLE);
                            txtviewWeight.setVisibility(View.GONE);
                            /*txtviewBmi.setBackgroundResource((R.drawable.sadface));
                            txtviewBmi.setBackgroundResource((R.drawable.sadface));*/

                        }

                    }
                }catch (JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
        requestQueue.add(jsonObjectRequest);
    }

    public void workout(final View view){
        new MaterialTapTargetPrompt.Builder(getActivity())
                .setTarget(view.findViewById(R.id.iv_weight))
                .setPrimaryText("Get your Workout Activities here !!!")
                .setSecondaryText("Tap for Workout Suggestion & Activities submission...")

                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                {
                    @Override
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state)
                    {
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED )

                        {

                            loginPreferences.edit().putString("tap","1").commit();

                        }
                    }
                })
                .show();
    }

 /*   public void Review(final View view){


        new MaterialTapTargetPrompt.Builder(getActivity())
                .setTarget(R.id.nav_reviewreport)
                .setAnimationInterpolator(new FastOutSlowInInterpolator())
                .setPrimaryText("Tap here for Weekly Review & Health Update..!")
                .setSecondaryText("Weekly Monitorning Played Major role for Health Journey..!")
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                {
                    @Override
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state)
                    {
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                        {
                        }
                    }
                })
                .show();
    }*/
   /* public void chatn(final View view){


        new MaterialTapTargetPrompt.Builder(getActivity())
                .setTarget(R.id.nav_nutricoach)
                .setAnimationInterpolator(new FastOutSlowInInterpolator())
                .setPrimaryText("Chat here with our highly experienced NutriCoach!")
                .setSecondaryText("You may ask your Diet related query  Here ..! ")
                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                {
                    @Override
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state)
                    {

                    }
                })
                .show();

    }*/

/*
    public void showRectPrompt( final View view) {
       // Context context=getContext().getApplicationContext();
        new MaterialTapTargetPrompt.Builder(getActivity())
                .setTarget(R.id.iv_dash_weight)
                .setPrimaryText("Tap here to submit your daily weight.")
                .setSecondaryText("Update it daily and get your Weight log history here..!!")
              // .setPromptBackground(new RectanglePromptBackground())
              //  .setPromptFocal(new RectanglePromptFocal())

                .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener()
                {
                    @Override
                    public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state)
                    {


                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED)
                        {


                        }
                    }
                })
                .show();
    }
*/


 /*  public void Weightdata()
    {
        for (int i=0;i<weightWeight.size();i++){

            try {
                date=new SimpleDateFormat("yyyy-MM-dd").parse(weightDate.get(i));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) {
                series= new LineGraphSeries<>(new DataPoint[]{
                        new DataPoint(i, Double.parseDouble(weightWeight.get(i))),
                });
            }
            // styling series
            final SimpleDateFormat dt=new SimpleDateFormat("dd-MM");
            graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
                @Override
                public String formatLabel(double value, boolean isValueX) {
                    if(isValueX){
                        return dt.format(date);
                    }else{
                        return super.formatLabel(value, isValueX);
                    }
                }
            });

            series.setTitle("Random Curve 1");
            series.setColor(getResources().getColor(R.color.colorPrimaryDark));
            series.setDrawDataPoints(true);
            series.setDataPointsRadius(10);
            series.setThickness(10);
// custom paint to make a dotted line
   *//* Paint paint = new Paint();
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(10);
    paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
    series.setCustomPaint(paint);*//*
            graph.addSeries(series);
        }
    }

*/
    /////////////////////////////////////////////////////////////////

   /* public void Bmidata()
    {
        for (int i=0;i<Bmicount.size();i++){
            //Date date = null;
            try {
                date=new SimpleDateFormat("yyyy-MM-dd").parse(BmiDate.get(i));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (date != null) {
                series= new LineGraphSeries<>(new DataPoint[]{
                        new DataPoint(i, Double.parseDouble(Bmicount.get(i))),
                });
            }        final SimpleDateFormat dt=new SimpleDateFormat("dd-MM");

            graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
                public String formatLabel(double value, boolean isValueX) {
                    if(isValueX){
                        return dt.format(date);
                    }else{
                        return super.formatLabel(value, isValueX);
                    }
                }
            });
            graph.getGridLabelRenderer().setNumHorizontalLabels(8);
            // only 4 because of the space
            // styling series
            series.setTitle("Random Curve 1");
            series.setColor(getResources().getColor(R.color.colorPrimaryDark));
            series.setDrawDataPoints(true);
            series.setDataPointsRadius(10);
            series.setThickness(10);
// custom paint to make a dotted line
  *//*  Paint paint = new Paint();
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(10);
    paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
    series.setCustomPaint(paint);*//*
            graph.addSeries(series);
        }
    }
*/
}

