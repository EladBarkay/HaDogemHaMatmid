package workspace.michlala.HDHM.Loaders.FileLoaders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import workspace.michlala.HDHM.Loaders.Loader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.*;

public class FileLoader extends Loader {

    private String type;
    private String mostRelevantPath;
    private Integer maxLines;

    public FileLoader(String path, String type) {
        super();
        setPath(path);
        this.type = type;
        maxLines = null;
    }

    public FileLoader(String path, String type, int maxLines) {
        super();
        this.type = type;
        this.maxLines = maxLines;
        setPath(path);
    }

    public FileWriter getFileWriter() throws IOException {
        this.mostRelevantPath = getRelevantPath();
        return new FileWriter(this.mostRelevantPath);
    }

    public void setPath(String path) {
        setSettings(new HashMap<>() {{
            put("path", path);
        }});
        mostRelevantPath = getRelevantPath();
    }

    public String getPath() {
        return (String) getSettings().get("path");
    }

    private String getRelevantPath() {
        String absPath = getPath();
        if (getPath().toLowerCase(Locale.ROOT).endsWith(type)) {
            absPath = getPath().substring(0, getPath().length() - 5);
        }
        int counter = 0;
        String finalAbsPath = absPath;
        while (new File(finalAbsPath + type).exists()) {
            finalAbsPath = absPath + "(" + counter + ")";
            counter++;
        }
        return finalAbsPath + type;
    }

    @Override
    public void load(Properties data) throws IOException {
        super.load(data);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Properties> getDividedData(Properties data, ObjectMapper mapper) {

        if (maxLines == null)
            return new ArrayList<Properties>() {{
                add(data);
            }};

        if (maxLines <= 0)
            throw new InvalidParameterException("Max lines cant be negative");

        ArrayList<Properties> toRet = new ArrayList<>();

        Properties tempProperty = new Properties();
        ArrayList<Properties> subProperties;

        if (data.keySet().size() == 1) {
            Object key = data.keySet().iterator().next();
            if (data.get(key) instanceof Collection<?>) {
                ArrayList<Properties> subData = new ArrayList<>((Collection<? extends Properties>) data.get(key));
                tempProperty = new Properties();
                subProperties = new ArrayList<>();
                tempProperty.put(key, subProperties);
                for (Properties property : subData) {
                    subProperties.add(property);
                    String toPrint = "";
                    try {
                        toPrint = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tempProperty.get(key));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    if (countLines(toPrint) > maxLines) {
                        subProperties.remove(subProperties.size() - 1);
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

    private static int countLines(String str) {
        final Integer[] counter = {0};
        str.chars().forEach(x -> {
            if ((char) x == '\n')
                counter[0]++;
        });
        return counter[0];
    }
}
