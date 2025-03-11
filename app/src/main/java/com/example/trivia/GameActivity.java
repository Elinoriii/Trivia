package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btna1, btna2, btna3, btna4;
    private TextView tvQuestion;
    private TextView tvQuestionNumber, tvPoints, tvGameOver;
    private Collection collection;
    private Question q;
    private int points = 0;
    private LinearLayout li;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        li = findViewById(R.id.activity_game);

        Intent i = getIntent();
        String backgrouncolor = i.getStringExtra("color");
        setBackgroundColor(backgrouncolor);

        tvQuestion = findViewById(R.id.tvQuestion);
        btna1 = findViewById(R.id.btna1);
        btna2 = findViewById(R.id.btna2);
        btna3 = findViewById(R.id.btna3);
        btna4 = findViewById(R.id.btna4);

        btna1.setOnClickListener(this);
        btna2.setOnClickListener(this);
        btna3.setOnClickListener(this);
        btna4.setOnClickListener(this);

        tvPoints = findViewById(R.id.tvPoints);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvGameOver = findViewById(R.id.tvGameOver);

        tvGameOver.setVisibility(View.INVISIBLE);

        collection = new Collection();
        collection.initQuestion();

        nextQuestion();
    }

    private void nextQuestion() {
        if (collection.isNotLastQuestion())
        {
            q = collection.getNextQuestion();

            tvQuestion.setText(q.getQuestion());
            btna1.setText(q.getA1());
            btna2.setText(q.getA2());
            btna3.setText(q.getA3());
            btna4.setText(q.getA4());
        }
        else
        {
           tvGameOver.setVisibility(View.VISIBLE);
           creareDialog();
        }
        //הפעולה בודקת האם השאלה הנוכחית האם השאלה הנוכחית היא השאלה האחרונה.
        //אם לא - ממשיכים הלאה
        // אם כן - ה - TextView שלנו הופך לנראה והדיאלוג מופיע

    }

    private void creareDialog()
    {
        CustomDialog customDialog = new CustomDialog(this);
        customDialog.show();
    }
    //הפעולה יוצרת עצם מסוג - CustomDialog ומקבלת - Context (הפנייה - כתובת ל - GameActivity)

    @Override
    public void onClick(View v) {

        if (v == btna1)
        {
            if (q.getCorrect() == 1)
            {
                points++;
            }
        }
        if (v == btna2)
        {
            if (q.getCorrect() == 2)
            {
                points++;
            }
        }
        if (v == btna3)
        {
            if (q.getCorrect() == 3)
            {
                points++;
            }
        }
        if (v == btna4)
        {
            if (q.getCorrect() == 4)
            {
                points++;
            }
        }
        tvPoints.setText("points: " + points);
        if (collection.isNotLastQuestion())
        {
            tvQuestionNumber.setText("Question number: " + (collection.getIndex() + 1));
        }
        nextQuestion();
    }
    //הפעולה מקבלת V, ובודקת על איזה כפתור המשתמש לחץ, פונים לעצם - qת בודקים האם זה נכון מה שנלחץ
    //אם כן הנקודות עולות
    //אם לא עוברים אל השאלה הבאה
    //בכל סיבוב של שאלה מעדכנים את הנקודות לפי הצורך, ובודקים לאחר כל שאלה האם זוהי השאלה האחרונה, ואם לא אז ממשיכים

    public void reset()
    {
        this.points = 0;
        collection.initQuestion();
        tvPoints.setText("Points: " + 0);
        tvQuestionNumber.setText("Question number: " + 1);
        tvGameOver.setVisibility(View.INVISIBLE);
        this.nextQuestion();
    }

    public void setBackgroundColor(String backgroundColor)
    {
        switch (backgroundColor)
        {
            case "Red":
            {
                li.setBackgroundColor(Color.RED);
                break;
            }
            case "Blue":
            {
                li.setBackgroundColor(Color.BLUE);
                break;
            }
            case "Pink":
            {
                li.setBackgroundColor(Color.argb(255,255,192,203));
                break;
            }
            case "Yellow":
            {
                li.setBackgroundColor(Color.YELLOW);
                break;
            }
            default:
                li.setBackgroundColor(Color.WHITE);
        }
    }
    //הפעולה מאפסת את כל נתוני המשחק - נקודות, שאלה וכל השאר
}