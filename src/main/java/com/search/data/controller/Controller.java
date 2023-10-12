package com.search.data.controller;

import com.search.data.model.EmailRequest;
import com.search.data.model.PartialData;
import com.search.data.model.SearchData;
import com.search.data.service.SearchDataService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import java.io.IOException;
import java.io.StringWriter;
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


	@PostMapping(value = "pushToKafka")
	public void pushToKafka(@RequestBody MultipartFile file){
		searchDataService.pushToKafka(file);
	}

	@GetMapping(value = "getPartialData")
	public PartialData getPartialData(@RequestParam Integer id){
		return searchDataService.getPartialData(id);
	}


	@GetMapping(value = "webClient")
	public void webClient(@RequestParam String data){
		searchDataService.webClient(data);
	}

	@GetMapping(value = "getDataCassandra")
	public Optional<SearchData> getDataCassandra(){
		return searchDataService.getDataCassandra();
	}


	@GetMapping(value = "getFromRedis")
	public SearchData getFromRedis(@RequestParam Integer id){
		return searchDataService.getFromRedis(id);
	}

	@PostMapping(value = "pushToRedis")
	public void pushToRedis(@RequestBody SearchData searchData)
	{
		searchDataService.pushToRedish(searchData);
	}

	@GetMapping(value = "getToken")
	public String getToken(@RequestParam String token) {
		return searchDataService.getToken(token);
	}

	@PostMapping(value = "velocity")
	public String velocity() {


		String message = "message";

		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init();
		Template template = velocityEngine.getTemplate("src/main/resources/templates/velocity.vm");

		VelocityContext context=new VelocityContext();
		context.put("message","Velocity Engine");
		context.put("data","Velocity Engine Data");
		StringWriter writer=new StringWriter();
		template.merge(context,writer);
		return writer.toString();
	}




//	@PostMapping(value ="elastic/search")
//	public String elasticSearch(){
//
//	}
}
