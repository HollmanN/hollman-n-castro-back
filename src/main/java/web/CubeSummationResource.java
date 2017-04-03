package web;

import domain.TestCase;
import service.CubeSummationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cube-summation")
public class CubeSummationResource {


    @Inject
    CubeSummationService cubeSummationService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response test(){
        return Response.ok(cubeSummationService.helloService()).build();
    }

    @POST
    @Path("/run")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response cubeSummation(TestCase testCase){

        return Response.ok(cubeSummationService.run(testCase)).build();
    }
}
