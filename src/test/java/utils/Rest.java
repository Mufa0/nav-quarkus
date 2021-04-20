package utils;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.five.nav.dto.requests.Request;


public class Rest {

    public static <T> T post(Request body, String resource, Class<T> type){
        return post(body,resource).as(type);
    }

    public static Response post(Request body, String resource){
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");
        request.body(body);
        return request.post(resource);
    }

    public static <T> T put(Request body, String resource, Class<T> type){
        return put(body, resource).as(type);
    }

    public static Response put(Request body, String resource){
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");
        request.body(body);
        return request.put(resource);
    }

    public static Response get(String resource){
        RequestSpecification request = RestAssured.given();
        return request.get(resource);
    }

    public static <T> T get(String resource, Class<T> type){
        return get(resource).as(type);
    }

    public static <T> T get(String resource, TypeRef<T> type){
        return get(resource).as(type);
    }

    public static Response delete(String resource){
        RequestSpecification request = RestAssured.given();
        return request.delete(resource);
    }

}
