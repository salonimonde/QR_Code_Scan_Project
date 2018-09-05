package com.example.bynry_01.qr_code_scan_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnClickHereToScan;
    private TextView txtValues;

    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtValues = findViewById(R.id.txt_values);
        btnClickHereToScan = findViewById(R.id.btn_click_here_to_scan);
        btnClickHereToScan.setOnClickListener(this);

        qrScan = new IntentIntegrator(this);

    }

    protected void OnActivityResult(int requestCode, int resultCode, Intent data){

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, requestCode, data);
        if(result != null){

            if(result.getContents() == null){

                Toast.makeText(this,"Result Not Found",Toast.LENGTH_LONG).show();
            }
            else{
                try{
                    String obj = result.getContents();

                    txtValues.setText(obj);
                }
                catch (Exception e){
                    e.printStackTrace();

                    Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
                }
            }

        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
            Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        qrScan.initiateScan();
    }
}
