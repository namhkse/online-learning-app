package service;

import com.google.gson.Gson;
import dao.StatisticDAO;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/statistics")
public class StatistService {

    private final StatisticDAO statistDAO;
    private final Gson gson;

    public StatistService() {
        statistDAO = new StatisticDAO();
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

    /**
     * Default get before 7 months
     *
     * @return
     */
    @GET
    @Path("/revenue")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchRevenue() {
        Response resp = null;
        LocalDate today = LocalDate.now();
        LocalDate before7Months = today.minusMonths(7);
        try {
            List table = statistDAO.calculateRevenues(before7Months.getMonthValue(),
                    before7Months.getYear(),
                    today.getMonthValue(),
                    today.getYear());
            String json = gson.toJson(table, List.class);
            resp = Response.ok(json).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
        return resp;
    }

    @GET
    @Path("/revenue/{m1}/{y1}/{m2}/{y2}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchRevenue(@PathParam("m1") int m1,
            @PathParam("y1") int y1,
            @PathParam("m2") int m2,
            @PathParam("y2") int y2) {
        Response resp = null;

        try {
            List table = statistDAO.calculateRevenues(m1, y1, m2, y2);
            String json = gson.toJson(table, List.class);
            resp = Response.ok(json).build();
        } catch (Exception ex) {
            ex.printStackTrace();
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

    @GET
    @Path("/amount_account_subject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchNumberOfAccountInAllSubject() {
        Response resp = null;
        try {
            List table = statistDAO.getAmountEnrollInAllSubject();
            String json = gson.toJson(table, List.class);
            resp = Response.ok(json).build();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
        return resp;
    }

    @GET
    @Path("/test")
    public Response test() {
        return Response.ok().build();
    }

    @GET
    @Path("/registration/{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countRegistration(@PathParam("from") String f, @PathParam("to") String t) {
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate from, to;
        try {
            from = LocalDate.parse(f, dft);
            to = LocalDate.parse(t, dft);
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        List ls = statistDAO.countRegistration(from, to);
        return Response.ok(gson.toJson(ls)).build();
    }

    @GET
    @Path("/revenue/subject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevenueOfAllSubject() {
        return Response.ok(gson.toJson(statistDAO.getRevenueOfAllSubject(null, null))).build();
    }

    @GET
    @Path("/revenue/subject/{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevenueOfAllSubject(@PathParam("from") String f, @PathParam("to") String t) {
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate from = null;
        LocalDate to = null;
        try {
            from = LocalDate.parse(f, dft);
            to = LocalDate.parse(t, dft);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok(gson.toJson(statistDAO.getRevenueOfAllSubject(from, to))).build();
    }
}
