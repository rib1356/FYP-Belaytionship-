package rob_pc.fypbelaytionship;

import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import rob_pc.fypbelaytionship.models.User;

public class LoginActivity extends AppCompatActivity {

    //Firebase
    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("User");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = findViewById(R.id.etUsername);
        final EditText etPassword = findViewById(R.id.etPassword);

        final Button bRegister = findViewById(R.id.bRegister);
        final Button bLogin = findViewById(R.id.bLogin);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        if(pref.getBoolean("logged",false)){ //If user is logged it is set to true, which means this if statement executes
            goToLoginActivity();
        }

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);

            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                login(username, password);

//
            }
        });

    }

    private void login(final String username, final String password){
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists())
                    if(!username.isEmpty()) {
                        User login = dataSnapshot.child(username).getValue(User.class);
                        if(login.getPassword().equals(password)){
                            Toast.makeText(LoginActivity.this, "Successful login", Toast.LENGTH_SHORT).show();
                            storeLogin(username);
                            goToLoginActivity();
                        }else{
                            Toast.makeText(LoginActivity.this, "Password is wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Username is not registered", Toast.LENGTH_SHORT).show();
                    }
                }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void storeLogin (String username){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("username", username);
        editor.putBoolean("logged", true);
        editor.commit();

    }

    public void goToLoginActivity(){
        Intent intent = new Intent(LoginActivity.this, MainPage.class);
        LoginActivity.this.startActivity(intent);
    }

}

//    Response.Listener<String> responseListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            Log.d("NASOFKNASFNALSKFN", response);
//                            JSONObject jsonResponse = new JSONObject(response);
//                            boolean success = jsonResponse.getBoolean("success"); //Get success from the PHP
//
//                            if(success){
//                                storeLogin(username);
//                                goToLoginActivity();
//                            }else{
//                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                                builder.setMessage("Login Failed")
//                                        .setNegativeButton("Retry", null).create().show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                };
//                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
//                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
//                queue.add(loginRequest);
