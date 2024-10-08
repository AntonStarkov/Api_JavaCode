import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import ru.javacode.pojo.ListOfUsers;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ListOfUsersTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }
    @Test
    public void getListOfUsers(){
        ListOfUsers listOfUsers =
                given()
                        .get("/api/users")
                        .body()
                        .as(ListOfUsers.class);
        List<String> emails = new ArrayList<>();
        for (int i = 1; i <= listOfUsers.getTotal_pages(); i++){
            ListOfUsers listOfUsersPerPage =
                    given()
                            .get("/api/users?page=" + i)
                            .body()
                            .as(ListOfUsers.class);
            for (int k = 0; k < listOfUsersPerPage.getPer_page(); k++) {
                emails.add(listOfUsersPerPage.getData().get(k).getEmail());
            }
        }
        MatcherAssert.assertThat(emails, everyItem(containsString("@reqres.in")));
    }
}
