package com.aspiretraining.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.aspiretraining.Service.StorageService;
import com.aspiretraining.Service.WordSearchService;

@Controller
@SessionAttributes({"searchText","fileName"})
public class ResultController {

	@Autowired
    StorageService storageService;
	
	@Autowired
	WordSearchService service;
	
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
		
		
		
	    @RequestMapping(value="/resultData",method = RequestMethod.GET)
		public String processResult(ModelMap model){
	    	List<String> searchTexts = new ArrayList<String>();
	    	System.out.println("Getting results page");
	    	
	    	String fileName="",searchText="";
	    	//if(!model.containsAttribute("searchText")){
	    		model.addAttribute("searchText", searchText);
	    		searchTexts = service.getList(searchText);
	    		System.out.println(searchText);
	    	//}
			
				model.addAttribute("fileName", fileName);
			
			List<String> list = new ArrayList<String>();
			
			try(Stream<String> stream = Files.lines(storageService.loadFile(fileName))){
				for(String temp : searchTexts)
					list = stream.filter(line->line.contains(temp)).collect(Collectors.toList());
				int total = 0;
				Iterator<String> itr = list.iterator();
				for(String temp: searchTexts){
					while(itr.hasNext()){
						String line = itr.next();
						if(line.contains(temp)){
							total = service.countWord(line, searchText);
							service.insertWordCount(searchText,total);
							service.insertLineSearch(line, searchText);
						
							if(!model.containsAttribute("lineSearch")){
								model.addAttribute("lineSearch", service.getLineSearch());
							}
							//service.insertLineSearch(lineSearch);
							if(!itr.hasNext()){
								if(!model.containsAttribute("word")){
									model.addAttribute("word", service.getWordCount());
								}
								//service.insertWordCount(word);
								total = 0;
							}
						}	
					}
					stream.close();
				}
			}catch(IOException e){
				log.error(e.getMessage());
				//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return "resultData";
		}
}
