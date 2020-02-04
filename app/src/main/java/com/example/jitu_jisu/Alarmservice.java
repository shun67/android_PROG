package com.example.jitu_jisu;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Alarmservice extends Service {
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    public void onCreate()
    {
        Thread thread = new Thread(null, task, "sentMessageService");
        thread.start();
    }
    Runnable task = new Runnable()
    {
        public void run() {
            Intent messageBroadcast = new Intent();
            messageBroadcast.setAction("MyAlarmAction");
            sendBroadcast(messageBroadcast);
            stopSelf();
        }

    };
}
