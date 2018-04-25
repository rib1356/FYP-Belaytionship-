package rob_pc.fypbelaytionship;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import rob_pc.fypbelaytionship.models.User;

public class RegisterActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference users;

    EditText etUsername, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("User");

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        final Button bRegister = findViewById(R.id.bRegister);


        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = etUsername.getText().toString(); //Getting the text
                final String password = etPassword.getText().toString();

                EncodeString(username); //Removes "." and changes to "," to store email

                final User user = new User(EncodeString(username), password);

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getEmail()).exists())
                            Toast.makeText(RegisterActivity.this, "This email is already in use", Toast.LENGTH_SHORT).show();
                        else{
                            users.child(user.getEmail()).setValue(user);
                            Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class); //Move to login screen
                            RegisterActivity.this.startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public static String EncodeString(String string) {
        return string.replace(".", ",");
    }

    public static String DecodeString(String string) {
        return string.replace(",", ".");
    }

}


//                Response.Listener<String> responseListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) { //Happens when the response has been executed
//                        try {
//                            Log.d("PHP response: ", response);
//                            JSONObject jsonResponse = new JSONObject(response); //Gets the string and converts into JSON
//                            boolean success = jsonResponse.getBoolean("success"); //Gets success from the JSON and sets variable
//
//                            if(success){
//
//                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class); //Move to login screen
//                                RegisterActivity.this.startActivity(intent);
//                            }else{
//                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this); //Otherwise show error
//                                builder.setMessage("Register Failed")
//                                       .setNegativeButton("Retry", null).create().show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                };
//
//                RegisterRequest registerRequest = new RegisterRequest(username, password, responseListener); //Starts a new request passing in the username and password
//                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this); //Creates a queue with volley to post the information to the database (online)
//                queue.add(registerRequest);