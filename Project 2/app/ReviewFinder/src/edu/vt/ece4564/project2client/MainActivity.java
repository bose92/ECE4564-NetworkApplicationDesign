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

public class MainActivity extends Activity {
	private Button getReviews_;
	private Button postReviews_;
	private Button getAddress_;
	private Button getSensorData_;
	private Button getAccelerometerData_;
	private Button getProximityData_;
	private EditText typeReviews_;
	private EditText getIP_;
	private EditText getPort_;
	private TextView showReviews_;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		showReviews_ = (TextView) findViewById(R.id.textView1);
		getReviews_ = (Button) findViewById(R.id.button1);
		postReviews_ = (Button) findViewById(R.id.button2);
		getAddress_ = (Button) findViewById(R.id.button3);
		getSensorData_ = (Button) findViewById(R.id.button4);
		getAccelerometerData_ = (Button) findViewById(R.id.button5);
		getProximityData_ = (Button) findViewById(R.id.button6);
		typeReviews_ = (EditText) findViewById(R.id.editText1);
		getIP_ = (EditText) findViewById(R.id.editText2);
		getPort_ = (EditText) findViewById(R.id.editText3);
		doConcurrencyNetworkOperation();
	}

	public void doConcurrencyNetworkOperation() {
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				final NetworkTask myTask = new NetworkTask(MainActivity.this);
				myTask.execute("", "", "getreview");
			}
		};

		getReviews_.setOnClickListener(listener);
		OnClickListener listener2 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				String review = typeReviews_.getText().toString()
						.replace(" ", "%20");
				final NetworkTask myTask = new NetworkTask(MainActivity.this);
				myTask.execute("", "", "postreview", review);
			}
		};

		postReviews_.setOnClickListener(listener2);
		OnClickListener listener3 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				String addressIP = getIP_.getText().toString();
				String addressPort = getPort_.getText().toString();
				final NetworkTask myTask = new NetworkTask(MainActivity.this);
				myTask.execute(addressIP, addressPort);
			}
		};

		getAddress_.setOnClickListener(listener3);
		OnClickListener listener4 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,
						SensorActivity.class));
			}
		};
		getSensorData_.setOnClickListener(listener4);
		OnClickListener listener7 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,
						AccelerometerSensorActivity.class));
			}
		};

		getAccelerometerData_.setOnClickListener(listener7);
		OnClickListener listener10 = new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,
						ProximityActivity.class));
			}

		};
		getProximityData_.setOnClickListener(listener10);

	}

	public void setReview(String value) {
		showReviews_.setText(value);
	}
}