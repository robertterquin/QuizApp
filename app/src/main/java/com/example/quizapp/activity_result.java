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

        // Initialize UI elements
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        progressBar = findViewById(R.id.progressBar);
        restartButton = findViewById(R.id.restartButton);
        exitButton = findViewById(R.id.exitButton);

        // Check if progressBar is null
        if (progressBar == null) {
            throw new NullPointerException("CircularProgressBar is NULL! Check your XML ID.");
        }

        // Get score from intent (default to avoid crashes)
        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("total", 5);

        // Prevent division by zero
        if (totalQuestions <= 0) {
            totalQuestions = 1;  // Avoid crash by setting at least 1
        }

        // Update score text
        scoreTextView.setText(String.format("%d / %d", score, totalQuestions));

        // Calculate progress percentage
        float progressPercentage = ((float) score / totalQuestions) * 100;

        // Animate progress bar
        ObjectAnimator progressAnimator = ObjectAnimator.ofFloat(progressBar, "progress", 0, progressPercentage);
        progressAnimator.setDuration(1000);
        progressAnimator.setInterpolator(new DecelerateInterpolator());
        progressAnimator.start();

        // Set result message based on score
        if (score == totalQuestions) {
            resultTextView.setText("Excellent 🎉");
        } else if (score >= totalQuestions / 2) {
            resultTextView.setText("Good Job 👍");
        } else {
            resultTextView.setText("Better Luck Next Time 😊");
        }

        // Restart quiz button
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(activity_result.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        // Exit button closes the app
        exitButton.setOnClickListener(v -> finishAffinity());
    }
}
