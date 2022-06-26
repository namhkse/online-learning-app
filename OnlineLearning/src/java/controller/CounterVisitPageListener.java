package controller;

import dao.StatistDAO;
import java.time.LocalDate;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class CounterVisitPageListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        new StatistDAO().plusNumberVisistPage(LocalDate.now(), 1);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }

}
