package com.example.navigationdrawerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText txt1,txt2,txt3;
    Button btn1;
    TextView txt4;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txt1 = findViewById(R.id.main2_name_edit);
        txt2 = findViewById(R.id.main2_email_edit);
        txt3 = findViewById(R.id.main2_password_edit);
        btn1 = findViewById(R.id.main2_sign_up_button);
        txt4 = findViewById(R.id.main2_a_registerd_text);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Instance_Class.callAPI().registerUser(txt1.getText().toString(),txt2.getText().toString(),txt3.getText().toString()).enqueue(new Callback<RegisterUser>() {
                    @Override
                    public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
                        Log.d("MMM", "onResponse: "+response.body());
                        if(response.body().getConnection()==1)
                        {
                            if(response.body().getResult()==1)
                            {
                                Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            if (response.body().getResult()==2)
                            {
                                Toast.makeText(RegisterActivity.this, "Already Registered", Toast.LENGTH_SHORT).show();
                            }
                            if (response.body().getResult()==0)
                            {
                                Toast.makeText(RegisterActivity.this, "Not Registered try again..", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Something went wrong.. \ntry again..", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterUser> call, Throwable t) {
                        Log.e("MMM", "onFailure:"+t.getLocalizedMessage());
                    }
                });




            }
        });
        txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}