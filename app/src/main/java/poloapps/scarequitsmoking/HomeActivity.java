package poloapps.scarequitsmoking;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2);
        ImageView mImageViewFilling = (ImageView) findViewById(R.id.MainAnim);
        ((AnimationDrawable) mImageViewFilling.getBackground()).start();
        final MediaPlayer Flatline= MediaPlayer.create(this, R.raw.flatline);
        // Flatline.start();
        int maxworldwideTD= 6000000;
        int maxusTD=500000;
        Calendar calendar = Calendar.getInstance();
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        TextView usHourly = (TextView) findViewById(R.id.usH);
        TextView usMonthly = (TextView) findViewById(R.id.usM);
        TextView usYearly = (TextView) findViewById(R.id.usY);
        TextView wwHourly = (TextView) findViewById(R.id.wwH);
        TextView wwMonthly = (TextView) findViewById(R.id.wwM);
        TextView wwYearly = (TextView) findViewById(R.id.wwY);

        usHourly.setText(Integer.toString(dayOfYear));
        usMonthly.setText(Integer.toString(hourOfDay));
        // Button button = (Button) findViewById(R.id.simple);

        //button.setOnClickListener(new View.OnClickListener() {
            //@Override
           // public void onClick(View arg0) {
              //  Flatline.stop();
            }
}
