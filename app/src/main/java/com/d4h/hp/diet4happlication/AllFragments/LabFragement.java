package com.d4h.hp.diet4happlication.AllFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllActivities.NavigationDrawerActivity;
import com.d4h.hp.diet4happlication.R;

public class LabFragement extends Fragment {

    TextView txtview1call,txtview1mob,txtview2call,txtview2mob,txtview3call,txtview3mob,txtview4call,txtview4mob,txtview5call,txtview5mob,txtview6Call,txtview6mob;
    String strCall="";
    String strCallStart;
    ImageView ivCall1,ivCall2,ivCall3,ivCall4,ivCall5,ivCall6,ivMob1,ivMob2,ivMob3,ivMob4,ivMob5 ,ivMob6;
    Context context;
    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_labs,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        txtview1call=view.findViewById(R.id.txtview_call1);
        txtview1mob=view.findViewById(R.id.txtview_mob1);
        txtview2call=view.findViewById(R.id.txtview_call2);
        txtview3call=view.findViewById(R.id.txtview_call3);
        //txtview3mob=view.findViewById(R.id.txtview_mob3);
        txtview4call=view.findViewById(R.id.txtview_call4);
        txtview4mob=view.findViewById(R.id.txtview_mob4);
        txtview5call=view.findViewById(R.id.txtview_call5);
        txtview5mob=view.findViewById(R.id.txtview_mob5);
        txtview6Call=view.findViewById(R.id.txtview_call6);


        ivCall3=view.findViewById(R.id.iv_call3);
        ivCall4=view.findViewById(R.id.iv_call4);
        ivCall2=view.findViewById(R.id.iv_call2);
        ivCall1=view.findViewById(R.id.iv_call1);
        ivCall5=view.findViewById(R.id.iv_call5);
        ivCall6=view.findViewById(R.id.iv_call6);

        ivMob4=view.findViewById(R.id.iv_mob4);
        ivMob1=view.findViewById(R.id.iv_mob1);
        ivMob5=view.findViewById(R.id.iv_mob5);



        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(
                "Associate Labs");

        txtview1call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview1call.getText().toString();
                phoneCall();
            }
        });
        txtview1mob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview1mob.getText().toString();
                phoneCall();
            }
        });
        txtview2call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview2call.getText().toString();
                phoneCall();
            }
        });

        txtview3call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview3call.getText().toString();
                phoneCall();
            }
        });
        txtview4call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview4call.getText().toString();
                phoneCall();
            }
        });
        txtview4mob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview4mob.getText().toString();
                phoneCall();
            }
        });
        txtview5call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall="02227832367";
                phoneCall();
            }
        });
        txtview5mob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview5mob.getText().toString();
                phoneCall();
            }
        });
        txtview6Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview6Call.getText().toString();
                phoneCall();
            }
        });

        ivCall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview1call.getText().toString();
                phoneCall();
            }
        });
        ivMob1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview1mob.getText().toString();
                phoneCall();
            }
        });
        ivCall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview2call.getText().toString();
                phoneCall();
            }
        });

        ivCall3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview3call.getText().toString();
                phoneCall();
            }
        });
        ivCall4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview4call.getText().toString();
                phoneCall();
            }
        });
        ivMob4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview4mob.getText().toString();
                phoneCall();
            }
        });
        ivCall5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall="02227832367";
                phoneCall();
            }
        });
        ivMob5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview5mob.getText().toString();
                phoneCall();
            }
        });
        ivCall6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCall=txtview6Call.getText().toString();
                phoneCall();
            }
        });

    }

    public void phoneCall(){
        //strCall=txtview1call.getText().toString();
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse("tel:"+strCall));
        startActivity(phoneIntent);
    }
   /* public void mobCall(){
        // strCall = txtview1mob.getText().toString();
        //Toast.makeText(ContactUsActivity.this, phone, Toast.LENGTH_SHORT).show();
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse("tel:"+strCall));
        startActivity(phoneIntent);
    }*/

}