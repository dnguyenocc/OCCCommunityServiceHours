package edu.orangecoastcollege.cs273.dnguyen1214.occcommunityservicehours;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by duyng on 12/14/2016.
 */
public class ShakeDetector implements SensorEventListener {

    // Constant to represent a shake threshold
    private static final float SHAKE_THRESHOLD = 25f;
    // Constant to represent how long between shakes (miliseconds)
    private static final float SHAKE_TIME_LAPSE = 2000;

    // What was the last time the event occurred;
    private long timeOfLastShake;

    // Define the listener to register onShake events
    private OnShakeListener shakeListener;

    // Constructor to create a new ShakeDetector passing in an OnShakeListener as argument:
    public ShakeDetector(OnShakeListener listener)
    {
        shakeListener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Determine if the event is an accelerometer
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            // Get the x, y, z values when this event occurs:
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Compare all 3 values against gravity
            float gForceX = x - SensorManager.GRAVITY_EARTH;
            float gForceY = y - SensorManager.GRAVITY_EARTH;
            float gForceZ = z - SensorManager.GRAVITY_EARTH;

            // Compute sum of squares
            double vector = Math.pow(gForceX, 2.0) + Math.pow(gForceY, 2.0) + Math.pow(gForceZ, 2.0);

            // Compute gForce
            float gForce = (float) Math.sqrt(vector);

            // Compare gForce against the threshold
            if (gForce > SHAKE_THRESHOLD)
            {
                // Get the current time:
                long now = System.currentTimeMillis();

                // Compare to see if the current time is at least 2000 miliseconds
                // greater than the time of the last shake

                if (now - timeOfLastShake >= SHAKE_TIME_LAPSE)
                {
                    timeOfLastShake = now;
                    // Register a shake event !!!
                    shakeListener.onShake();

                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    // Define our own interface (method for other classes to implement
    // called onShake()
    // It's the responsibility of MagicAnswerActivity to implement this method

    public interface OnShakeListener
    {
        void onShake();
    }
}

