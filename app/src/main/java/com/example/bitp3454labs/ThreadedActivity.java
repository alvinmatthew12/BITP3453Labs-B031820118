package com.example.bitp3454labs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ThreadedActivity extends AppCompatActivity {

    ImageView iv;
    TextView tv1;
    int Age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threaded);

        iv = findViewById(R.id.imgVwProfile);
        Intent intent = getIntent();
        String strMsg = intent.getStringExtra("varStr1");
        Age = intent.getIntExtra("varInt1",0);

        tv1 = findViewById(R.id.txtVwHello);
        tv1.setText("Hello and welcome "+strMsg + " you are " + Age +" years old.");
    }

    public void fnTakePic(View vw)
    {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv1.setText(tv1.getText().toString()+"..This is your Picture hehe..");
                    }
                });

            }
        };

        Thread thr = new Thread(run);
        thr.start();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        Bitmap bp = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bp);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("bp", bp);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}

