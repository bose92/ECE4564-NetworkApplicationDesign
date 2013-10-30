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

public class SensorActivity extends Activity {
	private Button exitSensors_;
	ProgressBar lightMeter;
	TextView textMax, textReading;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		Intent intent = getIntent();
		exitSensors_ = (Button) findViewById(R.id.button1);
		lightMeter = (ProgressBar) findViewById(R.id.lightmeter);
		textMax = (TextView) findViewById(R.id.max);
		textReading = (TextView) findViewById(R.id.reading);
		SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

		if (lightSensor == null) {
			Toast.makeText(SensorActivity.this, "No Light Sensor! quit-",
					Toast.LENGTH_LONG).show();
		} else {
			float max = lightSensor.getMaximumRange();
			lightMeter.setMax((int) max);
			textMax.setText("Max Reading: " + String.valueOf(max));

			sensorManager.registerListener(lightSensorEventListener,
					lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
			doConcurrencyNetworkOperation();
		}
	}

	SensorEventListener lightSensorEventListener = new SensorEventListener() {
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}

		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
				float currentReading = event.values[0];
				lightMeter.setProgress((int) currentReading);
				textReading.setText("Current Reading: "
						+ String.valueOf(currentReading));
			}
		}

	};

	public void doConcurrencyNetworkOperation() {
		OnClickListener listener5 = new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		};
		exitSensors_.setOnClickListener(listener5);
	}
}
