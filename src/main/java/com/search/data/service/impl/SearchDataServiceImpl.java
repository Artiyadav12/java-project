package com.search.data.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.search.data.model.EmailRequest;
import com.search.data.model.PartialData;
import com.search.data.model.SearchData;
import com.search.data.repository.CassandraRepo;
import com.search.data.repository.NewRepo;
import com.search.data.service.SearchDataService;
import com.search.data.util.MailUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import javax.mail.MessagingException;
import java.io.File;
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


    @Autowired
    @Qualifier("redisTemplateTotalData")
    private RedisTemplate<String, SearchData> redisCache;

    private String redisKey = "SearchRedis";


    @Override
    public Optional<SearchData> getSearchData() {
        return repo.findById(1);

    }

    @Override
    public void saveData(SearchData searchData) {

//        repo.save(searchData);
//
        cassandraRepo.insertProductToCassandra(searchData);

        // cassandraRepoInt.save(searchData);
        // elasticRepo.save(searchData);
        //elasticRepo.saveDataToES(searchData);
        //   kafkaTemplate.send(kafkaTestTopic, searchData);

    }

    @Override
    public void downloadFile() {

        //  File generateFile;
        File file = generateFileFrom();

    }

    @Override
    public void sendEmail(EmailRequest emailRequest) throws MessagingException {
        mailUtil.sendMail(emailRequest.getContent(), emailRequest.getContent());

    }

    @Override
    public void pushToRedish(SearchData searchData) {
        Jedis jedis = new Jedis("localhost", 6379);

        // jedis.sadd()
        redisCache.opsForHash().put(redisKey, searchData.getId(), searchData);


    }

    @Override
    public SearchData getFromRedis(Integer id) {

        Jedis jedis = new Jedis("localhost", 6379);
        String value = jedis.get("mykey");
        System.out.println("Value of 'mykey': " + value);
        // Close the Jedis connection
        // jedis.close();
        // (SearchData) redisCache.opsForHash().get(redisKey,id);
        return null;

    }

    @Override
    public String getToken(String token) {

       if(!isExpired(token))
           return token;
        String secret = "secret";
        Claims claims = Jwts.claims().setSubject(String.valueOf(1));
        claims.put("role", "admin");

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 60000);

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date()).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret).compact();


    }

    private Boolean isExpired(String token) {

        DecodedJWT decodedJWT = JWT.decode(token);
        Date expireAt= decodedJWT.getExpiresAt();
        return expireAt.before(new Date());
    }


    @Override
    public void pushToKafka(MultipartFile file) {

    }

    @Override
    public PartialData getPartialData(Integer id) {
        return repo.getData(id);

    }

    @Override
    public void webClient(String data) {

        //  HttpClient client=new ;
        //WebClient webClient = WebClient.create("http://localhost:3000");

    }

    @Override
    public Optional<SearchData> getDataCassandra() {
        cassandraRepo.getData();
        return Optional.empty();
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


//    public HttpResponse sendPostRequest2(String url, String contentType, String authStringEnc) {
//        HttpResponse httpResponse = new HttpResponse();
//
//        String responseMessage = "";
//        int responseCode = ResponseCode.FAILURE.getCode();
//        try {
//            HttpClient httpclient = HttpClients.createDefault();
//            HttpPost httppost = new HttpPost(url);
//// Request parameters and other properties.
//// List<NameValuePair> params = new ArrayList<NameValuePair>(paramMap.size());
//// for( String key : paramMap.keySet()){
//// params.add(new BasicNameValuePair(key, paramMap.get(key)));
//// }
//// httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//            if(contentType.equals(RequestContentType.JSON.name())){
//                httppost.addHeader("Content-Type", "application/json");
//                httppost.addHeader("Accept", "application/json");
//// httppost.addHeader("Authorization", "application/json");
//                httppost.addHeader("HTTP_X_MERCHANT_CODE", "MOG");
//            }else if(contentType.equals(RequestContentType.XML.name())){
//                httppost.addHeader("Content-Type", "text/xml");
//            } else if(contentType.equals(RequestContentType.FORM_DATA.name())){
//                httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
//            }
//
///**
// * if token is not null then set token authorization otherwise
// * use basic authentication
// */
//// if(token != null){
//// httppost.setHeader("Authorization", "Token "+token);
//// }
//            else if(authStringEnc != null){
//                httppost.setHeader("Authorization", "Basic " + authStringEnc);
//            }
////Execute and get the response.
//            if(logger.isDebugEnabled()){
//                logger.debug("Post Request All Data["+httppost+"]");
//            }
//            HttpResponse response = httpclient.execute(httppost);
//
//            HttpEntity entity = response.getEntity();
//
//            if (entity != null) {
//                InputStream instream = entity.getContent();
//                BufferedReader rd = new BufferedReader(new InputStreamReader(instream));
//                StringBuffer result = new StringBuffer();
//                String line = "";
//                while ((line = rd.readLine()) != null) {
//                    result.append(line);
//                }
//                rd.close();
//                httpResponse.setContent(result.toString());
//                responseMessage = result.toString();
//                responseCode = ResponseCode.SUCCESS.getCode();
//            }else{
//                responseMessage = "Failure from LSP.";
//                responseCode = ResponseCode.FAILURE.getCode();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        httpResponse.setResponseCode(responseCode);
//        httpResponse.setResponseMesage(responseMessage);
//        if(logger.isDebugEnabled()){
//            logger.debug("SendPostRequest["+httpResponse+"]");
//        }
//        return httpResponse;
//    }

}
