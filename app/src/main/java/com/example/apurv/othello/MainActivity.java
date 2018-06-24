package com.example.apurv.othello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    LinearLayout rootLayout;
    public ArrayList<LinearLayout> rows;

    public final int Incomplete = 1;
    public final int Lost = 2;
    public final int Won = 3;
    public int status;
    int current=-1;
    public Obutton[][] board;

    public final int size = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout = findViewById(R.id.root);
        setupboard();
    }

    private void setupboard() {

        rows = new ArrayList<>();
        status = 1;
        board = new Obutton[size][size];
        rootLayout.removeAllViews();


        for (int i = 0; i < size; i++) {

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
            linearLayout.setLayoutParams(layoutParams);

            rootLayout.addView(linearLayout);
            rows.add(linearLayout);
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Obutton button = new Obutton(this);
                LinearLayout.LayoutParams layoutParams =
                        new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                button.setLayoutParams(layoutParams);

                button.setOnClickListener(this);
                button.setOnLongClickListener(this);

                button.setCoordinate(i, j);

                LinearLayout row = rows.get(i);
                row.addView(button);
                board[i][j] = button;
            }
        }

        setupfirstdisks();
    }

    private void setupfirstdisks() {
        for (int i=3;i<5;i++)
        {
            for (int j=3;j<5;j++)
            {
                if(i==j)
                {
                    board[i][j].setBackground(getDrawable(R.drawable.black_disk));
                    board[i][j].setValue(-1);
                    board[i][j].setEnabled(false);
                }
                else
                {
                    board[i][j].setBackground(getDrawable(R.drawable.white_disk));
                    board[i][j].setValue(1);
                    board[i][j].setEnabled(false);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        Obutton button=(Obutton) view;
        if(move(current,button.getI(),button.getJ()))
        {
            Toast.makeText(this, "Valid Move", Toast.LENGTH_SHORT).show();
            turn(current);
            toggle();
        }
        else
        {
            Toast.makeText(this, "Not Valid Move", Toast.LENGTH_SHORT).show();
        }


    }

    private void show() {
        for (int i=0;i<=7;i++)
        {
            for (int j=0;j<=7;j++) {
                board[i][j].setText(Integer.toString(board[i][j].getValue()));
            }
        }
    }

    private void toggle() {
        if(current==(-1))
        {
            current=1;
        }
        else
        {
            current=-1;
        }
    }

    private void turn(int current) {

        for (int i=0;i<=7;i++)
        {
            for (int j=0;j<=7;j++)
            {
            int x=board[i][j].getValue();
                if(board[i][j].getValue()==current&&current==-1)
                {
                board[i][j].setBackground(getDrawable(R.drawable.black_disk));
                    board[i][j].setEnabled(false);
                }
                else if(board[i][j].getValue()==current&&current==1)
                {
                    board[i][j].setBackground(getDrawable(R.drawable.white_disk));
                    board[i][j].setEnabled(false);
                }

            }
        }

    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

    public boolean move(int symbol, int i, int j){

        boolean legal=false;



        if (board[i][j].getValue() == 0)
        {

            int posx;
            int posy;
            boolean flag;
            int current;



            for (int x = -1; x <= 1; x++)
            {
                for (int y = -1; y <= 1; y++)
                {

                    posx = j + x;
                    posy = i + y;
                    flag = false;

                    if(posx<0||posx>7||posy<0||posy>7)
                        continue;

                    current = board[posy][posx].getValue();



                    if (current == 0 || current == symbol)
                    {
                        continue;
                    }


                    int k=0;

                    while (!flag)
                    {
                        posx += x;
                        posy += y;
                        if(posx<0||posx>7||posy<0||posy>7)
                            break;

                        current = board[posy][posx].getValue();
                        k++;

                        if (current == symbol)
                        {
                            flag = true;
                            legal = true;



                            posx -= x;
                            posy -= y;
                            current = board[posy][posx].getValue();

                            while(k >= 0)
                            {
                                board[posy][posx].setValue( symbol);
                                posx -= x;
                                posy -= y;
                                k--;
                                if(posx<0||posx>7||posy<0||posy>7)
                                    break;
                                current = board[posy][posx].getValue();
                            }

                        }

                        else if (current == 0)
                        {
                            flag = true;
                        }
                    }
                }
            }
        }

        return legal;

    }
}
