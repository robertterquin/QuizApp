package com.example.quizapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizapp.Item;
import com.example.quizapp.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Item> {

    private final Activity context;
    private final ArrayList<Item> itemList;

    public CustomAdapter(Activity context, ArrayList<Item> itemList) {
        super(context, R.layout.custom_item, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View itemView = inflater.inflate(R.layout.custom_item, null, true);

        // Find views
        ImageView itemImage = itemView.findViewById(R.id.item_image);
        TextView itemTitle = itemView.findViewById(R.id.item_title);
        TextView itemDescription = itemView.findViewById(R.id.item_description);

        // Get current item
        Item currentItem = itemList.get(position);

        // Set data
        itemImage.setImageResource(currentItem.getImageResource());
        itemTitle.setText(currentItem.getTitle());
        itemDescription.setText(currentItem.getDescription());

        return itemView;
    }
}
