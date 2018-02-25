package com.yom.contract.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yom.contract.consumer.dto.TransactionRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(workOffline = true, ids = "com.yom.contract:producer:+:stubs:9177")
@DirtiesContext
public class ConsumerApplicationTests {

    public static final String MONEY_API = "/v1/forex/send-money";
    public static final String DETAIL_API = "/v1/forex/details/{transactionId}";

    @Autowired
    private MockMvc mockMvc;

    @Test
	public void test_should_SendMoney() throws Exception {

        TransactionRequest request = prepareTransactionRequest();

        String response = prepareSendMoneyResponse();

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post(MONEY_API).
                contentType(MediaType.APPLICATION_JSON_VALUE).
                accept(MediaType.APPLICATION_JSON_VALUE).
                content(mapper.writeValueAsString(request))).
                andExpect(status().isOk()).andExpect(content()
                .string(response));
    }

    @Test
    public void test_getTransactionDetailById() throws Exception{

        String response = prepareTransactionDetailResponse();

        mockMvc.perform(MockMvcRequestBuilders.get(DETAIL_API,
                "a62fe6d7-f898-44b7-8aba-6eaddece31be").
                contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    private String prepareTransactionDetailResponse() {
        return "{\"id\":\"a62fe6d7-f898-44b7-8aba-6eaddece31be\",\"amount\":1000,\"transactionStatus\":\"SUCCESS\"," +
                "\"senderAccount\":\"IND12345\",\"receiverAccount\":\"USA67890\",\"remarks\":\"For Education\"}";
    }

    private TransactionRequest prepareTransactionRequest() {
        TransactionRequest request = new TransactionRequest();
        request.setAmount(1000);
        request.setSenderAccount("IND12345");
        request.setReceiverAccount("USA67890");
        request.setRemarks("For Education");
        return request;
    }

    private String prepareSendMoneyResponse() {
        return "{\"transactionId\":\"a62fe6d7-f898-44b7-8aba-6eaddece31be\"," +
                "\"transactionStatus\":\"SUCCESS\"}";
    }
}
