package com.yom.contract.producer.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class TransactionDetail {

    private String id;
    private Integer amount;
    private String transactionStatus;
    private String senderAccount;
    private String receiverAccount;
    private String remarks;

}
