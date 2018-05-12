package com.example.mahmo.ven;

import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManegePayment extends AppCompatActivity {
    private Button select , save ;
    private TextView selected ;
    private EditText amount , date ;
    private DataBase mydb ;
    private String customer ;
    private String[] str ;
    private String strid ,stramount ,strdate ;
    private boolean inserted ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manege_payment);
        mydb = new DataBase(this) ;
        select = (Button)findViewById(R.id.selectpaymentCustomer);
        save = (Button)findViewById(R.id.paymentsave) ;
        selected= (TextView)findViewById(R.id.selectedpaymentCustomer);
        amount =(EditText)findViewById(R.id.amount);
        date = (EditText)findViewById(R.id.paymentDate);
        SharedPreferences sharedPreferences = getSharedPreferences("payments",Context.MODE_PRIVATE);
        customer = sharedPreferences.getString("select","no value");
        selected.setText("");

        if(customer.equals("no value")== false) {
            str = customer.split(",");
            selected.setText("Customer ID : " + str[0] + "\nName : " + str[1] + "\nAddress : " + str[2] + "\nPhone : " + str[3]);
            strid = str[0];
        }


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("Table", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                editor.putString("activity","payment");
                editor.commit() ;
                Intent i  =new Intent(ManegePayment.this,DispalyAll.class) ;
                finish();
                startActivity(i);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ven payment ;
                stramount = amount.getText().toString() ;
                strdate = date.getText().toString() ;
                payment = createPayment(strid,stramount,strdate);
                inserted = mydb.insert(payment);
                if(inserted){
                    Toast.makeText(ManegePayment.this,"Done",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ManegePayment.this,"not",Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public Ven createPayment(String id ,String amount ,String date){
        return new Payment(id,amount,date);
    }

}
