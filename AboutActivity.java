package com.example.zakatestimator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    private Button backHomeButton;
    private TextView githubLink;
    private Button button; // Ensure this is private and initialized properly

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Initialize buttons and views
        button = findViewById(R.id.shareButton);
        button.setOnClickListener(this); // Set OnClickListener for the share button

        backHomeButton = findViewById(R.id.backHomeButton);
        githubLink = findViewById(R.id.githubLink);

        // Set OnClickListener for the GitHub link
        githubLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://github.com/asyiqinharishadi";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        // Set OnClickListener for the Back to Home button
        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackHome();
            }
        });
    }

    // Navigate back to the MainActivity
    private void navigateBackHome() {
        Intent intent = new Intent(AboutActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // Handle button clicks
    @Override
    public void onClick(View v) {
        // Use the parameter 'v' instead of 'view'
        if (v == button) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this amazing app!");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, "Share via");
            startActivity(shareIntent);
        }
    }
}
