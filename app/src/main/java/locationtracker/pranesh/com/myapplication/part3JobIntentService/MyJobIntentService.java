package locationtracker.pranesh.com.myapplication.part3JobIntentService;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;

public class MyJobIntentService extends JobIntentService {
    public static final String TAG="MyJobIntentService";

    static void enqueWork(Context context, Intent intent){
        //To start service
        enqueueWork(context,MyJobIntentService.class,123,intent);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d(TAG, "onHandleWork");
        String input = intent.getStringExtra("data");


        for(int i=0;i<10;i++)
        {
            Log.d(TAG, input + "-"+i);
            if(isStopped()) return;
            SystemClock.sleep(500);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public boolean onStopCurrentWork() {
        //default true
        //tells resume the work or not
        //false means cancel the work
        Log.d(TAG, "onStopCurrentWork");
        return super.onStopCurrentWork();

    }
}
