package com.d4h.hp.diet4happlication.AllActivities;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.d4h.hp.diet4happlication.AllDataModels.InvoiceModel;

import com.d4h.hp.diet4happlication.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubcriptionInvoiceActivity extends AppCompatActivity {

    InvoiceModel model;
    private TextView txtviewName,txviewPlanName,txtviewDuration,txtviewPaymentDate,txtviewProgstdt,
            txtviewSubOver,txtviewPlanCost,txtviewOfferCost,txtviewAddDis,txtviewNetAmount,txtviewAmmtpaid,txtviewBalance;
    private String strName,strPlanName,strPlanDuration,strPDate,strProgDate,strSubOver,strPlancost,strOfferCost,strAddDis,strNetAmt,strAmtPaid,strBalance;
    Button btnCancle;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_invoice_detail);

        toolbar=findViewById(R.id.toolbar_all);
        //btnCancle=findViewById(R.id.btn_invoice_cancel);
        txtviewName=findViewById(R.id.txtview_inv_name);
        txviewPlanName=findViewById(R.id.txtview_inv_palnname);
        txtviewDuration=findViewById(R.id.txtview_inv_duration);
        txtviewPaymentDate=findViewById(R.id.txtview_inv_paymnentdate);
        txtviewProgstdt=findViewById(R.id.txtview_inv_progstartdate);
        txtviewSubOver=findViewById(R.id.txtview_inv_subcrover);
        txtviewPlanCost=findViewById(R.id.txtview_inv_plancost);
        txtviewOfferCost=findViewById(R.id.txtview_inv_offercost);
        txtviewAddDis=findViewById(R.id.txtview_inv_additionaldis);
        txtviewNetAmount=findViewById(R.id.txtview_inv_netpayment);
        txtviewAmmtpaid=findViewById(R.id.txtview_inv_amountpaid);
        txtviewBalance=findViewById(R.id.txtview_inv_balance);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Invoice");
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


        displayInvoice();

    }
    public void displayInvoice(){
        Bundle extras = getIntent().getExtras();
        String paymentId=extras.getString("pay_id");
        //Toast.makeText(this, ""+paymentId, Toast.LENGTH_SHORT).show();

       // http://uat.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_subscription.php?payment_id="+paymentId
        String url="https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/handle_subscription.php?payment_id="+paymentId;
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray=response.getJSONArray("output");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        strPlanName=jsonObject.getString("plan_name");
                        strSubOver=jsonObject.getString("plan_end_date");
                        strPlanDuration=jsonObject.getString("duration");
                        strAddDis=jsonObject.getString("extra_discount");
                        strAmtPaid=jsonObject.getString("paid_amount");
                        strNetAmt=jsonObject.getString("net_balance");
                        strBalance=jsonObject.getString("due_amount");
                        strOfferCost=jsonObject.getString("offer_cost");
                        strName=jsonObject.getString("name");
                        strPDate=jsonObject.getString("created_date");
                        strProgDate=jsonObject.getString("plan_started_date");
                        strPlancost=jsonObject.getString("plancost");

                        model=new InvoiceModel();
                        model.setInvName(strName);
                        model.setInvPlanName(strPlanName);
                        model.setInvDuration(strPlanDuration);
                        model.setInvSubOver(strSubOver);
                        model.setInvAddDis(strAddDis);
                        model.setInvAmtPaid(strAmtPaid);
                        model.setInvNetPay(strNetAmt);
                        model.setInvBalance(strBalance);
                        model.setInvOfferCost(strOfferCost);
                        model.setInvProgStartDate(strProgDate);
                        model.setInvPlanCost(strPlancost);
                        model.setInvpaymentDate(strPDate);


                        txtviewName.setText(model.getInvName());
                        txviewPlanName.setText(model.getInvPlanName());
                        txtviewDuration.setText(model.getInvDuration());
                        txtviewPaymentDate.setText(model.getInvpaymentDate());
                        txtviewProgstdt.setText(model.getInvProgStartDate());
                        txtviewSubOver.setText(model.getInvSubOver());
                        txtviewPlanCost.setText(model.getInvPlanCost());
                        txtviewOfferCost.setText(model.getInvOfferCost());
                        txtviewAddDis.setText(model.getInvAddDis());
                        txtviewNetAmount.setText(model.getInvNetPay());
                        txtviewAmmtpaid.setText(model.getInvAmtPaid());
                        txtviewBalance.setText(model.getInvBalance());


                    }

                }catch (JSONException e){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SubcriptionInvoiceActivity.this, "Invoice Nework Error", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}
