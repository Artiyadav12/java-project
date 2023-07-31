package com.search.data.kafka;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.data.model.SearchData;
//import com.search.data.repository.ElasticRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class KafkaConsumer {

//    @Autowired
//    ElasticRepo repo;
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    String topic;
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

//    @Autowired
//    @Qualifier("dbConfig")
//    private Properties mysqlProperties;

//    @KafkaListener(id = "batch-listener-test", topics = "test_search")
//    public void onReceiving(List<String> searchDataJSONs, @Header(KafkaHeaders.OFFSET) List<Long> offsets,
//                            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
//                            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Acknowledgment acknowledgment) {
//
//        this.topic = topic;
//       // searchDataJSONs = filterKafkaNullMessages(searchDataJSONs);
//        List<SearchData> searchData=filterKafkaNullMessagesObject(searchDataJSONs);
//        CountDownLatch latch = new CountDownLatch(searchDataJSONs.size());
////        for (int position = 0; position < searchDataJSONs.size(); position++) {
////            String supplierProductJSON = searchDataJSONs.get(position);
////            System.out.println("data------- " + supplierProductJSON);
////            repo.save(searchDataJSONs.get(0));
//           // repo.saveDataToES(searchDataJSONs.get(0),searchDataJSONs.get(1));
//            //threadPool.submit(new SearchDataUpdateToES(supplierProductJSON, latch));
////repo.save(searchData.get(0));
//        //}
//
//        try {
//            latch.await();
//            acknowledgment.acknowledge();
//            logger.info("acknowledged [" + topic + "]");
//        } catch (Exception e) {
//            logger.error("error occured while waiting at latch[" + Thread.currentThread().getName() + "]", e);
//        }
//
//    }


//    class SearchDataUpdateToES implements Callable<Boolean> {
//
//        String supplierProductJSON;
//        CountDownLatch latch;
//
//        public SearchDataUpdateToES(String supplierProductJSON, CountDownLatch latch) {
//            this.supplierProductJSON = supplierProductJSON;
//            this.latch = latch;
//        }
//
//        @Override
//        public Boolean call() throws Exception {
//
//            long startTime = System.currentTimeMillis();
//            String productID = "";
//            boolean productUpdated = false;
//
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                JsonNode jsonNode = mapper.readTree(supplierProductJSON).get("payload").get("after");
//
//                SearchData product = mapper.readValue(jsonNode.toString(), SearchData.class);
//
//                productID = String.valueOf(product.getId());
////                boolean productExistInEs = eSDataInfoUtil.checkForDocumentExistenceInEs(productID);
////                if (productExistInEs) {
//
//                    logger.info("Consumed supplier product[" + productID + "] from topic[" + topic + "]");
//                    repo.saveDataToES();
////                    if (productUpdated = eSDataInfoUtil.saveProductStockToES(productID)) {
////                        logger.info("Supplier Inventory Stock Status Updated for Product[" + productID + "] successfully !!!");
////                    } else {
////                        logger.info("Supplier Inventory Stock Status Updated for Product[" + productID + "] failed !!!");
////                    }
//               // }
//            } catch (Exception e) {
//                logger.error("Supplier Inventory Stock Status Updated for Product[" + productID + "] failed !!!", e);
//            } finally {
//                long endTime = System.currentTimeMillis();
//                logger.info("Time taken:[" + (endTime - startTime) + "]");
//                latch.countDown();
//            }
//            return productUpdated;
//
//        }
//
//    }

    private List<String> filterKafkaNullMessages(List<String> productJSONs) {

        List<String> filteredMessages = new ArrayList<String>();

        for (Object messageObject : productJSONs) {
            if (null != messageObject && !(messageObject.getClass() == KafkaNull.class) && (messageObject.getClass() == String.class)) {
                String strMessage = (String) messageObject;
                filteredMessages.add(strMessage);
            } else {
                logger.info("Filtered out kafka message:[" + messageObject + "]");
            }
        }
        return filteredMessages;

    }


    private List<SearchData> filterKafkaNullMessagesObject(List<String> productJSONs) {

        List<SearchData> filteredMessages = null;

        for (Object messageObject : productJSONs) {
            if (null != messageObject && !(messageObject.getClass() == KafkaNull.class) && (messageObject.getClass() == String.class)) {
                SearchData strMessage = (SearchData) messageObject;
                filteredMessages.add(strMessage);
            } else {
                logger.info("Filtered out kafka message:[" + messageObject + "]");
            }
        }
        return filteredMessages;

    }

}

