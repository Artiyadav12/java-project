package com.search.data.controller;

import com.search.data.model.EmailRequest;
import com.search.data.model.SearchData;

import com.search.data.service.SearchDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/search/data/*")
public class Controller {


	@Autowired
	SearchDataService searchDataService;
	
	@GetMapping(value = "ping")
	public String welcome() {
		return "Welcome";
	}


	@GetMapping(value = "getData")
	public Optional<SearchData> getSearchData(){
		return searchDataService.getSearchData();
	}

	@PostMapping(value = "saveData")
	public void saveSearchData(@RequestBody SearchData searchData){
		searchDataService.saveData(searchData);
	}

	public void downloadFile(){
		searchDataService.downloadFile();
	}

	@PostMapping(value = "sendEmail")
	public void sendEmail(@RequestBody EmailRequest emailRequest) throws MessagingException, IOException {
		searchDataService.sendEmail(emailRequest);
	}
}
