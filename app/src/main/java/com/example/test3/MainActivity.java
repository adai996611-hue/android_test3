package com.example.test3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnListView = findViewById(R.id.btn_listview);
        Button btnAlertDialog = findViewById(R.id.btn_alertdialog);
        Button btnMenu = findViewById(R.id.btn_menu);
        Button btnActionMode = findViewById(R.id.btn_actionmode);

        btnListView.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ListViewActivity.class)));

        btnAlertDialog.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AlertDialogActivity.class)));

        btnMenu.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, MenuActivity.class)));

        btnActionMode.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ActionModeActivity.class)));
    }
}