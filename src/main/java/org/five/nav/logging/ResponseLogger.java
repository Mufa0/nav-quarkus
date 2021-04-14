package org.five.nav.logging;


import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Slf4j
public class ResponseLogger implements ContainerResponseFilter {



    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        final String status = containerResponseContext.getStatusInfo().toString();
        final String object = containerResponseContext.getEntity() != null ? containerResponseContext.getEntity().toString() : "No entity";

        log.info("Response {} {} ", status, object);
    }
}

