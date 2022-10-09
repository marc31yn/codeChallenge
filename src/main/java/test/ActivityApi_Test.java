package test;

import files.Payload;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class ActivityApi_Test {

    private String baseURI = "https://fakerestapi.azurewebsites.net/";

    private RequestSpecification specHeader;
    private List<Integer> idsList;

    @BeforeClass
    public void setSpecBuilder() {
        specHeader = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test(enabled = true)
    public void activities_get(){

        String resource_get = "api/v1/Activities";

        Response response = given()
                .log().all()
                .spec(specHeader)
                .when().get(resource_get)
                .then()
//                .log().all()
                .extract().response();

        System.out.println("---------------------------------------------------------------");
        System.out.println("** The status code: " + response.statusCode());
        Assert.assertEquals(response.statusCode(), 200, "Verify the status code");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();
        String bodyString = body.asString();
//        System.out.println("Response Body is: " + bodyString);

        // For parsing jsPathON
        JsonPath jsPath = new JsonPath(bodyString);

        idsList = jsPath.getList("id");
        System.out.println("ID’s: "+idsList);

//        Iterate the jsPath, to check the data response
//        for (int i = 0; i < idsList.size(); i++) {
//            System.out.println(jsPath.getInt("["+i+"].id"));
//        }

     }

     @Test(dataProvider = "ActivitiesData", enabled = true)
    public void activities_post(Integer idAct, String titleAct){

         String resource_post = "api/v1/Activities";

         Response response = given()
//                 .log().all()
                 .spec(specHeader)
                 .body(Payload.addActivity(idAct,titleAct))
                 .when().post(resource_post)
                 .then()
                .log().all()
                 .extract().response();

         System.out.println("---------------------------------------------------------------");
         System.out.println("** The status code: " + response.statusCode());
         Assert.assertEquals(response.statusCode(), 200, "Verify the status code");
         System.out.println("---------------------------------------------------------------");

//         Post with the ids List values, saved on activities_get Method
//         for (int id: idsList ) {
//
//             Response response = given()
////                 .log().all()
//                     .spec(specHeader)
//                     .body(Payload.addActivity(id,"Activity "+id))
//                     .when().post(resource_post)
//                     .then()
//                     .log().all()
//                     .extract().response();
//
//             System.out.println("---------------------------------------------------------------");
//             System.out.println("** The status code: " + response.statusCode());
//             Assert.assertEquals(response.statusCode(), 200, "Verify the status code");
//             System.out.println("---------------------------------------------------------------");
//
//         }

     }

     @Test(dependsOnMethods = {"activities_get"}, enabled = true)
     public void activitiesID_get(){

         int valueIndex = randomNum(0, idsList.size()-1);
//         System.out.println("Random index value: "+ valueIndex);
         int idRandom = idsList.get(valueIndex);
         System.out.println("Random id value: "+ idRandom);

         String resource_get = "/api/v1/Activities/{id}";

         Response response = given()
//                 .log().all()
                 .spec(specHeader)
                 .when().get(resource_get,idRandom)
                 .then()
                .log().all()
                 .extract().response();

         System.out.println("---------------------------------------------------------------");
         System.out.println("** The status code: " + response.statusCode());
         Assert.assertEquals(response.statusCode(), 200, "Verify the status code");
         System.out.println("---------------------------------------------------------------");

     }

    @DataProvider(name = "ActivitiesData")
    public Object[][] getData() {
        return new Object[][]{
                {1, "Activity Alfa"},
                {2, "Activity Omega"},
                {3, "Activity Beta"},
                {4, "Activity Theta"}
        };
    }

    public int randomNum(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
