import WorkingDirectory.*;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        FileDemo();
        MessageDemo();
    }

    public static void FileDemo() throws Exception {
        var dir = workingDirectory.getInstance("E:\\java\\lab2");
        dir.toParent();
        dir.toChild("lab2");
        dir.makeDir("files");
        dir.childExists("files");
        dir.toChild("src");
        dir.printExt("java");
        System.out.println();
        dir.printChildHierarchy();
        System.out.println();
        dir.toParent();
        dir.toParent();
        System.out.println(dir.searchForDirectory("lab2"));
    }

    public static void MessageDemo() throws Exception {
        var results=new HashMap<String,Integer>();
        results.put("Math", 99);
        results.put("Physics",99);
        results.put("Language",99);
        var disciplines = new String[]{
                "Math",
                "Physics",
                "Language"
        };
        var student = new Student("John",results);
        var faculty = new Faculty("Interesting faculty", 220,disciplines);
        PdfHelper.saveTo("message.pdf",student.SendMessage(faculty));
    }
}

