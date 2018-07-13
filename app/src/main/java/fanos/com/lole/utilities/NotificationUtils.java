package fanos.com.lole.utilities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import fanos.com.lole.R;
import fanos.com.lole.activities.MainActivity;

public class NotificationUtils {
    private static final int PENDING_INTENT_ID = 123;
    private static final String NOTIFICATION_CHANNEL_ID = "notification-channel-id";
    private static final int NOTIFICATION_ID=321;

    private static PendingIntent contentIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context, PENDING_INTENT_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void loleNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, context.getString(R.string.main_notification_channel_name), NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(largeIcon(context))
                .setContentTitle("Lole Notification")
                .setContentText("This is to test if the notification is working")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("This is to test if the notification is working"))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(NOTIFICATION_ID,builder.build());
    }

    private static Bitmap largeIcon(Context context) {
        Resources resource = context.getResources();
        return BitmapFactory.decodeResource(resource, R.drawable.delivery_man_icon);
    }
}
