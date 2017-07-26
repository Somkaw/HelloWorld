package com.example.emokidz.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    int sum = 0;
    TextView tvResult;
    Button  btnOK;
    EditText edtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        sum = intent.getIntExtra("Result",0);

        Bundle bundle =  intent.getBundleExtra("cBundle");
        int x = bundle.getInt("x");
        int y = bundle.getInt("y");
        int z = bundle.getInt("z");

        //Serializable
        CoordinateSerializable c2 = (CoordinateSerializable)
                intent.getSerializableExtra("cSerializable");

        //Parcelable
        CoordinateParcelable c3 = (CoordinateParcelable)
                intent.getSerializableExtra("cParcelable");

        initInstance();

    }

    private void initInstance() {
        tvResult = (TextView) findViewById(R.id.tv2);
        tvResult.setText("Result"+"= "+sum);

        edtResult = (EditText) findViewById(R.id.edt3);;

        btnOK = (Button)findViewById(R.id.btnOK);
        btnOK.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent returnintent = new Intent();
                returnintent.putExtra("result",edtResult.getText().toString());
                setResult(RESULT_OK,returnintent);
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}
