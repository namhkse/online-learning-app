package service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.QuestionDAO;
import dao.QuizSessionDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import model.Account;
import model.QuizLesson;
import model.QuizSession;
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
    @Path("/session/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRemainTime(@PathParam("id") int id) {
        Account account = SessionUtil.getAccount(req);
        QuizSession quizSession = new dao.QuizSessionDAO().find(account.getId(), id);

        Map<String, String> time = new HashMap<>();
        time.put("startTime", quizSession.getStartTime().format(DateTimeFormatter.ISO_DATE_TIME));
        time.put("expiredTime", quizSession.getExpiredTime().format(DateTimeFormatter.ISO_DATE_TIME));

        return Response.ok().entity(gson.toJson(time)).build();
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
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        JsonObject jsonObject = (JsonObject) jsonElement;
        int quizId = jsonObject.get("quizId").getAsInt();
        System.out.println("==== Clear start exam time ");
        Map<String, String> msg = new HashMap<>();
        try {
            new QuizSessionDAO().finishQuiz(SessionUtil.getAccount(req).getId(), quizId, LocalDateTime.now());
        } catch (Exception ex) {
            ex.printStackTrace();
            msg.put("message", ex.getMessage());
            return Response.serverError().entity(gson.toJson(msg)).build();
        }
        System.out.println(json);
        msg.put("message", "Submit Successfully");
        return Response.ok().entity(gson.toJson(msg)).build();
    }
}
