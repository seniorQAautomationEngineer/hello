package com.hellosign.api;

import com.hellosign.data.Data;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;



public class ApiRequests {


    public Response uploadFile(String endpoint, String file, String cookies) {
        RequestSpecification httpRequest = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "multipart/form-data")
                .cookie(cookies)
                .multiPart(new File(file))
                .when();
        Response response = httpRequest.post(endpoint);
        return response;
    }

    public Response login(String endpoint) {
        RequestSpecification httpRequest = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "multipart/form-com.hellosign.data")
                .queryParam("login[email_address]", Data.emailUser2)
                .queryParam("login[password]", Data.password);
        Response response = httpRequest.post(endpoint);
        return response;
    }

    public Response samlLoginVerification(String endpoint, String csrfToken) {
        RequestSpecification httpRequest = RestAssured.given()
                .relaxedHTTPSValidation()
                .queryParam("csrfToken", csrfToken)
                .queryParam("account", Data.emailUser2)
                .when();
        Response response = httpRequest.post(endpoint);
        return response;
    }


    public String zapCertificate(String endpoint) {
        RequestSpecification httpRequest = RestAssured.given()
                .relaxedHTTPSValidation()
                .queryParam("csrfToken", Data.csrfToken)
                .queryParam("account", "alex.lavre2@gmail.com")
                .when();
        Response response = httpRequest.post(endpoint);
        String rsp = response.toString();
        response.getBody().prettyPrint();
        return rsp;
    }
}
