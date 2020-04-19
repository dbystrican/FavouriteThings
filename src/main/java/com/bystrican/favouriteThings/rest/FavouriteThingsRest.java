package com.bystrican.favouriteThings.rest;

import com.bystrican.favouriteThings.JDBC.FavouriteThing;
import com.bystrican.favouriteThings.bussiness.FavouriteThingsBusinessInterface;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("things")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FavouriteThingsRest {

    @Inject
    FavouriteThingsBusinessInterface favouriteThingsBussinessInterface;

    @GET
    @Path("")
    public Response getAllThings() {
        return Response.ok(favouriteThingsBussinessInterface.read()).build();
    }

    @GET
    @Path("{id}")
    public Response getThing(@PathParam("id") final int id) {
        FavouriteThing favouriteThing = favouriteThingsBussinessInterface.read(id);
        if(favouriteThing==null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(favouriteThingsBussinessInterface.read(id)).build();
    }

    @POST
    @Path("")
    public Response PostThing(FavouriteThing favouriteThing) {
        favouriteThingsBussinessInterface.insert(favouriteThing);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response PutThing(@PathParam("id") final int id,FavouriteThing favouriteThing) {
        return Response.ok(favouriteThingsBussinessInterface.update(id,favouriteThing)).build();
    }
    @DELETE
    @Path("{id}")
    public Response deleteThing(@PathParam("id") final int id) {
       favouriteThingsBussinessInterface.delete(id);
       return Response.ok().build();
    }

}
