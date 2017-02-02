package poloapps.scarequitsmoking;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
        //final MediaPlayer Flatline= MediaPlayer.create(this, R.raw.flatline);
        // Flatline.start();
        // Flatline.stop();

        //setRepeatingAsyncTask();
//        Button AsthmaButton = (Button) findViewById(R.id.Aradiobutton);
//        AsthmaButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//
//                Toast.makeText(getApplicationContext(),"Asthma Clicked", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        Button LungCancerButton = (Button) findViewById(R.id.LCradiobutton);
//        LungCancerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//
//                Toast.makeText(getApplicationContext(),"Lung Cancer Clicked",
//                                                                         Toast.LENGTH_LONG).show();
//            }
//        });

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

}