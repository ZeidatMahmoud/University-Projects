package com.example.mahmo.ven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnActiveListener;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

public class Choose extends AppCompatActivity {
    private SwipeButton newCustomer ,newProducts ,newOrders ,newPayment ;
    private TextView username ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        newCustomer =(SwipeButton) findViewById(R.id.maintainCustomers) ;
       newProducts = (SwipeButton) findViewById(R.id.maintainproducts);
        newPayment =(SwipeButton) findViewById(R.id.maintainpayment);
       newOrders = (SwipeButton) findViewById(R.id.maintainorders) ;
        username =(TextView)findViewById(R.id.usernameup);

        newCustomer.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {

                Intent i = new Intent(Choose.this,DealWithCustomers.class);
                startActivity(i);
            }
        });


        newCustomer.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {


            }
        });
        newProducts.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {

                Intent i = new Intent(Choose.this,DealWithProducts.class);
                startActivity(i);
            }
        });




        newOrders.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {

                Intent i = new Intent(Choose.this,MangeOrders.class) ;
                startActivity(i);
            }
        });


        newPayment.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {

                Intent i = new Intent(Choose.this,ManegePayment.class);
                startActivity(i);
            }
        });


/*
       newPayment.setOnStateChangeListener(new OnStateChangeListener() {
           @Override
           public void onStateChange(boolean active) {

           }
       });
       */





    }
}
