package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.MediaType

Contract.make {

    description("Perform Money Transfer from sender to receiver")
    request {

        method POST()

        url("/transactions")

        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
        body([
                amount         : 1000,
                senderAccount  : "IND12345",
                receiverAccount: "USA67890",
                remarks        : "For Education"

        ])
        stubMatchers {
            jsonPath('$.amount', byRegex(number()))
            jsonPath('$.senderAccount', byRegex(nonEmpty()))
            jsonPath('$.receiverAccount', byRegex(nonEmpty()))
            jsonPath('$.remarks', byRegex(nonEmpty()))

        }
    }

    response {
        status(200)
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
        body(
                [
                        transactionId    : "a62fe6d7-f898-44b7-8aba-6eaddece31be",
                        transactionStatus: "SUCCESS"
                ]
        )
        testMatchers {

            jsonPath('$.transactionId', byRegex(uuid()))
            jsonPath('$.transactionStatus', byEquality())

        }

    }
}