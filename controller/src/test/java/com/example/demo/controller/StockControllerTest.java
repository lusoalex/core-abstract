package com.example.demo.controller;

import com.example.demo.dto.in.StockFilter;
import com.example.demo.dto.out.StockDto;
import io.restassured.RestAssured;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import static io.restassured.RestAssured.given;
import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StockControllerTest {

  @Autowired
  private ResourceLoader resourceLoader;

  @BeforeTestClass
  public void setUp() {
    RestAssured.port = 8080;
  }

  @Test
  @DisplayName("Call Get /stock endpoint and return empty stock")
  public void call_get_stock_endpoint_return_empty_stock() {
    StockFilter filter = new StockFilter();
    filter.setName("test");
    given().header("version", 2)
            .header("Content-Type", "application/json")
            .body(filter)
            .get("/stock")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("state", CoreMatchers.equalTo(StockDto.State.EMPTY.toString()));
  }

  @Test
  @DisplayName("Call PATCH /stock endpoint to successfully update a stock")
  public void call_patch_stock_endpoint_to_update_stock() {

    String request = asString(resourceLoader.getResource("classpath:minimal-patch-test-case/request.json"));
    String expectedResponse = asString(resourceLoader.getResource("classpath:minimal-patch-test-case/response.json"));

    String response = given().header("version", 2)
                        .header("Content-Type", "application/json")
                        .body(request)
                        .patch("/stock")
                        .then()
                        .statusCode(HttpStatus.OK.value())
                        .body("state", CoreMatchers.equalTo(StockDto.State.SOME.toString()))
                        .extract()
                        .asString();

    Assertions.assertEquals(expectedResponse, response);

  }

  @Test
  @DisplayName("Call PATCH /stock endpoint to successfully update a big stock")
  public void call_patch_stock_endpoint_to_update_big_stock() {

    String request = asString(resourceLoader.getResource("classpath:big-patch-test-case/request.json"));
    String expectedResponse = asString(resourceLoader.getResource("classpath:big-patch-test-case/response.json"));

    String response = given().header("version", 2)
            .header("Content-Type", "application/json")
            .body(request)
            .patch("/stock")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("state", CoreMatchers.equalTo(StockDto.State.FULL.toString()))
            .extract()
            .asString();

    Assertions.assertEquals(expectedResponse, response);

  }

  @Test
  @DisplayName("Call PATCH /stock endpoint to successfully update a stock many times")
  public void call_patch_stock_endpoint_to_update_stock_many_times() {

    String request = asString(resourceLoader.getResource("classpath:minimal-patch-test-case/request.json"));
    String expectedResponse = asString(resourceLoader.getResource("classpath:minimal-patch-test-case/response_many_times.json"));

    given().header("version", 2)
            .header("Content-Type", "application/json")
            .body(request)
            .patch("/stock")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("state", CoreMatchers.equalTo(StockDto.State.SOME.toString()))
            .extract()
            .asString();

    String response = given().header("version", 2)
            .header("Content-Type", "application/json")
            .body(request)
            .patch("/stock")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("state", CoreMatchers.equalTo(StockDto.State.FULL.toString()))
            .extract()
            .asString();

    Assertions.assertEquals(expectedResponse, response);

  }

  @Test
  @DisplayName("Call PATCH /stock endpoint to unsuccessfully update a full stock")
  public void call_patch_stock_endpoint_to_update_full_stock() {

    String requestFull = asString(resourceLoader.getResource("classpath:full-stock-patch-test-case/request_full.json"));
    String request = asString(resourceLoader.getResource("classpath:full-stock-patch-test-case/request.json"));
    String expectedResponse = asString(resourceLoader.getResource("classpath:full-stock-patch-test-case/response.json"));

    given().header("version", 2)
            .header("Content-Type", "application/json")
            .body(requestFull)
            .patch("/stock")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("state", CoreMatchers.equalTo(StockDto.State.FULL.toString()))
            .extract()
            .asString();

    String response = given().header("version", 2)
            .header("Content-Type", "application/json")
            .body(request)
            .patch("/stock")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .extract()
            .asString();

    Assertions.assertEquals(expectedResponse, response);

  }

  @Test
  @DisplayName("Call PATCH /stock endpoint to unsuccessfully update a stock & exceed capacity")
  public void call_patch_stock_endpoint_to_update_stock_and_exceed_capacity() {

    String request = asString(resourceLoader.getResource("classpath:exceed-capacity-stock-patch-test-case/request.json"));
    String requestExceedCapacity = asString(resourceLoader.getResource("classpath:exceed-capacity-stock-patch-test-case/request_exceed_capacity.json"));
    String expectedResponse = asString(resourceLoader.getResource("classpath:exceed-capacity-stock-patch-test-case/response.json"));

    given().header("version", 2)
            .header("Content-Type", "application/json")
            .body(request)
            .patch("/stock")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("state", CoreMatchers.equalTo(StockDto.State.SOME.toString()))
            .extract()
            .asString();

    String response = given().header("version", 2)
            .header("Content-Type", "application/json")
            .body(requestExceedCapacity)
            .patch("/stock")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .extract()
            .asString();

    Assertions.assertEquals(expectedResponse, response);

  }

  @Test
  @DisplayName("Call PATCH /stock endpoint to unsuccessfully update a stock with negative quatity")
  public void call_patch_stock_endpoint_to_update_stock_with_negative_quantity() {

    String request = asString(resourceLoader.getResource("classpath:negative-quantity-stock-patch-test-case/request.json"));
    String expectedResponse = asString(resourceLoader.getResource("classpath:negative-quantity-stock-patch-test-case/response.json"));

    String response = given().header("version", 2)
            .header("Content-Type", "application/json")
            .body(request)
            .patch("/stock")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .extract()
            .asString();

    Assertions.assertEquals(expectedResponse, response);

  }


  public static String asString(Resource resource) {
    try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
      return FileCopyUtils.copyToString(reader);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }


}
