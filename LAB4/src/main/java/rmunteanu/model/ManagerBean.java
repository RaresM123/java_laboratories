package rmunteanu.model;

import rmunteanu.dbutils.dbUtils;
import rmunteanu.meeting_problem.MeetingProblemSolver;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "managerBean")
@RequestScoped
public class ManagerBean implements Serializable {

    private List<PersonBean> persons;
    public void addPerson(PersonBean person){
        dbUtils.insertPerson(person);
    }
    public void addMeeting(MeetingBean meeting){
        dbUtils.insertMeeting(meeting);
    }

    public List<PersonBean> getPersons(){
        return dbUtils.retrievePersons();
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
}
