package com.example.a2048g2;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Play extends AppCompatActivity implements GestureDetector.OnGestureListener {
    // Таблица
    protected int[][] table;
    // Поле для работы со свайпами
    protected GestureDetectorCompat gd;
    // Поле для звука свайпов
    protected MediaPlayer swipe_player;
    // Поле для звука выигрыша
    protected MediaPlayer win_player;
    // Поле для звука поражения
    protected MediaPlayer lose_player;
    // Поле для кнопки "в меню"
    protected Button button_go_menu;

    // Метод для подсчёта суммы очков
    protected int sum_table() {
        int sum = 0;
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table[i].length; j++) {
                sum += this.table[i][j];
            }
        }
        return sum;
    }

    // Метод изменения для счёта
    protected void changing_the_score() {
        TextView score = findViewById(R.id.score);
        score.setText("Score: " + String.valueOf(sum_table()));
    }

    // Метод для воиспроизведения звука свайпов
    protected void swipe_playback() {
        swipe_player.start();
    }

    // Метод для воиспроизведения звука победы
    protected void win_playback() {
        win_player.start();
    }

    // Метод для воиспроизведения звука поражения
    protected void lose_playback() {
        lose_player.start();
    }

    protected void setLoseFragment() {
        Lose lose = new Lose();
        FragmentTransaction ftl = getSupportFragmentManager().beginTransaction();
        ftl.replace(R.id.frame_layout, lose);
        ftl.commit();
    }

    protected void setWinFragment() {
        Win win = new Win();
        FragmentTransaction ftw = getSupportFragmentManager().beginTransaction();
        ftw.replace(R.id.frame_layout, win);
        ftw.commit();
    }

    // Проверка таблицы на наличие пустых ячеек
    protected boolean hasEmptyCells() {
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table[i].length; j++) {
                if (this.table[i][j] == 0) {
                    return true; // Найдена пустая ячейка
                }
            }
        }
        return false; // В таблице нет пустых ячеек
    }

    // Проверка на выигрыш
    protected boolean hasWin2048() {
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table[i].length; j++) {
                if (this.table[i][j] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    // Проверка на проигрыш
    protected boolean hasLost2048() {
        if (hasEmptyCells()) return false;
        // Проверяем, есть ли возможность создать пары одинаковых значений
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table[i].length; j++) {
                // Проверяем ячейки справа и снизу от текущей
                if (j < this.table.length - 1 && table[i][j] == table[i][j + 1]) {
                    return false; // Есть пары одинаковых значений, игрок не проиграл
                }
                if (i < this.table.length - 1 && table[i][j] == table[i + 1][j]) {
                    return false; // Есть пары одинаковых значений, игрок не проиграл
                }
            }
        }
        // Если вы дошли до этой точки, значит, на поле нет места для новых плиток и нет возможности создать пары одинаковых значений
        return true; // Игрок проиграл
    }

    // Метод для того, чтобы вывести таблицу на активность
    protected void populateTable(int[][] array) {
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
        if (array[0][0] != 0) textView0_0.setText(String.valueOf(array[0][0]));
        else textView0_0.setText("");
        if (array[0][1] != 0) textView0_1.setText(String.valueOf(array[0][1]));
        else textView0_1.setText("");
        if (array[0][2] != 0) textView0_2.setText(String.valueOf(array[0][2]));
        else textView0_2.setText("");
        if (array[0][3] != 0) textView0_3.setText(String.valueOf(array[0][3]));
        else textView0_3.setText("");
        if (array[1][0] != 0) textView1_0.setText(String.valueOf(array[1][0]));
        else textView1_0.setText("");
        if (array[1][1] != 0) textView1_1.setText(String.valueOf(array[1][1]));
        else textView1_1.setText("");
        if (array[1][2] != 0) textView1_2.setText(String.valueOf(array[1][2]));
        else textView1_2.setText("");
        if (array[1][3] != 0) textView1_3.setText(String.valueOf(array[1][3]));
        else textView1_3.setText("");
        if (array[2][0] != 0) textView2_0.setText(String.valueOf(array[2][0]));
        else textView2_0.setText("");
        if (array[2][1] != 0) textView2_1.setText(String.valueOf(array[2][1]));
        else textView2_1.setText("");
        if (array[2][2] != 0) textView2_2.setText(String.valueOf(array[2][2]));
        else textView2_2.setText("");
        if (array[2][3] != 0) textView2_3.setText(String.valueOf(array[2][3]));
        else textView2_3.setText("");
        if (array[3][0] != 0) textView3_0.setText(String.valueOf(array[3][0]));
        else textView3_0.setText("");
        if (array[3][1] != 0) textView3_1.setText(String.valueOf(array[3][1]));
        else textView3_1.setText("");
        if (array[3][2] != 0) textView3_2.setText(String.valueOf(array[3][2]));
        else textView3_2.setText("");
        if (array[3][3] != 0) textView3_3.setText(String.valueOf(array[3][3]));
        else textView3_3.setText("");
    }

    // Метод для того, чтобы в случайном месте ставилась 2 или 4
    protected void random_location() {
        int [] random_number = {2, 2, 2, 2, 2, 2, 2, 4};
        if (hasEmptyCells()) {
            List<int[]> emptyCells = new ArrayList<>();
            // Находим все незаполненные ячейки и добавляем их в список
            for (int i = 0; i < this.table.length; i++) {
                for (int j = 0; j < this.table[i].length; j++) {
                    if (this.table[i][j] == 0) {
                        emptyCells.add(new int[]{i, j});
                    }
                }
            }
            Random random = new Random();
            // Выбираем случайную ячейку из списка незаполненных ячеек
            int[] randomCellIndices = emptyCells.get(random.nextInt(emptyCells.size()));
            int randomRow = randomCellIndices[0];
            int randomCol = randomCellIndices[1];
            this.table[randomRow][randomCol] = random_number[random.nextInt(random_number.length)];
        }
    }

    // Метод для того, чтобы менять таблицу при свайпе вверх
    protected void onSwipeUp() {
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 1; j < this.table[i].length; j++) {
                if (this.table[j][i] == 0) {
                    // Пропускаем пустые ячейки
                    continue;
                }
                int k = j;
                while (k > 0 && (this.table[k - 1][i] == 0 || this.table[k - 1][i] == this.table[j][i])) {
                    k--;
                }
                if (this.table[k][i] == 0 || this.table[k][i] == this.table[j][i] && k != j) {
                    this.table[k][i] += this.table[j][i];
                    this.table[j][i] = 0;
                }
            }
        }
    }

    // Метод для того, чтобы менять таблицу при свайпе вниз
    protected void onSwipeDown() {
        for (int i = 0; i < this.table.length; i++) {
            for (int j = this.table[i].length - 2; j >= 0; j--) {
                if (this.table[j][i] == 0) {
                    // Пропускаем пустые ячейки
                    continue;
                }
                int k = j;
                while (k + 1 < this.table.length && (this.table[k + 1][i] == 0 || this.table[k + 1][i] == this.table[j][i])) {
                    k++;
                }
                if (this.table[k][i] == 0 || this.table[k][i] == this.table[j][i] && k != j) {
                    this.table[k][i] += this.table[j][i];
                    this.table[j][i] = 0;
                }
            }
        }
    }

    // Метод для того, чтобы менять таблицу при свайпе вправо
    protected void onSwipeRight() {
        for (int i = 0; i < this.table.length; i++) {
            for (int j = this.table[i].length - 2; j >= 0; j--) {
                if (this.table[i][j] == 0) {
                    // Пропускаем пустые ячейки
                    continue;
                }
                int k = j;
                while (k + 1 < this.table[i].length && (this.table[i][k + 1] == 0 || this.table[i][k + 1] == this.table[i][j])) {
                    k++;
                }
                if (this.table[i][k] == 0 || this.table[i][k] == this.table[i][j] && k != j) {
                    this.table[i][k] += this.table[i][j];
                    this.table[i][j] = 0;
                }
            }
        }
    }

    // Метод для того, чтобы менять таблицу при свайпе влево
    protected void onSwipeLeft() {
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 1; j < this.table[i].length; j++) {
                if (this.table[i][j] == 0) {
                    // Пропускаем пустые ячейки
                    continue;
                }
                int k = j;
                while (k > 0 && (this.table[i][k - 1] == 0 || this.table[i][k - 1] == this.table[i][j])) {
                    k--;
                }
                if (this.table[i][k] == 0 || this.table[i][k] == this.table[i][j] && k != j) {
                    this.table[i][k] += this.table[i][j];
                    this.table[i][j] = 0;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        table = new int[4][4];
        random_location();
        random_location();
        changing_the_score();
        populateTable(table);
        gd = new GestureDetectorCompat(this, this);
        button_go_menu = findViewById(R.id.button_go_menu);
        swipe_player = MediaPlayer.create(this, R.raw.swipe);
        win_player = MediaPlayer.create(this, R.raw.win);
        lose_player = MediaPlayer.create(this, R.raw.lose);
        button_go_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Play.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gd.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public boolean onDown(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        if (hasLost2048()) {
            setLoseFragment();
            lose_playback();
        } else if (hasWin2048()) {
            setWinFragment();
            win_playback();
        } else {
            int[][] copied_table = new int[this.table.length][];
            for (int i = 0; i < this.table.length; i++) {
                copied_table[i] = this.table[i].clone();
            }
            if (Math.abs(velocityX) > Math.abs(velocityY)) {
                if (velocityX > 0) {
                    // Свайп вправо
                    onSwipeRight();
                } else {
                    // Свайп влево
                    onSwipeLeft();
                }
            } else {
                if (velocityY > 0) {
                    // Свайп вниз
                    onSwipeDown();
                } else {
                    // Свайп вверх
                    onSwipeUp();
                }
            }
            if (!Arrays.deepEquals(copied_table, this.table)) {
                random_location();
                populateTable(this.table);
                changing_the_score();
                swipe_playback();
                if (hasLost2048()) {
                    setLoseFragment();
                    lose_playback();
                } else if (hasWin2048()) {
                    setWinFragment();
                    win_playback();
                }
            }
        }
        return true;
    }
}