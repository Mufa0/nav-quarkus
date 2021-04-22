package org.five.nav.client.audit;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/audits")
@RegisterRestClient
@Produces(APPLICATION_JSON)
public interface AuditController {

    @GET
    @Path("/owner/{owner}")
    List<Audit> getAll(@PathParam("owner") String owner);

    @GET
    @Path("/user/{user}")
    List<Audit> getAllForUser(@PathParam("user") String user);

    @GET
    @Path("/{code}")
    Audit get(@PathParam("code") String code);

    @POST
    Audit create(AuditRequest request);
}
