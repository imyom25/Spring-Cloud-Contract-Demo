package com.yom.contract.consumer.controller;

import com.yom.contract.consumer.dto.TransactionDetail;
import com.yom.contract.consumer.dto.TransactionRequest;
import com.yom.contract.consumer.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yogendra on 14/2/18.
 */
@RestController
public class ForexController {

    public static final String TRANSACTION_INFO_API = "http://localhost:9177/transactions/info/{transactionId}";
    public static final String PERFORM_TRANSACTION_API = "http://localhost:9177/transactions";

    @Autowired
    public ForexController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private RestTemplate restTemplate;

    @PostMapping(value = "/v1/forex/send-money", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType
            .APPLICATION_JSON_VALUE)
    public TransactionResponse sendMoney(@RequestBody TransactionRequest transactionRequest) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        ResponseEntity<TransactionResponse> responseEntity = restTemplate.
                exchange(RequestEntity.post(URI.create(PERFORM_TRANSACTION_API))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(transactionRequest), TransactionResponse.class);


        return responseEntity.getBody();

    }

    @GetMapping(value = "/v1/forex/details/{transactionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionDetail getTransactionDetail(@PathVariable("transactionId") String transactionId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");

        Map<String, String> params = new HashMap<>();
        params.put("transactionId", transactionId);

        ResponseEntity<TransactionDetail> responseEntity = restTemplate
                .exchange(TRANSACTION_INFO_API, HttpMethod.GET, new HttpEntity<>
                        (httpHeaders), TransactionDetail.class, params);

        return responseEntity.getBody();
    }

}
