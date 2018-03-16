package rob_pc.fypbelaytionship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


       // final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final TextView tvWelcome = findViewById(R.id.tvWelcome);
        final Button bProfile = findViewById(R.id.bProfile);


        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        String message = username + " Welcome bitch";
        tvWelcome.setText(message);
       // etUsername.setText(username);

        bProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, UserProfile.class);
                MainPage.this.startActivity(intent);
            }
        });



    }
}
