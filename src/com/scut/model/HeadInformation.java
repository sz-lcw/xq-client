package com.scut.model;  
   
import java.util.ArrayList;  
   
/**ÿ���ͷ����Ϣ��
 * @author swb
 *
 */
public class HeadInformation {  
    private String title;				//�����
    private String imgUrl;				//ͼƬ��ַ
    private String host;				//���췽
    private int id;						//ÿ�����Ӧ�Ķ���id
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