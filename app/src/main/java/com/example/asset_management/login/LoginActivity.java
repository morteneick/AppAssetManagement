package com.example.asset_management.login;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Connection
 * <p>
 * Version 1.0
 * </p>
 * 25.09.2020
 * AUTHOR: Morten Eickmann
 */

public class LoginActivity extends AppCompatActivity {
    private EditText NameET;
    private EditText PasswordET;
    private Button LoginBtn;
    private TextView Info;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        NameET = findViewById(R.id.editText);
        PasswordET = findViewById(R.id.editText2);
        Info = findViewById(R.id.textView);
        LoginBtn = findViewById(R.id.btnLogin);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = NameET.getText().toString();
                String Password = PasswordET.getText().toString();

                if (validateLogin(Username, Password)) {
                    Login login = new Login(Username, BCrypt.withDefaults().hashToString(12,
                            Password.toCharArray()));

                    //posting login data
                    Connection connection = new Connection();
                    connection.postLogin(login, getApplicationContext(), LoginActivity.this);

                }
            }
        });
        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.dallmann-bau.de"));
                startActivity(browserIntent);
            }
        });
    }

    private boolean validateLogin(String Username, String Passwd) {
        //catching empty data
        if (Passwd.trim().length() == 0 && Username.trim().length() == 0 || Username == null && Passwd == null) {
            Toast.makeText(getApplicationContext(), "Bitte Benutzernamen und Passwort eingeben.", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (Username == null || Username.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bitte Benutzernamen eingeben.", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        if (Passwd == null || Passwd.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bitte Passwort eingeben.", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }
}

