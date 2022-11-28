package FileHelpers;

import Entities.Faculty;
import Entities.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class JsonHelper {
    public static ArrayList<Student> readStudents(String path){
        JSONParser parser = new JSONParser();

        try(FileReader reader = new FileReader(path)) {
            var obj = parser.parse(reader);
            var studentList = (JSONArray) obj;
            var outList = new ArrayList<Student>();

            studentList.forEach(student -> {
                try {
                    outList.add(parseStudentObject((JSONObject) student));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            return outList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Student parseStudentObject(JSONObject student) throws Exception {
        var name="";
        var results=new HashMap<String,Integer>();

        name = (String) student.get("name");
        var resultsKeys = (JSONArray)student.get("resultsKeys");
        var resultsValues = (JSONArray)student.get("resultsValues");
        for(var i=0;i<resultsValues.size();i++){
            results.put((String) resultsKeys.get(i),Integer.valueOf(resultsValues.get(i).toString()));
        }

        return new Student(name, results);
    }

    public static ArrayList<Faculty> readFaculties(String path){
        JSONParser parser = new JSONParser();

        try(FileReader reader = new FileReader(path)) {
            var obj = parser.parse(reader);
            var facultyList = (JSONArray) obj;
            var outList = new ArrayList<Faculty>();

            facultyList.forEach(student -> {
                try {
                    outList.add(parseFacultyObject((JSONObject) student));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            return outList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Faculty parseFacultyObject(JSONObject faculty) throws Exception {
        var name="";
        var disciplines=new ArrayList<String>();
        var score = 0;

        name = (String) faculty.get("name");
        disciplines = (ArrayList<String>) faculty.get("disciplines");
        score = Integer.parseInt(faculty.get("score").toString());

        return new Faculty(name,score,disciplines.toArray(new String[0]));
    }
}
