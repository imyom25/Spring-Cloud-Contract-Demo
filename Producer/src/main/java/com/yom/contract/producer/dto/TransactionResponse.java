package com.yom.contract.producer.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class TransactionResponse {

    private String transactionId;
    private String transactionStatus;
}
