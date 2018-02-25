package com.yom.contract.producer.controller;

import com.yom.contract.producer.dto.TransactionDetail;
import com.yom.contract.producer.dto.TransactionRequest;
import com.yom.contract.producer.dto.TransactionResponse;
import com.yom.contract.producer.enums.TransactionSatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.yom.contract.producer.constant.TransactionConstant.*;


/**
 * Created by yogendra on 25/2/18.
 */
@RestController
public class TransactionController {

    @PostMapping(value = "/transactions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType
            .APPLICATION_JSON_VALUE)
    public TransactionResponse performTransaction(@RequestBody TransactionRequest transactionRequest) {

        return buildTransactionResponse();
    }

    private TransactionResponse buildTransactionResponse() {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactionId(ID);
        transactionResponse.setTransactionStatus(TransactionSatus.SUCCESS.name());
        return transactionResponse;
    }

    @GetMapping(value = "transactions/info/{transactionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public TransactionDetail getTransactionInfo(@PathVariable("transactionId") String transactionId) {

        return buildTransactionDetailResponse();
    }

    private TransactionDetail buildTransactionDetailResponse() {
        TransactionDetail response = new TransactionDetail();
        response.setId(ID);
        response.setAmount(1000);
        response.setTransactionStatus(TransactionSatus.SUCCESS.name());
        response.setSenderAccount(SENDER_ACCOUNT);
        response.setReceiverAccount(RECEIVER_ACCOUNT);
        response.setRemarks(REMARKS);
        return response;
    }


}
