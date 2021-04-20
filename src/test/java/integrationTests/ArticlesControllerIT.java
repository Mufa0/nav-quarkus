package integrationTests;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.common.mapper.TypeRef;
import org.five.nav.controllers.ArticleController;
import org.five.nav.dto.requests.ArticleRequest;
import org.five.nav.dto.responses.ArticleResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Rest;

import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(ArticleController.class)
public class ArticlesControllerIT {

    @Test
    public void getAllArticles_ExpectEndpointToReturnStatus200(){
        given().when().get()
                .then()
                .statusCode(200);
    }


    @Test
    @TestSecurity(user = "scott")
    public void createArticle_ExpectToReturnStatus200(){
        ArticleRequest request = ArticleRequest.builder()
                .content("This is content for testing!")
                .title("Title")
                .build();

        Rest.post(request,"").then().statusCode(200);
    }

    @Test
    @TestSecurity(user="scott")
    public void createArticle_ExpectExactArticleToBeReturned(){
        ArticleRequest request = ArticleRequest.builder()
                .content("This should be same!")
                .title("Same")
                .build();
        ArticleResponse response = Rest.post(request,"", ArticleResponse.class);

        Assertions.assertEquals(request.getContent(), response.getContent());
        Assertions.assertEquals(request.getTitle(), response.getTitle());
    }

    @Test
    @TestSecurity(user="bill")
    public void getArticlesFromUser_ExpectOnlyArticlesWhereUserIsAuthor(){
        List<String> titles = List.of("Title one", "Title two", "Title three");
        for(String title: titles){
            ArticleRequest request = ArticleRequest.builder().content("Content").title(title).build();
            Rest.post(request,"",ArticleResponse.class);
        }

        List<ArticleResponse> articles = Rest.get("/user", new TypeRef<>(){});

        Assertions.assertTrue(articles.size() == titles.size());
    }

    @Test
    @TestSecurity(user="scott")
    public void updateArticle_ExpectAllRequiredFieldsToBeUpdated(){
        ArticleRequest request = ArticleRequest.builder().content("Content").title("Title").build();
        ArticleResponse response = Rest.post(request,"", ArticleResponse.class);

        ArticleRequest updateRequest = ArticleRequest.builder().content("Content Updated").title("Title Updated").build();
        ArticleResponse updated = Rest.put(updateRequest, "/"+response.getId(),ArticleResponse.class);

        Assertions.assertEquals(updateRequest.getTitle(), updated.getTitle());
        Assertions.assertEquals(updateRequest.getContent(), updated.getContent());

    }

    @Test
    @TestSecurity(user="scott")
    public void deleteArticle_ExpectLowerNumberOfArticlesOnUser(){
        List<ArticleResponse> articles = Rest.get("/user", new TypeRef<>(){});
        if(articles.size() == 0){
            List<String> titles = List.of("Title one", "Title two", "Title three");
            for(String title: titles){
                ArticleRequest request = ArticleRequest.builder().content("Content").title(title).build();
                Rest.post(request,"",ArticleResponse.class);
            }
            articles = Rest.get("/user", new TypeRef<>(){});
        }

        Rest.delete("/"+articles.get(0).getId()).then().statusCode(200);
        List<ArticleResponse> afterDeletion = Rest.get("/user", new TypeRef<>(){});
        Assertions.assertEquals(afterDeletion.size(),articles.size()-1);
    }

}
