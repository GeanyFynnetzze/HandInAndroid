package com.example.handinv1.Quiz;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.handinv1.R;
import com.example.handinv1.Authentication.Register;

import java.util.Collections;
import java.util.List;

public class Quiz extends AppCompatActivity {

    TextView txtQuestion;
    TextView textViewScore, textViewQuestionCount, textViewCountDownTimer;
    RadioButton rb1, rb2, rb3;
    RadioGroup rbGroup;
    Button buttonNext;
    boolean answerd = false;
    List<Questions> questionsList;
    Questions currentQ;
    private int questionCounter = 0, questionTotalCount = 0;
    private ViewModelData viewModel;
    private ColorStateList colorOfButtons;
    private Handler handler = new Handler();
    private int correctAns = 0, wrongAns = 0;
    private int score = 0;
    private ImageView imageView;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        startUserInterface();

        //viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        ViewModelData viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ViewModelData.class);
        viewModel.getAllQuestions().observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(@Nullable List<Questions> questions) {
                Toast.makeText(Quiz.this, "IKUZO", Toast.LENGTH_SHORT).show();

                getContent(questions);
                startAwesomeQuiz();
            }
        });
        Log.i("DATATA", "onCreate() in QuizActivity");
    }

    void startUserInterface() {

        textViewScore = findViewById(R.id.txtScore);
        textViewQuestionCount = findViewById(R.id.txtTotalQuestion);
        txtQuestion = findViewById(R.id.txtQuetsionContainer);
        imageView = findViewById(R.id.imagething);

        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);

        buttonNext = findViewById(R.id.button_Next);
    }

    private void getContent(List<Questions> questions) {
        questionsList = questions;
    }

    private void setQuestionList() {
        rbGroup.clearCheck();
        imageView.setImageResource(R.drawable.ic_questioningright);
        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorAccent));
        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorAccent));
        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorAccent));

        rb1.setTextColor(Color.BLACK);
        rb2.setTextColor(Color.BLACK);
        rb3.setTextColor(Color.BLACK);

        questionTotalCount = questionsList.size();
        Collections.shuffle(questionsList);
        if (questionCounter < questionTotalCount - 1) {
            currentQ = questionsList.get(questionCounter);

            txtQuestion.setText(currentQ.getQuestion());
            rb1.setText(currentQ.getFirstOption());
            rb2.setText(currentQ.getSecondOption());
            rb3.setText(currentQ.getThirdOption());
            questionCounter++;
            answerd = false;
            buttonNext.setText("Next");

            textViewQuestionCount.setText("Questions: " + questionCounter + "/" + (questionTotalCount - 1));

        } else {
            Toast.makeText(this, "Your final score is " + score, Toast.LENGTH_SHORT).show();


            // Intent intent = new Intent(getApplicationContext(), Quiz.class);
            // startActivity(intent);

            rb1.setClickable(false);
            rb2.setClickable(false);
            rb3.setClickable(false);
            buttonNext.setClickable(false);
        }
    }

    private void startAwesomeQuiz() {
        setQuestionList();

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_button1:
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selectedans));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorAccent));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorAccent));
                        break;

                    case R.id.radio_button2:
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selectedans));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorAccent));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorAccent));
                        break;

                    case R.id.radio_button3:
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selectedans));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorAccent));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorAccent));

                }
            }
        });


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answerd) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        performOperation();
                    } else {
                        Toast.makeText(Quiz.this, "SELECT AN OPTION", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void performOperation() {

        answerd = true;


        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());

        int nrOfAnswer = rbGroup.indexOfChild(rbSelected) + 1;


        checkSolution(nrOfAnswer, rbSelected);
    }

    private void checkSolution(int nrOfAnswer, RadioButton rbSelected) {

        switch (currentQ.getAnswer()) {

            case 1:

                if (currentQ.getAnswer() == nrOfAnswer) {

                    rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correctans));
                    rb1.setTextColor(Color.WHITE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionList();
                        }
                    }, 500);

                    correctAns++;
                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));


                } else {
                    changetoIncorrectColor(rbSelected);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionList();
                        }
                    }, 500);
                    wrongAns++;
                }
                break;

            case 2:

                if (currentQ.getAnswer() == nrOfAnswer) {


                    rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correctans));
                    rb2.setTextColor(Color.WHITE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionList();
                        }
                    }, 500);

                    correctAns++;
                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));


                } else {

                    changetoIncorrectColor(rbSelected);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionList();
                        }
                    }, 500);

                    wrongAns++;


                }
                break;


            case 3:

                if (currentQ.getAnswer() == nrOfAnswer) {
                    rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correctans));
                    rb3.setTextColor(Color.WHITE);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionList();
                        }
                    }, 500);

                    correctAns++;


                    score += 10;  // score = score + 10
                    textViewScore.setText("Score: " + String.valueOf(score));


                } else {

                    changetoIncorrectColor(rbSelected);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionList();
                        }
                    }, 500);

                    wrongAns++;
                }
        }
        if (questionCounter == questionTotalCount - 1) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent home = new Intent(Quiz.this, Register.class);
                    startActivity(home);
                }
            }, 5000);

        }

    }

    private void changetoIncorrectColor(RadioButton rbselected) {
        rbselected.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wrongans));
        rbselected.setTextColor(Color.WHITE);
    }


}







