package com.myapplicationdev.android.lp3quiz1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnBasic, btnPicture, btnInbox;
    int notificationID = 88;
    int requestCode = 0;
    NotificationCompat.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBasic = (Button) findViewById(R.id.btnBasic);
        btnPicture = (Button) findViewById(R.id.btnBigPicture);
        btnInbox = (Button) findViewById(R.id.btnInbox);

        btnBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                basicStyleNotification();

            }
        });

        btnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bigPictureStyleNotification();
            }
        });

        btnInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inboxStyleNotification();
            }
        });

    }

    private void basicStyleNotification() {


        int NOTIFICATION_ID = 1;
        builder = new NotificationCompat.Builder(this,"default");
        builder.setSmallIcon(android.R.drawable.btn_star_big_off);
        builder.setContentTitle("LP3 Quiz1");
        builder.setContentText("This is simple");
        builder.setAutoCancel(true);
        PendingIntent launchIntent = getLaunchIntent(NOTIFICATION_ID, getBaseContext());
        Intent buttonIntent = new Intent(getBaseContext(), NotificationReceiver.class);
        buttonIntent.putExtra("notificationId", NOTIFICATION_ID);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, buttonIntent, 0);
        builder.setContentIntent(launchIntent);
        buildNotification(NOTIFICATION_ID);

    }


    private void inboxStyleNotification() {

        ArrayList<String> al = new ArrayList<>();
        al.add("This is the first line");
        al.add("This is the second line");
        al.add("This is the third line");

        NotificationCompat.InboxStyle inboxS = new NotificationCompat.InboxStyle();
        for (String tmp : al){ //iterate the entire ArrayList object and extract each element into variable tmp
            inboxS.addLine(tmp);  //do something to the tmp, which is to retrive the value and place into Notification Inbox Style
        };
        inboxS.setBigContentTitle("Inbox style");
        inboxS.setSummaryText("List of entries");

        int NOTIFICATION_ID = 1;


        PendingIntent launchIntent = getLaunchIntent(NOTIFICATION_ID, getBaseContext());
        builder = new NotificationCompat.Builder(this,"default");
        builder.setContentText("Expand to see content");
        builder.setSmallIcon(android.R.drawable.btn_star_big_off);
        builder.setContentTitle("LP3 Quiz1");
        builder.setStyle(inboxS);
        builder.setAutoCancel(true);
        builder.setContentIntent(launchIntent);

        buildNotification(NOTIFICATION_ID);
    }



    private void bigPictureStyleNotification() {
        int NOTIFICATION_ID = 1;

        Bitmap pic = BitmapFactory.decodeResource(getResources(), R.drawable.koala);
        Intent buttonIntent = new Intent(getBaseContext(), NotificationReceiver.class);
        buttonIntent.putExtra("notificationId", NOTIFICATION_ID);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(getBaseContext(), 0, buttonIntent, 0);
        PendingIntent launchIntent = getLaunchIntent(NOTIFICATION_ID, getBaseContext());
        builder = new NotificationCompat.Builder(this,"default");
        builder.setSmallIcon(android.R.drawable.btn_star_big_off);
        builder.setContentTitle("Big Picture Style");
        builder.setContentText("Koala!");
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(pic));
        builder.setAutoCancel(true);
        builder.setContentIntent(launchIntent);

        buildNotification(NOTIFICATION_ID);
    }

    public PendingIntent getLaunchIntent(int notificationId, Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("notificationId", notificationId);
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }
    private void buildNotification(int NOTIFICATION_ID) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}
