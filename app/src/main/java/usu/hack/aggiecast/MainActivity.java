package usu.hack.aggiecast;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    //    String TAG = this.getLocalClassName();
    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean initializedMP = false;

        if (mp == null) {
            initializedMP = initializeMediaPlayer();

            // Let's check
            Log.d(TAG, "initalizedMP: " + initializedMP);
        } else {
            Log.d(TAG, "You have initialized your media player");
            Log.d(TAG, "initalizedMP: " + initializedMP);
        }

    }


    // This will return true if it is able to initialize the media player
    private boolean initializeMediaPlayer() {
        mp = new MediaPlayer();
        try {
            mp.setDataSource("http://aggiestream.bluezone.usu.edu:8888/");
        } catch (Exception e) {
            Log.e(TAG, "Something went horribly wrong initalizing the media player");
            e.printStackTrace();

            // Return false because it didn't work
            return false;
        }

        // This is for keeping the phone awake to stream constantly
        mp.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);

        // Set some listeners
        mp.setOnPreparedListener((MediaPlayer.OnPreparedListener) this);
        mp.setOnCompletionListener((MediaPlayer.OnCompletionListener) this);
        mp.setOnErrorListener((MediaPlayer.OnErrorListener) this);
        mp.setOnSeekCompleteListener((MediaPlayer.OnSeekCompleteListener) this);

        return true;
    }
}
