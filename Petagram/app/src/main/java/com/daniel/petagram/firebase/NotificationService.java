package com.daniel.petagram.firebase;

import android.app.NotificationChannel;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.NotificationCompat.WearableExtender;

import com.daniel.petagram.MainActivity;
import com.daniel.petagram.Mejores5;
import com.daniel.petagram.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationService extends FirebaseMessagingService {

    public static  final  String TAG ="FIREBASE";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }

        /*Intent i =new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_ONE_SHOT);

        String channelId = "default_channel";
        //Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.huella)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(123, builder.build());*/
    }

    private void sendNotification(String messageBody) {
        //MAIN
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("perfil",1);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //MEJORES 5
        Intent intent1 = new Intent(this, Mejores5.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0 /* Request code */, intent1,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action1= new NotificationCompat.Action.Builder(R.drawable.ic_perfil,"Ver mi Perfil",pendingIntent).build();
        NotificationCompat.Action action2= new NotificationCompat.Action.Builder(R.drawable.bone,"Dar Like",pendingIntent).build();
        NotificationCompat.Action action3= new NotificationCompat.Action.Builder(R.drawable.five,"Ver 5 Mejores",pendingIntent1).build();

        NotificationCompat.WearableExtender wearableExtender = new NotificationCompat.WearableExtender();

        String channelId = "default_channel";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.huella)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .extend(wearableExtender.addAction(action1))
                        .extend(wearableExtender.addAction(action2))
                        .extend(wearableExtender.addAction(action3));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("NEW_TOKEN",s);

    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
        sendRegistrationToServer(token);
    }
}
