package rob_pc.fypbelaytionship;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class UserProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        final Spinner sGender = findViewById(R.id.sGender);
        ArrayAdapter<CharSequence> gAdapter = ArrayAdapter.createFromResource(this, R.array.sGender, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        gAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sGender.setAdapter(gAdapter);

        final Spinner sBouldering = findViewById(R.id.sBouldering);
        ArrayAdapter<CharSequence> bAdapter = ArrayAdapter.createFromResource(this, R.array.sBouldering, android.R.layout.simple_spinner_item);
        bAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sBouldering.setAdapter(bAdapter);





    }
}
