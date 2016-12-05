package com.alex.rcup.alextest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SplashScreen extends Activity {

    /**
     * Dur�e d'affichage du SplashScreen
     */
    protected int _splashTime = 2000;

    private Thread splashTread;

    /**
     * Chargement de l'Activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Log.i("Splash", "Ca splash");
        final SplashScreen sPlashScreen = this;

        /** Thread pour l'affichage du SplashScreen */
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(_splashTime);
                        Log.i("Splash", "Ca splash");
                    }
                } catch (InterruptedException e) {
                } finally {
                    finish();
                    Intent i = new Intent();
                    i.setClass(sPlashScreen, MainActivity.class);
                    startActivity(i);
                }
            }
        };

        splashTread.start();
    }

    /**
     * Si l'utilisateur fait un mouvement de haut en bas on passe �
     * l'Activity principale
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (splashTread) {
                splashTread.notifyAll();
            }
        }
        return true;
    }

}
