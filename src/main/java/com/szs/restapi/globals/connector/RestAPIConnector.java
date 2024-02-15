package com.szs.restapi.globals.connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Component("restApiConnector")
public class RestAPIConnector {

    public JSONObject connect(String httpUrl, HttpMethod httpMethod, Map<String, Object> httpParams) throws Exception {

        JSONObject responseJson = new JSONObject();

        try {

            ObjectMapper mapper = new ObjectMapper();

            URI uri = new URI(httpUrl);

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .headers("Content-Type", "application/json")
                    .headers("Authroization", "Bearer " + httpParams.get("accessToken").toString())
                    .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(httpParams)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            responseJson = new JSONObject(response.body());

        } catch( Exception e ) {
            responseJson.put("message", e.getMessage());
        }

        return responseJson;

    }

//    public JSONObject connect( String httpUrl, HttpMethod httpMethod, Map<String, Object> httpParams) throws IOException {
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Authorization", "Bearer " + httpParams.get("accessToken").toString());
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity httpEntity = new HttpEntity<>(JSONObject.class);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange( httpUrl, httpMethod, httpEntity, JSONObject.class, httpParams );
//
//        if(!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
//            throw new RuntimeException("API 호출 오류입니다 [ " + httpUrl + "]");
//        }
//
//        return responseEntity.getBody();
//
//    }

}
