package locationtracker.pranesh.com.myapplication.part2IntentService;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import locationtracker.pranesh.com.myapplication.R;

import static locationtracker.pranesh.com.myapplication.App.CHANNEL_ID;

public class MyIntentService extends IntentService {
 public static final String TAG ="MyIntentService";

 PowerManager.WakeLock  wakeLock;
    public MyIntentService() {
        super("MyIntentService");
        setIntentRedelivery(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"Example:Wakelock");
        wakeLock.acquire();

        Log.d(TAG, "wakelock acquire");
        //We have to run the service in foreground for oreo & later so creating notification & starting foregroung
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            Intent intent= new Intent(this,IntentActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

            Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setContentTitle("Running...")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingIntent)
                    .build();
            startForeground(1,notification);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        wakeLock.release();
        Log.d(TAG, "wakelock released");
    }

    @Override
    protected void onHandleIntent( @Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent");
        String input = intent.getStringExtra("data");

        for(int i=0;i<10;i++)
        {
            Log.d(TAG, input + "-"+i);
            SystemClock.sleep(500);
        }
    }
}
