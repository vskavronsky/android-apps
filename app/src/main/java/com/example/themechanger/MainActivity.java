package com.example.themechanger;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    AlertDialog alertDialog;
    EditText editText;
    Button lightMode, nightMode, systemMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightMode = findViewById(R.id.light_mode);
        nightMode = findViewById(R.id.night_mode);
        systemMode = findViewById(R.id.system_mode);

        UserTextViewModel userTextViewModel =
            new ViewModelProvider(this).get(UserTextViewModel.class);

        userTextViewModel.getUiState().observe(this, new Observer<UserText>() {
            @Override
            public void onChanged(UserText userText) {
                textView.setText(userText.getEnteredText());
            }
        });

        textView = findViewById(R.id.text_view);
        alertDialog = new AlertDialog.Builder(this).create();
        editText = new EditText(this);

        alertDialog.setTitle("Edit the text");
        alertDialog.setView(editText);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE TEXT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                userTextViewModel.updateState(editText.getText().toString());
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(textView.getText());
                alertDialog.show();
            }
        });

        lightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        nightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        });

        systemMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            }
        });
    }
}
