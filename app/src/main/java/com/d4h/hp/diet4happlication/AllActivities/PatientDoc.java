package com.d4h.hp.diet4happlication.AllActivities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.AllAdaptors.PatientDocAdapter;
import com.d4h.hp.diet4happlication.AllDataModels.GetDocModel;
import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PatientDoc extends AppCompatActivity  {

    FloatingActionButton floatingActionButton;
    ImageView image;
    protected static final int CAMERA_REQUEST = 0;
    protected static final int GALLERY_PICTURE = 1;
    private Intent pictureActionIntent = null;
    Bitmap bitmap;
    int IMG_REQUEST;
    String filename;
    String encodedImage="";
    Uri fileuri;
    EditText ed;
    LinearLayout ll;
    Button addDoc;
    RequestQueue requestQueue;
RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    private GetDocModel model;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
ProgressBar progressBar;

    String selectedImagePath;
    String pt_id;
    private List<GetDocModel> listgetmodel;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_doc);
        floatingActionButton=findViewById(R.id.fab_addDoc);
        image=findViewById(R.id.image);
         ed=findViewById(R.id.edpic);



         ll=findViewById(R.id.ll);
         addDoc=findViewById(R.id.addDoc);
         recyclerView=findViewById(R.id.rc_patdoc);
         requestQueue=Volley.newRequestQueue(this);
         sharedPreferences = getApplicationContext().getSharedPreferences("UserData", 0);
         pt_id = sharedPreferences.getString("patient_id", "");

        layoutManager=new LinearLayoutManager(this);
        listgetmodel=new ArrayList<>();
        progressBar=findViewById(R.id.fb_progressbar);
        toolbar=(Toolbar)findViewById(R.id.toolbar_all);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Patient Document");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

        adapter=new PatientDocAdapter(listgetmodel,getApplicationContext());

        doc_view();
        addDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senddoc(encodedImage);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDialog();
            }
        });
        }



    private void startDialog() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(
               this);
        myAlertDialog.setTitle("Upload Patient Document");
        myAlertDialog.setMessage("To upload pdf take a Screenshot of it!");

        myAlertDialog.setPositiveButton("Gallery",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                       showFileChooser();
                       }
                });
      /*  myAlertDialog.setNegativeButton(".PDF", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //getDocument();
              showPdfChooser();
            }
        });*/


        myAlertDialog.show();
    }
    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)


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
           //tv_chused.setText(filename);


            //String filename=path.substring(path.lastIndexOf("/")+1);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Bitmap lastBitmap = null;
                lastBitmap = bitmap;
                image.setImageBitmap(bitmap);
                floatingActionButton.setVisibility(View.GONE);

                ll.setVisibility(View.VISIBLE);
                image.setVisibility(View.VISIBLE);

                ed.setVisibility(View.VISIBLE);

              /*  ed.setFocusable(true);
                ed.setFocusableInTouchMode(true);
                ed.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(ed, InputMethodManager.SHOW_IMPLICIT);*/

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                lastBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                //docIntent(encodedImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        }

    private void showFileChooser ()  {

        IMG_REQUEST=1;
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
    public void senddoc(final String s){
        addDoc.setClickable(false);
        addDoc.setText("Adding..");
       progressBar.setVisibility(View.VISIBLE);
        String URI="https://www.diet4health.in/public/diet4health.in/d4h_api/handle_patdoc.php?";
    StringRequest stringRequest=new StringRequest(Request.Method.POST, URI, new Response.Listener<String>() {
        @SuppressLint("RestrictedApi")
        @Override
        public void onResponse(String response) {
            addDoc.setClickable(true);
            addDoc.setText("Add");
            image.setVisibility(View.GONE);
            ll.setVisibility(View.GONE);
            ed.setVisibility(View.GONE);
            floatingActionButton.setVisibility(View.VISIBLE);


            Toast.makeText(PatientDoc.this, "Your Document is Submited..!", Toast.LENGTH_SHORT).show();
          startActivity(new Intent(getApplicationContext(),PatientDoc.class));
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(PatientDoc.this, ""+error, Toast.LENGTH_SHORT).show();

        }
    }){
        @Override
        protected Map<String, String> getParams() {

         String rmk="";
         rmk=ed.getText().toString().replace(" ","+");
            Map<String, String> params = new HashMap<String, String>();
            params.put("p_id",pt_id);
            params.put("encoded_file",s);
            params.put("remark",rmk);

            return params;        }
    };
    requestQueue.add(stringRequest);
}
    public void doc_view(){

        RequestQueue mq=Volley.newRequestQueue(this);



        String food_diary_url=
                "https://www.diet4health.in/public/diet4health.in/d4h_api/handle_getdoc.php?"+"p_id="+pt_id;


        // String p_id=loginPreferences.getString("patient_id","");
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, food_diary_url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               progressBar.setVisibility(View.GONE);


                try {
                    JSONArray out=response.getJSONArray("data");
                    for(int i=0;i<out.length();i++){
                        JSONObject data=out.getJSONObject(i);
                        model=new GetDocModel();
                        String date=data.getString("date");
                        String file=data.getString("file");
                        String remark=data.getString("name");
                        String doc_id=data.getString("doc_id");

                        model.setDate(date);
                        model.setFilelink(file);
                        //here remark will be the name
                        model.setFilename(remark);
                        model.setDoc_id(doc_id);



                        listgetmodel.add(model);

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



}

