package com.search.data.service;

import com.search.data.model.EmailRequest;
import com.search.data.model.SearchData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;


public interface SearchDataService {
    Optional<SearchData> getSearchData();

    void saveData(SearchData searchData);

    void downloadFile();

    void sendEmail(EmailRequest emailRequest) throws MessagingException, IOException;
}
