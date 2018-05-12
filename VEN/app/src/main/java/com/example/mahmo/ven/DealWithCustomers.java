package com.example.mahmo.ven;

import android.content.Context;
import android.content.EntityIterator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DealWithCustomers extends AppCompatActivity {
    private EditText name, address, phone;
    private Button add, delete, dispaly;
    private String strname, straddress, strphone;
     private DataBase mydb ;
    private boolean inserted ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_with_customers);
        mydb = new DataBase(getApplicationContext()) ;



        name = (EditText)findViewById(R.id.customername);
        address = (EditText)findViewById(R.id.addresss);
        phone =(EditText)findViewById(R.id.phoneNumber);
        add =(Button)findViewById(R.id.add);
        delete  =(Button)findViewById(R.id.deletecustomers);
        dispaly =(Button)findViewById(R.id.dispalyall);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ven customer ;
                strname =name.getText().toString();
                straddress= address.getText().toString() ;
                strphone =phone.getText().toString() ;
                customer = createCustomer(strname,straddress,strphone);
                inserted = mydb.insert(customer);

                if(inserted==true){
                    Toast.makeText(DealWithCustomers.this,"yes",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(DealWithCustomers.this,"no",Toast.LENGTH_LONG).show();
                }
                name.setText("");
                address.setText("");
                phone.setText("");
            }
        });
        dispaly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences  =getSharedPreferences("Table", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                editor.putString("activity","customers") ;
                editor.commit();
                Intent i = new Intent(DealWithCustomers.this ,DispalyAll.class);
                startActivity(i);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences  =getSharedPreferences("Table", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                editor.putString("activity","customers") ;
                editor.commit();
                Intent i = new Intent(DealWithCustomers.this ,DeleteCustomer.class);
                startActivity(i);
            }
        });
        // mydb = new DataBase(this) ;
/*
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Ven customer ;
                strname =name.getText().toString();
                straddress= address.getText().toString() ;
                strphone =phone.getText().toString() ;
                customer = createCustomer(strname,straddress,strphone);
                //   inserted = mydb.insert(customer);

                if(inserted==true){
                    Toast.makeText(DealWithCustomers.this,"yes",Toast.LENGTH_LONG).show();
                }

            }
        });
        */


    }
    public static Ven createCustomer(String strname ,String straddress,String strphone){
        return new Customer(strname,straddress,strphone);
    }
}
