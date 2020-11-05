package rmunteanu.controller;

import rmunteanu.models.Languages;
import rmunteanu.models.wordDictContainer;
import rmunteanu.models.wordsDict;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "Tema2Servlet",
        urlPatterns = {"/controller", "/captcha"})

public class Lab2Servlet extends HttpServlet {
    Languages languages = new Languages();

    @Override
    public void init() throws ServletException {
        super.init();
    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        if(session.getAttribute("languages") == null)
            session.setAttribute("languages", languages);

        getServletContext().log("Current languages " + languages.toString());

        CaptchaGenerator captcha = new CaptchaGenerator();
        captcha.generateRandomMathString();
        String captchaString = captcha.captcha;
        session.setAttribute("captchaResult", captcha.valueOfCaptcha);

        // send captcha image as response
//        response.setContentType("image/png");

//        response.setContentType("text/html");

        captcha.CreateCaptcha(captchaString);

        //getServletContext().log("Current categories " + categories.toString());
        RequestDispatcher rd=request.getRequestDispatcher("jsp/input.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Languages languages;
        wordDictContainer entryContainer;
//        RecordContainer recordContainer;
        HttpSession session = request.getSession(true);

        Cookie lastLanguage = new Cookie("tema2.lastLanguage", request.getParameter("language"));
        lastLanguage.setComment("Last language picked");
        lastLanguage.setMaxAge(60*60*24); //in seconds
        response.addCookie(lastLanguage);

        if (session.isNew())
        {
            entryContainer = new wordDictContainer();
        }
        else
        {
            entryContainer = (wordDictContainer) session.getAttribute("entryContainer");

            if (entryContainer == null)
            {
                entryContainer = new wordDictContainer();
            }
        }

        languages = (Languages) session.getAttribute("languages");

        if (languages == null)
            languages = new Languages();

        response.setContentType("text/html");

        wordsDict entry = new wordsDict();
        entry.setLanguage(request.getParameter("language"));
        entry.setWord(request.getParameter("word"));
        entry.setDefinition(request.getParameter("definition"));
        String captchaInput = request.getParameter("captcha");
        String actualCaptcha = session.getAttribute("captchaResult").toString();
        if (captchaInput.equals(actualCaptcha)) {
            try {
                entryContainer.addRecord(entry);
                session.setAttribute("entryContainer", new wordDictContainer(entryContainer));
                session.setAttribute("languages", new Languages(languages));
                request.setAttribute("entries", entryContainer.getAllRecords());
                RequestDispatcher rd = request.getRequestDispatcher("jsp/result.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                getServletContext().log("Received the following exception " + e.toString());
                request.setAttribute("error", e.getMessage());
                RequestDispatcher rd = request.getRequestDispatcher("jsp/error.jsp");
                rd.forward(request, response);
            }
        }
        else
        {
            request.setAttribute("error", "wrong captcha");
            RequestDispatcher rd = request.getRequestDispatcher("jsp/error.jsp");
            rd.forward(request, response);
        }
    }
}
