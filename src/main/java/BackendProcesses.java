import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class BackendProcesses {



    public void uploadFile(String screeningFile, String screeningEndpoint, String cookies) {
        System.out.println(screeningEndpoint);
        // String fullCookies = "auth_token="+ cookies;
        System.out.println(cookies);
        given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "multipart/form-data")
                .cookie("auth_token", cookies)
                .multiPart(new File(screeningFile))
                .when()
                .post(screeningEndpoint)
                .then()
                .statusCode(200);
    }

    public void login(String endpoint) {
        String loginEndpoint = "https://app.hellosign.com/account/logIn";
        RequestSpecification httpRequest =  RestAssured.given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "multipart/form-data")
                .queryParam("login[email_address]", "alex.lavre2@gmail.com")
                .queryParam("login[password]", "Emirates@2019");

        Response response = httpRequest.post(loginEndpoint);
        response.getBody().prettyPrint();
    }

    public void samlLoginVerification() {
        String samplEndpoint = "https://app.hellosign.com/account/samlLoginVerification";
        RequestSpecification httpRequest =  RestAssured.given()
                .relaxedHTTPSValidation()
                .queryParam("csrfToken", "dd77177f43d0564d2ccf82df11d90b17158d1b9719c47f1c911c72a1ceddd44d")
                .queryParam("account", "alex.lavre2@gmail.com")
                .when();
        Response response = httpRequest.post(samplEndpoint);
        response.getBody().prettyPrint();
    }
//https://app.hellosign.com/attachment/conversionStatus/is_template/0/form_type_code/1?preloaded_tsm_group_key=d063a3a7f526d8f966d1dee07e40cfd7b670bfdd&root_snapshot_guid=57bdf928480ad203aa2afebf2679ca3529d530e7&unique_token=2364206488&c=0.5809055997485417
    //https://app.hellosign.com/attachment/asyncReorderSnapshots?c=0.7314588056674436
}
