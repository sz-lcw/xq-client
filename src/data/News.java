package data;

public class News {
	public String title;
	private String content;
	private Time beginTime;
	private Time endTime;
	private Time applyTime;
	private Time applyEnd;
	private String Host;
	private int totalNum;
	private int applyNum;
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Time getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Time beginTime) {
		this.beginTime = beginTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public Time getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Time applyTime) {
		this.applyTime = applyTime;
	}
	public Time getApplyEnd() {
		return applyEnd;
	}
	public void setApplyEnd(Time applyEnd) {
		this.applyEnd = applyEnd;
	}
	public String getHost() {
		return Host;
	}
	public void setHost(String host) {
		Host = host;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(int applyNum) {
		this.applyNum = applyNum;
	}
	
}