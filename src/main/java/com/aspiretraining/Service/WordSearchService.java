package com.aspiretraining.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspiretraining.DAO.ResultDAO;

@Service
public class WordSearchService {

	@Autowired
	private ResultDAO result;
	
	/*public int getWordCount(String searchText){
		return this.result.getWordCount(searchText);
	}
	
	public List<String> getAllLines(String searchText){
		return this.result.getAllLines(searchText);
	}*/
	
	public int countWord(String line,String searchText){
		int total = 0;
		List<String> tempo = new ArrayList<String>(Arrays.asList(line.split("\\s+")));
		for(String temp1 : tempo){
			if(temp1.equals(searchText))
				total += 1;
		}
		return total;
	}
	
	public List<String> getList(String searchText){
		List<String> searchTexts = new ArrayList<String>(Arrays.asList(searchText.split("\\s+")));
		StringBuffer sb = new StringBuffer();
		for(String temp: searchTexts){
			sb.append(temp);
			searchTexts.add(" "+sb.toString());
		}
		return searchTexts;
	}
	
	
	public void insertWordCount(String searchText,int count){
		this.result.insertWordCount(searchText,count);
	}
	
	public void insertLineSearch(String line,String searchText){
		this.result.insertLineSearch(line,searchText);
	}
	
	public Map<String,String> getLineSearch(){
		return this.result.getLineSearch();
	}
	
	public Map<String,Integer> getWordCount(){
		return this.result.getWordCount();
	}
	
	
	
}
