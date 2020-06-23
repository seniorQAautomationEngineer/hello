package com.hellosign.tests;

import com.hellosign.api.ApiRequests;
import com.hellosign.data.Data;
import com.hellosign.utility.Reports;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Requests {
    ApiRequests apiRequests = new ApiRequests();


    @Test(priority = 1)
    public void testSaml(){
        Response response = apiRequests.samlLoginVerification(Data.mainUrl+ Data.samlVerificationEdnpoint, Data.csrfToken);
        response.getBody().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testLoginRequest(){
        Response response = apiRequests.login(Data.mainUrl+ Data.loginEndpoint);
        response.getBody().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 302);
    }

    @Test(priority = 3)
    public void testUploadFile(){
        Response response = apiRequests.uploadFile(Data.mainUrl+ Data.attachmentsEndpoint+Data.attachmentsId,
                Data.pathToJpegFile, Data.cookies);
        response.getBody().prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
