package com.example.asset_management.login;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.example.asset_management.mainHub.MainHubActivity;

import java.util.ArrayList;

import static com.example.asset_management.R.id.action_FirstFragment_to_Login;
import static com.example.asset_management.R.id.login;


public class LoginActivity extends AppCompatActivity {
    String jsonName = "Login.json";
    private EditText NameET;
    private EditText PasswordET;
    private Button LoginBtn;
    private TextView Info;
    private ArrayList<UserInfo> userInfos = new ArrayList<>();

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

                if (validateLogin(Username,Password)){
                    Login login = new Login(Username, BCrypt.withDefaults().hashToString(12,Password.toCharArray()));

                    //posting login data
                    Connection connection = new Connection();
                    connection.postLogin(login, getApplicationContext());

                    // receiving answer
                    //connection.getLoginData(getApplicationContext());
/*
                    UserInfo ui = new UserInfo();
                    String user = ui.getFirstname();
                    Toast.makeText(getApplicationContext(), "Willkommen " + user, Toast.LENGTH_SHORT).show();*/
/*
                    UserInfo ui = new UserInfo();
                    boolean access = ui.getAccess();
                    String uname = ui.getFirstname() + ui.getSurname();

                    if (access == true){
                        //access granted
                        Toast.makeText(getApplicationContext(),uname,Toast.LENGTH_SHORT);
                        Intent intent = new Intent(LoginActivity.this, MainHubActivity.class);
                    }*/
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

    private boolean validateLogin(String Username, String Passwd){

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
   /* private void validate(String userName, String userPassword){
        String ucorrect = "Admin";
        String hashPassword = "1234";
        String bcyrptHashString = BCrypt.withDefaults().hashToString(12,hashPassword.toCharArray());

        BCrypt.Result result = BCrypt.verifyer().verify(bcyrptHashString.toCharArray(),bcyrptHashString);

        if (userName.equals(ucorrect) && result.verified){

            Intent intent = new Intent(LoginActivity.this, MainHubActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplication(),"Falsche Eingabe.", Toast.LENGTH_SHORT).show();
            counter --;
            if (counter == 0)
            {
                Login.setEnabled(false);
            }
        }
    }
*/
   /*JSONObject jsonResponseLogin = new JSONObject(result);
                try {
                    UserInfo currentUser = new UserInfo(jsonResponseLogin.getString("worker_id"), jsonResponseLogin.getString("e_mail"), jsonResponseLogin.getString("name"), jsonResponseLogin.getString("surname"), jsonResponseLogin.getString("role"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    userRights userRights = new userRights(jsonResponseLogin.getBoolean("add_device"), jsonResponseLogin.getBoolean("add_user"),
                            jsonResponseLogin.getBoolean("booking_device"), jsonResponseLogin.getBoolean("delete_booking"), jsonResponseLogin.getBoolean("delete_device"),
                            jsonResponseLogin.getBoolean("delete_user"), jsonResponseLogin.getBoolean("edit_booking"), jsonResponseLogin.getBoolean("edit_device"),
                            jsonResponseLogin.getBoolean("edit_user"), jsonResponseLogin.getBoolean("view_device"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

