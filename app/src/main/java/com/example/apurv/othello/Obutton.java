package com.example.apurv.othello;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;

public class Obutton extends AppCompatButton {
    int value;
    int i;
    int j;

    public Obutton(Context context) {
        super(context);
        setTextColor(Color.BLUE);
        setText(Integer.toString(value));
    }

    public void setValue(int value) {
        this.value = value;
        setText(Integer.toString(value));

    }

    public int getValue() {
        return value;
    }

    public void setCoordinate(int i, int j) {
        this.i=i;
        this.j=j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
