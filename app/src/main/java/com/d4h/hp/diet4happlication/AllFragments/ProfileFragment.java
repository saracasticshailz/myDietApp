package com.d4h.hp.diet4happlication.AllFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.d4h.hp.diet4happlication.AllActivities.ChangePasswordActivity;
import com.bumptech.glide.Glide;
import com.d4h.hp.diet4happlication.AllActivities.NavigationDrawerActivity;
import com.d4h.hp.diet4happlication.R;


import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    Context context;
    Button btnSubmit,tnChangePassword;
    TextView name,address,con,dob,email;
    ImageView ivPname,ivPhone,ivEmail;
    SharedPreferences loginpreference;
    CircleImageView circleImageView;

    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_profile,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSubmit=(Button)view.findViewById(R.id.btn_submit);
        name=view.findViewById(R.id.edittext_name);
        tnChangePassword=view.findViewById(R.id.btn_changepassword);

        con=view.findViewById(R.id.edittext_mobileno);
        email=view.findViewById(R.id.edittxt_email);
        ivPname=view.findViewById(R.id.iv_pname);
        ivPhone= view.findViewById(R.id.iv_pmobile);
        ivEmail=view.findViewById(R.id.iv_pemail);
        circleImageView=view.findViewById(R.id.ProfileImage);
        ivPname.setImageResource(R.drawable.ic_perm_identity_black_24dp);
        ivPhone.setImageResource(R.drawable.ic_phone_android_black_24dp);
        ivEmail.setImageResource(R.drawable.ic_email_black_24dp);

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(
                "Profile");
        loginpreference =context.getSharedPreferences("UserData", 0);
        name.setText(loginpreference.getString("name", ""));
        con.setText(loginpreference.getString("contact",""));
        email.setText(loginpreference.getString("email",""));
        String img=loginpreference.getString("profile_img","");
        Glide.with(context)
                .load("https://diet4health.in/public/diet4health.in/diet-panel/"+img)
                //.load("https://diet4health.in/public/diet4health.in/diet-panel/img/profile/download.jpg")
                .into(circleImageView);
        // email.setText(loginpreference.getString("email",""));
        // address.setText(loginpreference.getString("address",""));


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new DashBoardFragment();
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container,fragment).commit();

            }
        });
        tnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,ChangePasswordActivity.class);
                startActivity(i);
            }
        });
    }
}
