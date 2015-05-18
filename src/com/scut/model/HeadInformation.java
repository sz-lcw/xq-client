package com.scut.model;  
   
import java.util.ArrayList;  
   
/**每个活动头的信息类
 * @author swb
 *
 */
public class HeadInformation {  
    private String title;				//活动标题
    private String imgUrl;				//图片地址
    private String host;				//主办方
    private int id;						//每个活动对应的独立id
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String thumbnailUrl) {
		this.imgUrl = thumbnailUrl;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}  

}  