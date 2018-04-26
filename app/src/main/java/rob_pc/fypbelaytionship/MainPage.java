package rob_pc.fypbelaytionship;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainPage extends AppCompatActivity {

    private static final String TAG = "MainPage";
    private static final int ERROR_DIALOG_REQUEST = 9001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        final TextView tvWelcome = findViewById(R.id.tvWelcome);
        final Button bProfile = findViewById(R.id.bProfile);
        final Button bLogout = findViewById(R.id.bLogout);
        final Button bHS = findViewById(R.id.bHS);

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

        bHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, HealthSafety.class);
                MainPage.this.startActivity(intent);
            }
        });

        if(isServicesOK()){
            init();
            statusCheck();
        }

    }

    private void init(){
        Button bMap = findViewById(R.id.bMaps);
        bMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: Checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainPage.this);
        if (available == ConnectionResult.SUCCESS) {
            Log.d(TAG, "isServicesOK: Google play services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //An error occured but can be resolved
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainPage.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You cant make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }


}
