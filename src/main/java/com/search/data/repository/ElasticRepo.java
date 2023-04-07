//package com.search.data.repository;
//
//import com.search.data.model.SearchData;
//import org.apache.http.Header;
//import org.elasticsearch.action.DocWriteRequest;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.stereotype.Repository;
//
//import java.io.IOException;
//
//import static jdk.nashorn.internal.objects.NativeRegExp.source;
//
////@Repository
//public interface ElasticRepo extends ElasticsearchRepository<SearchData, Integer> {
////public class ElasticRepo {
//
////    @Autowired
////    private RestHighLevelClient restHighLevelClient;
//
//   // public void saveDataToES(String searchData,String searchData1){
//
//
////
////        BulkRequest bulkRequest=new BulkRequest();
////        bulkRequest.add(new IndexRequest("data_1",
////               "_doc", searchData)
////                .source(searchData));
////        bulkRequest.add(new IndexRequest("data_1",
////                "_doc", searchData)
////                .source(searchData1));
////
////        try {
////            restHighLevelClient.bulk(bulkRequest, (Header) bulkRequest);
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
//   //}
//
//
//
//}
