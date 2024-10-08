import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javacode.pojo.UpdateUser;

import java.time.Instant;

import static io.restassured.RestAssured.given;

public class UpdateUserInformationTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }
    @Test
    public void takeAndCompareDate() {
        UpdateUser updateUser =
                given()
                        .header("Content-type", "application/json")
                        .body("{\"name\": \"morpheus\", \"job\": \"zion resident\"}")
                        .patch("/api/users/2")
                        .body()
                        .as(UpdateUser.class);
        String date1 = Instant.now().toString().substring(0, 16);
        String date2 = updateUser.getUpdatedAt().substring(0, 16);
        Assert.assertEquals(date1, date2);
    }
}
