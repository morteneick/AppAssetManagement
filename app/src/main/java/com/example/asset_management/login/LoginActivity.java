package com.example.asset_management.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asset_management.R;
import com.example.asset_management.mainHub.MainHubActivity;

import static com.example.asset_management.R.id.login;


public class LoginActivity extends AppCompatActivity {

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

        Name = (EditText)findViewById(R.id.editText);
        Password = (EditText)findViewById(R.id.editText2);
        Info = (TextView) findViewById(R.id.textView);
        Login = (Button)findViewById(R.id.btnLogin);

        Info.setText("Versuche übrig: 10");


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

    }
    private void validate(String userName, String userPassword){
        String ucorrect = "Admin";
        String pcorrect = "1234";

        if (userName.equals(ucorrect) && userPassword.equals(pcorrect)){

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

}