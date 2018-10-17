package com.jit.library.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jit.library.R;
import com.jit.library.manager.SessionManager;

public class SplashActivity extends AppCompatActivity {

    SessionManager splashSessionManager;
    Context mContext;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mContext = getApplicationContext();
        splashSessionManager = new SessionManager(mContext);
        handler = new Handler();
        runnable = new Runnable()
        {
            @Override
            public void run()
            {
                //start your activity here
                if (splashSessionManager.checkLogin())
                {
                    // user is not logged in redirect him to Login Activity
                    Intent i = new Intent(mContext, UserActivity.class);
                    // Closing all the Activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    // Staring Login Activity
                    startActivity(i);

                    //finish Activity
                    finish();
                }else
                {
                    // user is not logged in redirect him to Login Activity
                    Intent i = new Intent(mContext, LoginActivity.class);
                    // Closing all the Activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    // Staring Login Activity
                    startActivity(i);

                    //finish Activity
                    finish();
                }
            }
        };
        handler.postDelayed(runnable, 3000);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(runnable);
    }

}


