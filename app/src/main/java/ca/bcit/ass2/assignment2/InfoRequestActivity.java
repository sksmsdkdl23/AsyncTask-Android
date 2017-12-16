package ca.bcit.ass2.assignment2;

import android.os.Build;
import android.provider.Contacts;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class InfoRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_request);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String x = Build.MANUFACTURER;
        TextView manufacturer = (TextView) findViewById(R.id.manufacturer_string);
        manufacturer.setText(x);

        x = Build.MODEL;
        TextView model = (TextView) findViewById(R.id.model_string);
        model.setText(x);

        x = Integer.toString(Build.VERSION.SDK_INT);
        TextView version = (TextView) findViewById(R.id.version_string);
        version.setText(x);

        x = Build.VERSION.RELEASE;
        TextView version_release = (TextView) findViewById(R.id.version_release_string);
        version_release.setText(x);

        x = Settings.Secure.getString(getApplicationContext().getContentResolver(),Settings.Secure.ANDROID_ID);
        TextView serial_number = (TextView) findViewById(R.id.serial_number_string);
        serial_number.setText(x);
    }
}
