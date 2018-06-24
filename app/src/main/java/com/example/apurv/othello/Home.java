package com.example.apurv.othello;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity implements View.OnClickListener{

    EditText et1;
    EditText et2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button=findViewById(R.id.button4);
        et1=findViewById(R.id.editText);
        et2=findViewById(R.id.editText2);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String black_player=et1.getText().toString();
        String white_player=et2.getText().toString();

        if(TextUtils.isEmpty(black_player))
        {
            et1.setError("Fill Name");
        }
        if(TextUtils.isEmpty(white_player))
        {
            et2.setError("Fill Name");
        }

        Intent intent=new Intent(this,MainActivity.class);

        intent.putExtra("black",black_player);
        intent.putExtra("white",white_player);
        startActivity(intent);
    }
}
