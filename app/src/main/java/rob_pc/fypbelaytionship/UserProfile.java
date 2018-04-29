package rob_pc.fypbelaytionship;


import android.content.Intent;

import android.nfc.Tag;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


public class UserProfile extends AppCompatActivity {

    private static final String TAG = "UserProfile";

    EditText etName;
    EditText etAge;
    Spinner sGender;
    Spinner sBouldering;
    Spinner sBouldering1;
    Spinner sTrad;
    Spinner sTrad1;
    Spinner sSport;
    Spinner sSport1;
    Spinner sYearsClimbing;
    Spinner sClimbingFreq;
    EditText etFavouriteCrag;
    Spinner sFavouriteRock;
    EditText etFavouriteClimb;

    private ProgressBar progressBar;

    //Firebase database
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        mAuthListener = new AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
            }
        };
        populateSpinners();

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child("users").child(userID).exists()){ //Checking if the user exists in the database
                        loadData(dataSnapshot);
                    }
                    else {
                        //Do nothing and allow user to create profile
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
    }

    public void populateSpinners() {

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        sGender = findViewById(R.id.sGender);
        sBouldering = findViewById(R.id.sBouldering);
        sBouldering1 = findViewById(R.id.sBouldering1);
        sTrad = findViewById(R.id.sTrad);
        sTrad1 = findViewById(R.id.sTrad1);
        sSport = findViewById(R.id.sSport);
        sSport1 = findViewById(R.id.sSport1);
        sYearsClimbing = findViewById(R.id.sYearsClimbing);
        sClimbingFreq = findViewById(R.id.sClimbingFreq);
        etFavouriteCrag = findViewById(R.id.etFavouriteCrag);
        sFavouriteRock = findViewById(R.id.sFavouriteRock);
        etFavouriteClimb = findViewById(R.id.etClimbingEx);

        // -- Can this be changed? --
        ArrayAdapter<CharSequence> gAdapter = ArrayAdapter.createFromResource(this, R.array.sGender, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        gAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sGender.setPadding(0, sGender.getPaddingTop(), sGender.getPaddingRight(), sGender.getPaddingBottom());
        // Apply the adapter to the spinner
        sGender.setAdapter(gAdapter);

        ArrayAdapter<CharSequence> bAdapter = ArrayAdapter.createFromResource(this, R.array.sBouldering, android.R.layout.simple_spinner_item);
        bAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sBouldering.setAdapter(bAdapter);
        sBouldering1.setAdapter(bAdapter);

        ArrayAdapter<CharSequence> tAdapter = ArrayAdapter.createFromResource(this, R.array.sTrad, android.R.layout.simple_spinner_item);
        tAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sTrad.setAdapter(tAdapter);
        sTrad1.setAdapter(tAdapter);

        ArrayAdapter<CharSequence> sAdapter = ArrayAdapter.createFromResource(this, R.array.sSport, android.R.layout.simple_spinner_item);
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sSport.setAdapter(sAdapter);
        sSport1.setAdapter(sAdapter);

        ArrayAdapter<CharSequence> ycAdapter = ArrayAdapter.createFromResource(this, R.array.sYearsClimbing, android.R.layout.simple_spinner_item);
        ycAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sYearsClimbing.setAdapter(ycAdapter);

        ArrayAdapter<CharSequence> cfAdapter = ArrayAdapter.createFromResource(this, R.array.sClimbingFreq, android.R.layout.simple_spinner_item);
        cfAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sClimbingFreq.setAdapter(cfAdapter);

        ArrayAdapter<CharSequence> frAdapter = ArrayAdapter.createFromResource(this, R.array.sFavouriteRock, android.R.layout.simple_spinner_item);
        frAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sFavouriteRock.setAdapter(frAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_profile, menu);
        return super.onCreateOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //This is where the action bar clicks will be going to do stuff
        int id = item.getItemId();

        if (id == R.id.action_save) {
            Log.d("Database Response:", "Save button pressed");
            firebaseSave();
            progressBar.setVisibility(View.VISIBLE);
//            final MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
//
//
//            if(dbHandler.checkForTables()){ //Check if a table exists If true update
//                updateProfileData();
//            }
//            else { //Else create new data
//                saveProfileData();
//            }

            //Otherwise save
            Intent intent = new Intent(UserProfile.this, MainPage.class);
            UserProfile.this.startActivity(intent);
            return true;
        }

        if (id == R.id.action_home) {
            Intent intent = new Intent(UserProfile.this, MainPage.class);
            UserProfile.this.startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

//    public boolean userExists(){
//
//        DataSnapshot snapshot = new DataSnapshot();
//        Log.d(TAG, "userExists: " + snapshot);
//
//
//    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void firebaseSave() {

        Log.d(TAG, "onClick: Attempting to add object to database.");
        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();
        myRef.child("users").child(userID).child("name").setValue(etName.getText().toString());
        myRef.child("users").child(userID).child("age").setValue(etAge.getText().toString());
        myRef.child("users").child(userID).child("gender").setValue(sGender.getSelectedItem().toString());
        myRef.child("users").child(userID).child("onSightBouldering").setValue(sBouldering.getSelectedItem().toString());
        myRef.child("users").child(userID).child("workedBouldering").setValue(sBouldering1.getSelectedItem().toString());
        myRef.child("users").child(userID).child("onSightTrad").setValue(sTrad.getSelectedItem().toString());
        myRef.child("users").child(userID).child("workedTrad").setValue(sTrad1.getSelectedItem().toString());
        myRef.child("users").child(userID).child("onSightSport").setValue(sSport.getSelectedItem().toString());
        myRef.child("users").child(userID).child("workedSport").setValue(sSport1.getSelectedItem().toString());
        myRef.child("users").child(userID).child("yearsClimbing").setValue(sYearsClimbing.getSelectedItem().toString());
        myRef.child("users").child(userID).child("climbingFreq").setValue(sClimbingFreq.getSelectedItem().toString());
        myRef.child("users").child(userID).child("favCrag").setValue(etFavouriteCrag.getText().toString());
        myRef.child("users").child(userID).child("favRock").setValue(sFavouriteRock.getSelectedItem().toString());
        myRef.child("users").child(userID).child("favClimbing").setValue(etFavouriteClimb.getText().toString());


    }

    private void loadData(DataSnapshot dataSnapshot) {


        Log.d(TAG, "loadData:Test1 " + dataSnapshot.getChildren());

        //Looping through all of the data
        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            Object all = ds.child(userID).getValue();

            //Log.d(TAG, "loadData:PRINTING ALL " + all);

            UserInformation uInfo = new UserInformation();
            Log.d(TAG, "loadData:Test2 " + ds.child(userID).getValue());
            String name = ds.child(userID).getValue(UserInformation.class).getName();
            String age = ds.child(userID).getValue(UserInformation.class).getAge();
            String gender = ds.child(userID).getValue(UserInformation.class).getGender();
            String onSightBouldering = ds.child(userID).getValue(UserInformation.class).getOnSightBouldering();
            String onSightTrad = ds.child(userID).getValue(UserInformation.class).getOnSightTrad();
            String onSightSport = ds.child(userID).getValue(UserInformation.class).getOnSightSport();
            String workedBouldering = ds.child(userID).getValue(UserInformation.class).getWorkedBouldering();
            String workedTrad = ds.child(userID).getValue(UserInformation.class).getWorkedTrad();
            String workedSport = ds.child(userID).getValue(UserInformation.class).getWorkedSport();
            String yearsClimbing = ds.child(userID).getValue(UserInformation.class).getYearsClimbing();
            String climbingFreq = ds.child(userID).getValue(UserInformation.class).getClimbingFreq();
            String favCrag = ds.child(userID).getValue(UserInformation.class).getFavCrag();
            String favRock = ds.child(userID).getValue(UserInformation.class).getFavRock();
            String favClimbing = ds.child(userID).getValue(UserInformation.class).getFavClimbing();

            Log.d(TAG, "loadData: " + name + gender + age + onSightBouldering + onSightTrad + onSightSport);

            etName.setText(name, TextView.BufferType.EDITABLE);
            etAge.setText(age, TextView.BufferType.EDITABLE);
            etFavouriteCrag.setText(favCrag, TextView.BufferType.EDITABLE);
            etFavouriteClimb.setText(favClimbing, TextView.BufferType.EDITABLE);
            //HOW TO SET AGE?

            ArrayAdapter<CharSequence> gAdapter = ArrayAdapter.createFromResource(this, R.array.sGender, android.R.layout.simple_spinner_item);
            gAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sGender.setAdapter(gAdapter);
            if (gender != null) {
                int spinnerPosition = gAdapter.getPosition(gender);
                sGender.setSelection(spinnerPosition);
            }

            ArrayAdapter<CharSequence> bAdapter = ArrayAdapter.createFromResource(this, R.array.sBouldering, android.R.layout.simple_spinner_item);
            bAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sBouldering.setAdapter(bAdapter);
            if (onSightBouldering != null) {
                int spinnerPosition = bAdapter.getPosition(onSightBouldering);
                sBouldering.setSelection(spinnerPosition);
            }
            sBouldering1.setAdapter(bAdapter);
            if (workedBouldering != null) {
                int spinnerPosition = bAdapter.getPosition(workedBouldering);
                sBouldering1.setSelection(spinnerPosition);
            }

            ArrayAdapter<CharSequence> tAdapter = ArrayAdapter.createFromResource(this, R.array.sTrad, android.R.layout.simple_spinner_item);
            tAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sTrad.setAdapter(tAdapter);
            if (onSightTrad != null) {
                int spinnerPosition = tAdapter.getPosition(onSightTrad);
                sTrad.setSelection(spinnerPosition);
            }
            sTrad1.setAdapter(tAdapter);
            if (workedTrad != null) {
                int spinnerPosition = tAdapter.getPosition(workedTrad);
                sTrad1.setSelection(spinnerPosition);
            }

            ArrayAdapter<CharSequence> sAdapter = ArrayAdapter.createFromResource(this, R.array.sSport, android.R.layout.simple_spinner_item);
            sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sSport.setAdapter(sAdapter);
            if (onSightSport != null) {
                int spinnerPosition = sAdapter.getPosition(onSightSport);
                sSport.setSelection(spinnerPosition);
            }
            sSport1.setAdapter(sAdapter);
            if (workedSport != null) {
                int spinnerPosition = sAdapter.getPosition(workedSport);
                sSport1.setSelection(spinnerPosition);
            }

            ArrayAdapter<CharSequence> ycAdapter = ArrayAdapter.createFromResource(this, R.array.sYearsClimbing, android.R.layout.simple_spinner_item);
            ycAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sYearsClimbing.setAdapter(ycAdapter);
            if (yearsClimbing != null) {
                int spinnerPosition = ycAdapter.getPosition(yearsClimbing);
                sYearsClimbing.setSelection(spinnerPosition);
            }

            ArrayAdapter<CharSequence> cfAdapter = ArrayAdapter.createFromResource(this, R.array.sClimbingFreq, android.R.layout.simple_spinner_item);
            cfAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sClimbingFreq.setAdapter(cfAdapter);
            if (climbingFreq != null) {
                int spinnerPosition = cfAdapter.getPosition(climbingFreq);
                sClimbingFreq.setSelection(spinnerPosition);
            }

            ArrayAdapter<CharSequence> frAdapter = ArrayAdapter.createFromResource(this, R.array.sFavouriteRock, android.R.layout.simple_spinner_item);
            frAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sFavouriteRock.setAdapter(frAdapter);
            if (favRock != null) {
                int spinnerPosition = frAdapter.getPosition(favRock);
                sFavouriteRock.setSelection(spinnerPosition);
            }

        }


    }

    private void comments(){
//    public void saveProfileData(){
//
//        dbHandler = new MyDBHandler(this, null, null, 1);
//
//        final int id = 0;
//        String name = etName.getText().toString();
//        String age = etAge.getText().toString();
//        String gender = sGender.getSelectedItem().toString();
//        String onSightBouldering = sBouldering.getSelectedItem().toString();
//        String workedBouldering = sBouldering1.getSelectedItem().toString();
//        String onSightTrad = sTrad.getSelectedItem().toString();
//        String workedTrad = sTrad1.getSelectedItem().toString();
//        String onSightSport = sSport.getSelectedItem().toString();
//        String workedSport = sSport1.getSelectedItem().toString();
//        String yearsClimbing = sYearsClimbing.getSelectedItem().toString();
//        String climbingFreq = sClimbingFreq.getSelectedItem().toString();
//        String favouriteCrag = etFavouriteCrag.getText().toString();
//        String favouriteRock = sFavouriteRock.getSelectedItem().toString();
//        String favouriteClimb = etFavouriteClimb.getText().toString();
//
//        Log.d("DB", "Saving to local db");
//        Log.d("DB", "Adding" + name + age + gender + onSightBouldering + workedBouldering);
//
////        dbHandler.addProfile(new UserInformation(id, name, age, gender, onSightBouldering, onSightTrad, onSightSport,
////                                                 workedBouldering, workedTrad, workedSport,
////                                                 yearsClimbing, climbingFreq, favouriteCrag,
////                                                 favouriteRock, favouriteClimb));
//        dbHandler.close();
//    }
//
//    public void updateProfileData(){
//
//        dbHandler = new MyDBHandler(this, null, null, 1);
//
//        final int id = 0;
//        String name = etName.getText().toString();
//        String age = etAge.getText().toString();
//        String gender = sGender.getSelectedItem().toString();
//        String onSightBouldering = sBouldering.getSelectedItem().toString();
//        String workedBouldering = sBouldering1.getSelectedItem().toString();
//        String onSightTrad = sTrad.getSelectedItem().toString();
//        String workedTrad = sTrad1.getSelectedItem().toString();
//        String onSightSport = sSport.getSelectedItem().toString();
//        String workedSport = sSport1.getSelectedItem().toString();
//        String yearsClimbing = sYearsClimbing.getSelectedItem().toString();
//        String climbingFreq = sClimbingFreq.getSelectedItem().toString();
//        String favouriteCrag = etFavouriteCrag.getText().toString();
//        String favouriteRock = sFavouriteRock.getSelectedItem().toString();
//        String favouriteClimb = etFavouriteClimb.getText().toString();
//
//        Log.d("DB", "Updating to local db");
//        Log.d("DB", "Adding" + name + age + gender + onSightBouldering + workedBouldering);
//
//        dbHandler.updateProfile(id, name, age, gender, onSightBouldering, onSightTrad, onSightSport,
//                workedBouldering, workedTrad, workedSport,
//                yearsClimbing, climbingFreq, favouriteCrag,
//                favouriteRock, favouriteClimb);
//        dbHandler.close();
//    }
//
//    public void getDataTest(){
//
//        dbHandler = new MyDBHandler(this, null, null, 1);
//        final List<UserInformation> value = dbHandler.getAll();
//
//        for (UserInformation li : value) {
//            String name = li.getName();
//            String age = li.getAge();
//            String gender = li.getGender();
//            String onSightBouldering = li.getOnSightBouldering();
//            String onSightTrad = li.getOnSightTrad();
//            String onSightSport = li.getOnSightSport();
//            String workedBouldering = li.getWorkedBouldering();
//            String workedTrad = li.getWorkedTrad();
//            String workedSport = li.getWorkedSport();
//            String yearsClimbing = li.getYearsClimbing();
//            String climbingFreq = li.getClimbingFreq();
//            String favCrag = li.getFavCrag();
//            String favRock = li.getFavRock();
//            String favClimbing = li.getFavClimbing();
//
//            System.out.println("*****************************************************************");
//            System.out.println(name + age + gender + onSightBouldering + onSightTrad + onSightSport +
//                    workedBouldering + workedTrad + workedSport
//                    + yearsClimbing + climbingFreq + favCrag + favRock + favClimbing);
//        }
//    }
//
//    public void loadProfileData(){
//
//        dbHandler = new MyDBHandler(this, null, null, 1);
//        final List<UserInformation> value = dbHandler.getAll();
//
//        String name = "";
//        String age = "";
//        String gender = "";
//        String onSightBouldering = "";
//        String onSightTrad = "";
//        String onSightSport = "";
//        String workedBouldering = "";
//        String workedTrad = "";
//        String workedSport = "";
//        String yearsClimbing = "";
//        String climbingFreq = "";
//        String favCrag = "";
//        String favRock = "";
//        String favClimbing = "";
//
//        for (UserInformation li : value) {
//            name = li.getName();
//            age = li.getAge();
//            gender = li.getGender();
//            onSightBouldering = li.getOnSightBouldering();
//            onSightTrad = li.getOnSightTrad();
//            onSightSport = li.getOnSightSport();
//            workedBouldering = li.getWorkedBouldering();
//            workedTrad = li.getWorkedTrad();
//            workedSport = li.getWorkedSport();
//            yearsClimbing = li.getYearsClimbing();
//            climbingFreq = li.getClimbingFreq();
//            favCrag = li.getFavCrag();
//            favRock = li.getFavRock();
//            favClimbing = li.getFavClimbing();
//
//            System.out.println("*****************************************************************");
//            System.out.println(name + age + gender + onSightBouldering + onSightTrad + onSightSport +
//                    workedBouldering + workedTrad + workedSport
//                    + yearsClimbing + climbingFreq + favCrag + favRock + favClimbing);
//
//
//        }
//
//        etName.setText(name, TextView.BufferType.EDITABLE);
//        etAge.setText(age, TextView.BufferType.EDITABLE);
//        etFavouriteCrag.setText(favCrag, TextView.BufferType.EDITABLE);
//        etFavouriteClimb.setText(favClimbing, TextView.BufferType.EDITABLE);
//        //HOW TO SET AGE?
//
//        ArrayAdapter<CharSequence> gAdapter = ArrayAdapter.createFromResource(this, R.array.sGender, android.R.layout.simple_spinner_item);
//        gAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sGender.setAdapter(gAdapter);
//        if (gender != null) {
//            int spinnerPosition = gAdapter.getPosition(gender);
//            sGender.setSelection(spinnerPosition);
//        }
//
//        ArrayAdapter<CharSequence> bAdapter = ArrayAdapter.createFromResource(this, R.array.sBouldering, android.R.layout.simple_spinner_item);
//        bAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sBouldering.setAdapter(bAdapter);
//        if (onSightBouldering != null) {
//            int spinnerPosition = bAdapter.getPosition(onSightBouldering);
//            sBouldering.setSelection(spinnerPosition);
//        }
//        sBouldering1.setAdapter(bAdapter);
//        if (workedBouldering != null) {
//            int spinnerPosition = bAdapter.getPosition(workedBouldering);
//            sBouldering1.setSelection(spinnerPosition);
//        }
//
//        ArrayAdapter<CharSequence> tAdapter = ArrayAdapter.createFromResource(this, R.array.sTrad, android.R.layout.simple_spinner_item);
//        tAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sTrad.setAdapter(tAdapter);
//        if (onSightTrad != null) {
//            int spinnerPosition = tAdapter.getPosition(onSightTrad);
//            sTrad.setSelection(spinnerPosition);
//        }
//        sTrad1.setAdapter(tAdapter);
//        if (workedTrad != null) {
//            int spinnerPosition = tAdapter.getPosition(workedTrad);
//            sTrad1.setSelection(spinnerPosition);
//        }
//
//        ArrayAdapter<CharSequence> sAdapter = ArrayAdapter.createFromResource(this, R.array.sSport, android.R.layout.simple_spinner_item);
//        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sSport.setAdapter(sAdapter);
//        if (onSightSport != null) {
//            int spinnerPosition = sAdapter.getPosition(onSightSport);
//            sSport.setSelection(spinnerPosition);
//        }
//        sSport1.setAdapter(sAdapter);
//        if (workedSport != null) {
//            int spinnerPosition = sAdapter.getPosition(workedSport);
//            sSport1.setSelection(spinnerPosition);
//        }
//
//        ArrayAdapter<CharSequence> ycAdapter = ArrayAdapter.createFromResource(this, R.array.sYearsClimbing, android.R.layout.simple_spinner_item);
//        ycAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sYearsClimbing.setAdapter(ycAdapter);
//        if (yearsClimbing != null) {
//            int spinnerPosition = ycAdapter.getPosition(yearsClimbing);
//            sYearsClimbing.setSelection(spinnerPosition);
//        }
//
//        ArrayAdapter<CharSequence> cfAdapter = ArrayAdapter.createFromResource(this, R.array.sClimbingFreq, android.R.layout.simple_spinner_item);
//        cfAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sClimbingFreq.setAdapter(cfAdapter);
//        if (climbingFreq != null) {
//            int spinnerPosition = cfAdapter.getPosition(climbingFreq);
//            sClimbingFreq.setSelection(spinnerPosition);
//        }
//
//        ArrayAdapter<CharSequence> frAdapter = ArrayAdapter.createFromResource(this, R.array.sFavouriteRock, android.R.layout.simple_spinner_item);
//        frAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sFavouriteRock.setAdapter(frAdapter);
//        if (favRock != null) {
//            int spinnerPosition = frAdapter.getPosition(favRock);
//            sFavouriteRock.setSelection(spinnerPosition);
//        }
//
//
//
//    }
//}


//    Response.Listener<String> responseListener = new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) { //Happens when the response has been executed
//                    try {
//
//                        //Log.d("PHP response: ", response);
//                        JSONObject jsonResponse = new JSONObject(response); //Gets the string and converts into JSON
//                        boolean success = jsonResponse.getBoolean("success");
//
//                        if(success){
//                            Log.d("Database Response:", "Data has been posted");
//                        }else{
//                            Log.d("Database Response:", "Error");
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            };
//
//            String name = etName.getText().toString();
//            DatabaseRequest databaseRequest = new DatabaseRequest(username, name, responseListener); //Starts a new request passing in the username and password
//            RequestQueue queue = Volley.newRequestQueue(UserProfile.this); //Creates a queue with volley to post the information to the database (online)
//            queue.add(databaseRequest);
    }
}


