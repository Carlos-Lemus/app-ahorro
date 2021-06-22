package com.achmadqomarudin.animatedbottombar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.achmadqomarudin.animatedbottombar.activities.MenuActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_example1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_example1 = findViewById(R.id.btn_example1);


        btn_example1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_example1:
                startActivity(new Intent(MainActivity.this, MenuActivity.class));
                break;
        }
    }
}
