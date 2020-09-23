package com.example.asset_management.login;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.asset_management.R;
import com.example.asset_management.connection.Connection;
import com.example.asset_management.mainHub.MainHubActivity;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.asset_management.jsonhandler.JsonHandler;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.asset_management.login.Login;

import static com.example.asset_management.R.id.login;


public class LoginActivity extends AppCompatActivity {
    String jsonName = "Login.json";
    //JSONObject jsonLogin = new JSONObject();
    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Info;
    private int counter = 10;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(login);

        Name = findViewById(R.id.editText);
        Password = findViewById(R.id.editText2);
        Info = findViewById(R.id.textView);
        Login = findViewById(R.id.btnLogin);

        //Info.setText("Versuche übrig: 10");


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login login = new Login(Name.getText().toString(), BCrypt.withDefaults().hashToString(12, Password.getText().toString().toCharArray()));

                String createDeviceMessage = JsonHandler.createJsonFromObject(login,
                        jsonName, getApplicationContext());

                Toast.makeText(getApplicationContext(), createDeviceMessage, Toast.LENGTH_SHORT)
                        .show();

                //posting login data
                Connection connection = new Connection();
                connection.postLogin(login, getApplicationContext());

                /*JSONObject jsonResponseLogin = new JSONObject(result);  // in progress TODO recive json
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

                //validate(Name.getText().toString(), Password.getText().toString());
            }
        });


        // Benutzer und Rechte - Empfangen JSON
        //


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

