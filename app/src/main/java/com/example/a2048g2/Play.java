package com.example.a2048g2;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Play extends AppCompatActivity {
    // Таблица
    protected int[][] table;
    // Метод для того, чтобы вывести таблицу на активность
    protected void populateTable(int[][] array){
        TextView textView0_0 = findViewById(R.id.textView0_0);
        TextView textView0_1 = findViewById(R.id.textView0_1);
        TextView textView0_2 = findViewById(R.id.textView0_2);
        TextView textView0_3 = findViewById(R.id.textView0_3);
        TextView textView1_0 = findViewById(R.id.textView1_0);
        TextView textView1_1 = findViewById(R.id.textView1_1);
        TextView textView1_2 = findViewById(R.id.textView1_2);
        TextView textView1_3 = findViewById(R.id.textView1_3);
        TextView textView2_0 = findViewById(R.id.textView2_0);
        TextView textView2_1 = findViewById(R.id.textView2_1);
        TextView textView2_2 = findViewById(R.id.textView2_2);
        TextView textView2_3 = findViewById(R.id.textView2_3);
        TextView textView3_0 = findViewById(R.id.textView3_0);
        TextView textView3_1 = findViewById(R.id.textView3_1);
        TextView textView3_2 = findViewById(R.id.textView3_2);
        TextView textView3_3 = findViewById(R.id.textView3_3);
        if(array[0][0] != 0) textView0_0.setText(String.valueOf(array[0][0]));
        if(array[0][1] != 0) textView0_1.setText(String.valueOf(array[0][1]));
        if(array[0][2] != 0) textView0_2.setText(String.valueOf(array[0][2]));
        if(array[0][3] != 0) textView0_3.setText(String.valueOf(array[0][3]));
        if(array[1][0] != 0) textView1_0.setText(String.valueOf(array[1][0]));
        if(array[1][1] != 0) textView1_1.setText(String.valueOf(array[1][1]));
        if(array[1][2] != 0) textView1_2.setText(String.valueOf(array[1][2]));
        if(array[1][3] != 0) textView1_3.setText(String.valueOf(array[1][3]));
        if(array[2][0] != 0) textView2_0.setText(String.valueOf(array[2][0]));
        if(array[2][1] != 0) textView2_1.setText(String.valueOf(array[2][1]));
        if(array[2][2] != 0) textView2_2.setText(String.valueOf(array[2][2]));
        if(array[2][3] != 0) textView2_3.setText(String.valueOf(array[2][3]));
        if(array[3][0] != 0) textView3_0.setText(String.valueOf(array[3][0]));
        if(array[3][1] != 0) textView3_1.setText(String.valueOf(array[3][1]));
        if(array[3][2] != 0) textView3_2.setText(String.valueOf(array[3][2]));
        if(array[3][3] != 0) textView3_3.setText(String.valueOf(array[3][3]));
    }
    // метод для того, чтобы в случайном месте ставилась 2
    protected void random_location(int[][] array){
        List<int[]> emptyCells = new ArrayList<>();
        // Находим все незаполненные ячейки и добавляем их в список
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j] == 0) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }
        Random random = new Random();
        // Выбираем случайную ячейку из списка незаполненных ячеек
        int[] randomCellIndices = emptyCells.get(random.nextInt(emptyCells.size()));
        int randomRow = randomCellIndices[0];
        int randomCol = randomCellIndices[1];
        table[randomRow][randomCol] = 2;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        table = new int[4][4];
        random_location(table);
        random_location(table);
        populateTable(table);
    }
}
