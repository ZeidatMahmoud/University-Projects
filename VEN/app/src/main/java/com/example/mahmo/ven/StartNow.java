package com.example.mahmo.ven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartNow extends AppCompatActivity {
    private TextView welcome ;
    private DataBase mydb ;
    private String name ;
    private Button startNow ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_now);
        mydb = new DataBase(this) ; // creating the database
        welcome =(TextView)findViewById(R.id.welcome);
        startNow = (Button)findViewById(R.id.startnow) ;
        name = mydb.getUserName();
        welcome.setText("Welcome"+name+" !"); // set the TextVeiw Wlcome + user name
        startNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.insertSelected(); // to excute once
                mydb.insertSelected(); // insert another
                Intent i = new Intent(StartNow.this ,Login.class) ; // go to login activity
                finish();
                startActivity(i);
            }
        });
    }
}
