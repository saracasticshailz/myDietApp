package com.d4h.hp.diet4happlication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Mysingleton {
    public static Mysingleton mysingleton;
    RequestQueue requestQueue;
    Context mcon;
    private Mysingleton(Context context){
        mcon=context;
        requestQueue=getrequestque();
    }

    private RequestQueue getrequestque() {
        if(requestQueue==null){
            requestQueue=Volley.newRequestQueue(mcon.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized Mysingleton getMysingleton(Context context){
        if(mysingleton==null){
            mysingleton=new Mysingleton(context);
        }
        return mysingleton;
    }
    public <T>void addToRequestque(Request<T> request){
        requestQueue.add(request);

    }
}
