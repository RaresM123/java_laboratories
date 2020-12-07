package rmunteanu.meeting_problem;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.chocosolver.solver.*;
import org.chocosolver.solver.variables.*;
import org.chocosolver.solver.constraints.*;
import rmunteanu.model.FileNameBean;

public class MeetingProblemSolver {

    Solver solverProblem;
    int meetingsNr;
    int agentsNr;
    int timespacesForMeetings;
    int[][] participation;
    int[][] distanceBetweenMeetings;

    IntVar[] meeting;
    static Map<Integer, Integer> results = new HashMap<>();

    public void Init(FileNameBean fileNameBean) throws IOException {
        try (Scanner sc = new Scanner(new File(fileNameBean.getFilename()))) {

            meetingsNr = sc.nextInt();
            agentsNr = sc.nextInt();
            timespacesForMeetings = sc.nextInt();

            participation = new int[agentsNr][meetingsNr];
            distanceBetweenMeetings = new int[meetingsNr][meetingsNr];

            for (int i = 0; i < agentsNr; i++) {
                int n = 0;
                sc.next();
                for (int j = 0; j < meetingsNr; j++) {
                    participation[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < meetingsNr; i++) {
                sc.next();
                for (int j = 0; j < meetingsNr; j++) {
                    distanceBetweenMeetings[i][j] = sc.nextInt();
                }
            }

        }
    }
    public void constraintsSolver(){
        for (int m1 = 0; m1 < meetingsNr; m1 ++) {
            for (int m2 = m1 + 1; m2 < meetingsNr; m2++) {
                boolean parallelMeetings = checkParalelMeetigsPosibility(m1,m2);
                if(!parallelMeetings) {
                    Constraint first_constraint = ICF.arithm(meeting[m1],"-",meeting[m2],">",distanceBetweenMeetings[m1][m2]);
                    Constraint second_constraint = ICF.arithm(meeting[m2],"-",meeting[m1],">",distanceBetweenMeetings[m1][m2]);

                    solverProblem.post(LCF.or(first_constraint,second_constraint));
                }
            }
        }

    }

    public MeetingProblemSolver(FileNameBean fileNameBean) throws IOException {
            Init(fileNameBean);
            solverProblem = new Solver("meeting scheduling problem"); // create an instance of Solver

            meeting = VF.enumeratedArray("meetings for all persons", meetingsNr,
                    0, timespacesForMeetings - 1, solverProblem);

            constraintsSolver();
        }


    boolean checkParalelMeetigsPosibility(int m1, int m2) {
        boolean notSameMeeting = true;
        for (int  i = 0; i < agentsNr; i++) {
            if (participation[i][m1] == 1 && participation[i][m2] == 1) {
                notSameMeeting = false;
                break;
            }
        }
        return notSameMeeting;
    }

    public boolean solveProblem() {
        return solverProblem.findSolution();
    }

    public void populateResults() {

        for (int i = 0; i < meetingsNr; i++) {
//            System.out.println(i + " " + meeting[i].getValue());
            results.put(i, meeting[i].getValue());
        }
    }

    public static Map<Integer, Integer> getResults(){
        return results;
    }
}
