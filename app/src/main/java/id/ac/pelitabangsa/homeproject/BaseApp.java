package id.ac.pelitabangsa.homeproject;

import android.content.Context;
import android.os.PersistableBundle;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.androidquery.AQuery;

public class BaseApp extends AppCompatActivity {
    public Context context;
    public AQuery aQuery;
    public AlphaAnimation BtnAnimasi = new AlphaAnimation(1F, 0.1F);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        aQuery = new AQuery(context);
    }
}