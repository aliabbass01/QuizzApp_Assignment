package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuestionsActivity extends AppCompatActivity
{
    private TextView numberOfQuestion;
    private TextView countDownTimerTextView;
    private TextView question;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private Button goToNextQuestion;
    private List<Question> questionList;
    private int totalQuestions;
    private int questionCounter;
    private Question currentQuestion;
    private int score = 0;
    private String name;
    private static final long COUNTDOWN = 10000;
    private CountDownTimer countDownTimer;
    private long timeLeft;
    private ColorStateList colorStateList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        numberOfQuestion = (TextView) findViewById(R.id.question_number);
        countDownTimerTextView = (TextView) findViewById(R.id.question_timer);
        question = (TextView) findViewById(R.id.question);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioButton1 = (RadioButton) findViewById(R.id.option_1);
        radioButton2 = (RadioButton) findViewById(R.id.option_2);
        radioButton3 = (RadioButton) findViewById(R.id.option_3);
        radioButton4 = (RadioButton) findViewById(R.id.option_4);
        goToNextQuestion = (Button) findViewById(R.id.goto_next_question);

        name = getIntent().getExtras().getString("name");
        totalQuestions = Integer.parseInt(getIntent().getExtras().getString("count"));

        questionList = new ArrayList<>();

        currentQuestion = new Question();

        FillArrayWithQuestions();

        Collections.shuffle(questionList);

        ShowNextQuestion();

        goToNextQuestion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (radioButton1.isChecked() || radioButton2.isChecked() || radioButton3.isChecked() || radioButton4.isChecked())
                {
                    VerifyAnswer();
                    ShowNextQuestion();
                }
                else
                {
                    Toast.makeText(QuestionsActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void FillArrayWithQuestions()
    {
        String q1 = "Android Is Developed By";
        String q1_op_1 = "Apple";
        String q1_op_2 = "Google";
        String q1_op_3 = "Microsoft";
        String q1_op_4 = "Android Inc";
        String q1_ans = "Android Inc";

        Question question1 = new Question(q1, q1_op_1, q1_op_2, q1_op_3, q1_op_4, q1_ans);
        questionList.add(question1);

        String q2 = "Android Web Browser Is Based On";
        String q2_op_1 = "Chrome";
        String q2_op_2 = "Open-source Webkit";
        String q2_op_3 = "Safari";
        String q2_op_4 = "Firefox";
        String q2_ans = "Open-source Webkit";

        Question question2 = new Question(q2, q2_op_1, q2_op_2, q2_op_3, q2_op_4, q2_ans);
        questionList.add(question2);

        String q3 = "What Does AAPT Stands For?";
        String q3_op_1 = "Android Asset Processing Tool";
        String q3_op_2 = "Android Asset Providing Tool";
        String q3_op_3 = "Android Asset Packaging Tool";
        String q3_op_4 = "Android Asset Packaging Technique";
        String q3_ans = "Android Asset Packaging Tool";

        Question question3 = new Question(q3, q3_op_1, q3_op_2, q3_op_3, q3_op_4, q3_ans);
        questionList.add(question3);

        String q4 = "Android Is Based On Which Kernal";
        String q4_op_1 = "Linux";
        String q4_op_2 = "Windows";
        String q4_op_3 = "Mac";
        String q4_op_4 = "RedHat";
        String q4_ans = "Linux";

        Question question4 = new Question(q4, q4_op_1, q4_op_2, q4_op_3, q4_op_4, q4_ans);
        questionList.add(question4);

        String q5 = "ADB stands for";
        String q5_op_1 = "Android Debug Bridge";
        String q5_op_2 = "Android Driver Bridge";
        String q5_op_3 = "Android Delete Bridge";
        String q5_op_4 = "Android Destroy Bridge";
        String q5_ans = "Android Debug Bridge";

        Question question5 = new Question(q5, q5_op_1, q5_op_2, q5_op_3, q5_op_4, q5_ans);
        questionList.add(question5);

        String q6 = "What is log message in android?";
        String q6_op_1 = "Same as printf()";
        String q6_op_2 = "Log message is used to debug a program";
        String q6_op_3 = "Same as Toast().";
        String q6_op_4 = "None of these";
        String q6_ans = "Log message is used to debug a program";

        Question question6 = new Question(q6, q6_op_1, q6_op_2, q6_op_3, q6_op_4, q6_ans);
        questionList.add(question6);

        String q7 = "Android is";
        String q7_op_1 = "Web server";
        String q7_op_2 = "Web client";
        String q7_op_3 = "Operating system";
        String q7_op_4 = "None od these";
        String q7_ans = "Operating system";

        Question question7 = new Question(q7, q7_op_1, q7_op_2, q7_op_3, q7_op_4, q7_ans);
        questionList.add(question7);

        String q8 = "OHA stands for";
        String q8_op_1 = "Open Handset Alliance";
        String q8_op_2 = "Open Handset Application";
        String q8_op_3 = "Open Handset Association";
        String q8_op_4 = "Open Handset Animation";
        String q8_ans = "Open Handset Alliance";

        Question question8 = new Question(q8, q8_op_1, q8_op_2, q8_op_3, q8_op_4, q8_ans);
        questionList.add(question8);

        String q9 = "Parent class of Activity?";
        String q9_op_1 = "Object";
        String q9_op_2 = "Context";
        String q9_op_3 = "ActivityGroup";
        String q9_op_4 = "ActivityThemeWrapper";
        String q9_ans = "ActivityThemeWrapper";

        Question question9 = new Question(q9, q9_op_1, q9_op_2, q9_op_3, q9_op_4, q9_ans);
        questionList.add(question9);

        String q10 = "Which configuration file holds the permission to use the internet?";
        String q10_op_1 = "Layout file";
        String q10_op_2 = "Property file";
        String q10_op_3 = "Manifest file";
        String q10_op_4 = "Java source file";
        String q10_ans = "Manifest file";

        Question question10 = new Question(q10, q10_op_1, q10_op_2, q10_op_3, q10_op_4, q10_ans);
        questionList.add(question10);
    }

    private void VerifyAnswer()
    {
        countDownTimer.cancel();
        RadioButton selected = findViewById(radioGroup.getCheckedRadioButtonId());

        String ans = selected.getText().toString();
        if (ans == currentQuestion.getAnswerNum())
        {
            score++;
        }
    }

    private void ShowNextQuestion()
    {
        radioGroup.clearCheck();
        if (questionCounter < totalQuestions)
        {
            currentQuestion = questionList.get(questionCounter);
            question.setText(currentQuestion.getQuestion());
            radioButton1.setText(currentQuestion.getOption1());
            radioButton2.setText(currentQuestion.getOption2());
            radioButton3.setText(currentQuestion.getOption3());
            radioButton4.setText(currentQuestion.getOption4());

            questionCounter++;
            numberOfQuestion.setText("Question" + questionCounter + "/" + totalQuestions);

            timeLeft = COUNTDOWN;
            StartDownTimer();
        }
        else
        {
            goToNextQuestion.setText("Finish");
            Intent resultIntent = new Intent(QuestionsActivity.this, ResultActivity.class);
            resultIntent.putExtra("score", ""+score);
            resultIntent.putExtra("name", name);
            startActivity(resultIntent);
            this.finish();
        }
    }

    private void StartDownTimer()
    {
        countDownTimer = new CountDownTimer(timeLeft, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timeLeft = millisUntilFinished;
                UpdateCountDownTimerText();
            }

            @Override
            public void onFinish() {
                timeLeft = 0;
                UpdateCountDownTimerText();
                ShowNextQuestion();
            }
        }.start();
    }

    private void UpdateCountDownTimerText()
    {
        int minutes = (int) (timeLeft / 1000) / 60;
        int seconds = (int) (timeLeft / 1000) % 60;

        String timeFormat = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countDownTimerTextView.setText(timeFormat);

        if (timeLeft < 6000)
        {
            countDownTimerTextView.setTextColor(Color.RED);
        }
        else
        {
            countDownTimerTextView.setTextColor(Color.BLACK);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
        }
    }
}