package workspace.michlala.HDHM.Loaders.JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import workspace.michlala.HDHM.Loaders.Loader;

import java.io.*;
import java.util.*;

public class JSON_Loader extends Loader {
    private final String JSON_TYPE = ".json";
    private final int MAX_LINES = 4600;

    private String mostRelevantPath;

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
        this.mostRelevantPath = getRelevantPath();
        return new FileWriter(this.mostRelevantPath);
    }

    public void setPath(String path){
        setSettings(new HashMap<>(){{put("path", path);}});
        mostRelevantPath = getRelevantPath();
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
        while (new File(finalAbsPath + JSON_TYPE).exists()){
            finalAbsPath = absPath + "(" + counter + ")";
            counter++;
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
        ArrayList<Properties> dividedData = getDividedData(data);
        for (Properties subData : dividedData){
            for (Object key : subData.keySet()){
                if (key == "rows"){
                    mapper.writerWithDefaultPrettyPrinter().writeValue(getFileWriter(), subData.get("rows"));
                }
            }
        }
    }

    private ArrayList<Properties> getDividedData(Properties data) {
        ObjectMapper mapper = new ObjectMapper();

        ArrayList<Properties> toRet = new ArrayList<>();

        Properties tempProperty = new Properties();
        ArrayList<Properties> subProperties;

        if (data.keySet().size() == 1){
            Object key = data.keySet().iterator().next();
            if (data.get(key) instanceof Collection<?>){
                ArrayList<Properties> subData = new ArrayList<>((Collection<? extends Properties>) data.get(key));
                tempProperty = new Properties();
                subProperties = new ArrayList<>();
                tempProperty.put(key, subProperties);
                for (Properties property : subData){
                    subProperties.add(property);
                    String toPrint = "";
                    try {
                        toPrint = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tempProperty.get(key));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    if (countLines(toPrint) > MAX_LINES){
                        subProperties.remove(subProperties.size()-1);
                        toRet.add((Properties) tempProperty.clone());
                        subProperties = new ArrayList<>();
                        subProperties.add(property);
                        tempProperty.replace(key, subProperties);
                    }
                }
            }
        }

        toRet.add(tempProperty);
        return toRet;
    }

    private static int countLines(String str){
        String[] lines = str.split("\r\n|\r|\n");
        return  lines.length;
    }
}