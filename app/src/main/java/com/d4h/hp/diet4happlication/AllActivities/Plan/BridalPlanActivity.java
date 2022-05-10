package com.d4h.hp.diet4happlication.AllActivities.Plan;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllActivities.PayMentGateWay;
import com.d4h.hp.diet4happlication.AllActivities.TermsAndConditionActivity;
import com.d4h.hp.diet4happlication.R;

public class BridalPlanActivity extends AppCompatActivity {

    Button btnFirstPlan;
    SharedPreferences signUpprefer;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bridal_plan);

        btnFirstPlan=findViewById(R.id.btn_first_bridal_plan);

        signUpprefer = getSharedPreferences("SignUpData", 0);
        editor = signUpprefer.edit();
        editor.putString("plan_info","5");

        btnFirstPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  editor.putString("plan_due","1");
                editor.putString("plan_cost","1500");
                 dialogTermsCondition();*/

                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:9702070155"));
                startActivity(phoneIntent);

            }
        });
    }
    public void dialogTermsCondition(){
        final Dialog dialog = new Dialog(BridalPlanActivity.this);
        dialog.setContentView(R.layout.layout_dialog_termscondition);
        dialog.setCancelable(true);
        dialog.setTitle("");
        dialog.show();
        Window window = dialog.getWindow();
        dialog.getWindow().setTitleColor(getResources().getColor(R.color.colorPrimary));
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.myloginbutton));
        dialog.getWindow();
        /*int dividerId = dialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = dialog.findViewById(dividerId);
        divider.setBackgroundColor(getResources().getColor(R.color.colorOrange));*/

        final EditText edittextEmail;
        final Button btnOk;
        final TextView txtviewTerms;

        final CheckBox checkboxTc;

        edittextEmail=dialog.findViewById(R.id.edittext_terms_email);
        btnOk=dialog.findViewById(R.id.btn_terms_ok);
        checkboxTc=dialog.findViewById(R.id.checkbox_terems);
        txtviewTerms=dialog.findViewById(R.id.txtview_terms);
        btnOk.setVisibility(View.GONE);

        editor.putString("dialogemail", edittextEmail.getText().toString());
        editor.commit();

        txtviewTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(BridalPlanActivity.this,TermsAndConditionActivity.class);
                startActivity(i);
            }
        });
        checkboxTc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btnOk.setVisibility(View.VISIBLE);
                }else {
                    checkboxTc.setError("please select terms and condition");
                    btnOk.setVisibility(View.GONE);
                }
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(BridalPlanActivity.this,PayMentGateWay.class);
                startActivity(i);
            }
        });

    }
}
