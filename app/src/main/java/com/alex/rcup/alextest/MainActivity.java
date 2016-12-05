package com.alex.rcup.alextest;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.app.Fragment;

import com.alex.rcup.alextest.BLEService.BLEService;
import com.alex.rcup.alextest.fragments.podo;
import com.alex.rcup.alextest.fragments.podo_infos;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    private podo podoInstance = null;
    public  BLEService mBLEService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar (c'est déja remove je  crois)
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar (pour passer en full screen)
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        //Secret button de l'activité (ca va me permettre de génerer des trucs en douche) hihihi
        //mButton=(Button) findViewById(R.id.button);
        //mButton.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("This app needs location access");
                builder.setMessage("Please grant location access so this app can detect beacons.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialog) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
                        }
                    }
                });
                builder.show();
            }
        }
        //On instancie l'application avec le fragment podo
        podoInstance = podo.newInstance();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, podoInstance)
                .commit();

        mBLEService = BLEService.getInstance(this);
        mBLEService.startScan();
        IntentFilter filter = new IntentFilter();
        filter.addAction(NOTIFICATION_SERVICE);
        this.registerReceiver(dataReceiver, filter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("TAG", "coarse location permission granted");
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Functionality limited");
                    builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons when in the background.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                        }

                    });
                    builder.show();
                }
                return;
            }
        }
    }
    private final BroadcastReceiver dataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            BluetoothGattCharacteristic accelCharacteristic = new BluetoothGattCharacteristic(UUID.fromString("00000001-1212-efde-1523-785fef13d123"), BluetoothGattCharacteristic.PERMISSION_READ, BluetoothGattCharacteristic.PERMISSION_READ);
            BluetoothGattCharacteristic pressureCharacteristic = new BluetoothGattCharacteristic(UUID.fromString("00000002-1212-efde-1523-785fef13d123"), BluetoothGattCharacteristic.PERMISSION_READ, BluetoothGattCharacteristic.PERMISSION_READ);
            BluetoothGattCharacteristic energyCharacteristic = new BluetoothGattCharacteristic(UUID.fromString("00000003-1212-efde-1523-785fef13d123"), BluetoothGattCharacteristic.PERMISSION_READ, BluetoothGattCharacteristic.PERMISSION_READ);
            BluetoothGattCharacteristic accelConfigCharacteristic = new BluetoothGattCharacteristic(UUID.fromString("00000004-1212-efde-1523-785fef13d123"), BluetoothGattCharacteristic.PERMISSION_READ, BluetoothGattCharacteristic.PERMISSION_READ);
            BluetoothGattCharacteristic pressureConfigCharacteristic = new BluetoothGattCharacteristic(UUID.fromString("00000005-1212-efde-1523-785fef13d123"), BluetoothGattCharacteristic.PERMISSION_READ, BluetoothGattCharacteristic.PERMISSION_READ);
            BluetoothGattCharacteristic energyConfigCharacteristic = new BluetoothGattCharacteristic(UUID.fromString("00000006-1212-efde-1523-785fef13d123"), BluetoothGattCharacteristic.PERMISSION_READ, BluetoothGattCharacteristic.PERMISSION_READ);
            energyCharacteristic.setValue(intent.getByteArrayExtra("energy"));
            accelCharacteristic.setValue(intent.getByteArrayExtra("accel"));
            pressureCharacteristic.setValue(intent.getByteArrayExtra("pressure"));
            energyConfigCharacteristic.setValue(intent.getByteArrayExtra("energyconfig"));
            accelConfigCharacteristic.setValue(intent.getByteArrayExtra("accelconfig"));
            pressureConfigCharacteristic.setValue(intent.getByteArrayExtra("pressureconfig"));
            if (energyCharacteristic.getValue() != null) {
                mHandler.sendMessage(Message.obtain(null, MSG_ENERGY_DATA, energyCharacteristic));
            }
            if (accelCharacteristic.getValue() != null) {
                mHandler.sendMessage(Message.obtain(null, MSG_ACCEL_DATA, accelCharacteristic));
            }
            if (pressureCharacteristic.getValue() != null) {
                mHandler.sendMessage(Message.obtain(null, MSG_PRESSURE_DATA, pressureCharacteristic));
            }
            if (energyConfigCharacteristic.getValue() != null) {
                mHandler.sendMessage(Message.obtain(null, MSG_ENERGY_CONFIG, energyConfigCharacteristic));
            }
            if (accelConfigCharacteristic.getValue() != null) {
                mHandler.sendMessage(Message.obtain(null, MSG_ACCEL_CONFIG, accelConfigCharacteristic));
            }
            if (pressureConfigCharacteristic.getValue() != null) {
                mHandler.sendMessage(Message.obtain(null, MSG_PRESSURE_CONFIG, pressureConfigCharacteristic));
            }
        }
    };

    /**
     * Handler to process multiple events on the main thread
     **/
    private static final int MSG_ENERGY_CONFIG = 302;
    private static final int MSG_ACCEL_CONFIG = 402;
    private static final int MSG_PRESSURE_CONFIG = 502;
    private static final int MSG_ENERGY_DATA = 301;
    private static final int MSG_ACCEL_DATA = 401;
    private static final int MSG_PRESSURE_DATA = 501;
    private BluetoothGattCharacteristic mCharacteristic = null;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            byte[] trame;
            switch (msg.what) {
                case MSG_ENERGY_DATA:
                    mCharacteristic = (BluetoothGattCharacteristic) msg.obj;
                    break;
                case MSG_ACCEL_DATA:
                    mCharacteristic = (BluetoothGattCharacteristic) msg.obj;
                    break;
                case MSG_PRESSURE_DATA:
                    mCharacteristic = (BluetoothGattCharacteristic) msg.obj;
                    updateStep(mCharacteristic);
                    break;
                case MSG_ENERGY_CONFIG:
                    mCharacteristic = (BluetoothGattCharacteristic) msg.obj;
                    trame = mCharacteristic.getValue();
                    break;
                case MSG_ACCEL_CONFIG:
                    mCharacteristic = (BluetoothGattCharacteristic) msg.obj;
                    trame = mCharacteristic.getValue();
                    break;
                case MSG_PRESSURE_CONFIG:
                    mCharacteristic = (BluetoothGattCharacteristic) msg.obj;
                    trame = mCharacteristic.getValue();
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        FragmentManager fragmentManager = getFragmentManager();
        switch (v.getId()) {
            /*
            case R.id.button:

                fragmentManager.beginTransaction()
                        .replace(R.id.container, podo_infos.newInstance())
                        .commit();

                break;
                */
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void updateStep (BluetoothGattCharacteristic c) {
        Integer lowerByte = c.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 1);
        Integer lower2Byte = c.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 2);
        Integer lower3Byte = c.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 3);
        Integer upperByte = c.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 4); // Note: interpret MSB as unsigned.
        int steps = (lowerByte << 24) + (lower2Byte << 16) + (lower3Byte << 8) + upperByte;
        podoInstance.mTextView_nbpas.setText(String.valueOf(steps));
        podoInstance.updateConstell(steps, podoInstance.dailySteps);
    }
}