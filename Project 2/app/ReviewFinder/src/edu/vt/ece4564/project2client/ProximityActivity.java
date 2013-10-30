package edu.vt.ece4564.project2client;

import com.example.reviewfinder.R;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ImageView;

public class ProximityActivity extends Activity implements SensorEventListener {
	private SensorManager mSensorManager;
	private Sensor mSensor;
	ImageView iv;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_proximity);
		Intent intent = getIntent();
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		iv = (ImageView) findViewById(R.id.imageView1);
	}

	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mSensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.values[0] == 0) {
			iv.setImageResource(R.drawable.near);
		} else {
			iv.setImageResource(R.drawable.far);
		}
	}
}
