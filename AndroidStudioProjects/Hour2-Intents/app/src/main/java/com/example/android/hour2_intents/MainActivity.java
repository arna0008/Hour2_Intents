package com.example.android.hour2_intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Intent.ACTION_VIEW;

/*
    This app is to demonstrate how Intents work
    So far, i've created a button, and set an onClickListener to use that button
    it will redirect me to the next page (which is the SecondaryActivity)


 */

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* Creating a button and going to another activity  */

        //define a button
        Button goSecondActivityButton = (Button) findViewById(R.id.go_to_second_activity_button); //findViewById uses the button's Id

        //call the setOnClickListener and pass OnClickListener (android.View.view.onClickListener)
        goSecondActivityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                /*
                    need to create an Intent here
                    It takes in a Context (android.content.Context), and the class/Activity you want to go to...
                 */
                Intent goToSecondaryActivityIntent = new Intent(getApplicationContext(), SecondaryActivity.class);

                //adding info to pass to another method. putExtra takes in (com.talkingmessage.MESSAGE, "The Info you wanna pass...") - (key, value)... strange key...
                //secondary activity will recieve this info and display it in a textView
                goToSecondaryActivityIntent.putExtra("com.talkingandroid.MESSAGE","Hello, I'm info from MainActivity!");

                //call the startAcitivity method and pass the Intend we created
                startActivity(goToSecondaryActivityIntent);

            }
        });


        /* LAUNCHING A MAP */
        /* Using an implicit Intent so that our app will launch with map coordinates */

        //Show Map button
        Button showMapButton = (Button) findViewById(R.id.map_button);

        //set the OnClickListener for the new Button
        showMapButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){


                String geoUri = "geo:37.422,-122.084?z=8"; //create the string that will be parsed - has lat and long, and zoom level (8)

                Uri geo = Uri.parse(geoUri); //create the URI (Universal Resource Identifier) which will parse the string

                //The Intent is in the following format -> Intent(String action, Uri uri)
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, geo); //create the Intent which takes in an ACTION_VIEW intent which is a specific kind of intent for

                //some devices cannot handle certain intents.
                //need to first check that the device can handle ("resolve") the intent - if it can't, it returns null
                if(mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);//startActivity method to action show the map's Activity
                }


            }
        });

        /* LAUNCHING A WEB PAGE */

        //button
        Button showWebPageButton = (Button) findViewById(R.id.web_button);

        showWebPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String webUrl = "https://www.google.ca";
                Uri web = Uri.parse(webUrl); //Universal Resource Identifier
                Intent webIntent = new Intent(Intent.ACTION_VIEW, web);

                if(webIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(webIntent);
                }
            }
        });



        /* Bouta try sending an email... */
        //email field
       final EditText emailField = (EditText) findViewById(R.id.email_field);

        //send message to email
        Button sendButton = (Button) findViewById(R.id.send_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //got da string email
                String[] recipient = {"ian.arnald@gmail.com"};
               //String recipient = emailField.getText().toString();

                //make Intent, pass the ACTION_SEND static variable
                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setType("message/rfc822");//note the type of message **IMPORTANT** otherwise, crashes
                /* Add the extras: the email recipient, subject, and message */
                emailIntent.putExtra(Intent.EXTRA_EMAIL, recipient);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hidden Fences - Motion Picture of the Year");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Wow, what a surprise - not!");


//                try {
//                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//                } catch (android.content.ActivityNotFoundException ex) {
//                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//                }


                if(emailIntent.resolveActivity(getPackageManager()) != null){


                    startActivity(Intent.createChooser(emailIntent, "Send email using...")); //Intent.createChooser(Intent, "Message prompt")


                    Toast.makeText(MainActivity.this, "Going to send email", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
