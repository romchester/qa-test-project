import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    private static final String END_POINT_POSTS = "https://jsonplaceholder.typicode.com/posts";
    private static final String END_POINT_COMMENTS = "https://jsonplaceholder.typicode.com/comments";

    @Test
    public void getPostById1() {
        int postId = 1;

        given()
                .pathParam("id", postId)
                .when()
                .get(END_POINT_POSTS + "/{id}")
                .then()
                .statusCode(200)
                .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
                .body("userId", equalTo(1));
    }

    @Test
    public void getCommentsByPostId() {
        given()
                .queryParam("postId", 1)
                .when()
                .get(END_POINT_COMMENTS)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].email", notNullValue())
                .body("every { it.postId == 1 }", is(true));
    }

    @Test
    public void createPostAndExtractId() {
        String creatingPost = """
                {
                    "title": "My Test Post",
                    "body": "This is a test",
                    "userId": 2
                }
                """;

        int createdId =
                given()
                        .contentType(ContentType.JSON)
                        .body(creatingPost)
                        .when()
                        .post(END_POINT_POSTS)
                        .then()
                        .statusCode(201)
                        .body("title", equalTo("My Test Post"))
                        .body("userId", equalTo(2))
                        .body("id", notNullValue())
                        .extract()
                        .path("id");

        System.out.println("Created ID: " + createdId);
    }

    @Test
    public void deletePost() {
        int postId = 1;

        given()
                .pathParam("id", postId)
                .when()
                .delete(END_POINT_POSTS + "/{id}")
                .then()
                .statusCode(200);
    }
}