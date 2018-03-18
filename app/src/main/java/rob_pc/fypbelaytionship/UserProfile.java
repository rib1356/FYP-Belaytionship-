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
        sGender.setPadding(0, sGender.getPaddingTop(), sGender.getPaddingRight(), sGender.getPaddingBottom());
        // Apply the adapter to the spinner
        sGender.setAdapter(gAdapter);

        final Spinner sBouldering = findViewById(R.id.sBouldering);
        final Spinner sBouldering1 = findViewById(R.id.sBouldering1);

        ArrayAdapter<CharSequence> bAdapter = ArrayAdapter.createFromResource(this, R.array.sBouldering, android.R.layout.simple_spinner_item);
        bAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sBouldering.setAdapter(bAdapter);
        sBouldering1.setAdapter(bAdapter);

        final Spinner sTrad = findViewById(R.id.sTrad);
        final Spinner sTrad1 = findViewById(R.id.sTrad1);
        ArrayAdapter<CharSequence> tAdapter = ArrayAdapter.createFromResource(this, R.array.sTrad, android.R.layout.simple_spinner_item);
        tAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sTrad.setAdapter(tAdapter);
        sTrad1.setAdapter(tAdapter);

        final Spinner sSport = findViewById(R.id.sSport);
        final Spinner sSport1 = findViewById(R.id.sSport1);
        ArrayAdapter<CharSequence> sAdapter = ArrayAdapter.createFromResource(this, R.array.sSport, android.R.layout.simple_spinner_item);
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sSport.setAdapter(sAdapter);
        sSport1.setAdapter(sAdapter);

        final Spinner sYearsClimbing = findViewById(R.id.sYearsClimbing);
        ArrayAdapter<CharSequence> ycAdapter = ArrayAdapter.createFromResource(this, R.array.sYearsClimbing, android.R.layout.simple_spinner_item);
        ycAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sYearsClimbing.setAdapter(ycAdapter);

        final Spinner sClimbingFreq = findViewById(R.id.sClimbingFreq);
        ArrayAdapter<CharSequence> cfAdapter = ArrayAdapter.createFromResource(this, R.array.sClimbingFreq, android.R.layout.simple_spinner_item);
        cfAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sClimbingFreq.setAdapter(cfAdapter);

        final Spinner sFavouriteRock = findViewById(R.id.sFavouriteRock);
        ArrayAdapter<CharSequence> frAdapter = ArrayAdapter.createFromResource(this, R.array.sFavouriteRock, android.R.layout.simple_spinner_item);
        frAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sFavouriteRock.setAdapter(frAdapter);


    }
}
