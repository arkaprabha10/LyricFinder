package com.example.lyricfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    EditText e2;
    TextView t1;
    Button b1;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // mAuth = FirebaseAuth.getInstance();
        //main app code
        e1= findViewById(R.id.edtArtistName);
        e2= findViewById(R.id.edtSongName);
        t1=findViewById(R.id.txtLyrics);
        b1=findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"mama mia",Toast.LENGTH_SHORT).show();
                String url ="https://api.lyrics.ovh/v1/"+e1.getText().toString()+"/"+e2.getText().toString();
                //url.replaceAll(" " ,"%20");
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                     try{
                         JSONObject jsonObject = new JSONObject(response.toString());
                         Log.i("check", "hahaahaha");
                        t1.setText(jsonObject.getString("lyrics"));
                    }
                     catch(JSONException e)
                     {
                        e.printStackTrace();
                     }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("check", "errrror mofo");
                            }
                        });
                requestQueue.add(jsonObjectRequest);
            }
        });
    }

   /* @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }*/

    /*private void updateUI(FirebaseUser currentUser) {
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),"App has been resumed",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),"App has been stopped",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"App is destroyed",Toast.LENGTH_SHORT).show();
    }
}
