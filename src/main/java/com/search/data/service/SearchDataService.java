package com.search.data.service;

import com.search.data.model.EmailRequest;
import com.search.data.model.PartialData;
import com.search.data.model.SearchData;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;


public interface SearchDataService {
    Optional<SearchData> getSearchData();

    void saveData(SearchData searchData);

    void downloadFile();

    void sendEmail(EmailRequest emailRequest) throws MessagingException, IOException;

    void pushToKafka(MultipartFile file);

    PartialData getPartialData(Integer id);

    void webClient(String data);

    Optional<SearchData> getDataCassandra();

    void pushToRedish(SearchData searchData);

    SearchData getFromRedis(Integer id);

    String getToken(String token);
}
