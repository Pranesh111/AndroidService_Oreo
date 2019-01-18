package locationtracker.pranesh.com.myapplication.part1Forground;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import locationtracker.pranesh.com.myapplication.R;

import static locationtracker.pranesh.com.myapplication.App.CHANNEL_ID;

public class ForegroundService1 extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("data");
        Intent pending = new Intent(this, Foreground1.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, pending, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();

        //if not called killed by system itself within 5 s / 15 s
        startForeground(1, notification);

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
