package com.refreshus.jinwoo.circle_refreshus;


import android.app.ListFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends ListFragment {

    private ArrayList<Item> items;
    private ItemAdapter itemAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


        items = new ArrayList<Item>();
        items.add(new Item("Egg", false));
        items.add(new Item("Milk", false));
        items.add(new Item("Chicken", true));
        items.add(new Item("Turkey", false));
        items.add(new Item("cookies", false));
        items.add(new Item("Pickles", false));
        items.add(new Item("Beer",  true));
        items.add(new Item("Apple", false));

        itemAdapter = new ItemAdapter(getActivity(), items);

        setListAdapter(itemAdapter);

        // grey divider lines

        getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.white));
        getListView().setDividerHeight(1);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
    }

    public void addItem(String Item, String Description, boolean isChecked){
        items.add(new Item("New Item", false));
        setListAdapter(itemAdapter);
        Log.d("myTag", "This is my message");
    }
}
