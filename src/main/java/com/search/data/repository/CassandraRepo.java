package com.search.data.repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.search.data.config.CassandraConfig;
import com.search.data.model.SearchData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CassandraRepo {


    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    CassandraConfig cassandraConfig;

//    @Autowired
//    CassandraTemplate cassandraTemplate;

    private MappingManager mappingManager;
    private Mapper<SearchData> mapper;


    public ResultSet getData(){
        ResultSet results = null;
        Statement query = QueryBuilder.select().column("brand_name").from("products","brand_details_translation").allowFiltering()
                .where(QueryBuilder.eq("id_brand", 1));
        try {
           // results = cassandraTemplate.getCqlOperations().queryForResultSet(query);
//			logger.info(String.format("In getQueryExecutor METHOD in CpnDao CLASS for fetch detail & get Response from DB: {} %s", results));
        } catch (Exception e) {
            logger.error("Database exception occur in getMsnbyId METHOD of CpnDao CLASS: {} " + query, e);

        }

        return results;
    }


    public void insertProductsToCassandra(List<SearchData> productList){

            Session session = cassandraConfig.getSession();
            mappingManager = new MappingManager(session);
            mapper = mappingManager.mapper(SearchData.class);
            for(SearchData product : productList){
                try{
                    mapper.save(product);
                }catch(Exception e){
                    System.out.println("Product insertion to cassandra: " + e.getMessage());
                }
            }
    }

    public void insertProductToCassandra(SearchData product){

        Session session = cassandraConfig.getSession();
        mappingManager = new MappingManager(session);
        mapper = mappingManager.mapper(SearchData.class);

            try{
                mapper.save(product);
                System.out.println("Product insertion to cassandra: ");
            }catch(Exception e){
                System.out.println("Exception occured while Product insertion to cassandra: " + e.getMessage());
            }

    }

}
