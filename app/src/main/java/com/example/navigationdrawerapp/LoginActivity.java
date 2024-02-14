package com.example.navigationdrawerapp;

import static com.example.navigationdrawerapp.MainActivity.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    EditText email,password;
    Button btn1;
    ImageView img;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn1 = findViewById(R.id.main3_logout_sign_in_btn);
        img = findViewById(R.id.main3_logout_back_img);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Instance_Class.callAPI().loginUser(email.getText().toString(),password.getText().toString()).
                        enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        if(response.body().getConnection()==1){
                            if(response.body().getResult()==1){
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                editor.putBoolean("Login",false);
                                editor.putString("id",response.body().getUserdata().getId());
                                editor.putString("name",response.body().getUserdata().getName());
                                editor.putString("email",response.body().getUserdata().getEmail());
                                editor.putString("password",response.body().getUserdata().getPassword());
                                editor.commit();
                                 Intent intent = new Intent(LoginActivity.this,DrawerMain_Activity.class);
                                 startActivity(intent);
                            }else {
                                Toast.makeText(LoginActivity.this, "Can't Login", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Can't successful Login", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}