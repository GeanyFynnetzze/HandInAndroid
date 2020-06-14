package com.example.handinv1.Quiz;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModelData extends AndroidViewModel {

    private Repository repository;

    private LiveData<List<Questions>> allQuestions;

    public ViewModelData(Application application) {
        super(application);

        repository = new Repository(application);
        allQuestions = repository.getAllQuestions();

    }

    public LiveData<List<Questions>> getAllQuestions() {
        return allQuestions;
    }
}


