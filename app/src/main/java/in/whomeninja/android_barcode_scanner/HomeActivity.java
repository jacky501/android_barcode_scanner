package in.whomeninja.android_barcode_scanner;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends ActionBarActivity implements ScanResultReceiver{
    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        contentTxt = (TextView)findViewById(R.id.scan_content);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * event handler for scan button
     * @param view view of the activity
     */
    public void scanNow(View view){
        // add fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ScanFragment scanFragment = new ScanFragment();
        fragmentTransaction.add(R.id.scan_fragment,scanFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void scanResultData(String codeFormat, String codeContent){
        // display it on screen
        contentTxt.setText("CONTENT: " + codeContent);
    }

    @Override
    public void scanResultData(NoScanResultException noScanData) {
        Toast toast = Toast.makeText(this,noScanData.getMessage(), Toast.LENGTH_SHORT);
        toast.show();
    }
}
