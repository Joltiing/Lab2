package WorkingDirectory;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Directory worker
 */
public class workingDirectory {

    /**
     * just instance
     */
    private static workingDirectory instance;

    /**
     * current directory
     */
    private File currentFile;

    /**
     * current directory name
     */
    private String directoryName;

    /**
     * Creates new instance of WorkingDirectory (singletone)
     * @param directoryName directory name
     * @throws Exception wrong name
     */
    private workingDirectory(String directoryName) throws Exception {
        this.directoryName = directoryName;

        currentFile=new File(directoryName);

        if(!currentFile.isDirectory()){
            throw new Exception("Wrong dir");
        }
    }

    /**
     * returns current instance of working directory or creates new
     * @param directoryName directory name
     * @return instance
     * @throws Exception wrong name
     */
    public static workingDirectory getInstance(String directoryName) throws Exception {
        if (instance == null) {
            instance = new workingDirectory(directoryName);
        }

        return instance;
    }

    /**
     * Returns content list of current directory
     * @return String[] content names
     */
    public String[] GetContent(){
        return currentFile.list();
    }

    /**
     * Returns absolute path of current file
     * @return String value of path.
     */
    public String GetAbsolutePath(){
        return currentFile.getAbsolutePath();
    }

    /**
     * Moves to parent directory
     */
    public void toParent(){
        var parent = currentFile.getParentFile();
        currentFile = parent != null
                ? parent
                : currentFile;
        directoryName = currentFile.toString();
    }

    /**
     * Checks existance of child
     * @param childName name of checked child
     * @return check result
     * @throws Exception wrong name
     */
    public boolean childExists(String childName) throws Exception {
        if(childName.isEmpty()){
            throw new Exception("Empty child");
        }

        var filter = new FilenameFilter() {
            public boolean accept(File f, String name)
            {
                return Objects.equals(name, f.getName());
            }
        };

        return Objects.requireNonNull(currentFile.list(filter)).length > 0;
    }

    /**
     * Creates directory in current
     * @param newDirPath new directory name
     * @return true if created, false if not
     * @throws Exception wrong name
     */
    public boolean makeDir(String newDirPath) throws Exception {
        if(newDirPath.isEmpty()){
            throw new Exception("Empty path");
        }

        var newDir = new File(currentFile.getPath()+"\\"+newDirPath);
        return newDir.mkdir();
    }

    /**
     * Moves to child directory
     * @param childName child name
     * @return true, if moved, false, if not
     * @throws Exception wrong name
     */
    public boolean toChild(String childName) throws Exception {
        if(childName.isEmpty()){
            throw new Exception("Empty childName");
        }

        var newDir = new File(currentFile.getPath()+"\\"+childName);
        var flag = newDir.exists();

        if(flag) {
            currentFile = newDir;
            directoryName = newDir.getName();
            return true;
        }

        else return false;
    }

    /**
     * Deletes all child directories.
     * @return true, if all dirs successfully deleted, false otherwise.
     */
    public boolean rmChildDirs() {
        var fileList = currentFile.listFiles();
        var successFlag = true;

        for(var file : fileList){
            if(file.isDirectory()){
                successFlag = file.delete();
            }
        }

        return successFlag;
    }

    /**
     * Searches for all files in directory with this extension.
     * @param ext extension.
     */
    //print???
    public void printExt(String ext){
        var fileList = currentFile.list();
        var extList = new ArrayList<String>();

        for (var file : fileList){
            if(file.endsWith(ext)){
                extList.add(file);
            }
        }

        System.out.println(extList.isEmpty()? "There is no files with this ext." : String.format("These files have .%s ext:",ext));

        for (var file: extList) {
            System.out.println(file);
        }
    }

    /**
     * Prints file hierarchy.
     */
    public void printChildHierarchy() {
        printChildHierarchy(0,currentFile);
    }

    /**
     * Prints file hierarchy.
     * @param tabs insertion level
     * @param recFile recursion rope :)
     */
    private void printChildHierarchy(int tabs,File recFile){
        var fileList = recFile.listFiles();

        for (var file : fileList){
            System.out.println("----".repeat(tabs)+file.getName());

            if(file.isDirectory()) {
                printChildHierarchy(tabs + 1, file);
            }
        }
    }

    /**
     * Checks exiting of directory
     * @param searchName Checked directory
     * @return true, if exists, false otherwise.
     */
    public boolean searchForDirectory(String searchName) {
        return searchForDirectory(currentFile,searchName);
    }

    /**
     * Checks exiting of directory
     * @param recFile recursion rope :)
     * @param searchName Checked directory
     * @return true, if exists, false otherwise.
     */
    private boolean searchForDirectory(File recFile,String searchName){
        var fileList = recFile.listFiles();

        for (var file : fileList){
            if(file.isDirectory()) {
                if(file.getName().equals(searchName)){
                    return true;
                }

                return searchForDirectory(file,searchName);
            }
        }

        return false;
    }
}
