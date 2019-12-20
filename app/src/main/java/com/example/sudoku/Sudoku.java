package com.example.sudoku;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.time.Duration;
import java.time.Instant;

public class Sudoku extends AppCompatActivity {
    private int coordinate = 0;
    private Integer[] nums = {1,2,3,4,5,6,7,8,9,0} ;
    private int[][][] boards = {
            {
                    {3, 0, 9, 0, 0, 0, 0, 2, 0},
                    {0, 7, 0, 0, 0, 6, 0, 0, 5},
                    {0, 0, 0, 4, 7, 0, 0, 9, 0},
                    {4, 0, 0, 5, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 8, 7},
                    {6, 0, 0, 1, 0, 0, 0, 0, 0},
                    {0, 3, 0, 6, 8, 0, 4, 0, 9},
                    {5, 0, 0, 0, 3, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 3, 0}
            },
            {
                    {0, 0, 0, 0, 0, 0, 0, 3, 0},
                    {0, 6, 2, 0, 0, 4, 0, 0, 8},
                    {5, 0, 0, 0, 0, 0, 7, 0, 0},
                    {9, 1, 0, 5, 0, 0, 0, 0, 7},
                    {0, 5, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 7, 0, 4, 2, 0, 0, 0},
                    {0, 0, 1, 0, 7, 0, 0, 0, 6},
                    {0, 0, 0, 9, 0, 6, 8, 0, 0},
                    {7, 0, 0, 0, 5, 0, 0, 0, 0}
            },
            {
                    {6, 0, 0, 0, 0, 0, 0, 3, 0},
                    {0, 0, 9, 0, 5, 8, 0, 0, 4},
                    {0, 0, 0, 0, 0, 2, 5, 0, 8},
                    {0, 0, 6, 0, 3, 0, 0, 7, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {4, 0, 0, 0, 0, 1, 2, 0, 0},
                    {0, 0, 0, 0, 6, 0, 0, 0, 5},
                    {0, 0, 8, 0, 0, 0, 0, 0, 0},
                    {0, 9, 7, 5, 2, 0, 0, 0, 0}
            },
            {
                    {0, 0, 0, 0, 0, 8, 3, 1, 0},
                    {0, 0, 2, 3, 0, 6, 0, 5, 0},
                    {3, 0, 8, 0, 0, 0, 0, 7, 6},
                    {1, 5, 9, 8, 0, 0, 2, 6, 7},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {8, 6, 3, 0, 0, 2, 1, 4, 9},
                    {7, 3, 0, 0, 0, 0, 6, 0, 1},
                    {0, 9, 0, 5, 0, 7, 8, 0, 0},
                    {0, 8, 4, 2, 0, 0, 0, 0, 0}
            },
            {
                    {0, 0, 0, 3, 5, 0, 0, 9, 0},
                    {9, 0, 0, 7, 6, 0, 0, 1, 0},
                    {0, 8, 0, 0, 2, 0, 0, 5, 4},
                    {0, 0, 5, 4, 8, 0, 0, 7, 0},
                    {8, 0, 1, 9, 0, 2, 6, 0, 5},
                    {0, 7, 0, 0, 1, 6, 3, 0, 0},
                    {6, 9, 0, 0, 4, 0, 0, 2, 0},
                    {0, 1, 0, 0, 9, 7, 0, 0, 8},
                    {0, 4, 0, 0, 3, 1, 0, 0, 0}
            },
            {
                    {4, 0, 0, 7, 0, 0, 0, 2, 0},
                    {0, 6, 9, 0, 8, 0, 0, 7, 0},
                    {8, 0, 7, 0, 5, 0, 0, 0, 9},
                    {9, 7, 0, 0, 0, 1, 0, 5, 2},
                    {0, 3, 2, 0, 0, 0, 7, 8, 0},
                    {5, 8, 0, 3, 0, 0, 0, 1, 4},
                    {6, 0, 0, 0, 1, 0, 2, 0, 3},
                    {0, 4, 0, 0, 3, 0, 5, 6, 0},
                    {0, 9, 0, 0, 0, 5, 0, 0, 1}
            }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);
        final Board board = new Board(this.boards);
        board.getView();
        ArrayAdapter aas = new ArrayAdapter(this,R.layout.slots,board.ViewBoard);
        final GridView bd = (GridView) findViewById(R.id.board);
        bd.setAdapter(aas);
        bd.setOnItemClickListener(new GridView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                coordinate = position ;
            }
        });
        GridView numbers = (GridView) findViewById(R.id.number);
        ArrayAdapter numaas = new ArrayAdapter(this, R.layout.slots, this.nums) ;
        numbers.setAdapter(numaas);
        final Instant start = Instant.now();
        numbers.setOnItemClickListener(new GridView.OnItemClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                board.Aboard[coordinate] = nums[position] ;
                board.getView();
                ArrayAdapter aas = new ArrayAdapter(Sudoku.this, R.layout.slots,board.ViewBoard);
                bd.setAdapter(aas);
                if( board.isFill() ){
                    Instant end = Instant.now();
                    TextView res = (TextView) findViewById(R.id.Res);
                    if( board.Check() ){
                        long time = Duration.between(start,end).toMillis() ;
                        res.setText("Success , Time:"+ String.valueOf((int)(time/600)) + "分鐘" +  String.valueOf((int)((time%600)/10)) + "秒");
                    }else{
                        res.setText("Not Correct");
                    }
                }
            }
        });

    }
}