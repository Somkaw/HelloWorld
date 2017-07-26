package com.example.emokidz.helloworld;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    TextView textView1;
    Button buttonCal;
    RadioGroup radioGroup;

    CustomViewGroup viewGroup1;
    CustomViewGroup viewGroup2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getBoolean(R.bool.potrait_only)) {

            //fix screen

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }


        setContentView(R.layout.activity_main);

        initInsatance();
    }

    private void initInsatance() {

        final TextView tvHello = (TextView) findViewById(R.id.tvHello);
        tvHello.setMovementMethod(LinkMovementMethod.getInstance());
        tvHello.setText(Html.fromHtml("<a href=\"http://nuuneoi.com\">http://nuuneoi.com</a>"));


        final EditText editTextHello = (EditText) findViewById(R.id.editTextHello);
        editTextHello.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    tvHello.setText(editTextHello.getText());
                    return true;
                }
                return false;
            }
        });

        Button btnCopy = (Button) findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                tvHello.setText(editTextHello.getText());
            }
        });

        editText1 = (EditText) findViewById(R.id.edt1);
        editText2 = (EditText) findViewById(R.id.edt2);
        textView1 = (TextView) findViewById(R.id.tv1);
        buttonCal = (Button) findViewById(R.id.btn1);
        radioGroup = (RadioGroup) findViewById(R.id.rdGroup);


        buttonCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val1 = 0;
                int val2 = 0;
                int sum = 0;

                try {


                    val1 = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException e) {

                }
                try {
                    val2 = Integer.parseInt(editText2.getText().toString());
                } catch (NumberFormatException e) {

                }


                //calculator
                switch (radioGroup.getCheckedRadioButtonId()) {

                    case R.id.rbPlus:
                        sum = val1 + val2;
                        break;
                    case R.id.rbMinus:
                        sum = val1 - val2;
                        break;
                    case R.id.rbMultiply:
                        sum = val1 * val2;
                        break;
                    case R.id.rbDivide:
                        sum = val1 / val2;
                        break;
                }
                textView1.setText(sum + "");
                Log.d("Calculation", "Result= " + sum);
                Toast.makeText(MainActivity.this, "Result =" + sum, Toast.LENGTH_LONG)
                        .show();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("Result", sum);

                // Playground

                Coordinate c1 = new Coordinate();
                c1.x = 5;
                c1.y = 10;
                c1.z = 20;

                Bundle bundle = new Bundle();
                bundle.putInt("x", c1.x);
                bundle.putInt("y", c1.y);
                bundle.putInt("z", c1.z);

                intent.putExtra("cBundle", bundle);

                //CoordinateSerializable

                CoordinateSerializable c2 = new CoordinateSerializable();
                c2.x = 5;
                c2.y = 10;
                c2.z = 20;

                intent.putExtra("cSerializable", c2);

                //CoordinateParcelable

                CoordinateParcelable c3 = new CoordinateParcelable();
                c3.x = 5;
                c3.y = 10;
                c3.z = 20;

                intent.putExtra("cParcelable", c3);

                startActivityForResult(intent, 12345);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);

            }


        });

        viewGroup1 = (CustomViewGroup) findViewById(R.id.viewGroup1);
        viewGroup2 = (CustomViewGroup) findViewById(R.id.viewGroup2);

        viewGroup1.setButtonText("Hello");
        viewGroup2.setButtonText("World");

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==12345){
            if (resultCode==RESULT_OK){

                String txtResult = data.getStringExtra("result");

                Toast.makeText(MainActivity.this,txtResult,Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.item_setting) {
            return true;
        }
        return super.onOptionsItemSelected(item);

            }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //outState.putString("text",textView1.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //textView1.setText(savedInstanceState.getString("text"));
    }
}


