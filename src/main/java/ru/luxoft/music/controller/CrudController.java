package ru.luxoft.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.luxoft.music.models.Song;
import ru.luxoft.music.services.AbstractService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * CrudController.
 *
 * @author Evgeniy_Medvedev
 */
@RestController
public class CrudController {

    private final AbstractService<Song> songService;

    private final JmsTemplate producer;

    private final Logger logg = Logger.getLogger(CrudController.class.getName());

    public CrudController(AbstractService<Song> songService, JmsTemplate producer) {
        this.songService = songService;
        this.producer = producer;
    }

    @JmsListener(destination = "java:jms/queue/music/askedBy")
    @SendTo("java:jms/queue/music/askedBy-answer")
    public List<Song> getSong(String parameter) {
        if (parameter.equals("findAll")) {
            return songService.getAll();
        }
        return songService.takeBySomething(parameter);
    }

    @JmsListener(destination = "java:jms/queue/music/create")
    @SendTo("java:jms/queue/music/create-answer")
    public UUID createSong(Song song) {
        logg.info("song is being created - " + song);
        return songService.create(song);
    }

    @JmsListener(destination = "java:jms/queue/music/update")
    @SendTo("java:jms/queue/music/update-answer")
    public boolean updateSong(Song song) {
        logg.info(String.format("Song %s is being updated", song));
        return songService.update(song);
    }

    @JmsListener(destination = "java:jms/queue/music/delete")
    @SendTo("java:jms/queue/music/delete-answer")
    public boolean deleteSong(Song song) {
        logg.info(String.format("Song %s is being deleted", song));
        return songService.delete(song);
    }

    @GetMapping("/sendTest")
    public void sendTest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String mode = request.getParameter("mode");
        String prefix = request.getParameter("prefix");

        if (prefix == null || "".equals(prefix))
            prefix = "prefix";

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String msg;
        if (mode.equalsIgnoreCase("send")) {
            out.print("send messages :<br>");
            for (int i = 4; i > 0; i--) {
                msg = prefix + " #" + i;
                out.print("&bull; " + msg + "<br />");
                String some = String.valueOf(i);
                producer.convertAndSend("java:jms/queue/music", some);
            }
        } else {
            out.print("receive messages :<br>");
            for (int i = 10; i > 0; i--) {
                out.print("&mdash; nothing <br />");
            }

        }
        out.close();
    }
}