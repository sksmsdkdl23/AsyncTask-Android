package ca.bcit.ass2.assignment2;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountryActivity extends AppCompatActivity implements Serializable {

    private ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Map<String, List<Country>> map = (Map<String, List<Country>>) getIntent().getExtras().get("country");
        String region = (String) getIntent().getExtras().get("region");
        final List<Country> country = map.get(region);
        List<String> countries = new ArrayList<>();
        for (int i = 0; i < country.size(); i++) {
            countries.add(country.get(i).getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, countries
        );
        ListView listView = (ListView) findViewById(R.id.country_list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String countryString =  ((TextView)view).getText().toString();
                Country countryDetail = null;
                for (int j = 0; j < country.size(); j++) {
                    if(country.get(j).getName().equalsIgnoreCase(countryString)) {
                        countryDetail = country.get(j);
                        break;
                    }
                }
                if(i >= 0) {
                    Intent intent = new Intent(CountryActivity.this, CountryDetailActivity.class);
                    intent.putExtra("country", countryDetail);
                    startActivity(intent);

                }

            }
        });
    }
    private void setShareActionIntent(String text) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu. This adds items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Countries Pope");
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_my_device:
                Intent i = new Intent(this, InfoRequestActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
