package FileHelpers;

import Entities.Faculty;
import Entities.Student;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.valueOf;

public class XmlHelper {
    public static ArrayList<Student> GetStudentsFromXml(String filePath) throws Exception {
        var documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        var xmlDoc = documentBuilder.parse(filePath);
        var root = xmlDoc.getDocumentElement();
        var rootNodes = root.getChildNodes();

        var studentList = new ArrayList<Student>();

        for (var rootNodeIndex = 0; rootNodeIndex < rootNodes.getLength(); rootNodeIndex++){
            if(rootNodes.item(rootNodeIndex).getNodeName().equals("student")) {

                var studentNode = rootNodes.item(rootNodeIndex).getChildNodes();
                var name="";
                var results= new HashMap<String,Integer>();

                for (var studentNodeIndex = 0; studentNodeIndex < studentNode.getLength(); studentNodeIndex++){
                    if(studentNode.item(studentNodeIndex).getNodeName().equals("name")) {
                        name = studentNode.item(studentNodeIndex).getTextContent();
                    }

                    if(studentNode.item(studentNodeIndex).getNodeName().equals("results")){
                        var resultNode=studentNode.item(studentNodeIndex).getChildNodes();

                        for (var resultNodeIndex = 0; resultNodeIndex < resultNode.getLength(); resultNodeIndex++) {
                            if(resultNode.item(resultNodeIndex).getNodeName().equals("result")){
                                results.put(resultNode.item(resultNodeIndex).getAttributes().getNamedItem("name")
                                        .getTextContent(),valueOf(resultNode.item(resultNodeIndex).getTextContent()));
                            }
                        }
                    }
                }

                studentList.add(new Student(name,results));
            }
        }

        return studentList;
    }

    public static ArrayList<Faculty> GetFacultiesFromXml(String filePath) throws Exception {
        var documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        var xmlDoc = documentBuilder.parse(filePath);
        var root = xmlDoc.getDocumentElement();
        var rootNodes = root.getChildNodes();

        var facultyList = new ArrayList<Faculty>();

        for (var rootNodeIndex = 0; rootNodeIndex < rootNodes.getLength(); rootNodeIndex++){
            if(rootNodes.item(rootNodeIndex).getNodeName().equals("faculty")) {

                var facultyNode = rootNodes.item(rootNodeIndex).getChildNodes();
                var name="";
                var disciplines= new ArrayList<String>();
                var sum = 0;

                for (var facultyNodeIndex = 0; facultyNodeIndex < facultyNode.getLength(); facultyNodeIndex++){
                    if(facultyNode.item(facultyNodeIndex).getNodeName().equals("name")) {
                        name = facultyNode.item(facultyNodeIndex).getTextContent();
                    }

                    if(facultyNode.item(facultyNodeIndex).getNodeName().equals("requirement")) {
                        sum = Integer.parseInt(facultyNode.item(facultyNodeIndex).getTextContent());
                    }

                    if(facultyNode.item(facultyNodeIndex).getNodeName().equals("disciplines")){
                        var disciplineNode=facultyNode.item(facultyNodeIndex).getChildNodes();

                        for (var disciplineNodeIndex = 0; disciplineNodeIndex < disciplineNode.getLength(); disciplineNodeIndex++) {
                            if(disciplineNode.item(disciplineNodeIndex).getNodeName().equals("discipline")){
                                disciplines.add(disciplineNode.item(disciplineNodeIndex).getTextContent());
                            }
                        }
                    }
                }

                facultyList.add(new Faculty(name,sum,disciplines.toArray(new String[0])));
            }
        }

        return facultyList;
    }
}
