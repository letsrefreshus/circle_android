package com.refreshus.jinwoo.circle_refreshus;


import android.app.ListFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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

        /*
        String[] values = new String[]{"Egg", "Ham", "Milk", "Salad", "Cheese", "Soda", "Wine", "Lemon","Chicken","Beef","Flowers"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);
        */

        items = new ArrayList<Item>();
        items.add(new Item("Egg", "Grade A", false));
        items.add(new Item("Milk", "2% Foodlion", false));
        items.add(new Item("Chicken", "Breast", true));
        items.add(new Item("Turkey", "Thigh", false));
        items.add(new Item("cookies", "White Macadamia", false));
        items.add(new Item("Pickles", "in a Jar", false));
        items.add(new Item("Beer", "Summer Shandy", true));
        items.add(new Item("Apple", "Figi", false));

        itemAdapter = new ItemAdapter(getActivity(), items);

        setListAdapter(itemAdapter);

        // grey divider lines
        getListView().setDivider(ContextCompat.getDrawable(getActivity(), android.R.color.darker_gray));
        getListView().setDividerHeight(1);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);
    }
}
