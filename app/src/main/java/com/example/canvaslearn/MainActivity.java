package com.example.canvaslearn;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.graphs.DiagramView;
import com.example.graphs.GraphData;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    DiagramView diagramView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Slider columnSlider = findViewById(R.id.columnSlider);
        Slider colorSlider = findViewById(R.id.colorSlider);
        diagramView = findViewById(R.id.diagramView);

        setDiagramView(5);

        columnSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(Slider slider) {
                setDiagramView((int) slider.getValue());
            }
        });

        colorSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(Slider slider) {
                diagramView.setColumnsColor(getColorFromValue(slider.getValue()));
            }
        });


    }

    private void setDiagramView(int progress) {
        ArrayList<GraphData> graphDataList = new ArrayList<>();
        for (int i = 0; i < progress; i++) {
            // random value between 10 and 200
            int randomValue = (int) (Math.random() * 190) + 10;
            graphDataList.add(new GraphData("Item " + i, randomValue));
        }
        diagramView.setDataList(graphDataList);
        diagramView.setAnimateColumns(true);
        diagramView.setTextBold(true);
        diagramView.setDataList(graphDataList);
        diagramView.setTheBackgroundColor(Color.parseColor("#FFFFFF"));
        diagramView.setColumnsColor(Color.parseColor("#FF5722"));
        diagramView.setColumnsTextColor(Color.parseColor("#000000"));
        diagramView.setColumnsTextSize(40);
        diagramView.setSpaceBetweenColumns(10);
    }

    private int getColorFromValue(float value) {
        // Example: Map value to a color gradient from red to green
        int red = (int) (255 * (1 - value / 10));
        int green = (int) (255 * (value / 10));
        return Color.rgb(red, green, 0);
    }
}