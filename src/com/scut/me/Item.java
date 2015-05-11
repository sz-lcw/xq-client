package com.scut.me;


public class Item {
	private String name;
	private int imageId;

	public Item(String name,int imageId) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.imageId = imageId;
	}
   public void setImageId(int imageId )
   {
	   this.imageId = imageId;
   }
   public String getName(){
	   return name;
   }
   public int getImageId(){
	   return imageId;
   }
}
