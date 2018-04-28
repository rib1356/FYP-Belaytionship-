package rob_pc.fypbelaytionship.OldDatabase;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DatabaseRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "https://belaytionships.000webhostapp.com/databaseSave.php";
    private Map<String, String> params;

    public DatabaseRequest(String username, String name, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("name", name);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
