package org.five.nav.exceptions;

import java.io.Serializable;

public class ArticleNotFoundException extends Exception implements Serializable {

    public ArticleNotFoundException(){
        super ();
    }

    public ArticleNotFoundException(String msg){
        super(msg);
    }

    public ArticleNotFoundException(String msg, Exception e){
        super(msg,e);
    }

}
