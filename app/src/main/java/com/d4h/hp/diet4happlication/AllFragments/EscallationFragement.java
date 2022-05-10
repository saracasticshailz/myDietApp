package com.d4h.hp.diet4happlication.AllFragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.AllActivities.NavigationDrawerActivity;
import com.d4h.hp.diet4happlication.AllAdaptors.EscallationAdaptor;
import com.d4h.hp.diet4happlication.AllDataModels.EscallationModel;
import com.d4h.hp.diet4happlication.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EscallationFragement extends Fragment {

    private Context context;
    private Button btnAdd;
    private String pt_id;
    SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<EscallationModel> listEscallation;
    private RecyclerView.Adapter adapter;
    private EscallationModel model;
    private String strSubject,strIssue;

    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_escallation,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnAdd=view.findViewById(R.id.btn_ecsmainadd);
        recyclerView=view.findViewById(R.id.rv_escallation);
        listEscallation=new ArrayList<>();
        adapter=new EscallationAdaptor(context,listEscallation);

        layoutManager=new LinearLayoutManager(context);
        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(
                "Escalation");

        displayData();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataDialog();
            }
        });

    }

    public void displayData(){
        sharedPreferences = context.getSharedPreferences("UserData", 0);
        pt_id=sharedPreferences.getString("patient_id","");
        String url="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_escalation.php?patinet_id="+pt_id;
        final RequestQueue requestQueue=Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                  // Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                    JSONArray out = response.getJSONArray("output");
                    for (int i = 0; i < out.length(); i++) {

                        JSONObject data = out.getJSONObject(i);
                        String strTest_id = data.getString("testimonial_id");
                        String strDate = data.getString("created_date");
                        String strSubject = data.getString("testimonial");
                        String strStatus = data.getString("status");

                        model=new EscallationModel();
                        model.setEscDate(strDate);
                        model.setEscStatus(strStatus);
                        model.setEscSubject(strSubject);
                        model.setEscId(strTest_id);
                        listEscallation.add(model);
                    }

                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {

                    e.printStackTrace();
                }
                setRecyclerView();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Escallation Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void addDataDialog(){

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_addescallation);
        dialog.setCancelable(false);
        dialog.setTitle("Add Escallation");
        dialog.show();
        Window window = dialog.getWindow();
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimaryDark);
        dialog.getWindow().setTitleColor(getResources().getColor(R.color.colorPrimary));
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.myloginbutton));
        dialog.getWindow();


        /*int dividerId = dialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = dialog.findViewById(dividerId);
        divider.setBackgroundColor(getResources().getColor(R.color.colorOrange));*/

        final  Button btnAdd,btnCancle;
        final EditText editTextSubject,editTextIssue;


        btnAdd=dialog.findViewById(R.id.btn_addesc);
        editTextSubject=dialog.findViewById(R.id.edittext_esc_add_sub);
        editTextIssue=dialog.findViewById(R.id.edittext_esc_add_issue);
        btnCancle=dialog.findViewById(R.id.btn_esc_cancel);

        displayData();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strSubject = editTextSubject.getText().toString().trim();
                strSubject = strSubject.replace(" ", "+");
                strIssue = editTextIssue.getText().toString().trim();
                strIssue = strIssue.replace(" ", "+");
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                sharedPreferences = context.getSharedPreferences("UserData", 0);
                pt_id = sharedPreferences.getString("patient_id", "");
               // https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/add_escalation.php?subject=testsubject&issue=chat&patient_id=197/add_escalation.php?subject=testsubject&issue=chat&patient_id=197
                String url = "https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/add_escalation.php?patient_id=" + pt_id
                        + "&subject=" + strSubject + "&issue=" + strIssue;
                if (strSubject.equals("")) {
                    editTextSubject.setError("Please Enter Subject");
                } else if (strIssue.equals("")) {
                    editTextIssue.setError("Please Enter Issue");
                } else {


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // JSONObject jsonObject=new JSONObject();

                            Toast.makeText(context, "Your Escallation is added", Toast.LENGTH_SHORT).show();
                            listEscallation.clear();
                            displayData();
                            dialog.dismiss();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "Escallation Network Error", Toast.LENGTH_SHORT).show();
                        }
                    });

                    requestQueue.add(jsonObjectRequest);
                }
            }
        });

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    public void setRecyclerView(){
        recyclerView.setLayoutManager(layoutManager);
        // recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

}
