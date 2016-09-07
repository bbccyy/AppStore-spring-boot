package com.spring.boot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "app_info")
@Data
public class App {
	
	@Id
	private String appid;
	private int score;
	private String title;
	private String url;
	private String thumbnail_url;
	private String intro;
	private String developer;
	private String top5App;
	
	public App(){}

	public App(String appid, String title, String url, String developer) {
		super();
		this.appid = appid;
		this.title = title;
		this.url = url;
		this.developer = developer;
		this.score = 5;
	}
	
}
