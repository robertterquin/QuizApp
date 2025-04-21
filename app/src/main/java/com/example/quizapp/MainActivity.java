package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> itemList;
    GridView gridView;
    ImageView imageView;


    private boolean isHeartFilled = false;
    private ImageView heartImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = findViewById(R.id.search_logo);
        gridView = findViewById(R.id.gridView);
        itemList = new ArrayList<>();


        itemList.add(new Item(R.drawable.parchment, "History", "Learn about past events."));
        itemList.add(new Item(R.drawable.science, "Science", "Explore the wonders of science."));
        itemList.add(new Item(R.drawable.sports, "Sports", "Test your sports knowledge."));
        itemList.add(new Item(R.drawable.geography, "Geography", "Discover countries and landmarks."));
        itemList.add(new Item(R.drawable.music, "Music", "Guess songs, artists, and genres."));
        itemList.add(new Item(R.drawable.math, "Math", "Solve mathematical problems."));
        itemList.add(new Item(R.drawable.research, "Literature", "Dive into books, and poetry."));
        itemList.add(new Item(R.drawable.digital, "Technology", "Stay updated with new techs."));
        itemList.add(new Item(R.drawable.art, "Art", "Appreciate famous paintings."));
        itemList.add(new Item(R.drawable.animals, "Animals", "Learn about wildlife and species."));
        itemList.add(new Item(R.drawable.food, "Food", "Test your knowledge of cuisines."));
        itemList.add(new Item(R.drawable.movies, "Movies", "Guess films, directors, and actors."));

        CustomAdapter myAdapter = new CustomAdapter(MainActivity.this, itemList);
        gridView.setAdapter(myAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item selectedItem = itemList.get(position);
                openQuizActivity(selectedItem.getTitle());
            }
        });

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        heartImageView = findViewById(R.id.heart_button);
        heartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHeartFilled) {
                    heartImageView.setImageResource(R.drawable.hollow_heart);
                } else {
                    heartImageView.setImageResource(R.drawable.heart);
                }
                isHeartFilled = !isHeartFilled;
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchPopup();
            }
        });
    }


    private void showSearchPopup() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);

        EditText input = dialogView.findViewById(R.id.edit_text_category);
        Button btnSearch = dialogView.findViewById(R.id.btn_search);
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = input.getText().toString().trim();
                searchCategory(userInput);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void searchCategory(String category) {
        boolean found = false;

        for (Item item : itemList) {
            if (item.getTitle().equalsIgnoreCase(category)) {
                openQuizActivity(item.getTitle());
                found = true;
                break;
            }
        }

        if (!found) {
            Toast.makeText(this, "Category not found!", Toast.LENGTH_SHORT).show();
        }
    }

    private void openQuizActivity(String category) {
        Intent intent = new Intent(MainActivity.this, activity_quiz.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
