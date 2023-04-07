package com.search.data.service.impl;

import com.search.data.config.CassandraConfig;
import com.search.data.model.EmailRequest;
import com.search.data.model.SearchData;
import com.search.data.repository.CassandraRepo;
//import com.search.data.repository.CassandraRepoInt;
//import com.search.data.repository.ElasticRepo;
import com.search.data.repository.NewRepo;
import com.search.data.service.SearchDataService;
import com.search.data.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class SearchDataServiceImpl implements SearchDataService {

    @Autowired
    NewRepo repo;

    @Autowired
    CassandraRepo cassandraRepo;

//    @Autowired
//    CassandraRepoInt cassandraRepoInt;

//    @Autowired
//    ElasticRepo elasticRepo;

    @Value("${kafka.test.topic}")
    private String kafkaTestTopic;


    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private MailUtil mailUtil;


    @Override
    public Optional<SearchData> getSearchData() {
        return repo.findById(1);

    }

    @Override
    public void saveData(SearchData searchData) {

//        repo.save(searchData);
//
//       cassandraRepo.insertProductToCassandra(searchData);

       // cassandraRepoInt.save(searchData);
       // elasticRepo.save(searchData);
        //elasticRepo.saveDataToES(searchData);
        kafkaTemplate.send(kafkaTestTopic, searchData);

    }

    @Override
    public void downloadFile() {

      //  File generateFile;
        File file=generateFileFrom();

    }

    @Override
    public void sendEmail(EmailRequest emailRequest) throws MessagingException {
        mailUtil.sendMail(emailRequest.getContent(),emailRequest.getContent());

    }

    private File generateFileFrom() {

        try {
            // if (null != categoryCode) {
            StringBuilder fileName = new StringBuilder();

            fileName.append("TEST").append("_");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            Date date = new Date();
            String time = sdf.format(date);
            fileName.append("_").append(time).append("FILE_EXTENSION");
            return new File("TEMPLATE_CSV_DOWNLOAD_PATH" + fileName.toString().replaceAll(" ", ""));
            //}
        } catch (Exception e) {
//            logger.error("Error while generating fileName in downmload template :{}", e);
//            context.addError(ResponseCode.FILE_DIRECTORY_NOT_EXIST,
//                    "Error occuring, while creating file in directory" + e);
        }
        return null;
    }
}
