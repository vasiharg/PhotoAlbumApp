package com.example.va20072267.photoalbumapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import android.os.Handler;


public class MainActivity extends ActionBarActivity {

    private Button previousBtn, nextButton;
    private ToggleButton autoPlay;
    private ImageView previewBox;
    private int[] imageList;
    int i=0;
    public static final String TAG = "SOS";

    private Handler mhandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previewBox = (ImageView)findViewById(R.id.previewImg);
        previewBox.setImageResource(R.drawable.fruit1);
        imageList = new int[]{R.drawable.fruit1, R.drawable.fruit2, R.drawable.fruit3, R.drawable.fruit4, R.drawable.fruit5, R.drawable.fruit6};

        autoPlay = (ToggleButton)findViewById(R.id.toggleButton);
        previousBtn = (Button) findViewById(R.id.prevBtn);
        nextButton = (Button) findViewById(R.id.nextBtn);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Next Clicked..." + i);
                if(i >= 0 && i < imageList.length - 1) { //if i value is higher or equal to zero and  less than 5
                    i++;                                    // increment i
                    previewBox.setImageResource(imageList[i]); //set the image resource  as i
                }else if( i == imageList.length-1 ){ //or id i values is equal to the last  list element
                    i = 0;                          //set i to zero to display the first element of the list
                    previewBox.setImageResource(imageList[i]);
                }
            }
        });

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //instantiating onClick method
                if(i == 0){                 //if i is zero
                    i = imageList.length - 1; //set i to last  list item element
                    previewBox.setImageResource(imageList[i]);// set image resource corresponding to i
                }else{                                          //if i is not zero
                    i--;                                        //decrement the i
                    previewBox.setImageResource(imageList[i]);  //set image resource corresponding to i
                }
            }
        });

        autoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (autoPlay.isChecked()) {
                    mhandler.postDelayed(runnable, 1500);
                    i = 0;
                    Toast.makeText(MainActivity.this, "Runnable is set On", Toast.LENGTH_SHORT).show();
                } else {
                    mhandler.removeCallbacks(runnable);
                    Toast.makeText(MainActivity.this, "Runnable is set Off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            previewBox.setImageResource(0);
            previewBox.setBackgroundResource(imageList[i]);
            mhandler.postDelayed(runnable,1500);
            i++;

            if (i == imageList.length - 1) i = 0;
        }
    };
}
