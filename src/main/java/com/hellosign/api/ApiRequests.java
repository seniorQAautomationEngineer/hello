package com.hellosign.api;

import com.hellosign.data.Data;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ApiRequests {


    public void uploadFile(String endpoint, String file, String cookies) {
        RequestSpecification httpRequest = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "multipart/form-com.hellosign.data")
                .cookie(cookies)
                .multiPart(new File(file))
                .when();
        Response response = httpRequest.post(endpoint);
        response.getBody().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    public void login(String endpoint) {
        RequestSpecification httpRequest = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "multipart/form-com.hellosign.data")
                .queryParam("login[email_address]", Data.emailUser2)
                .queryParam("login[password]", Data.password);
        Response response = httpRequest.post(endpoint);
        response.getBody().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 302);
    }

    public void samlLoginVerification(String endpoint, String csrfToken) {
        RequestSpecification httpRequest = RestAssured.given()
                .relaxedHTTPSValidation()
                .queryParam("csrfToken", csrfToken)
                .queryParam("account", Data.emailUser2)
                .when();
        Response response = httpRequest.post(endpoint);
        response.getBody().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    public String zapCertificate(String endpoint) {
        RequestSpecification httpRequest = RestAssured.given()
                .relaxedHTTPSValidation()
                .queryParam("csrfToken", "dd77177f43d0564d2ccf82df11d90b17158d1b9719c47f1c911c72a1ceddd44d")
                .queryParam("account", "alex.lavre2@gmail.com")
                .when();
        Response response = httpRequest.post(endpoint);
        String rsp = response.toString();
        response.getBody().prettyPrint();
        return rsp;
    }
//https://app.hellosign.com/attachment/conversionStatus/is_template/0/form_type_code/1?preloaded_tsm_group_key=d063a3a7f526d8f966d1dee07e40cfd7b670bfdd&root_snapshot_guid=57bdf928480ad203aa2afebf2679ca3529d530e7&unique_token=2364206488&c=0.5809055997485417
    //https://app.hellosign.com/attachment/asyncReorderSnapshots?c=0.7314588056674436
}
