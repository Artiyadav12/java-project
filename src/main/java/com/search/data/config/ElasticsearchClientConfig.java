//package com.search.data.config;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages
//        = "com.search.data.*")
//@ComponentScan(basePackages = {"com.search.data"})
//public class ElasticsearchClientConfig extends
//        AbstractElasticsearchConfiguration {
//
//    @Value("${spring.data.elasticsearch.host}")
//    private String host;
//
//    @Value("${spring.data.elasticsearch.port}")
//    private Integer port;
//
//    @Override
//    @Bean
//    public RestHighLevelClient elasticsearchClient() {
//
//        final ClientConfiguration clientConfiguration =
//                ClientConfiguration
//                        .builder()
//                        .connectedTo(host + ":" + port + "/")
//                        .build();
//
//        return RestClients.create(clientConfiguration).rest();
//    }
//}
