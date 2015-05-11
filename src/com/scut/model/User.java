package com.scut.model;

/*用户数据类*/
public class User {
	public static final int TYPE_COMMON = 0;		//common user
	public static final int TYPE_PUBLISH = 1;		//publisher
	public static final int TYPE_ADMIN = 2;			//administrator
	
	private String name;	
	private String passward;
	private String email;
	private int number;		//编号
	private String school;
	private int type;
	//private Image head;

	
	public User(int number,String name,String passward,String email,String school,int type){
		this.number = number;
		this.name = name;
		this.passward = passward;
		this.email = email;
		this.school = school;
		this.type = type;

	}
	
	//getter
	public String getName(){
		return name;
	}	
	public String getPassward(){
		return passward;
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
