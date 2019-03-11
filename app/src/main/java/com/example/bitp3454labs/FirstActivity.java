package com.example.bitp3454labs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class FirstActivity extends AppCompatActivity {


    TextView txtvwAge;
    EditText edtName,edtYear;
    Button btnClick;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        txtvwAge = (TextView) findViewById(R.id.txtvwAge);
        edtName = (EditText) findViewById(R.id.edtTxtName);
        edtYear = (EditText) findViewById(R.id.edtYear);
        iv = (ImageView) findViewById(R.id.imgVwFirst);

//        byte[] byteArray = getIntent().getIntArrayExtra("Image");
//        Bitmap bp = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);

    }

    public void fnGreet(View vw)
    {
        String strName = edtName.getText().toString();
        String strYear = edtYear.getText().toString();
        int intYear = Integer.parseInt(strYear);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int age = year - intYear;


        txtvwAge.setText("Hello and welcome "+strName + " you are " + age +" years old." );
    }

    public void fnThreadActivity(View vw)
    {

        String strYear = edtYear.getText().toString();
        int intYear = Integer.parseInt(strYear);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int age = year - intYear;

        Intent intent = new Intent(this, ThreadedActivity.class);
        String strMsg = ((EditText) findViewById(R.id.edtTxtName)).getText().toString();
        intent.putExtra("varStr1", strMsg);
        intent.putExtra("varInt1", age);
        startActivityForResult(intent, 4);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if (data.getExtras().get("bp") != null)
        {

            Bitmap bp = data.getParcelableExtra("bp");
            iv.setImageBitmap(bp);
        }

    }
}
