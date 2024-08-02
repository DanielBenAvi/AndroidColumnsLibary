package com.example.canvaslearn;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.graphs.DiagramView;
import com.example.graphs.GraphData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DiagramView diagramView = findViewById(R.id.diagramView);
        ArrayList<GraphData> graphDataList = new ArrayList<>();
        graphDataList.add(new GraphData("Item 1", 100));
        graphDataList.add(new GraphData("Item 2", 150));
        graphDataList.add(new GraphData("Item 3", 300));
        graphDataList.add(new GraphData("Item 4", 100));
        graphDataList.add(new GraphData("Item 5", 50));
        diagramView.setAnimateColumns(true);
        diagramView.setDataList(graphDataList);
        diagramView.setTheBackgroundColor(Color.parseColor("#FFC107"));
        diagramView.setColumnsColor(Color.parseColor("#FF5722"));
        diagramView.setColumnsTextColor(Color.parseColor("#FFFFFF"));
        diagramView.setColumnsTextSize(40);
        diagramView.setSpaceBetweenColumns(10);

    }
}