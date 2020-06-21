package com.hellosign.tests;

import com.hellosign.api.ApiRequests;
import com.hellosign.data.Data;
import org.testng.annotations.Test;

public class Requests {
    ApiRequests apiRequests = new ApiRequests();


    @Test(priority = 1)
    public void testSaml(){
        apiRequests.samlLoginVerification(Data.mainUrl+ Data.samlVerificationEdnpoint, Data.csrfToken);
    }

    @Test(priority = 2)
    public void testLoginRequest(){
        apiRequests.login(Data.mainUrl+ Data.loginEndpoint);
    }

    @Test(priority = 3)
    public void testUploadFile(){
        apiRequests.uploadFile(Data.mainUrl+ Data.attachmentsEndpoint+Data.attachmentsId,
                Data.pathToJpegFile, Data.cookies);
    }
}
