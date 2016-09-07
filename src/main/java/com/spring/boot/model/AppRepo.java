package com.spring.boot.model;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepo extends JpaRepository<App, String> {
	
	public App findByAppid(String id);
	
	public List<App> findTop10ByScoreOrderByScoreDesc(int score);
	
	public List<App> findByScore(int scroe, Pageable pageable);
	
	@Query("from App a order by a.score desc")
	public List<App> myFindTopN(Pageable pageable);
	
	@Query(value = "SELECT * FROM app_info ORDER BY score DESC LIMIT ?1", nativeQuery = true)
	public List<App> myAnotherFindTopN(int topN);
	
}
