package poloapps.scarequitsmoking;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cig2grave_layout);

        int RandAnimSeq = new Random().nextInt(3);
        final ImageView mGif = (ImageView) findViewById(R.id.Animation);

        switch (RandAnimSeq) {
            case 0:
                mGif.setBackgroundResource(R.drawable.an_1);
                break;
            case 1:
                mGif.setBackgroundResource(R.drawable.an_2);
                break;
            case 2:
                mGif.setBackgroundResource(R.drawable.an_3);
                break;

        }

        ((AnimationDrawable) mGif.getBackground()).start();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //final MediaPlayer Flatline= MediaPlayer.create(this, R.raw.flatline);
        // Flatline.start();
        // Flatline.stop();
        setdeaths();

        Button DisBtn = (Button) findViewById(R.id.diseases_btn);
        DisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ((AnimationDrawable) mGif.getBackground()).stop();
                Intent intent = new Intent (HomeActivity.this, TDActivity.class);
                startActivity(intent);
            }
        });

        Button CncrBtn = (Button) findViewById(R.id.cancer_btn);
        CncrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                ((AnimationDrawable) mGif.getBackground()).stop();
                Intent intent = new Intent (HomeActivity.this, TDActivity.class);
                startActivity(intent);
            }
        });

        setRepeatingAsyncTask();
    }
    private void setdeaths( ){

        int WWdeathsperday  = 16854;
        int USdeathsperday  = 1440;
        int USdeathsperhour = USdeathsperday / 24;
        int WWdeathsperhour = WWdeathsperday / 24;
        int WWdeathspermin  = WWdeathsperhour / 60;
        int WWdeathspersec  = WWdeathspermin / 60;

        Calendar calendar = Calendar.getInstance();
        int dayOfMonth    = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfYear     = calendar.get(Calendar.DAY_OF_YEAR);
        int hourOfDay     = calendar.get(Calendar.HOUR_OF_DAY);
        int minOfHour     = calendar.get(Calendar.MINUTE);
        int WWsecdeaths   = calendar.get(Calendar.SECOND) * WWdeathspersec;

        int hourlyUS   = minOfHour; // 1 death per minute
        int hourlyWW   = ( minOfHour * WWdeathspermin ) + WWsecdeaths ;
        int dailyUS    = ( hourOfDay * USdeathsperhour ) + minOfHour ;
        int monthUS    = ( dayOfMonth - 1 ) * USdeathsperday + dailyUS;
        int dailyWW    = ( hourOfDay * WWdeathsperhour ) + ( minOfHour * WWdeathspermin  )
                                                                            + WWsecdeaths ;
        int monthWW    = ( dayOfMonth - 1 ) * WWdeathsperday + dailyWW;
        int yearUS     = ( dayOfYear - 1 ) * USdeathsperday + dailyUS;
        int yearWW     = ( dayOfYear - 1 ) * WWdeathsperday + dailyWW;

        TextView usHourly  = (TextView) findViewById(R.id.usH);
        TextView usMonthly = (TextView) findViewById(R.id.usM);
        TextView usYearly  = (TextView) findViewById(R.id.usY);
        TextView usToday   = (TextView) findViewById(R.id.usT);
        TextView wwHourly  = (TextView) findViewById(R.id.wwH);
        TextView wwMonthly = (TextView) findViewById(R.id.wwM);
        TextView wwYearly  = (TextView) findViewById(R.id.wwY);
        TextView wwToday   = (TextView) findViewById(R.id.wwT);

        usHourly.setText(Integer.toString(hourlyUS));
        usToday.setText(Integer.toString(dailyUS));
        usMonthly.setText(Integer.toString(monthUS));
        usYearly.setText(Integer.toString(yearUS));
        wwHourly.setText(Integer.toString(hourlyWW));
        wwToday.setText(Integer.toString(dailyWW));
        wwMonthly.setText(Integer.toString(monthWW));
        wwYearly.setText(Integer.toString(yearWW));
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
                            setdeaths();
                        } catch (Exception e) {
                            // error, do something
                        }
                    }
                });
            }
        };
        timer.schedule(task, 0, 10*1000);  // interval of one minute
    }

}