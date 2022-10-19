package test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.CoverPhoto;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class BookApi_Test {

    private String baseURI = "https://fakerestapi.azurewebsites.net/";

    private RequestSpecification specHeader;

    @BeforeClass
    public void setSpecBuilder() {
        specHeader = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test(enabled = true)
    public void books_get(){

        System.out.println("---- Second Section: T10 Verify the Schema Book endpoint – GET ----");

        String resource_get = "api/v1/Books";

        System.out.println("-------------------------- SCHEMA VALIDATION -------------------------------------");

        File schema = new File(System.getProperty("user.dir")+"\\src\\main\\java\\files\\schemaBook.json");

        Response response = given()
                .spec(specHeader)
                .when().get(resource_get)
                .then()
//                .log().all()
                .assertThat().statusCode(200)
                .body(matchesJsonSchema(schema))
                .extract().response();

        System.out.println("-------------------------- Printing response headers -----------------------------");

        Headers allHeaders = response.headers();
        // Iterate over all the Headers
        for(Header header : allHeaders) {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }

    }

    @Test
    public void covertPhotos_get(){

        System.out.println("---- Third Section: T11 Verify the CoverPhoto endpoint – GET ----");

        String resource_get = "api/v1/CoverPhotos";

        Response response = given()
//                .log().all()
                .spec(specHeader)
                .when().get(resource_get)
                .then()
//                .log().all()
                .extract().response();

        System.out.println("-------------------------- RESPONSE WITH POJO-------------------------------------");
        Assert.assertEquals(response.statusCode(), 200, "Verify status code. CoverPhotos/ API ");

        JsonPath jsonPath = response.jsonPath();
        List<CoverPhoto> coverPhtList = jsonPath.getList("$", CoverPhoto.class);

        for (CoverPhoto cover: coverPhtList) {
//            System.out.println("Id: "+cover.getId());
            System.out.println("IdBook: "+cover.getIdBook());
            System.out.println("Url:: "+cover.getUrl());
            System.out.println("---------------------------");
        }




    }
}
