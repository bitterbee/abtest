package com.netease.demo.abtest;

import android.app.AlertDialog;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.netease.lib.abtest.ui.layout.DynamicLayoutInflater;
import com.netease.libs.abtestbase.CryptoUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private DynamicLayoutInflater dynamicLayoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneABTester test1 = new OneABTester();
        TextView tvName = (TextView) findViewById(R.id.tv_name);
        tvName.setText(test1.getName());

        testCustomLayout();
    }

    private void testCustomLayout() {

        if (dynamicLayoutInflater == null) {
            dynamicLayoutInflater = new DynamicLayoutInflater(this);
            if (!dynamicLayoutInflater.isValid()) {
                return;
            }
        }

        try {
            AssetManager am = getAssets();
            InputStream is = am.open("test_custom_layout.bin");

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] slice = new byte[1024];
            int count;
            while ((count = is.read(slice)) > 0) {
                os.write(slice, 0, count);
            }

            String content = CryptoUtil.base64Encode(os.toByteArray());
            dynamicLayoutInflater.inflate(content, (ViewGroup) findViewById(R.id.replace_stub), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onShowDialog(View v) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.show();

        View contentView = getLayoutInflater().inflate(R.layout.dlg_demo0, null);
        contentView.findViewById(R.id.btn_alert_negative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        contentView.findViewById(R.id.btn_alert_positive).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(contentView);
    }

    public void onShowPopupWindow(View v) {
        PopupWindowView popup = new PopupWindowView(this);
        View subview = getLayoutInflater().inflate(R.layout.popupwindow_demo0, null);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        popup.addSubView(subview, lp);
        popup.showInCenter(findViewById(R.id.ll_content));
    }
}