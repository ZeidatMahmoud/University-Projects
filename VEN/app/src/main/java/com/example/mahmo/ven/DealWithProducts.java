package com.example.mahmo.ven;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class DealWithProducts extends AppCompatActivity {
    private EditText name ,price ,quntity ;
    private Button add, delete ,display;
    private DataBase mydb ;
    private String strname ;
    private int intprice , intquntity ;
    private boolean inserted ;
    private int flag = 0 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_with_products);
        mydb =new DataBase(this) ;
        name = (EditText)findViewById(R.id.productname);
        price = (EditText)findViewById(R.id.productprice);
        quntity = (EditText)findViewById(R.id.productquntity);
        add =(Button)findViewById(R.id.addproduct) ;
        delete =(Button)findViewById(R.id.deleteproducts);
        display = (Button)findViewById(R.id.dispalyallproducts);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ven product ;
                flag = 0 ;
                int newQuntity ;
                strname = name.getText().toString() ;
                intprice = Integer.parseInt(price.getText().toString());
                intquntity =Integer.parseInt(quntity.getText().toString()) ;
                product = CreateProduct(strname,intprice,intquntity);
                Cursor cursor = mydb.getAll(mydb.PROUDUCT_TABLE);
                if(cursor.getCount() == 0) {
                    inserted = mydb.insert(product);
                }
                else {
                    while (cursor.moveToNext()){
                        if(strname.equals(cursor.getString(1))){
                            newQuntity = Integer.parseInt(cursor.getString(3)) ;
                            mydb.updateQuntity(newQuntity+intquntity ,strname) ;
                            flag =1 ;

                        }

                    }
                    if(flag == 0){
                        inserted = mydb.insert(product);
                    }

                }

                if(inserted == true){
                    Toast.makeText(DealWithProducts.this,"yes",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(DealWithProducts.this,"no",Toast.LENGTH_SHORT).show();
                }



            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("Table", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                editor.putString("activity","products") ;
                editor.commit() ;
                Intent i = new Intent(DealWithProducts.this,DispalyAll.class);
                startActivity(i);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("Table", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                editor.putString("activity","products") ;
                editor.commit() ;
                Intent i = new Intent(DealWithProducts.this,DeleteCustomer.class);
                startActivity(i);

            }
        });




    }
    public static Ven CreateProduct(String strname ,int intprice ,int intquntity){
        return new Product(strname,intprice,intquntity);
    }

}
