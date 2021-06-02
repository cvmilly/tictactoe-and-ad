package com.example.popupad;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button[][] cells= new Button[3][3];

    private boolean play1=true;
    private boolean adTurn=false;
    private int round;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // create all the buttons on the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "but" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                cells[i][j] = findViewById(resID);
                cells[i][j].setOnClickListener(this);
            }
        }


        final Button reset=findViewById(R.id.playAgain);
        reset.setOnClickListener(view -> {
            for (int i=0; i<3;i++)
            {
                for (int j=0; j<3;j++)
                {
                    cells[i][j].setBackground(getDrawable(R.drawable.blank));
                    cells[i][j].setTag("blank");
                }
            }
            round=0;
            play1=true;
            Button turn=findViewById(R.id.turn);
            turn.setText("Noles Begin");



            // EVERY OTHER TIME USER CLICKS TO PLAY AGAIN AD POPS
            adTurn=!adTurn;
            if(adTurn)
            {
                PopUp popUp=new PopUp();
                popUp.showPopUp(view);
            }
        });



    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View view) {



        if (play1)
        {
            ((Button) view).setBackground(getDrawable(R.drawable.nole));
            view.setTag("nole");

        }
        else
        {
            ((Button)view).setBackground(getDrawable(R.drawable.gator));
            view.setTag("gator");
        }

        Button turn=findViewById(R.id.turn);
        if (play1)
            turn.setText("Gator's Turn");
        else
            turn.setText("Nole's Turn");

        round++;

        if (checkWinner())
        {
            if (play1)
                play1win();
            else
                play2win();
        }else if (round==9)
            draw();
        else
            play1=!play1;
    }

    private boolean checkWinner() {
        String[][] field=new String[3][3];
        int i,j;

        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                field[i][j] = cells[i][j].getTag().toString();
            }
        }

        for (i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("blank")) {
                return true;
            }
        }
        for (i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("blank")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("blank")) {
            return true;
        }
        return field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("blank");
    }

    private void play1win() {
        Toast.makeText(this, "Noles Remain Victorious!",Toast.LENGTH_SHORT).show();
    }
    private void play2win() {
        Toast.makeText(this, "Gators Remain Victorious!", Toast.LENGTH_SHORT).show();
    }
    private void draw() {
        Toast.makeText(this, "DRAW!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void resetBoard()
    {
        for (int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                cells[i][j].setBackground(getDrawable(R.drawable.blank));
                cells[i][j].setTag("blank");
            }
        }
        round=0;
        play1=true;
        Button turn=findViewById(R.id.turn);
        turn.setText("Noles Begin");
    }

}
