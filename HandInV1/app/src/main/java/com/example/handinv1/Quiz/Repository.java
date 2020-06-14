package com.example.handinv1.Quiz;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.handinv1.Quiz.QuestionDatabase;
import com.example.handinv1.Quiz.Questions;
import com.example.handinv1.Quiz.QuestionsDao;

import java.util.List;

public class Repository {


        private QuestionsDao questionsDao;
        private LiveData<List<Questions>> allQuestions;


        public Repository(Application application){
            QuestionDatabase db = QuestionDatabase.getInstance(application);
            questionsDao = db.questionDao();
            allQuestions = questionsDao.getAllQuestions();
        }


        public LiveData<List<Questions>> getAllQuestions(){
            return allQuestions;
        }
    }

