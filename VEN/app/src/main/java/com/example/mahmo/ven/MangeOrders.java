package com.example.mahmo.ven;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MangeOrders extends AppCompatActivity {
    private Button selectCustomer ,selectProduct ,save ,advance;
    private EditText quntity ,date ,dueDate ;
    private TextView selCustomer ,selProduct ;
    private RadioButton paied ,not ;
    private RadioGroup group ;
    private String strquntity , strdate ,strdueDate ,strcus,strpro;
    private DataBase mydb ;
    private String first  ,second  ;
    private ArrayList<String >arrayList = new ArrayList<String>();
    private boolean paiedornot ;
    private String[] splited ;
    private boolean inserted =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mange_orders);
        mydb = new DataBase(this) ;
        selectCustomer = (Button)findViewById(R.id.selectCustomer) ;
        selectProduct = (Button)findViewById(R.id.selectProduct);
        save = (Button)findViewById(R.id.saveData);
        quntity =(EditText)findViewById(R.id.orderquntity);
        date =(EditText)findViewById(R.id.date) ;
        dueDate =(EditText)findViewById(R.id.duedate) ;
        selCustomer =(TextView)findViewById(R.id.selectedcustomers) ;
        selProduct = (TextView)findViewById(R.id.selectedProduct) ;
        paied = (RadioButton)findViewById(R.id.paied) ;
        not = (RadioButton)findViewById(R.id.not);
        group = (RadioGroup)findViewById(R.id.group);
        advance = (Button)findViewById(R.id.advance);


        final Cursor cursor = mydb.getSelectedData();

        if(cursor.getCount()==0){
            Toast.makeText(MangeOrders.this, "no data",Toast.LENGTH_SHORT).show();
        }

        else {
            cursor.moveToFirst();
            selCustomer.setText(cursor.getString(1));
            cursor.moveToNext();
            selProduct.setText(cursor.getString(1));
        }

        selectCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  mydb.updateSelected(selectedname,"1");
                SharedPreferences sharedPreferences  =getSharedPreferences("Table", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                editor.putString("activity","customers") ;
                editor.commit();
                Intent i = new Intent(MangeOrders.this,DispalyAll.class);
                finish();
                startActivity(i);


            }


        });


        selectProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  mydb.updateSelected(selectedname,"2");


                SharedPreferences sharedPreferences = getSharedPreferences("Table", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                editor.putString("activity","products") ;
                editor.commit() ;
                Intent i = new Intent(MangeOrders.this , DispalyAll.class);
                finish();
                startActivity(i) ;


            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ven order ;
                int productId ,customerId,intquntity ;
                strcus = selCustomer.getText().toString() ;
                strpro = selProduct.getText().toString() ;
                strquntity = quntity.getText().toString() ;
                strdate = date.getText().toString() ;
                strdueDate =dueDate.getText().toString() ;
                if(paied.isChecked()){
                    paiedornot = true ;
                }
                else{
                    paiedornot = false ;
                }
                splited = strcus.split(",");
                customerId = Integer.parseInt(splited[1]);
                splited = strpro.split(",");
                productId = Integer.parseInt(splited[1]);
                intquntity = Integer.parseInt(strquntity) ;
                Cursor cursQuntity = mydb.getProductQuntity();
                String s ="1" ;
                while (cursQuntity.moveToNext()){
                    if(cursQuntity.getString(0).equals(""+productId)){
                        s =cursQuntity.getString(3);
                    }
                }
               // String s =mydb.getProductQuntity(""+productId);
                String name ="" ;
                if(Integer.parseInt(s) < intquntity){
                    Toast.makeText(MangeOrders.this,"this Quntity is not supported in the inventory , sorry !",Toast.LENGTH_LONG).show();
                }
                else {
                    Cursor cursor1 = mydb.findProductName();
                    while(cursor1.moveToNext()){
                        if(cursor1.getString(0).equals(""+productId)){
                             name =cursor1.getString(1);
                        }
                    }
                    mydb.updateQuntity(Integer.parseInt(s)-intquntity,name);


                    order = createOrder(productId, customerId, intquntity, strdate, strdueDate, paiedornot);

                    inserted = mydb.insert(order);
                }
                if(inserted == true){
                    Toast.makeText(MangeOrders.this,"Done",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MangeOrders.this,"not Done",Toast.LENGTH_SHORT).show();
                }
                selCustomer.setText("");
                selProduct.setText("");
                quntity.setText("");
                date.setText("");
                dueDate.setText("");


            }
        });
        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MangeOrders.this,Advance.class);
                startActivity(i);
            }
        });


    }
    public static Ven createOrder(int productID,int customerId,int orderQuntity,String orderDate,String orderDue,boolean pai){
        return new Order(productID ,customerId ,orderQuntity,orderDate ,orderDue ,pai);
    }
}
