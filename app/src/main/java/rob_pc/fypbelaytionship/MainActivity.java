package rob_pc.fypbelaytionship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void SignUpPage(View view) {
        Intent startSignUP = new Intent(this, SignUpPage.class);

        startActivity(startSignUP);
    }
}
