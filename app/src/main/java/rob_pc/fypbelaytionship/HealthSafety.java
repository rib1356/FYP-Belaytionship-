package rob_pc.fypbelaytionship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class HealthSafety extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_safety);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_map_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //This is where the action bar clicks will be going to do stuff
        int id = item.getItemId();

        if(id == R.id.action_home){
            Intent intent = new Intent(HealthSafety.this, MainPage.class);
            HealthSafety.this.startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }



}
