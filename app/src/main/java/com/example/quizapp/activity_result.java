package com.example.quizapp;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class activity_result extends AppCompatActivity {

    private TextView resultTextView, scoreTextView;
    private Button restartButton, exitButton;
    private CircularProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        progressBar = findViewById(R.id.progressBar);
        restartButton = findViewById(R.id.restartButton);
        exitButton = findViewById(R.id.exitButton);

        if (progressBar == null) {
            throw new NullPointerException("CircularProgressBar is NULL! Check your XML ID.");
        }


        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("total", 5);


        if (totalQuestions <= 0) {
            totalQuestions = 1;
        }

        scoreTextView.setText(String.format("%d / %d", score, totalQuestions));

        float progressPercentage = ((float) score / totalQuestions) * 100;

        ObjectAnimator progressAnimator = ObjectAnimator.ofFloat(progressBar, "progress", 0, progressPercentage);
        progressAnimator.setDuration(1000);
        progressAnimator.setInterpolator(new DecelerateInterpolator());
        progressAnimator.start();

        if (score == totalQuestions) {
            resultTextView.setText("Excellent 🎉");
        } else if (score >= totalQuestions / 2) {
            resultTextView.setText("Good Job 👍");
        } else {
            resultTextView.setText("Better Luck Next Time 😊");
        }

        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(activity_result.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        exitButton.setOnClickListener(v -> finishAffinity());
    }
}
