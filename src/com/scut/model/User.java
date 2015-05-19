package com.scut.model;

/*用户数据类*/
public class User {
	public static final int TYPE_COMMON = 0;		//common user
	public static final int TYPE_PUBLISH = 1;		//publisher
	public static final int TYPE_ADMIN = 2;			//administrator
	
	private String name;	
	private String headImgPath;
	private String email;
	private int number;		//编号
	private String school;
	private int type;
	//private Image head;

	
	public User(int number,String name,String headImgPath,String email,String school,int type){
		this.number = number;
		this.name = name;
		this.headImgPath = headImgPath;
		this.email = email;
		this.school = school;
		this.type = type;

	}
	
	//getter
	public String getName(){
		return name;
	}	
	public String getHeadImgPath(){
		return headImgPath;
	}
	public String getEmail(){
		return email;
	}
	public String getSchool(){
		return school;
	}
	public int getType(){
		return type;
	}
	public int getNumber(){
		return number;
	}

}
