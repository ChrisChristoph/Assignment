package org.chris;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ConnectionResourceTest {

    @Test
    public void testPingEndpoint() {
        given()
                .when().get("/ping")
                .then()
                .statusCode(200)
                .body(is("pong"));
    }

}