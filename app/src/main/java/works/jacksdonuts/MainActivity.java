package works.jacksdonuts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.parse.Parse;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

import works.jacksdonuts.core.initialdata.InitialData;
import works.jacksdonuts.state.Session;
import works.jacksdonuts.state.SessionManager;
import works.jacksdonuts.view.home.HomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        initialParse();

        LoadData();
        startBackgroundWork();
        goToHome();
    }

    private void startBackgroundWork() {

        if (isVersionUpdated()) {
            //
        } else {

        }
    }

    private boolean isVersionUpdated() {
        return false;
    }

    private void goToHome() {

        startActivity(new Intent(this, HomeActivity.class));
    }

    private JSONObject getJsonObject() {
        JSONObject jsonObject;
        try {
            final InputStream in = getResources().openRawResource(R.raw.appdef);
            Writer writer = new StringWriter();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
            reader.close();
            final String json = writer.toString();
            writer.close();
            jsonObject = new JSONObject(json);
            in.close();

        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve resource.", e);
        }
        return jsonObject;
    }

    private void LoadData() {

        final JSONObject jsonObject = getJsonObject();
        InitialData initialData = null;

        try {
            String a= jsonObject.toString();
            initialData = new Gson().fromJson(a, InitialData.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        * Initialise Session inside session manager
        * This is a one time process in entire application
        */
        SessionManager sessionManager = SessionManager.getInstance();
        Session session = new Session();
        if (initialData != null) {
            session.setInitialData(initialData);
        }
        sessionManager.setSession(session);
    }

    private void initialParse() {
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("jackdonuts")
                .clientKey("coolmunchdonuts")
                .server("http://jackdonuts.herokuapp.com/parse")
                .build()
        );
    }

}