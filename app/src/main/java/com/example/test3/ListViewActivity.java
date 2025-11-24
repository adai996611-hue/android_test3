package com.example.test3;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "animal_channel";
    private NotificationManager notificationManager;

    // 动物数据
    private String[] animalNames = {"猫", "狗", "大象", "狮子", "猴子", "老虎"};
    private int[] animalImages = {
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.elephant,
            R.drawable.lion,
            R.drawable.monkey,
            R.drawable.tiger
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        createNotificationChannel();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        ListView listView = findViewById(R.id.list_view);

        // 准备数据
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < animalNames.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", animalNames[i]);
            map.put("image", animalImages[i]);
            data.add(map);
        }

        // 创建适配器
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.list_item,
                new String[]{"name", "image"},
                new int[]{R.id.tv_animal_name, R.id.iv_animal}
        );

        listView.setAdapter(adapter);

        // 设置点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 显示Toast
                String message = "你选择了: " + animalNames[position];
                Toast.makeText(ListViewActivity.this, message, Toast.LENGTH_SHORT).show();

                // 发送通知
                sendNotification(animalNames[position]);
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "动物通知";
            String description = "显示选择的动物信息";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void sendNotification(String animalName) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.cat) // 使用应用程序图标
                .setContentTitle(animalName)
                .setContentText("这是关于" + animalName + "的详细信息")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }
}