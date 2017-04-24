package com.aspiretraining.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.aspiretraining.Service.StorageService;

@Controller
@SessionAttributes({"searchText","fileName"})
public class FileUploadController {
 
    @Autowired
    StorageService storageService;
    
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    
    MultipartFile file;
 
    //List<String> files = new ArrayList<String>();
 
    @RequestMapping(value="/",method=RequestMethod.GET)
    public String listUploadedFiles(ModelMap model) {
        return "uploadForm";
    }
 
    @RequestMapping(value="/",method=RequestMethod.POST)
    public String handleFileUpload(@ModelAttribute String fileName,@RequestParam(required=true) String searchText,@RequestParam("file") MultipartFile file,ModelMap model) {
        try {
        	
        		storageService.store(file);
           	
        		if(!model.containsAttribute("searchText"))
        			model.addAttribute("searchText", searchText);
        		
        		if(!model.containsAttribute("fileName"))
        			model.addAttribute("fileName", file.getOriginalFilename());
        		
            //model.addAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
            System.out.println("file uploaded...going to check result");
            
        } catch (Exception e) {
            model.addAttribute("message", "FAIL to upload " + file.getOriginalFilename() + "!");
            return "uploadForm";
        }
        return "resultData";
    }
    
    
   
}