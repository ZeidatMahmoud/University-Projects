package com.example.mahmo.ven;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateOrders extends AppCompatActivity {
    private TextView productid ,customerid ,id  ;
    private EditText quntity ,date ,duedate ;
    private Button update,delete ;
    private String strid,strcustomer ,strproduct,strdate,strduedate,strquntity ;
    private DataBase mydb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_orders);
        mydb = new DataBase(this);
        productid =(TextView)findViewById(R.id.showproductid);
        customerid = (TextView)findViewById(R.id.showcustomerid);
        id = (TextView)findViewById(R.id.showid);
        quntity = (EditText)findViewById(R.id.updatequntity);
        date = (EditText)findViewById(R.id.showDate);
        duedate = (EditText)findViewById(R.id.showDuedate);
        update = (Button)findViewById(R.id.showupdate);
        delete = (Button)findViewById(R.id.showdelete);

        SharedPreferences sharedPreferences = getSharedPreferences("dispaly", Context.MODE_PRIVATE) ;
        strid = sharedPreferences.getString("id","no vlue");
        strcustomer =sharedPreferences.getString("customer","no vlue");
        strproduct =sharedPreferences.getString("product","no vlue");
        strquntity = sharedPreferences.getString("quntity","no vlue");
        strdate = sharedPreferences.getString("date","no vlue");
        strduedate = sharedPreferences.getString("duedate","no vlue");
        productid.append(strproduct);
        id.append(strid);
        customerid.append(strcustomer);
        quntity.setText(strquntity);
        date.setText(strdate);
        duedate.setText(strduedate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mydb.updateOrders(strid,quntity.getText().toString(),date.getText().toString(),duedate.getText().toString());
                id.setText("ID : ");
                productid.setText("product ID :");
                customerid.setText("Customer ID : ");
                quntity.setText("");
                date.setText("");
                duedate.setText("");

            }
        });

    }

}
