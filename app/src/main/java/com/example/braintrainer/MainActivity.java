package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    Button playButton;
    int locationOfAnswers;
    TextView sumTextView;
    ArrayList<Integer> answers=new ArrayList<>();
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView answerTextView;
    TextView scoreTextView;
    TextView timerTextView;
    int numberOfQuestions=0;int score=0;
    boolean isActive=true;
    ConstraintLayout gameLayout;

    public void onClick(View view){
        playButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    public void playAgain(View view)
    {
        score=0;
        numberOfQuestions=0;
        playAgainButton.setVisibility(View.INVISIBLE);
        isActive=true;
        timerTextView.setText("30s");
        answerTextView.setText("");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        newQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000)+"s");
            }

            @Override
            public void onFinish() {
                answerTextView.setText("TIME UP!");
                answerTextView.setTextColor(Color.RED);
                isActive=false;
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view) {

        if (isActive) {
            answerTextView.setText("");
            if (Integer.toString(locationOfAnswers).equals(view.getTag().toString())) {
                answerTextView.setText("CORRECT!");
                answerTextView.setTextColor(Color.GREEN);
                score++;
            } else {
                answerTextView.setText("WRONG :(");
                answerTextView.setTextColor(Color.RED);
            }
            numberOfQuestions++;
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            newQuestion();
        }
    }

    public void newQuestion()
    {
        Random rand=new Random();

        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        answers.clear();
        sumTextView.setText(Integer.toString(a)+ "+"+Integer.toString(b));
        locationOfAnswers= rand.nextInt(4);
        for(int i=0;i<4;i++)
        {
            if(i == locationOfAnswers)
            {
                answers.add(a+b);
            }
            else
            {
                int wrongAnswer=rand.nextInt(41);
                while(wrongAnswer==a+b)
                {
                    wrongAnswer=rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView=(TextView) findViewById(R.id.sumTextView);
        playButton=(Button) findViewById(R.id.playButton);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        answerTextView=findViewById(R.id.answerTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timerTextView);
        playAgainButton=findViewById(R.id.playAgainButton);
        gameLayout=findViewById(R.id.gameLayout);

        playButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}