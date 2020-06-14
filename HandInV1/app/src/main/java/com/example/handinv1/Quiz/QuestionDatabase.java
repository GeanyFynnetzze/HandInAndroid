package com.example.handinv1.Quiz;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//
    @Database(entities = {Questions.class}, version = 2)
    public abstract class QuestionDatabase extends RoomDatabase {

        private static QuestionDatabase INSTANCE;

        public abstract QuestionsDao questionDao();

        public static synchronized QuestionDatabase getInstance(final Context context){

            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        QuestionDatabase.class, "questions_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(RoomDBCallback)
                        .build();
            }

            return INSTANCE;

        }

        private static RoomDatabase.Callback RoomDBCallback = new RoomDatabase.Callback(){


            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);


                new PopulateDbAsyncTask(INSTANCE).execute();
            }
        };

        private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {

            private QuestionsDao questionDao;


            private PopulateDbAsyncTask(QuestionDatabase db){

                questionDao = db.questionDao();

            }

            @Override
            protected Void doInBackground(Void... voids){

                questionDao.insert(new Questions("What Pokemon does Pikachu evolve into?","Raichu","Jolteon","Meowstic",1));
                questionDao.insert(new Questions("What's the most effective Poke Ball in the game?","Timer Ball","Ultra Ball","Master Ball",3));
                questionDao.insert(new Questions("How many Gym Badges must a trainer collect before challenging the Elite Four?","7","8","9",2));
                questionDao.insert(new Questions("What's the device trainers use to keep a record of their Pokemon encounters?","Pokecounter","Pokephone","PokeDex",3));
                questionDao.insert(new Questions("If you need to revive your fainted Pokemon to full health, where do you go?","Mount Fuji","Pokemon Center","Gym",2));
                questionDao.insert(new Questions("What are the three types of starter Pokemon?","Grass,Fire,Water","Psychic,Fighting,Ghost","Electric,Ground,Poison",1));
                questionDao.insert(new Questions("What type of attacks are Normal type Pokemon immune to?","Ghost","Fire","Dark",1));
                questionDao.insert(new Questions("Which of these is NOT an evolutionary stone?","Fire Stone","Dragon Stone","Thunder Stone",2));
                questionDao.insert(new Questions("What type of Pokemon is Mewtwo?","Dark","Fighting","psychic",3));

                return null;
            }
        }



    }


