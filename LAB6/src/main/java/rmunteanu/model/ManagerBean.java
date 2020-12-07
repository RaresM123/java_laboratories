package rmunteanu.model;

import rmunteanu.dbutils.dbUtils;
import rmunteanu.meeting_problem.MeetingProblemSolver;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "managerBean")
@RequestScoped
public class ManagerBean implements Serializable {

    private List<PersonBean> persons;
    private List<LocationBean> locations;

    public void addPerson(PersonBean person){
        dbUtils.insertPerson(person);
    }
    public void addMeeting(MeetingBean meeting){
        dbUtils.insertMeeting(meeting);
    }
    public void addLocation(LocationBean location) {dbUtils.insertLocation(location); }

    public List<PersonBean> getPersons(){
        return dbUtils.retrievePersons();
    }
    public List<LocationBean> getLocations(){
        return dbUtils.retrieveLocations();
    }

    public void solveMeetingProblem(FileNameBean fileNameBean) throws IOException {
        MeetingProblemSolver solver = new MeetingProblemSolver(fileNameBean);
        if (solver.solveProblem()){
            solver.populateResults();
        }
    }

    public List<Map.Entry<Integer, Integer>> getMeetingsResults(){

        return new ArrayList(MeetingProblemSolver.getResults().entrySet());
    }

    public static List<MeetingBean> MeetingsSearchAlg(String topic, String personName, String locationName)
    {
        try {
            return dbUtils.searchMeetingsAlgorithm(topic,personName,locationName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ArrayList<>();
        }
    }
}
