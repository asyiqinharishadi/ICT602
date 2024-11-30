package com.example.zakatestimator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalculateActivity extends AppCompatActivity {

    private EditText weightInput, goldValueInput;
    private RadioGroup goldTypeGroup;
    private TextView zakatPayableView, totalZakatView;
    private Button calculateButton, clearButton, backHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        weightInput = findViewById(R.id.weightInput);
        goldValueInput = findViewById(R.id.goldValueInput);
        goldTypeGroup = findViewById(R.id.goldTypeGroup);
        zakatPayableView = findViewById(R.id.zakatPayableView);
        totalZakatView = findViewById(R.id.totalZakatView);

        calculateButton = findViewById(R.id.calculateButton);
        clearButton = findViewById(R.id.clearButton);
        backHomeButton = findViewById(R.id.backHomeButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateZakat();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });

        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackHome();
            }
        });
    }

    private void calculateZakat() {
        try {
            double weight = Double.parseDouble(weightInput.getText().toString());
            double goldValue = Double.parseDouble(goldValueInput.getText().toString());

            int selectedType = goldTypeGroup.getCheckedRadioButtonId();
            if (selectedType == -1) {
                Toast.makeText(this, "Please select the gold type.", Toast.LENGTH_LONG).show();
                return;
            }

            double uruf = (selectedType == R.id.keepGold) ? 85 : 200;

            double zakatPayableValue = Math.max(0, weight - uruf) * goldValue;
            double totalZakat = zakatPayableValue * 0.025;

            // Update TextViews
            zakatPayableView.setText(String.format("RM %.2f", zakatPayableValue));
            totalZakatView.setText(String.format("RM %.2f", totalZakat));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input. Please enter valid numbers.", Toast.LENGTH_LONG).show();
        }
    }

    private void clearFields() {
        weightInput.setText("");
        goldValueInput.setText("");
        goldTypeGroup.clearCheck();
        zakatPayableView.setText("");
        totalZakatView.setText("");
    }

    private void navigateBackHome() {
        Intent intent = new Intent(CalculateActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
