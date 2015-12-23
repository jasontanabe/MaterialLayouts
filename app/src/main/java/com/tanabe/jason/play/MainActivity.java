package com.tanabe.jason.play;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Button testScrollButton = (Button) findViewById(R.id.test_scroll_button);
        testScrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ScrollingActivity.class);
                startActivity(i);
            }
        });
        Button testPhoneButton = (Button) findViewById(R.id.test_phone_button);
        testPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, PhoneActivity.class);
                startActivity(i);
            }
        });
    }
}
