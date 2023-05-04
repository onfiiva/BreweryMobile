package com.example.brewery;

import static com.example.brewery.Service.AuthAsyncTask.Connection;
import static com.example.brewery.Service.AuthAsyncTask.gotToken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brewery.Service.APIInterface;
import com.example.brewery.Service.AuthAsyncTask;
import com.example.brewery.Service.RequestBuilder;

public class MainActivity extends AppCompatActivity {

    TextView toReg;
    EditText phoneAuth, passwordAuth;
    Button authButton;
    public static String phone, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneAuth = findViewById(R.id.phone_auth);
        passwordAuth = findViewById(R.id.password_auth);
        authButton = findViewById(R.id.button_auth);

        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phone = phoneAuth.getText().toString();
                password = passwordAuth.getText().toString();

                new AuthAsyncTask().execute();

                while (Connection == false) {
                }
                if (gotToken != null) {
                    Intent intent = new Intent(getApplicationContext(), BeerActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);

                    Toast.makeText(getApplicationContext(), "Успешная авторизация.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Неверно введены данные.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}