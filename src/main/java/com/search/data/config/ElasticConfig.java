//package com.search.data.config;
//
//
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ElasticConfig {
//
//    @Value("${spring.data.elasticsearch.host}")
//    private String host;
//
//    @Value("${spring.data.elasticsearch.port}")
//    private Integer port;
//
////    @Value("${spring.data.elasticsearch.cluster.name}")
////    private String clusterName;
//
//    private static final Logger log = LoggerFactory.getLogger(ElasticConfig.class);
//
//    @Bean
//    public RestHighLevelClient createInstance() {
//        return buildClient();
//    }
//
//    /**
//     * This method id to build ES client with provided host and port     * @return RestHighLevelClient
//     */
//    private RestHighLevelClient buildClient() {
//        RestHighLevelClient restHighLevelClient = null;
//        try {
//            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, "http")));
//        } catch (Exception e) {
//             log.error(e.getMessage());
//        }
//         log.info("--------Rest high level client----------- : " + restHighLevelClient);
//        return restHighLevelClient;
//    }
//
//}
//
