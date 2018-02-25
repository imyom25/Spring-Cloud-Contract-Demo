import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.MediaType

Contract.make {
    description("Should return Transaction detail for transaction Id")
    request {
        method GET()
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
        url("/transactions/info/a62fe6d7-f898-44b7-8aba-6eaddece31be")
    }
    response {
        status(200)
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
        body([
                id:"a62fe6d7-f898-44b7-8aba-6eaddece31be",
                amount:1000,
                transactionStatus:"SUCCESS",
                senderAccount:"IND12345",
                receiverAccount:"USA67890",
                remarks:"For Education"

        ])
        testMatchers {
            jsonPath('$.id', byEquality())
            jsonPath('$.transactionStatus', byEquality())
            jsonPath('$.remarks', byRegex(nonBlank()))
        }
    }
}