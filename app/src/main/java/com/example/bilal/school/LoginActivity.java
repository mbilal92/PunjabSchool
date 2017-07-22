package com.example.bilal.school;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public class LoginActivity extends AppCompatActivity {
    Context _c;
    RequestQueue queue;
    boolean permissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _c = this;
        queue = Volley.newRequestQueue(this);
        Intent i = new Intent(this, GPSService.class);
        startService(i);
        boolean hasPermission3 = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED);
        boolean hasPermission4 = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission3 || !hasPermission4) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION},
                    528);
        } else {
            permissionGranted = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 528: {
                if (grantResults.length > 0)
                {
                    for (int i = 0 ;i <grantResults.length; i++){
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            permissionGranted = true;
                        } else {
                            permissionGranted = false;
                            Toast.makeText(LoginActivity.this, "The app was not allowed the required permissions. Hence, it cannot function properly. Please consider granting it permissions", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                } else
                {
                    Toast.makeText(LoginActivity.this, "The app was not allowed the required permissions. Hence, it cannot function properly. Please consider granting it permissions", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        Button login = (Button) findViewById(R.id.button);
        final EditText username = (EditText) findViewById(R.id.editText_username);
        final EditText password = (EditText) findViewById(R.id.editText_password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                Intent i = new Intent(_c, MainActivity.class);
                i.putExtra("agent_id", 2);
                i.putExtra("follow_ups", 0);
                startActivity(i);
                finish();

//                final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
//                pd.setMessage("Please wait!!");
//                pd.setTitle("Login");
//                pd.show();
//                JSONObject obj = new JSONObject();
//                    obj.put("u_name", username.getText().toString());
//                    obj.put("password", password.getText().toString());
//
//                JsonObjectRequest jsObjRequest = new JsonObjectRequest
//                        (Request.Method.POST, "http://shns.bitmatrix.co/api/login/", obj, new Response.Listener<JSONObject>() {
//
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                Log.i("Json", response.toString());
//                                pd.cancel();
//                                try {
//                                    if (response.getBoolean("authentication")) {
//                                        Intent i = new Intent(_c, MainActivity.class);
//                                        i.putExtra("agent_id", response.getInt("agent_id"));
//                                        i.putExtra("userName", response.getString("name"));
//                                        i.putExtra("follow_ups", response.getInt("follow_ups"));
//                                        startActivity(i);
//                                        finish();
//                                    } else {
//                                        Toast.makeText(_c, "Invalid Login and password, Please try again.",Toast.LENGTH_LONG).show();
//                                    }
//                                } catch (Exception e) {
//
//                                }
//
//                            }
//                        }, new Response.ErrorListener() {
//
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                // TODO Auto-generated method stub
//                                Log.i("Error", error.toString());
//                                Toast.makeText(_c, "Connection Error",Toast.LENGTH_LONG).show();
//                                pd.cancel();
//                            }
//                        });
//                queue.add(jsObjRequest);
            } catch (Exception e) {

            }
            }
        });

    }

}