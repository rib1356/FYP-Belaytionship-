package rob_pc.fypbelaytionship;

import android.content.Intent;
import android.content.SharedPreferences;
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

        final TextView tvWelcome = findViewById(R.id.tvWelcome);
        final Button bProfile = findViewById(R.id.bProfile);
        final Button bLogout = findViewById(R.id.bLogout);

        String username = "";

        final SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        String restoredText = prefs.getString("username", null); //Get username
        if (restoredText != null) {
            username = prefs.getString("username", "No username defined"); //"No name defined" is the default value.
        }

        String message = username + " Welcome bitch";
        tvWelcome.setText(message);

        bProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, UserProfile.class);
                MainPage.this.startActivity(intent);
            }
        });

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);  Should this be used rather than declaring final higher in code?
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear(); //Clear all of the shared preference data when the user logs out. This will stop them from automatically logging back in
                editor.commit(); // commit changes

                Intent intent = new Intent(MainPage.this, LoginActivity.class);
                MainPage.this.startActivity(intent);
            }
        });



    }
}
