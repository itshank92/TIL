package com.newlecture.web.entity;

import java.util.Date;

public class Notice {	
    private int id;
    private String title;
    private String writerid;
    private Date regdate;
    private String content;
    private int hit;
    private String files;
    
    public Notice() {
		// TODO Auto-generated constructor stub
	}

	public Notice(int id, String title, String writerid, Date regdate, String content, int hit, String files) {
		super();
		this.id = id;
		this.title = title;
		this.writerid = writerid;
		this.regdate = regdate;
		this.content = content;
		this.hit = hit;
		this.files = files;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriterid() {
		return writerid;
	}

	public void setWriterid(String writerid) {
		this.writerid = writerid;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", writerid=" + writerid + ", regdate=" + regdate
				+ ", content=" + content + ", hit=" + hit + ", files=" + files + "]";
	}
    
}
