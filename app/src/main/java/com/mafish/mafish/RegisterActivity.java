package com.mafish.mafish;

import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mafish.mafish.helper.SqliteHelper;
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
                if (validate()) {
                    String UserName = etName.getText().toString();
                    String Email = etNoHp.getText().toString();
                    String Password = etPassword.getText().toString();

                    //Check in the database is there any user associated with  this email
                        //Email does not exist now add new user to database
                        sqliteHelper.addUser(new User(null, UserName, Email, Password));
                        Snackbar.make(buttonRegister, "Berhasil mendaftar! ", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {

                        //Email exists with email input provided so show error user already exist
                        Snackbar.make(buttonRegister, "Masukan data yang valid!", Snackbar.LENGTH_LONG).show();
                    }
            }
        });
    }


    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String UserName = etName.getText().toString();
        String Email = etNoHp.getText().toString();
        String Password = etPassword.getText().toString();

        //Handling validation for UserName field

        //Handling validation for Email field
        if (Email.isEmpty()) {
            valid = false;
            etNoHp.setError("Masukan No HP yg valid!");
        } else {
            valid = true;
            etNoHp.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            etPassword.setError("Masukan password yg valid!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                etPassword.setError(null);
            } else {
                valid = false;
                etPassword.setError("Password harus lebih dari 5 karakter");
            }
        }

        return valid;
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
