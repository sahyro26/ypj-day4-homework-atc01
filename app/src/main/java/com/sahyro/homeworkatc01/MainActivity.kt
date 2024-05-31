package com.sahyro.homeworkatc01

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.sahyro.homeworkatc01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.snackBarButton.setOnClickListener {
            Snackbar.make(view, "SnackBar Button Clicked", Snackbar.LENGTH_LONG)
                .setAction("OK", {})
                .setActionTextColor(Color.WHITE)
                .setBackgroundTint(Color.DKGRAY)
                .show()
        }

        binding.alertDialogButton.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Save")
            alertDialogBuilder.setMessage("Are you sure you want to save your changes?")
            //alertDialogBuilder.setPositiveButton("Yes", DialogInterface.OnClickListener(function = x))
            alertDialogBuilder.setPositiveButton("Yes") { dialog, which ->
                Snackbar.make(view,
                    "Yes Alert Button Clicked", Snackbar.LENGTH_SHORT).show()
            }
            alertDialogBuilder.setNegativeButton("No") { dialog, which ->
                Snackbar.make(view,
                   "No Alert Button Clicked", Snackbar.LENGTH_SHORT).show()
            }
            alertDialogBuilder.setNeutralButton("Maybe") { dialog, which ->
                Snackbar.make(view,
                    "Maybe Alert Button Clicked", Snackbar.LENGTH_SHORT).show()
            }
            alertDialogBuilder.show()
        }

        binding.notificationButton.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel_id = "channel_01"
                val channel_Name="notification"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val mChannel = NotificationChannel(channel_id, channel_Name, importance)
                mChannel.description = "test description"
                mChannel.enableLights(true)
                mChannel.lightColor = Color.RED
                mChannel.enableVibration(true)

                val intent = Intent(this, ResultActivity::class.java).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK }
                val pendingIntent: PendingIntent = PendingIntent.getActivities(this , 0,
                    arrayOf(intent), PendingIntent.FLAG_IMMUTABLE)

                // Use Notification.Builder to add the notification objects
                val notification: Notification = Notification.Builder(this, channel_id)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Android ATC Notification")
                    .setContentText("Check Android ATC New Course !!")
                    .setContentIntent(pendingIntent)
                    .build()

                // Registerr or add the channel with your Android system
                val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

                if (mNotificationManager != null) {
                    mNotificationManager.createNotificationChannel(mChannel)
                    //Show the notification
                    mNotificationManager.notify(1, notification)
                }
            }
        }

        binding.webviewButton.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }
    }
}