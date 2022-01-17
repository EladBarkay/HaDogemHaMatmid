package workspace.michlala.HDHM.Loaders.JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import workspace.michlala.HDHM.Loaders.Loader;

import java.io.*;
import java.util.*;

public class JSON_Loader extends Loader {
    private final String JSON_TYPE = ".json";
    private final int MAX_LINES = 50000;

    private String mostRelevantPath;
    private FileWriter writer;

    public JSON_Loader(HashMap<String, Object> settings) {
        super(settings);
    }

    public JSON_Loader(String path){
        super();
        setPath(path);
    }


    public JSON_Loader() {
    }

    private FileWriter getFileWriter() throws IOException {
        if (this.mostRelevantPath.equals(getRelevantPath())){
            if (writer==null)
                setPath(mostRelevantPath);
            return writer;
        }
        else{
            this.writer.close();
            this.writer = new FileWriter(getRelevantPath());
            this.mostRelevantPath = getRelevantPath();
        }
        return this.writer;
    }

    public void setPath(String path){
        setSettings(new HashMap<>(){{put("path", path);}});
        try {
            writer = new FileWriter(path);
            mostRelevantPath = getRelevantPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath(){
        return (String) getSettings().get("path");
    }

    private String getRelevantPath(){
        String absPath = getPath();
        if (getPath().toLowerCase(Locale.ROOT).endsWith(JSON_TYPE)){
            absPath = getPath().substring(0, getPath().length()-5);
        }
        int counter = 0;
        String finalAbsPath = absPath;
        while (new File(finalAbsPath + JSON_TYPE).exists()
                && fileLines(finalAbsPath + JSON_TYPE) >= MAX_LINES){
            finalAbsPath = absPath + "(" + counter + ")";
        }
        return finalAbsPath + JSON_TYPE;
    }

    private int fileLines(String path) {
        int counter = 0;
        try {
            Scanner s = new Scanner(new File(path));
            while (s.hasNextLine()){
                s.nextLine();
                counter++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return counter;
    }



    @Override
    public void load(Properties data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        for (Object key : data.keySet()){
            if (key == "rows"){
                mapper.writerWithDefaultPrettyPrinter().writeValue(getFileWriter(), data.get("rows"));
            }
        }
    }
}