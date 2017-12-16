package ca.bcit.ass2.assignment2;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

public class CountryDetailActivity extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Country country = (Country) getIntent().getExtras().get("country");

        WebView flag = (WebView) findViewById(R.id.flag);
        flag.getSettings().setUseWideViewPort(true);
        flag.getSettings().getBuiltInZoomControls();
        flag.getSettings().setLoadWithOverviewMode(true);
        String flagHtml = "<html><body><img src=\"" + country.getFlag() + "\" width=\"100%\" height=\"100%\"\"/></body></html>";
        flag.loadData(flagHtml, "text/html", null);
        //flag.loadUrl(country.getFlag());
        TextView name = (TextView) findViewById(R.id.name);
        TextView capital = (TextView) findViewById(R.id.capital);
        TextView region = (TextView) findViewById(R.id.region);
        TextView population = (TextView) findViewById(R.id.population);
        TextView area = (TextView) findViewById(R.id.area);
        TextView border = (TextView) findViewById(R.id.borders);
        name.setText(country.getName());
        capital.setText("Capital: " + country.getCapital());
        region.setText("Region: " + country.getRegion());
        population.setText("Population: " + country.getPopulation());
        area.setText("Area: " + country.getArea());
        border.setText("Borders: " + country.getBorders());
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
        setShareActionIntent("Hello World");
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
