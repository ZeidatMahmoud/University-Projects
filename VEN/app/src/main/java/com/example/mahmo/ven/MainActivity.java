package com.example.mahmo.ven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button login, signup ,button; // declare the buttons used in this activity
    private final int noOfUsers =1; // the total number of users who can sign up
    private TextView text;
    DataBase mydb  ; // get access to the database



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // remove the title bar
        setContentView(R.layout.activity_get_started);
        mydb = new DataBase(this) ; //we are using the database


        login = (Button)findViewById(R.id.goToLogin) ; // find the log in button
        signup = (Button)findViewById(R.id.signup);// find thr sign up button
        text =(TextView) findViewById(R.id.noOfuser);
       // button = (Button)findViewById(R.id.free);


          login.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v) {
                  Intent  i = new Intent(MainActivity.this ,Login.class); // go to login activity
                  startActivity(i);
                  
              }
          });

          signup.setOnClickListener(new View.OnClickListener() {
        // @Override
          public void onClick(View v) {

         if(mydb.getRawsCount(mydb.TABLE_NAME)<noOfUsers){ // we can allow to spacific number of users to sighn in
         Intent i = new Intent(MainActivity.this, User.class); // fill data for user activity
          startActivity(i); // start now



         }
          else {
         text.setText("There is enough number of users");

         }
           }
          });






    }
}



