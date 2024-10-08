import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
public class CustomerRegisterTest {
    private int expectedStatusCode;
    private String jsonLoginPassword;
    public CustomerRegisterTest (String jsonLoginPassword, int expectedStatusCode){
        this.jsonLoginPassword = jsonLoginPassword;
        this.expectedStatusCode = expectedStatusCode;
    }
    @Parameterized.Parameters
    public static Object[][] loginPassword(){
        return new Object[][] {
                {"{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}", 200},
                {"{\"email\": \"eve.holt@reqres.in\"}", 400}
        };
    }
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }
    @Test
    public void successfulRegistration () {
        ValidatableResponse responseToRegistration =
                given()
                        .header("Content-type", "application/json")
                        .body(jsonLoginPassword)
                        .when()
                        .post("/api/register")
                        .then()
                        .assertThat()
                        .statusCode(expectedStatusCode);
    }
}
