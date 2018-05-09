package rob_pc.fypbelaytionship;

import android.content.Intent;
import android.content.SharedPreferences;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialising variables
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        final Button bRegister = findViewById(R.id.bRegister);
        final Button bLogin = findViewById(R.id.bLogin);

        mAuth = FirebaseAuth.getInstance();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        if (pref.getBoolean("logged", false)) { //If user is logged it is set to true, which means this if statement executes and user will continue straight to main page
            gotoMainPage();
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
                login();

            }
        });

    }

    public void login() {

        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        //Validates emails
        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
        }else if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
        }else if (password.length() < 6) {
            etPassword.setError("Minimum password length of 6");
            etPassword.requestFocus();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        storeLogin(email);
                        gotoMainPage();
                    } else {
                        Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        etEmail.setError("Check email");
                        etPassword.setError("Check password");
                    }
                }
            });
        }
    }

    public void storeLogin(String email) {
        //Creates a shared preference and uses mode private as this is very difficult to access (no private information is saved here)
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("username", email);
        editor.putBoolean("logged", true);
        editor.commit();

    }

    public void gotoMainPage() {
        Intent intent = new Intent(LoginActivity.this, MainPage.class);
        LoginActivity.this.startActivity(intent);
    }

    public boolean emailValidation(String email, String password){
        if (email.isEmpty()) {
            return false;
        }else if (password.isEmpty()) {
            return false;
        }else if (password.length() < 6) {
            return false;
        }
        return true;
    }
}

//    private void login(final String username, final String password){
//        users.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if(dataSnapshot.child(username).exists())
//                    if(!username.isEmpty()) {
//                        User login = dataSnapshot.child(username).getValue(User.class);
//                        if(login.getPassword().equals(password)){
//                            Toast.makeText(LoginActivity.this, "Successful login", Toast.LENGTH_SHORT).show();
//                            storeLogin(username);
//                            goToLoginActivity();
//                        }else{
//                            Toast.makeText(LoginActivity.this, "Password is wrong", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    else{
//                        Toast.makeText(LoginActivity.this, "Username is not registered", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
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
