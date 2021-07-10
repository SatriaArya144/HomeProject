package id.ac.pelitabangsa.homeproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.WindowManager;


import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import id.ac.pelitabangsa.homeproject.BaseApp;
import id.ac.pelitabangsa.homeproject.JavaHelper;
import id.ac.pelitabangsa.homeproject.R;


import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends BaseApp {
    private EditText logtxtUser, logtxtPassword;
    private TextView loglblRegister;
    private Button logbtnLogin;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        setupView();
        sessionManager = new SessionManager(getApplicationContext());
        loglblRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(BtnAnimasi);
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        logbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        logtxtUser.setError(null);
        logtxtPassword.setError(null);
        /*check kebaradan teks*/
        if (JavaHelper.isEmpty(logtxtUser)) {
            logtxtUser.setError("Email masih kosong");
            logtxtUser.requestFocus();
        } else if (JavaHelper.isEmpty(logtxtPassword)) {
            logtxtPassword.setError("Password masih kosong");
            logtxtPassword.requestFocus();
        } else {
            /*kirim data ke server*/
            String URL = JavaHelper.BASE_URL + "login.php";
            Map<String, String> param = new HashMap<>();
            param.put("email", logtxtUser.getText().toString());
            param.put("password", logtxtPassword.getText().toString());

            /*menampilkan progressbar saat mengirim data*/
            ProgressDialog pd = new ProgressDialog(context);
            pd.setIndeterminate(true);
            pd.setCancelable(false);
            pd.setInverseBackgroundForced(false);
            pd.setCanceledOnTouchOutside(false);
            pd.setTitle("Info");
            pd.setMessage("Login");
            pd.show();

            try {
                /*format ambil data*/
                aQuery.progress(pd).ajax(URL, param, String.class, new AjaxCallback<String>() {
                    @Override
                    public void callback(String url, String object, AjaxStatus status) {
                        /*jika objek tidak kosong*/
                        if (object != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(object);
                                String result = jsonObject.getString("result");
                                String msg = jsonObject.getString("msg");
                                if (result.equalsIgnoreCase("true")) {
                                    sessionManager.createSession(logtxtUser.getText().toString());
                                    startActivity(new Intent(context, MainActivity.class));
                                    JavaHelper.pesan(context, msg);
                                    finish();
                                } else {
                                    JavaHelper.pesan(context, msg);
                                }
                            } catch (JSONException e) {
                                JavaHelper.pesan(context, "Error convert data json");
                            }
                        }
                    }
                });
            } catch (Exception e) {
                JavaHelper.pesan(context, "Gagal mengambil data");
            }
        }
    }

    private void setupView() {
        logtxtUser = (EditText) findViewById(R.id.logtxtUser);
        logtxtPassword = (EditText) findViewById(R.id.logtxtPassword);
        logbtnLogin = (Button) findViewById(R.id.logbtnLogin);
        loglblRegister = (TextView) findViewById(R.id.loglblRegister);
    }
}
