package com.spring.boot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.model.App;
import com.spring.boot.model.AppRepo;

@RestController
public class MainController {
	
	@Autowired
	private AppRepo appRepo;
	
	@ResponseBody @RequestMapping(value = "/app", method = RequestMethod.GET)
	public List<App> getTop10(){
		//Pageable topTen = new PageRequest(0, 10);
		//List<App> apps = appRepo.findTop10ByScoreOrderByScoreDesc(10);
		//List<App> apps = appRepo.findByScore(10, topTen);
		//List<App> apps = appRepo.myFindTopN(topTen);
		List<App> apps = appRepo.myAnotherFindTopN(10);
		return apps;
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public App getById(@PathVariable String id){
		App app = appRepo.findByAppid(id);
		return app;
	}
	
	@RequestMapping(value = "/app/getRecomApps/similarapp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<App> getRecomApps(@RequestBody List<String> appids){
		if(appids==null || appids.isEmpty()){
			System.out.println("No input data error!");
			return null;
		}
		System.out.println("input apps: " + appids);
		List<App> apps =  new ArrayList<>();
		for(String id : appids){
			App app = appRepo.findByAppid(id);
			if(app==null) continue;
			apps.add(app);
		}
		if(apps.isEmpty()){
			System.out.println("AppController 65: no recommandation apps found!");
			return apps;
		}
		System.out.println("==> Retrieve "+apps.size()+" Recom Apps <==");	
		return apps;
	}
}
