import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.json.JsonArray;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet(name = "Tema1Servet",
        urlPatterns = {"/tema"})

public class tema1 extends HttpServlet{

    private Map<Integer, List<String>> list_of_words;
    private String jsonFileName;
    private JSONObject wordsDict;

    private void logClientInfo(ServletContext context, HttpServletRequest request) {
        context.log(String.format("Method: %s --- IP: %s --- User-Agent: %s --- Language: %s --- Parameters: %s",
                request.getMethod(), request.getRemoteAddr(), request.getHeader("User-Agent"),
                request.getHeader("Accept-Language"), request.getParameterMap().toString()));
    }

    public JSONObject getJson(){
        JSONObject wordsList = null;
        JSONParser jsonParser = new JSONParser();
        System.out.println(jsonFileName);
        try (FileReader reader = new FileReader(jsonFileName)) {
            //Read JSON file
//            System.out.println(reader.toString());
            Object obj = jsonParser.parse(reader);

            wordsList = (JSONObject) obj;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return wordsList;
    }

    public void populateHashMap(){

        for(Object key : wordsDict.keySet()){

            List<String> words = new ArrayList<>();

            JSONArray arrayWords = (JSONArray)wordsDict.get(key);
            for (Object arrayWord : arrayWords) {
                words.add((String) arrayWord);
            }
            list_of_words.put(Integer.valueOf((String)key), words);
        }
    }

    private List<String> intersect(List<String> A, List<String> B) {
        List<String> rtnList = new LinkedList<>();
        for(String dto : A) {
            if(B.contains(dto)) {
                rtnList.add(dto);
            }
        }
        return rtnList;
    }

    private static List<String> WordsRec(String base, int length, String characters) {
        List<String> combinations = new ArrayList<String>();
        for (int i = 0; i < characters.length(); i++){
            char c = characters.charAt(i);
            if (length == 1) {
                combinations.add(base + String.valueOf(c));
            }
            else{
                combinations.addAll(WordsRec(base+c,length-1,characters));
            }
        }
        return combinations;
    }
    public List<String> processCombinations(String letters, int number)
    {
        List<String> returnList = null;
//        char[] lettersArray = letters.toCharArray();
        returnList = WordsRec("", number, letters);
        return returnList;
    }
    public List<String> processMap(int number, String letters){
        List<String> currentWords = list_of_words.get(number);
        List<String> formatedWords = processCombinations(letters, number);
        List<String> validWords = intersect(currentWords, formatedWords);
        return validWords;
    }

    @Override
    public void init() throws ServletException{
        super.init();
        list_of_words = new HashMap<>();
        jsonFileName = "D:\\FAC_MASTER\\AN1\\SEM1\\JAVA\\tema1\\src\\main\\java\\words.json";
        wordsDict = getJson();
        populateHashMap();
    }


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = getServletContext();
        logClientInfo(context, request);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        String letters = (String)request.getParameter("letters");
        if(letters==null) {
            out.println("Not valid string");
            return;
        }
        Integer lengthLetters = letters.length();
        List<String> validStrings = new ArrayList<>();
        for(int i = 1; i <= lengthLetters; i++){

            validStrings.addAll(processMap(i, letters));

        }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Valid Words</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Valid Words list:</h1>");
            out.println("<table border=\"0\"><tr><th>Words</th>");
            for (String str : validStrings){
                out.println("<tr><td>" + str + "</td><td>");
            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String value = request.getParameter("letters");

        ServletContext context = getServletContext();
        logClientInfo(context, request);
        context.log(String.format("Received value for letters %s", value));
//        System.out.println(context.getRealPath("/"));
        FileWriter writer = new FileWriter(context.getRealPath("/") + "validWords.txt", false);
//        writer.write(context.getRealPath("/"));
        if(value == null){
            writer.write("The key is not valid for value");
            writer.close();
            return;
        }

        Integer lengthLetters = value.length();
        List<String> validStrings = new ArrayList<>();
        for(int i = 1; i <= lengthLetters; i++){

            validStrings.addAll(processMap(i, value));

        }
        writer.write("List of valid Strings:\n");
        for (String str : validStrings){
            writer.write(str + "\n");
        }
        writer.close();
        response.sendRedirect("/tema1-1.0-SNAPSHOT/validWords.txt");
    }

}
