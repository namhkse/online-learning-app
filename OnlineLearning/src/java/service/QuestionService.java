package service;

import com.google.gson.Gson;
import dao.QuestionDAO;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import model.Question;
import util.SessionUtil;

@Path("/question")
public class QuestionService {

    private final QuestionDAO questionDAO;
    private final Gson gson;

    @Context
    private HttpServletRequest req;

    public QuestionService() {
        questionDAO = new QuestionDAO();
        gson = new Gson();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@PathParam("id") int id) {
        Question question = questionDAO.findById(id);
        return Response.ok().entity(gson.toJson(question)).build();
    }

    @GET
    @Path("/remaintime/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRemainTime(@PathParam("id") int id) {
        Map<String, String> msg = new HashMap<>();
        LocalDateTime startTime = SessionUtil.getStartQuiz(req, "quiz." + id);
        if (startTime == null) {
            msg.put("time", null);
        } else {
            LocalDateTime endTime = startTime.plusMinutes(30);
            msg.put("start", startTime.format(DateTimeFormatter.ISO_DATE_TIME));
            msg.put("finish", endTime.format(DateTimeFormatter.ISO_DATE_TIME));
        }
        return Response.ok().entity(gson.toJson(msg)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByLessonId(@QueryParam("lesson") int id) {
        List<Question> questions = questionDAO.findByLessonId(id);
        return Response.ok().entity(gson.toJson(questions)).build();
    }

    @POST
    @Path("/submit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response submitAnswer(String json) {
        System.out.println(json);
        return Response.ok().build();
    }
}
