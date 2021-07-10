package id.ac.pelitabangsa.homeproject;


import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager;

    private TextView txtprofil;
    private Button btnlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtprofil = (TextView)findViewById(R.id.txtprofil);
        btnlogout = (Button)findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });

        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        String name = user.get(SessionManager.kunci_email);
        txtprofil.setText(Html.fromHtml("<b>" + name + "</b>"));
    }
}
