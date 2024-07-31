package com.example.canvaslearn;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DiagramView diagramView = findViewById(R.id.diagramView);
        ArrayList<Data> dataList = new ArrayList<>();
        dataList.add(new Data("Item 1", 100));
        dataList.add(new Data("Item 2", 150));
        dataList.add(new Data("Item 3", 200));
        dataList.add(new Data("Item 4", 100));
        dataList.add(new Data("Item 5", 50));
        diagramView.setDataList(dataList);
    }
}