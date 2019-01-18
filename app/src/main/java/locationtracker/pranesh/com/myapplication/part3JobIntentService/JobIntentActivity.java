package locationtracker.pranesh.com.myapplication.part3JobIntentService;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import locationtracker.pranesh.com.myapplication.R;
import locationtracker.pranesh.com.myapplication.part2IntentService.MyIntentService;


public class JobIntentActivity extends AppCompatActivity implements  View.OnClickListener {
    Button btnEnq;
    EditText editText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_intent);
        btnEnq = findViewById(R.id.btnEnq);
        editText = findViewById(R.id.editText);

        btnEnq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnEnq:
                String input = editText.getText().toString();
                Intent intent = new Intent(this, MyJobIntentService.class);
                intent.putExtra("data", input);

                MyJobIntentService.enqueWork(this,intent);
               break;

            default:
                break;
        }
    }
}