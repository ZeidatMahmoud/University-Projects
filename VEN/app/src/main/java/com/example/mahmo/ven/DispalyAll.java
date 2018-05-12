package com.example.mahmo.ven;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DispalyAll extends AppCompatActivity {
    private DataBase mydb ;
    private ListView list ;
    private HashMap customers  = new HashMap<Integer,String>();
    private CustomAdabter adapter ;
    private Button clearAll ;
    private String flag ;
    private int pos ;
    private  String[] str ;
    private String splitted ;
    private int toKnow ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispaly_all);
       // array.add("mahmood") ;


        mydb = new DataBase(this) ;
        list = (ListView)findViewById(R.id.dispalyall);
        clearAll = (Button)findViewById(R.id.clear);
        Cursor cursor ;
        final SharedPreferences sharedPreferences = getSharedPreferences("Table", Context.MODE_PRIVATE);
        flag =sharedPreferences.getString("activity","no vlaue");
        if(flag.equals("customers")){
           cursor =mydb.getAll(mydb.CUSTOMER_TABLE);
            toKnow =1 ;

        }
        else if(flag.equals("products")){
            cursor =mydb.getAll(mydb.PROUDUCT_TABLE);
            toKnow =2 ;
        }
        else if(flag.equals("orders")){
            cursor =mydb.getAll(mydb.ORDER_TABLE);
            toKnow =0 ;
        }
        else if(flag.equals("payment")){
            cursor =mydb.getAll(mydb.CUSTOMER_TABLE);
            toKnow = 5 ;
        }
        else{
            cursor =mydb.getAll(mydb.ORDER_TABLE);
            toKnow =4 ;
        }




       // mydb.freeTable(mydb.CUSTOMER_TABLE);

        if(cursor.getCount()==0){
            Toast.makeText(DispalyAll.this,"There is no elements",Toast.LENGTH_LONG).show();

        }
        else{
            int  i =0  ;
            if(toKnow == 0){
                while (cursor.moveToNext()){
                    customers.put(i,cursor.getString(0)+","+cursor.getString(1)+","+cursor.getString(2)+","+cursor.getString(3)+","+cursor.getString(4)+","+cursor.getString(5)+","+cursor.getString(6));
                    i++ ;

                }

            }
            else {
                while (cursor.moveToNext()) {
                    customers.put(i, cursor.getString(0) + "," + cursor.getString(1) + "," + cursor.getString(2) + "," + cursor.getString(3));
                    i++;

                }
            }
               // ListAdapter customAdapter = new ListAdapter(this, R.layout.,array)
            ///str = (String[])array.toArray();

            adapter = new CustomAdabter(this,0,customers);
            list.setAdapter(adapter);



        }
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag.equals("customers")){
                    mydb.freeTable(mydb.CUSTOMER_TABLE);
                }
                else if(flag.equals("products")){
                    mydb.freeTable(mydb.PROUDUCT_TABLE);
                }





            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position ;
                str = ((String) customers.get(pos)).split(",");
                if(toKnow == 5){
                    SharedPreferences sharedPreferences = getSharedPreferences("payments",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("select",(String) customers.get(pos)) ;
                    editor.commit() ;
                    Intent i = new Intent(DispalyAll.this ,ManegePayment.class);
                    finish();
                    startActivity(i);
                }
                if(toKnow==0){
                    SharedPreferences sharedPreferences = getSharedPreferences("dispaly",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit() ;
                    editor.putString("id", str[0]) ;
                    editor.putString("customer",str[1]) ;
                    editor.putString("product",str[2]);
                    editor.putString("quntity",str[3]);
                    editor.putString("date",str[4]);
                    editor.putString("duedate",str[5]);
                    editor.commit() ;
                    Intent i = new Intent(DispalyAll.this,UpdateOrders.class);
                    finish();
                    startActivity(i);
                }

                if(toKnow == 1) {
                    mydb.updateSelected(str[1]+","+str[0], "1");
                    Intent i = new Intent(DispalyAll.this,MangeOrders.class);
                    finish();
                    startActivity(i);
                }
                else if(toKnow == 2){
                    mydb.updateSelected(str[1]+","+str[0],"2");
                    Intent i = new Intent(DispalyAll.this,MangeOrders.class);
                    finish();
                    startActivity(i);
                }



            }
        });




    }
}
