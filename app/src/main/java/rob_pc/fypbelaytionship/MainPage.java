package rob_pc.fypbelaytionship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final TextView tvWelcome = (TextView) findViewById(R.id.tvWelcome);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        String message = username + "Welcome bitch";
        tvWelcome.setText(message);
        etUsername.setText(username);


    }
}
