package com.example.mahmo.ven;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteCustomer extends AppCompatActivity {

    private ImageButton find ;
    private Button  delete;
    private TextView id , nametext , address , phonetext;
    private EditText search ;
    private DataBase mydb ;
    private int index  =0;
    private String flag ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_customer);
        mydb = new DataBase(this) ;

        id = (TextView)findViewById(R.id.idContent);
        nametext = (TextView)findViewById(R.id.nameContent);
        address = (TextView)findViewById(R.id.addresscontent);
        phonetext = (TextView)findViewById(R.id.phonecontent);
        search = (EditText) findViewById(R.id.search);
        delete = (Button)findViewById(R.id.delete);
        find =(ImageButton)findViewById(R.id.searchbutton) ;

        final Cursor cursor ;
        SharedPreferences sharedPreferences = getSharedPreferences("Table", Context.MODE_PRIVATE);
        flag =sharedPreferences.getString("activity","no vlaue");
        if(flag.equals("customers")){
            cursor =mydb.getAll(mydb.CUSTOMER_TABLE);
        }
        else if(flag.equals("products")){
            cursor =mydb.getAll(mydb.PROUDUCT_TABLE);
        }
        else{
            cursor =mydb.getAll(mydb.CUSTOMER_TABLE);
        }


        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cursor.moveToFirst();
                id.setText("");
                nametext.setText("");
                address.setText("");
                phonetext.setText("");
                if(cursor.getCount() == 0){
                    Toast.makeText(DeleteCustomer.this,"There is no elements",Toast.LENGTH_SHORT).show();
                }

                else{
                    while(cursor.moveToNext()){

                            if(search.getText().toString().equals(cursor.getString(1))||search.getText().toString().equals(cursor.getString(2))||search.getText().toString().equals(cursor.getString(3))){
                                id.setText(cursor.getString(0));
                                nametext.setText(cursor.getString(1));
                                address.setText(cursor.getString(2));
                                phonetext.setText(cursor.getString(3));
                                search.setText("");
                                index =1 ;
                               // cursor.moveToFirst() ;
                            }



                                }
                                if(index == 0){
                                    Toast.makeText(DeleteCustomer.this,"Not Found",Toast.LENGTH_SHORT).show();
                                }


                        }





            }
        });


    }
}
