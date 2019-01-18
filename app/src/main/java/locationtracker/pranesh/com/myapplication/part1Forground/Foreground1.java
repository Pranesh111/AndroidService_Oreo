package locationtracker.pranesh.com.myapplication.part1Forground;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import locationtracker.pranesh.com.myapplication.R;

public class Foreground1 extends AppCompatActivity implements View.OnClickListener {
    Button btnStart, btnStop;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        editText = findViewById(R.id.editText);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnStart:
                String input = editText.getText().toString();
                Intent intent = new Intent(this, ForegroundService1.class);
                intent.putExtra("data", input);

                //It work since our app is open/foreground , if we call startService from background - IllegalStateExption
                //if from background we need to run the service then startForeGroundService  & we have Time Window of 5s/15s
                //within 5s to call startForeground

                //startService(intent);
                ContextCompat.startForegroundService(this,intent);
                break;
            case R.id.btnStop:
                Intent stopIntent = new Intent(this, ForegroundService1.class);
                stopService(stopIntent);

                break;
            default:
                break;
        }
    }
}
