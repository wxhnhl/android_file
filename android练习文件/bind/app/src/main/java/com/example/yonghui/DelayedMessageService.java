package com.example.yonghui;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class DelayedMessageService extends IntentService {

    public static final int NOTIFICATION_ID = 5453;
    public static final String EXTRA_MESSAGE="message";
    public DelayedMessageService() {
        super("DelayedMessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            try{
                wait(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        String text = intent.getStringExtra(EXTRA_MESSAGE);
        Log.i("DelayedMessageService","This message is:"+text);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle(getString(R.string.question))
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[]{0,1000})
                .setAutoCancel(true);

        Intent actionIntent = new Intent(this,MainActivity.class);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(
                this,
                0,
                actionIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(actionPendingIntent);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,builder.build());
    }


}
