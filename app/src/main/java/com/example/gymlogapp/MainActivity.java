package com.example.gymlogapp;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gymlogapp.database.GymLogRepository;
import com.example.gymlogapp.database.entities.GymLog;
import com.example.gymlogapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private GymLogRepository repository;

    String mExercise ="";
    double mWeight = 0.0;
    int mReps = 0;

    int loggedInUserId = -1;

    public static final String TAG = "DAC_GYMLOG"; // Log tag for system log

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = GymLogRepository.getRepository(getApplication()); // access to database

        binding.logDisplayTextView.setMovementMethod(new ScrollingMovementMethod()); // scroll
        updateDisplay();

        binding.logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInformationFromDisplay();
                insertGymLogRecord();
                updateDisplay();
                //Toast.makeText(MainActivity.this, "IT WORKED!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void insertGymLogRecord() {
        if(mExercise.isEmpty()){
            return;
        }
        GymLog log = new GymLog(mExercise,mWeight,mReps,loggedInUserId);
        repository.insertGymLog(log);
    }

    private void updateDisplay() {
        ArrayList<GymLog> allLogs = repository.getAllLogs();
        if( allLogs.isEmpty()){
            binding.logDisplayTextView.setText("Nothing to show, time to hit the gym!");
        }
        StringBuilder sb = new StringBuilder();
        for(GymLog log : allLogs){
            sb.append(log);
        }
        binding.logDisplayTextView.setText(sb.toString());

    }

    private void getInformationFromDisplay() {
        mExercise = binding.exerciseInputEditText.getText().toString(); // getting info from input
        try{
            mWeight = Double.parseDouble(binding.weightInputEditText.getText().toString());
        } catch (NumberFormatException e){
            Log.d(TAG, "Error reading value from Weight edit text.");
        }
        try{
            mReps = Integer.parseInt(binding.repInputEditText.getText().toString());
        } catch (NumberFormatException e){
            Log.d(TAG, "Error reading value from Reps edit text.");
        }
    }
}