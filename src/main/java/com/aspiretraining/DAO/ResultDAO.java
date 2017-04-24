package com.aspiretraining.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.aspiretraining.Entity.LineSearch;
import com.aspiretraining.Entity.WordCount;

@Repository
public class ResultDAO {
	private Map<String,Integer> wordCount;
	private Map<String,String> lineSearch;
	
	private WordCount wc;
	private LineSearch ls;
	
	
	
	/*public int getWordCount(String searchText){
		return this.wordCount.get(searchText);
	}
	
	public List<String> getAllLines(String searchText){
		List<String> lines = new ArrayList<String>();
		Set<String> keySet = lineSearch.keySet();
		Iterator<String> itr = keySet.iterator();
		while(itr.hasNext()){
			String line = itr.next();
			if(line==searchText)
				lines.add(line);
		}
		return lines;
	}*/
	
	public void insertWordCount(String searchText,int count){
		wc.setCount(count);
		wc.setSearchText(searchText);
		this.wordCount.put(wc.getSearchText(),wc.getCount());
	}
	
	
	
	public void insertLineSearch(String line,String searchText){
		ls.setLine(line);
		ls.setSearchText(searchText);
		this.lineSearch.put(ls.getLine(),ls.getSearchText());
	}
	
	public Map<String,String> getLineSearch(){
		return lineSearch;
	}
	
	public Map<String,Integer> getWordCount(){
		return wordCount;
	}
	
}
