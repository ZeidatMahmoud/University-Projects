package com.example.mahmo.ven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class User extends AppCompatActivity {
    DataBase mydb ;
    private Button save ;
    private EditText name ,password ,rePassword ;
    private String strName ,StrPassword ,strRePass ;
    private TextView error ;
    private boolean inserted ;
    private int noOfRaws ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        try {
            mydb = new DataBase(this);
        }
        catch (Exception e){

        }
       // mydb = new DataBase(this);
        name = (EditText)findViewById(R.id.fullname) ;
        password = (EditText)findViewById(R.id.password) ;
        rePassword = (EditText)findViewById(R.id.repassword);
        save  = (Button)findViewById(R.id.save);
        error = (TextView)findViewById(R.id.passmatch) ;



        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Ven user ;
                strName = name.getText().toString() ;
                StrPassword = password.getText().toString() ;
                strRePass = rePassword.getText().toString() ;
                if(StrPassword.equals(strRePass)){
                   // user = createUser(strName,StrPassword);
                  inserted =   mydb.insertData(strName,StrPassword);
                    noOfRaws = mydb.getRawsCount(mydb.TABLE_NAME);
                    mydb.close();
                    if(inserted == true){
                        Toast.makeText(User.this,"inserted "+noOfRaws,Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(User.this,"not inserted",Toast.LENGTH_LONG).show();
                    }
                    error.setText("");

                        Intent i = new Intent(User.this,StartNow.class);
                        finish();
                        startActivity(i);
                }
                else{
                    error.setText("Password Do not Match");
                }

            }
        });

    }
    public static Ven createUser(String name ,String Password){

        return new UserData(name,Password);
    }
}
