package com.example.test3;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

public class ActionModeActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_mode);

        // 设置 Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 启用返回按钮
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = findViewById(R.id.list_view_action_mode);

        // 初始化数据 - 完全按照文档中的示例
        data = new ArrayList<>();
        data.add("One");
        data.add("Two");
        data.add("Three");
        data.add("Four");
        data.add("Five");

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_activated_1, data);
        listView.setAdapter(adapter);

        // 设置多选模式 - 完全按照文档要求
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // 更新标题显示选中数量 - 完全按照文档中的"1 selected"格式
                int selectedCount = listView.getCheckedItemCount();
                mode.setTitle(selectedCount + " selected");

                // 更新选中项的背景
                updateSelectionBackground();
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // 加载ActionMode菜单
                mode.getMenuInflater().inflate(R.menu.action_mode_menu, menu);
                mode.setTitle("0 selected"); // 初始标题
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.action_delete) {
                    // 删除选中的项目
                    SparseBooleanArray selected = listView.getCheckedItemPositions();
                    List<String> toRemove = new ArrayList<>();

                    for (int i = selected.size() - 1; i >= 0; i--) {
                        if (selected.valueAt(i)) {
                            int position = selected.keyAt(i);
                            toRemove.add(data.get(position));
                        }
                    }

                    // 从数据源中移除
                    data.removeAll(toRemove);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(ActionModeActivity.this,
                            "删除了 " + toRemove.size() + " 个项目", Toast.LENGTH_SHORT).show();

                    mode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // 清除所有选中状态
                listView.clearChoices();
                updateSelectionBackground();
            }
        });
    }

    private void updateSelectionBackground() {
        // 强制刷新列表视图以更新选中状态
        adapter.notifyDataSetChanged();
    }
}