package com.example.test3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MenuActivity extends AppCompatActivity {

    private TextView tvTestContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // 设置自定义 Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 启用返回按钮
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        tvTestContent = findViewById(R.id.tv_test_content);

        // 设置 toolbar 的导航点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 加载菜单
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // 处理返回按钮
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        // 处理普通菜单项
        if (id == R.id.menu_normal) {
            Toast.makeText(this, "点击了普通菜单项", Toast.LENGTH_SHORT).show();
            return true;
        }

        // 处理字体大小
        if (id == R.id.font_small) {
            tvTestContent.setTextSize(10);
            Toast.makeText(this, "字体大小：小(10号)", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.font_medium) {
            tvTestContent.setTextSize(16);
            Toast.makeText(this, "字体大小：中(16号)", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.font_large) {
            tvTestContent.setTextSize(20);
            Toast.makeText(this, "字体大小：大(20号)", Toast.LENGTH_SHORT).show();
            return true;
        }

        // 处理字体颜色
        else if (id == R.id.color_red) {
            tvTestContent.setTextColor(Color.RED);
            Toast.makeText(this, "字体颜色：红色", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.color_black) {
            tvTestContent.setTextColor(Color.BLACK);
            Toast.makeText(this, "字体颜色：黑色", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}