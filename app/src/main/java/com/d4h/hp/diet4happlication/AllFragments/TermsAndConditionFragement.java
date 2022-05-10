package com.d4h.hp.diet4happlication.AllFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.d4h.hp.diet4happlication.AllActivities.NavigationDrawerActivity;
import com.d4h.hp.diet4happlication.R;


public class TermsAndConditionFragement extends Fragment {

    Context context;
    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_privacy_policy,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((NavigationDrawerActivity) getActivity()).getSupportActionBar().setTitle(
                "Terms & Condition");
        WebView mywebview = (WebView)view.findViewById(R.id.webview_privacypolicy);

        mywebview.loadUrl("https://www.diet4health.in/public/diet4health.in/d4h_api_v1.1/terms.html");
    }
}
