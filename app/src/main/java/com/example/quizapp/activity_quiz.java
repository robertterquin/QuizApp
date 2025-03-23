package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class activity_quiz extends AppCompatActivity {

    TextView questionTextView;
    RadioGroup optionsGroup;
    RadioButton option1, option2, option3, option4;
    Button nextButton, backButton;

    ArrayList<Questions> questions;
    int currentQuestionIndex = 0;
    int score = 0;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        category = getIntent().getStringExtra("category");

        TextView categoryTextView = findViewById(R.id.categoryTextView);
        categoryTextView.setText(category);

        questionTextView = findViewById(R.id.questionTextView);
        optionsGroup = findViewById(R.id.optionsGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);

        // Load questions based on category
        questions = getQuestionsByCategory(category);

        displayQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAnswerSelected()) {
                    Toast.makeText(activity_quiz.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    return; // Stop execution if no answer is selected
                }

                checkAnswer(); // Check the selected answer

                if (currentQuestionIndex < questions.size() - 1) {
                    currentQuestionIndex++;
                    displayQuestion();
                } else {
                    // ✅ Redirect to results page after last question
                    Intent intent = new Intent(activity_quiz.this, activity_result.class);
                    intent.putExtra("score", score);
                    intent.putExtra("total", questions.size());
                    startActivity(intent);
                    finish();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    displayQuestion();
                }
            }
        });
    }

    private boolean isAnswerSelected() {
        return optionsGroup.getCheckedRadioButtonId() != -1; // Checks if any radio button is selected
    }

    private ArrayList<Questions> getQuestionsByCategory(String category) {
        ArrayList<Questions> questionList = new ArrayList<>();

        if (category.equals("History")) {
            questionList.add(new Questions("Who was the first President of the Philippines?",
                    new ArrayList<String>() {{ add("Emilio Aguinaldo"); add("Manuel L. Quezon"); add("Jose Rizal"); add("Andres Bonifacio"); }}, 0));

            questionList.add(new Questions("When did the Philippines gain independence from Spain?",
                    new ArrayList<String>() {{ add("1896"); add("1898"); add("1945"); add("1901"); }}, 1));

            questionList.add(new Questions("Which civilization built the Banaue Rice Terraces?",
                    new ArrayList<String>() {{ add("Igorots"); add("Tagalogs"); add("Visayans"); add("Aetas"); }}, 0));

            questionList.add(new Questions("What year did the Cry of Pugad Lawin happen?",
                    new ArrayList<String>() {{ add("1896"); add("1898"); add("1901"); add("1872"); }}, 0));

            questionList.add(new Questions("Who is known as the Father of the Philippine Revolution?",
                    new ArrayList<String>() {{ add("Andres Bonifacio"); add("Jose Rizal"); add("Emilio Aguinaldo"); add("Antonio Luna"); }}, 0));
        }

        if (category.equals("Science")) {
            questionList.add(new Questions("What is the chemical symbol for gold?",
                    new ArrayList<String>() {{ add("Au"); add("Ag"); add("Fe"); add("Cu"); }}, 0));

            questionList.add(new Questions("What is the powerhouse of the cell?",
                    new ArrayList<String>() {{ add("Nucleus"); add("Mitochondria"); add("Ribosome"); add("Endoplasmic Reticulum"); }}, 1));

            questionList.add(new Questions("What is the largest organ in the human body?",
                    new ArrayList<String>() {{ add("Liver"); add("Heart"); add("Skin"); add("Lungs"); }}, 2));

            questionList.add(new Questions("What is the hardest natural substance on Earth?",
                    new ArrayList<String>() {{ add("Gold"); add("Diamond"); add("Iron"); add("Platinum"); }}, 1));

            questionList.add(new Questions("What is the chemical symbol for water?",
                    new ArrayList<String>() {{ add("H2O"); add("CO2"); add("O2"); add("NaCl"); }}, 0));
        }

        if (category.equals("Sports")) {
            questionList.add(new Questions("Which country won the first FIFA World Cup in 1930?",
                    new ArrayList<String>() {{ add("Brazil"); add("Germany"); add("Uruguay"); add("Argentina"); }}, 2));

            questionList.add(new Questions("Which sport is played at Wimbledon?",
                    new ArrayList<String>() {{ add("Tennis"); add("Golf"); add("Cricket"); add("Soccer"); }}, 0));

            questionList.add(new Questions("Which country has won the most Olympic gold medals?",
                    new ArrayList<String>() {{ add("USA"); add("China"); add("Russia"); add("Germany"); }}, 0));

            questionList.add(new Questions("Which sport is known as the King of Sports?",
                    new ArrayList<String>() {{ add("Soccer"); add("Basketball"); add("Cricket"); add("Golf"); }}, 0));

            questionList.add(new Questions("Which country won the most recent FIFA World Cup in 2018?",
                    new ArrayList<String>() {{ add("Brazil"); add("Germany"); add("France"); add("Spain"); }}, 2));
        }

        if (category.equals("Geography")) {
            questionList.add(new Questions("What is the capital of France?",
                    new ArrayList<String>() {{ add("Berlin"); add("London"); add("Paris"); add("Madrid"); }}, 2));

            questionList.add(new Questions("What is the largest continent by land area?",
                    new ArrayList<String>() {{ add("Africa"); add("Asia"); add("Europe"); add("North America"); }}, 1));

            questionList.add(new Questions("What is the longest river in the world?",
                    new ArrayList<String>() {{ add("Amazon River"); add("Nile River"); add("Yangtze River"); add("Mississippi River"); }}, 1));

            questionList.add(new Questions("What is the smallest country in the world?",
                    new ArrayList<String>() {{ add("Monaco"); add("Vatican City"); add("San Marino"); add("Liechtenstein"); }}, 1));

            questionList.add(new Questions("What is the largest ocean on Earth?",
                    new ArrayList<String>() {{ add("Atlantic Ocean"); add("Indian Ocean"); add("Arctic Ocean"); add("Pacific Ocean"); }}, 3));
        }

        if (category.equals("Music")) {
            questionList.add(new Questions("Who is known as the King of Pop?",
                    new ArrayList<String>() {{ add("Michael Jackson"); add("Elvis Presley"); add("Prince"); add("Madonna"); }}, 0));

            questionList.add(new Questions("Which band is known for the song 'Bohemian Rhapsody'?",
                    new ArrayList<String>() {{ add("The Beatles"); add("Queen"); add("Led Zeppelin"); add("Pink Floyd"); }}, 1));

            questionList.add(new Questions("Which artist is known for the song 'Hello'?",
                    new ArrayList<String>() {{ add("Adele"); add("Taylor Swift"); add("Beyonce"); add("Rihanna"); }}, 0));

            questionList.add(new Questions("Which genre of music originated in Jamaica?",
                    new ArrayList<String>() {{ add("Reggae"); add("Salsa"); add("Samba"); add("Tango"); }}, 0));

            questionList.add(new Questions("Which artist is known as the Queen of Soul?",
                    new ArrayList<String>() {{ add("Whitney Houston"); add("Aretha Franklin"); add("Diana Ross"); add("Tina Turner"); }}, 1));
        }

        if (category.equals("Math")) {
            questionList.add(new Questions("What is the value of pi (π)?",
                    new ArrayList<String>() {{ add("3.14"); add("2.71"); add("1.62"); add("1.41"); }}, 0));

            questionList.add(new Questions("What is the square root of 144?",
                    new ArrayList<String>() {{ add("10"); add("12"); add("14"); add("16"); }}, 1));

            questionList.add(new Questions("What is the sum of the interior angles of a triangle?",
                    new ArrayList<String>() {{ add("90 degrees"); add("180 degrees"); add("270 degrees"); add("360 degrees"); }}, 1));

            questionList.add(new Questions("What is the value of 5! (5 factorial)?",
                    new ArrayList<String>() {{ add("10"); add("20"); add("30"); add("120"); }}, 3));

            questionList.add(new Questions("What is the value of log10(100)?",
                    new ArrayList<String>() {{ add("1"); add("2"); add("10"); add("100"); }}, 1));
        }

        if (category.equals("Literature")) {
            questionList.add(new Questions("Who wrote 'Romeo and Juliet'?",
                    new ArrayList<String>() {{ add("William Shakespeare"); add("Charles Dickens"); add("Jane Austen"); add("Mark Twain"); }}, 0));

            questionList.add(new Questions("What is the first book in the Harry Potter series?",
                    new ArrayList<String>() {{ add("Harry Potter and the Chamber of Secrets"); add("Harry Potter and the Goblet of Fire"); add("Harry Potter and the Philosopher's Stone"); add("Harry Potter and the Prisoner of Azkaban"); }}, 2));

            questionList.add(new Questions("Who wrote 'To Kill a Mockingbird'?",
                    new ArrayList<String>() {{ add("Harper Lee"); add("F. Scott Fitzgerald"); add("Ernest Hemingway"); add("John Steinbeck"); }}, 0));

            questionList.add(new Questions("What is the setting of 'The Great Gatsby'?",
                    new ArrayList<String>() {{ add("New York City"); add("Los Angeles"); add("Chicago"); add("San Francisco"); }}, 0));

            questionList.add(new Questions("Who wrote 'Pride and Prejudice'?",
                    new ArrayList<String>() {{ add("Jane Austen"); add("Charlotte Bronte"); add("Emily Bronte"); add("Louisa May Alcott"); }}, 0));
        }

        if (category.equals("Technology")) {
            questionList.add(new Questions("Who is the founder of Microsoft?",
                    new ArrayList<String>() {{ add("Steve Jobs"); add("Bill Gates"); add("Mark Zuckerberg"); add("Jeff Bezos"); }}, 1));

            questionList.add(new Questions("What does CPU stand for?",
                    new ArrayList<String>() {{ add("Central Processing Unit"); add("Computer Processing Unit"); add("Central Processor Unit"); add("Computer Processor Unit"); }}, 0));

            questionList.add(new Questions("What is the most popular programming language in 2021?",
                    new ArrayList<String>() {{ add("Java"); add("Python"); add("C++"); add("JavaScript"); }}, 1));

            questionList.add(new Questions("What is the largest tech company in the world?",
                    new ArrayList<String>() {{ add("Apple"); add("Microsoft"); add("Amazon"); add("Google"); }}, 3));

            questionList.add(new Questions("What is the most visited website in the world?",
                    new ArrayList<String>() {{ add("Google"); add("YouTube"); add("Facebook"); add("Amazon"); }}, 0));
        }

        if (category.equals("Art")) {
            questionList.add(new Questions("Who painted the Mona Lisa?",
                    new ArrayList<String>() {{ add("Vincent van Gogh"); add("Leonardo da Vinci"); add("Pablo Picasso"); add("Michelangelo"); }}, 1));

            questionList.add(new Questions("Which artist is known for the painting 'The Starry Night'?",
                    new ArrayList<String>() {{ add("Claude Monet"); add("Edvard Munch"); add("Salvador Dali"); add("Vincent van Gogh"); }}, 3));

            questionList.add(new Questions("What is the art of making images with an assemblage of small pieces of colored glass, stone, or other materials?",
                    new ArrayList<String>() {{ add("Sculpture"); add("Mosaic"); add("Collage"); add("Pottery"); }}, 1));

            questionList.add(new Questions("Who sculpted the statue of David?",
                    new ArrayList<String>() {{ add("Leonardo da Vinci"); add("Michelangelo"); add("Donatello"); add("Raphael"); }}, 1));

            questionList.add(new Questions("Which artist is known for the painting 'Guernica'?",
                    new ArrayList<String>() {{ add("Pablo Picasso"); add("Salvador Dali"); add("Vincent van Gogh"); add("Claude Monet"); }}, 0));
        }

        if (category.equals("Animals")) {
            questionList.add(new Questions("What is the largest mammal in the world?",
                    new ArrayList<String>() {{ add("Elephant"); add("Blue Whale"); add("Giraffe"); add("Hippopotamus"); }}, 1));

            questionList.add(new Questions("What is the fastest land animal?",
                    new ArrayList<String>() {{ add("Cheetah"); add("Lion"); add("Leopard"); add("Tiger"); }}, 0));

            questionList.add(new Questions("What is the largest species of bear?",
                    new ArrayList<String>() {{ add("Polar Bear"); add("Grizzly Bear"); add("Black Bear"); add("Kodiak Bear"); }}, 3));

            questionList.add(new Questions("What is the national bird of the United States?",
                    new ArrayList<String>() {{ add("Bald Eagle"); add("Golden Eagle"); add("Hawk"); add("Falcon"); }}, 0));

            questionList.add(new Questions("What is the largest species of shark?",
                    new ArrayList<String>() {{ add("Great White Shark"); add("Whale Shark"); add("Tiger Shark"); add("Hammerhead Shark"); }}, 1));
        }

        if (category.equals("Food")) {
            questionList.add(new Questions("What is the national dish of Spain?",
                    new ArrayList<String>() {{ add("Sushi"); add("Pizza"); add("Paella"); add("Pasta"); }}, 2));

            questionList.add(new Questions("What is the main ingredient in guacamole?",
                    new ArrayList<String>() {{ add("Tomato"); add("Avocado"); add("Onion"); add("Garlic"); }}, 1));

            questionList.add(new Questions("What is the most consumed fruit in the world?",
                    new ArrayList<String>() {{ add("Banana"); add("Apple"); add("Orange"); add("Grapes"); }}, 0));

            questionList.add(new Questions("What is the national dish of Japan?",
                    new ArrayList<String>() {{ add("Sushi"); add("Ramen"); add("Udon"); add("Tempura"); }}, 0));

            questionList.add(new Questions("What is the main ingredient in hummus?",
                    new ArrayList<String>() {{ add("Chickpeas"); add("Lentils"); add("Black Beans"); add("Kidney Beans"); }}, 0));
        }

        if (category.equals("Movies")) {
            questionList.add(new Questions("Who directed the movie 'Inception'?",
                    new ArrayList<String>() {{ add("Christopher Nolan"); add("Steven Spielberg"); add("James Cameron"); add("Quentin Tarantino"); }}, 0));

            questionList.add(new Questions("Which movie won the Best Picture Oscar in 2020?",
                    new ArrayList<String>() {{ add("1917"); add("Joker"); add("Parasite"); add("Once Upon a Time in Hollywood"); }}, 2));

            questionList.add(new Questions("Who played the character of Jack in 'Titanic'?",
                    new ArrayList<String>() {{ add("Leonardo DiCaprio"); add("Brad Pitt"); add("Johnny Depp"); add("Tom Cruise"); }}, 0));

            questionList.add(new Questions("What is the highest-grossing film of all time?",
                    new ArrayList<String>() {{ add("Avatar"); add("Avengers: Endgame"); add("Titanic"); add("Star Wars: The Force Awakens"); }}, 1));

            questionList.add(new Questions("Which movie features the song 'My Heart Will Go On'?",
                    new ArrayList<String>() {{ add("The Bodyguard"); add("Titanic"); add("Moulin Rouge!"); add("The Lion King"); }}, 1));
        }

        return questionList;
    }

    private void displayQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Questions currentQuestion = questions.get(currentQuestionIndex);
            questionTextView.setText(currentQuestion.getQuestions());

            ArrayList<String> options = currentQuestion.getOptions();
            option1.setText(options.get(0));
            option2.setText(options.get(1));
            option3.setText(options.get(2));
            option4.setText(options.get(3));

            optionsGroup.clearCheck();
        }
    }

    private void checkAnswer() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            int selectedIndex = optionsGroup.indexOfChild(selectedRadioButton);

            if (selectedIndex == questions.get(currentQuestionIndex).getCorrectAnswerIndex()) {
                score++;
            }

        }
    }
}
