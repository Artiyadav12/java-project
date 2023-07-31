package com.search.data.repository;


import com.search.data.model.PartialData;
import com.search.data.model.SearchData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


public interface NewRepo extends JpaRepository<SearchData,Integer> {

    @Query(value = "select new com.search.data.model.PartialData(id,name) from SearchData where id=?1 ")
    PartialData getData(Integer id);




	//private final static Logger logger = LoggerFactory.getLogger(NewRepo.class);
	
//	public static void main(String[] args) {
//
//	//	logger.info("hello");
//		List<String> s=new ArrayList<>();
//		//s.add("4");
//		List<String> list=null;
//		String result = null;
//		if (s != null)
//			result=s.toString();
//
//		System.out.print(result);
//
//	}

}
