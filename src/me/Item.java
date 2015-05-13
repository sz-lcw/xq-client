package me;

import android.graphics.drawable.Drawable;


public class Item {
	private String name;
	private int imageId;
    private Drawable drawable;
	public Item(String name,int imageId) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.imageId = imageId;
	}
   public void setImage(Drawable drawable )
   {
	   this.drawable = drawable;
   }
   public Drawable getDrawable(){
	   return drawable;
   }
   public String getName(){
	   return name;
   }
   public int getImageId(){
	   return imageId;
   }
}
