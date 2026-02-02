package com.example.task1ch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextA;
    private EditText editTextB;
    private EditText editTextC;
    private EditText editTextX;

    private TextView textViewResult;
    private Button buttonResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextA = findViewById(R.id.editTextNumber_A);
        editTextB = findViewById(R.id.editTextNumber_B);
        editTextC = findViewById(R.id.editTextNumber_C);
        editTextX = findViewById(R.id.editTextNumber_X);

        textViewResult = findViewById(R.id.textView_result);
        buttonResult = findViewById(R.id.button_result);

        buttonResult.setEnabled(false);

        TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChangeqd(Editable s) {}
        };

        editTextA.addTextChangedListener(watcher);
        editTextB.addTextChangedListener(watcher);
        editTextC.addTextChangedListener(watcher);
        editTextX.addTextChangedListener(watcher);

        if (savedInstanceState != null) {

            editTextA.setText(savedInstanceState.getString("A"));
            editTextB.setText(savedInstanceState.getString("B"));
            editTextC.setText(savedInstanceState.getString("C"));
            editTextX.setText(savedInstanceState.getString("X"));
            textViewResult.setText(savedInstanceState.getString("RESULT"));
        }

        checkInput();
    }

    private void checkInput() {

        String a = editTextA.getText().toString().trim();
        String b = editTextB.getText().toString().trim();
        String c = editTextC.getText().toString().trim();
        String x = editTextX.getText().toString().trim();

        boolean isFilled = !a.isEmpty()
                && !b.isEmpty()
                && !c.isEmpty()
                && !x.isEmpty();

        buttonResult.setEnabled(isFilled);
    }

    public void onClick(View v) {

        double a, b, c, x, y;

        try {
            a = Double.parseDouble(editTextA.getText().toString());
            b = Double.parseDouble(editTextB.getText().toString());
            c = Double.parseDouble(editTextC.getText().toString());
            x = Double.parseDouble(editTextX.getText().toString());
        } catch (Exception e) {
            textViewResult.setText("Ошибка ввода!");
            return;
        }

        if (x < 4 && b == 0) {
            textViewResult.setText("Деление на ноль!");
            return;
        }

        if (x < 4) {
            y = (Math.pow(x, 2) + Math.pow(a, 2)) / (2 * b);
        } else {
            y = Math.pow(x, 3) * (a - b);
        }

        textViewResult.setText(String.valueOf(y));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("A", editTextA.getText().toString());
        outState.putString("B", editTextB.getText().toString());
        outState.putString("C", editTextC.getText().toString());
        outState.putString("X", editTextX.getText().toString());
        outState.putString("RESULT", textViewResult.getText().toString());
    }
}
