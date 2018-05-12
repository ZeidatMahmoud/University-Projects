package com.example.mahmo.ven;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Advance extends AppCompatActivity {
    private Button showAll ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance);
        showAll = (Button)findViewById(R.id.show);

        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("Table", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                editor.putString("activity","orders");
                editor.commit() ;
                Intent i = new Intent(Advance.this,DispalyAll.class) ;
                startActivity(i);
            }
        });

    }
}
