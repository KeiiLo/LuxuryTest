package com.alex.rcup.alextest.tools;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.alex.rcup.alextest.R;
import com.alex.rcup.alextest.fragments.podo;

import org.w3c.dom.Text;

/**
 * A class used to perform periodical updates,
 * specified inside a runnable object. An update interval
 * may be specified (otherwise, the class will perform the
 * update every 2 seconds).
 *
 * @author Carlos Simões
 */
public class UIUpdater {
    private int mInterval = 10000; // 10 seconds by default, can be changed later
    private Handler mHandler;
    private int compteur= 0;

    protected void onCreate(Bundle bundle) {

        // your code here
        TextView mText_nbpas;
        mText_nbpas= (TextView)podo.view_podo.findViewById(R.id.nbpas);
        mText_nbpas.setText(compteur);
        mHandler = new Handler();
        startRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                //updateStatus(); //this function can change value of mInterval.
            } finally {
                // 100% guarantee that this always happens, even if
                // your update method throws an exception
                mHandler.postDelayed(mStatusChecker, mInterval);
                compteur = compteur +1;
            }
        }
    };

    public void startRepeatingTask() {
        mStatusChecker.run();
    }

    public void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }
}