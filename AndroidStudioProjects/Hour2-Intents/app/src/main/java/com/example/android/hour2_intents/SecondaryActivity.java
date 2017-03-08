package com.example.android.hour2_intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Intent.ACTION_VIEW;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

    /* OLD */

        //get the intent from MainActivity
        //Intent intentFromMainActivity = getIntent();

        //identify the string using the key "com.talkingandroid.MESSAGE"
        //String message = intentFromMainActivity.getStringExtra("com.talkingandroid.MESSAGE");
        // /* END OLD */

        String message = "No data from Intent";

        Intent intent = getIntent();

        if(intent != null){ //only if the intent is not null - otherwise, if null, default message is "no date from Intent"
            if(intent.hasExtra("com.talkingandroid.MESSAGE")){ //if there is a message, get it and update message

                message = intent.getStringExtra("com.talkingandroid.MESSAGE"); //message "Hello, I'm info from MainActivity!"

            }
            else if(intent.hasExtra(Intent.EXTRA_TEXT)){ //if there is EXTRA_TEXT, then update message to that

                message = intent.getStringExtra(Intent.EXTRA_TEXT); //EXTRA_TEXT from a different Intent...
            }
        }

        //message = intent.getStringExtra(Intent.EXTRA_TEXT); //TEST

        //messageTextView will be the text view we created in the XML layout so we can set it to what was passed by the intent
        TextView messageTextView = (TextView) findViewById(R.id.message); //use the id to attach the message TextView to the TextView I created
        messageTextView.setText(message); //pass the message received from the other class's intent, and set messageTextView to that String


        /* Create telephone dial button */
        Button dialNumberButton = (Button) findViewById(R.id.dialer_button);

        //setOnClickListener
        dialNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = "tel:31235551234";
                Uri phoneNum = Uri.parse(number);

                Intent dialIntent = new Intent(Intent.ACTION_DIAL, phoneNum); //**had to add <action android:name="android.intent.action.DIAL" /> to the manifest to allow permission to the dialer

                //check if the device allows the dialer (can call)
                if(dialIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(dialIntent);
                }
            }
        });


    }//end of onCreate()



    //menu code excluded
}
