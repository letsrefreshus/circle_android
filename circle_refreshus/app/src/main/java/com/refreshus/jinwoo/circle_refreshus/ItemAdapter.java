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

    public static class ViewHolder{
        TextView itemTitle;
        CheckBox itemBox;
    }

    public ItemAdapter(Context context, ArrayList<Item> items){
        super(context, 0 ,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the data item for this position
        Item item = getItem(position);

        ViewHolder viewHolder;
        // Check if an existing view is being reused, otherwise inflate a new view from custom row layout
        if(convertView == null){

            // if we don't have a view that is being used, create one and make sure you create a
            // view holder along with it to save our view references to.
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

            // set our views to our view holder so that we no longer have to go back and
            // use find view by id every time we have a new one
            viewHolder.itemTitle = (TextView) convertView.findViewById(R.id.listItemFoodTitle);
            viewHolder.itemBox = (CheckBox) convertView.findViewById(R.id.listItemCheckbox);

            // use set tag to remember our viewholder which is holding references to our widgets
            convertView.setTag(viewHolder);
        } else {
            // we already have a view so just go to our viewholder and grab the widgets from it.
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.itemTitle.setText(item.getItemName());
        viewHolder.itemBox.setChecked(item.getCheck());

        // now that w e modified the view to display appropriate data,
        // return it so it will be displayed.
        return convertView;
    }
}
