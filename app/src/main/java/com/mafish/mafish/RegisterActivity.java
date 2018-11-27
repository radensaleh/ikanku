package com.mafish.mafish;

import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mafish.mafish.model.User;

public class RegisterActivity extends AppCompatActivity {

    EditText etName;
    EditText etNoHp;
    EditText etPassword;

    //Declaration Button
    Button buttonRegister;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sqliteHelper = new SqliteHelper(this);
        initTextViewLogin();
        initViews();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String UserName = etName.getText().toString();
                    String NoHp = etNoHp.getText().toString();
                    String Password = etPassword.getText().toString();

                    //Check in the database is there any user associated with  this email

                        //Email does not exist now add new user to database
                        sqliteHelper.addUser(new User(null, UserName, NoHp, Password));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(buttonRegister, "Berhasil mendaftar! Yuk langsung login ", Snackbar.LENGTH_LONG).show();
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    }
        });
    }

    //this method used to set Login TextView click event
    private void initTextViewLogin() {
        Button btnLinkLogin = (Button) findViewById(R.id.btn_link_login);
        btnLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        etName = (EditText) findViewById(R.id.et_name);
        etNoHp = (EditText) findViewById(R.id.et_nohp);
        etPassword = (EditText) findViewById(R.id.et_password);
        buttonRegister = (Button) findViewById(R.id.btn_signup);

    }

    //This method is used to validate input given by user
}
