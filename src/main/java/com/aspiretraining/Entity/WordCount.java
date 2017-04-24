package com.aspiretraining.Entity;


public class WordCount {
	private String searchText;
	private int count;
	
	public WordCount(String searchText, int count) {
		this.searchText = searchText;
		this.count = count;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
