package com.refreshus.jinwoo.circle_refreshus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Jinwoo on 7/23/2016.
 */
public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context context, ArrayList<Item> items){
        super(context, 0 ,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the data item for this position
        Item item = getItem(position);

        // Check if an existing view is being reused, otherwise inflate a new view from custom row layout
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
        }

        // Grab references of views so we can populate them with specific item row data
        TextView itemTitle = (TextView) convertView.findViewById(R.id.listItemFoodTitle);
        TextView itemText = (TextView) convertView.findViewById(R.id.listItemFoodText);
        CheckBox itemBox = (CheckBox) convertView.findViewById(R.id.listItemCheckbox);

        // Fill each new referenced view with data associated with note it's referencing
        itemTitle.setText(item.getItemName());
        itemText.setText(item.getMessage());
        itemBox.setChecked(item.getCheck());

        return convertView;
    }
}
