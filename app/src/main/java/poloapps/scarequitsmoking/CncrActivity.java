package poloapps.scarequitsmoking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Timer;
import java.util.TimerTask;

public class CncrActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancer_layout);

        Spinner spinner = (Spinner) findViewById(R.id.cncr_sel_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cncer_sel_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.cignum_sel_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.cignum_sel_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = (Spinner) findViewById(R.id.freq_sel_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.freq_sel_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) findViewById(R.id.leng_sel_spinner);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.len_sel_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

       final SharedPreferences mSettings = this.getSharedPreferences("Settings", 0);
        final SharedPreferences.Editor editor = mSettings.edit();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                String spinval = adapter.getItemAtPosition(position).toString();
                //      cancer_tv.setText(spinval);
                // Showing selected spinner item
                Toast.makeText(getApplicationContext(),
                        spinval, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter1, View v,
                                       int position, long id) {
                // On selecting a spinner item
                String spinval1 = adapter1.getItemAtPosition(position).toString();
                Button AdH = (Button) findViewById(R.id.button2);

                editor.putInt("S1",position);
                editor.commit();

                AdH.setEnabled(checkAH());

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter2, View v,
                                       int position, long id) {
                // On selecting a spinner item
                //String spinval2 = adapter2.getItemAtPosition(position).toString();
                //      cancer_tv.setText(spinval);
                Button AdH = (Button) findViewById(R.id.button2);

                editor.putInt("S2",position);

                editor.commit();

                AdH.setEnabled(checkAH());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter3, View v,
                                       int position, long id) {
                // On selecting a spinner item
                //String spinval3 = adapter3.getItemAtPosition(position).toString();

                Button AdH = (Button) findViewById(R.id.button2);
                editor.putInt("S3",position);

                editor.commit();
                AdH.setEnabled(checkAH());
                // Showing selected spinner item
              // Toast.makeText(getApplicationContext(),
                     //  Integer.toString(position), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        Button RiskCalc = (Button) findViewById(R.id.button2);
        RiskCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                Integer Sp1Sel = mSettings.getInt("S1", 0);
                Integer Sp2Sel = mSettings.getInt("S2", 0);
                Integer Sp3Sel = mSettings.getInt("S3", 0);
                Integer Clc = Sp1Sel*Sp2Sel*Sp3Sel;
                Toast.makeText(getApplicationContext(), Integer.toString(Clc),
                                                                    Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        super.startActivity(intent);
        return;
    }

    private void setRepeatingAsyncTask() {

        final Handler handler = new Handler();
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {

                        } catch (Exception e) {
                            // error, do something
                        }
                    }
                });
            }
        };
        timer.schedule(task, 0, 30*1000);  // interval of one minute
    }

    private boolean checkAH(){
        SharedPreferences mSettings = this.getSharedPreferences("Settings", 0);
        Integer Sp1Sel = mSettings.getInt("S1", 0);
        Integer Sp2Sel = mSettings.getInt("S2", 0);
        Integer Sp3Sel = mSettings.getInt("S3", 0);
        if (Sp1Sel == 0 || Sp2Sel == 0 || Sp3Sel == 0) {
            return false;
        }
        else return true;
    }

}