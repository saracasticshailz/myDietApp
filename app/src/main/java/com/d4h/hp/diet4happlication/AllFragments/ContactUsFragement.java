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


public class ContactUsFragement extends Fragment {


    ImageView ivHome,ivMobile,ivPhone,ivMail;
    TextView txtviewMobile,txtviewPhone,txtviewMap,txtviewEmail;
    Context context;
    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_contactus,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivHome=view.findViewById(R.id.iv_add);
        ivMobile=view.findViewById(R.id.iv_mobile);
        ivPhone=view.findViewById(R.id.iv_phone);
        ivMail=view.findViewById(R.id.iv_email);
        txtviewMobile=view.findViewById(R.id.txtview_mobile);
        txtviewPhone=view.findViewById(R.id.txtview_phone);
        txtviewMap=view.findViewById(R.id.txtview_map);
        txtviewEmail=view.findViewById(R.id.txtview_email);

        ivHome.setImageResource(R.drawable.ic_perm_identity_black_24dp);
        ivMobile.setImageResource(R.drawable.ic_phone_android_black_24dp);
        ivPhone.setImageResource(R.drawable.ic_call_black_24dp);
        ivMail.setImageResource(R.drawable.ic_email_black_24dp);
        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(
                "Contact");
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapCall();
            }
        });
        txtviewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapCall();
            }
        });

        ivMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobCall();
            }
        });
        txtviewMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobCall();
            }
        });

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               phoneCall();
            }
        });
        txtviewPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneCall();
            }
        });

        ivMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailCall();
            }
        });
        txtviewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailCall();
            }
        });
    }
    public void mapCall(){
        String address="B2-4/3, Sri Tuja Bhawani Marg, Near Fire Station, Sector-16, Vashi, Navi Mumbai, Maharashtra 400703";
        String url = "http://maps.google.com/maps?daddr="+address;
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
        startActivity(intent);
    }
    public void mobCall(){
        String phone = txtviewMobile.getText().toString();
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse("tel:+919702070155"));
        startActivity(phoneIntent);
    }
    public void phoneCall(){
        String phone = txtviewPhone.getText().toString();
        //Toast.makeText(ContactUsActivity.this, phone, Toast.LENGTH_SHORT).show();
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse("tel:02227650455"));
        startActivity(phoneIntent);
    }
    public void emailCall(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@diet4health.in" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "mail body");
        startActivity(Intent.createChooser(intent, ""));
    }
}
