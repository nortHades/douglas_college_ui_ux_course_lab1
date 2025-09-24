package com.example.viewdemo;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView imgViewSample;
    //cannot use findviewByid until setContentView is called;
    final String TAG = "VIEW DEMO";



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView txtViewSample = findViewById(R.id.txtViewSample);
        imgViewSample = findViewById(R.id.imageViewSample);
        Button btnShowTextOrImage = findViewById(R.id.btnShowTextOrimage);
        Button btnShowBoth = findViewById(R.id.btnShowBoth);


        try {
            int number = Integer.parseInt("23.5");

        } catch (Exception ex) {
//            Toast.makeText(this,"Check the format",Toast.LENGTH_SHORT).show();
            Log.d(TAG,"Perhaps format error");
            ex.printStackTrace(System.err);
            Log.d(TAG,Log.getStackTraceString(ex));
        }


        Drawable img = ResourcesCompat.getDrawable((getResources()),R.drawable.border,getTheme());
        if (img != null) {
            img.setBounds(0,0,img.getIntrinsicWidth(),img.getIntrinsicHeight());
            //Intrinsic Width: The natural width of the Drawable as defined in its resource file.
            txtViewSample.setCompoundDrawables(img,null,img,null);
            txtViewSample.setCompoundDrawablePadding(8);
            txtViewSample.setAlpha(1f);//1f- fully opaque, of - fully transparent
        }

       btnShowBoth.setOnClickListener((View view)->{

       });


        btnShowBoth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewSample.setVisibility(View.VISIBLE);
                imgViewSample.setVisibility(View.VISIBLE);
            }
        });

        btnShowTextOrImage.setOnClickListener((View view)->{

            if(btnShowTextOrImage.getText().equals(getString(R.string.txtShowImage))){
                imgViewSample.setVisibility(View.VISIBLE);
                txtViewSample.setVisibility(View.INVISIBLE);
                btnShowTextOrImage.setText(R.string.txtShowText);
            }else{
                imgViewSample.setVisibility(View.GONE);
                txtViewSample.setVisibility(View.VISIBLE);
                btnShowTextOrImage.setText(R.string.txtShowText);
            }



        });


        txtViewSample.setOnTouchListener(new CustomTouchListener(MainActivity.this){
            @Override
            public void onSingleClick() {
                Log.d(TAG, "Detected single click ont the textView");
                super.onSingleClick();
                if(txtViewSample.getCurrentTextColor() != getColor(R.color.purple)){
                    txtViewSample.setTextColor(getColor(R.color.purple));
                }else {
                    txtViewSample.setTextColor(getColor(R.color.white));
                }
            }

            @Override
            public void onLongClick() {
                Log.d(TAG, "Detected Long click ont the textView");
                super.onLongClick();
            }

            @Override
            public void onDoubleClick() {
                Log.d(TAG, "Detected double click ont the textView");
                super.onDoubleClick();
            }
        });
    }
}