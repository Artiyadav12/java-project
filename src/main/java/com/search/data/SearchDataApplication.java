package com.search.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableJpaRepositories("com.search.data.*")
//@EnableElasticsearchRepositories("com.search.data.*")
@EnableAutoConfiguration(exclude = {FreeMarkerAutoConfiguration.class})
//@EnableScheduling
//@EnableEurekaClient
public class SearchDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchDataApplication.class, args);
    }

//	@Value("${kafka.buyer_order_consume.topic}")
//	private String kafkaBuyerOrderConsumeTopic;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


//	@Bean
//	public NewTopic topic() {
//		return TopicBuilder.name(kafkaBuyerOrderConsumeTopic)
//				.partitions(10)
//				.replicas(1)
//				.build();
//	}
//
//	@KafkaListener(id = "myId", topics = "buyer_order")
//	public void listen(String in) {
//		System.out.println(in);
//	}

//	@Bean
//	public ApplicationRunner runner(KafkaTemplate<String, String> template) {
//		return args -> {
//			template.send(kafkaBuyerOrderConsumeTopic, "test");
//		};
//	}

}
