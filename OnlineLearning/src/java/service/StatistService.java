package service;

import com.google.gson.Gson;
import dao.StatistDAO;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/statist")
public class StatistService {
    
    private final StatistDAO statistDAO;
    private final Gson gson;
    
    public StatistService() {
        statistDAO = new StatistDAO();
        gson = new Gson();
    }
    
    @GET
    @Path("/coursesubject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchAmountCourseOfAllSubject() {
        Response resp = null;
        try {
            List table = statistDAO.countCourseInAllSubject();
            String json = gson.toJson(table, List.class);
            resp = Response.ok(json).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
        return resp;
    }
    
    @GET
    @Path("/enrollcourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchAmountEnrollInAllCourse() {
        Response resp = null;
        try {
            List table = statistDAO.countNumberEnrollInAllCourse();
            String json = gson.toJson(table, List.class);
            resp = Response.ok(json).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
        return resp;
    }
    
    @GET
    @Path("/revenue")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchRevenue() {
        Response resp = null;
        try {
            List table = statistDAO.calculateRevenues();
            String json = gson.toJson(table, List.class);
            resp = Response.ok(json).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
        return resp;
    }
    
    @GET
    @Path("/blogcategorytrend")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchNumberOfViewInAllBlogCategory() {
        Response resp = null;
        try {
            List table = statistDAO.countNumberViewOfAllBlogCategory();
            String json = gson.toJson(table, List.class);
            resp = Response.ok(json).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
        return resp;
    }
}
