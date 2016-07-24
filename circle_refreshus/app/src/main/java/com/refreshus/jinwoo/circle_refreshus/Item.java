package com.refreshus.jinwoo.circle_refreshus;

/**
 * Created by Jinwoo on 7/23/2016.
 */
public class Item {
    private String itemName, message;
    private long itemId, dateCreatedMilli;
    private boolean check;

    public Item(String itemName, String message, boolean check){
        this.itemName = itemName;
        this.message= message;
        this.check = check;
        this.itemId = 0;
        this.dateCreatedMilli = 0;
    }

    public Item(String itemName, String message, Boolean check, long itemId, long dateCreatedMilli){
        this.itemName = itemName;
        this.message= message;
        this.check = check;
        this.itemId = itemId;
        this.dateCreatedMilli = dateCreatedMilli;
    }

    public String getItemName(){
        return itemName;
    }

    public String getMessage(){
        return message;
    }

    public boolean getCheck(){ return check; }

    public long gotDate(){return dateCreatedMilli;}

    public long getItemId(){ return itemId;}

    public String toString(){
        return "ID: "+ itemId + " Item Name: " + itemName + " Message: " + message +
                " isChecked: " + check + " Date: " + dateCreatedMilli;
    }
/*
    public int getAsscoiatedDrawable(){
        return categoryToDrawable(category);
    }

    public static int categoryToDrawable(Category itemCategory){
        switch(itemCategory){
            case EGG:
                return R.drawable.air;
            case MILK:
                return R.drawable.googleplus;
            case CHEESE:
                return R.drawable.instagram;
            case OTHERS:
                return R.drawable.youtube;
        }
        return R.drawable.googleplusgrey;
    }*/
}