package org.five.nav.exceptions.article;

import java.io.Serializable;

public class ArticleNotFoundException extends RuntimeException implements Serializable {

    public long id;

    public String message;

    public Exception e;

    public  ArticleNotFoundException(long id){
        this.id = id;
        this.message = String.format("Article with id: %d was not found!", id);
        this.e = null;
    }

    public ArticleNotFoundException(long  id, Exception e){
        this.id = id;
        this.message = String.format("Article with id: %d was not found!", id);
        this.e = e;
    }

}
