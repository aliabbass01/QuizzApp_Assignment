package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    private EditText candidateName;
    private EditText noOfQuestions;
    private Button startQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        candidateName = (EditText) findViewById(R.id.candidate_name);
        noOfQuestions = (EditText) findViewById(R.id.no_of_questions);
        startQuizButton = (Button) findViewById(R.id.start_quiz_button);

        startQuizButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                StartQuiz();
            }
        });
    }

    private void StartQuiz()
    {
        String name = candidateName.getText().toString();
        String questionCount = noOfQuestions.getText().toString();

        if (name.isEmpty())
        {
            candidateName.setError("Please enter your name");
            candidateName.requestFocus();
        }
        else if (questionCount.isEmpty() || Integer.parseInt(questionCount) < 5  || Integer.parseInt(questionCount) > 10)
        {
            noOfQuestions.setError("Enter a number between 5 & 10");
            noOfQuestions.requestFocus();
        }
        else
        {
            Intent questionsIntent = new Intent(MainActivity.this, QuestionsActivity.class);
            questionsIntent.putExtra("name", name);
            questionsIntent.putExtra("count", questionCount);
            startActivity(questionsIntent);
            this.finish();
        }
    }
}