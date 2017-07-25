package project5_1.cookandroid.com.chap14_project14_1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 8 on 2017-07-25.
 */

public class MusicService extends Service {

    MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("서비스 테스트", "onCreate()");
    }

    @Override
    public void onDestroy() {
        Log.i("서비스 테스트", "onDestroy()");
        mp.stop();
        super.onDestroy();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("서비스 테스트", "onStartCommand()");

        mp = MediaPlayer.create(this, R.raw.song1);
        mp.setLooping(true);
        mp.start();

        return super.onStartCommand(intent, flags, startId);
    }
}
