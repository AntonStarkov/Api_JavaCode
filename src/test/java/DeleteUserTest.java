import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }
    @Test
    public void deleteSecondUser(){
        ValidatableResponse responseToDeleteUser =
                given()
                        .delete("/api/users/2")
                        .then()
                        .assertThat()
                        .statusCode(204);
    }
}
