package com.example.mahmo.ven;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.spark.submitbutton.SubmitButton;

public class Login extends AppCompatActivity {
    private SubmitButton login ;
    private EditText name ,password;
    private String strname ,strpassword ;
    private String nameBase ,passwordBase ;
    private TextView error ;
    private DataBase mydb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb = new DataBase(this);
        setContentView(R.layout.activity_login);
        login = (SubmitButton) findViewById(R.id.login) ;
        name = (EditText)findViewById(R.id.userLogin) ;
        password =(EditText)findViewById(R.id.passwordLogin);
        error = (TextView)findViewById(R.id.error);
       // error.setText(mydb.getUserName());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strname = name.getText().toString() ;
                strpassword = password.getText().toString();
                nameBase =mydb.getUserName() ;
                passwordBase = mydb.getPassword() ;

                if(strname.equals(nameBase)&& strpassword.equals(passwordBase)){ // if the user name or password match
                    AsyncTask<String,String,String>demoLogin = new AsyncTask<String, String, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            try{
                                Thread.sleep(1000) ;
                            }
                            catch (InterruptedException e){
                                e.printStackTrace();

                            }
                            return "done";
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            if(s.equals("done")){
                                Intent i = new Intent(Login.this ,Choose.class) ;
                                finish();
                                startActivity(i);
                            }
                        }
                    };
                    login.startAnimation();
                    demoLogin.execute();


                }
                else{
                    name.setText("");
                    password.setText("");
                    error.setText("Wrong user name or password"); // print message if the username or password incorrect
                }


            }
        });

    }
}
