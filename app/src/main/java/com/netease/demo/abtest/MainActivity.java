package com.netease.demo.abtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneABTester test1 = new OneABTester();
        TextView tvName = findViewById(R.id.tv_name);
        tvName.setText(test1.getName());
    }
}