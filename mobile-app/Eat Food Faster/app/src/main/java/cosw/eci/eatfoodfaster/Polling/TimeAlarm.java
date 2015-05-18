package cosw.eci.eatfoodfaster.Polling;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import cosw.eci.eatfoodfaster.R;


/**
 * Created by luisfediaz on 18/05/15.
 */
public class TimeAlarm  extends BroadcastReceiver {

    NotificationManager nm;

    @Override

    public void onReceive(Context context, Intent intent) {
        System.out.println(" ejecutando la alarma ");
        LogicaNotificacion ln =  new LogicaNotificacion(context);
        ln.execute("14");
        /*nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        CharSequence from = "Nothin";
        CharSequence message = "Crazy About Android...";
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(), 0);
        Notification notif = new Notification(R.drawable.icon,
                "Crazy About Android...", System.currentTimeMillis());
        notif.setLatestEventInfo(context, from, message, contentIntent);
        nm.notify(1, notif);
        Toast.makeText(context, "Sorry, please try again", Toast.LENGTH_SHORT).show();*/
    }
}
