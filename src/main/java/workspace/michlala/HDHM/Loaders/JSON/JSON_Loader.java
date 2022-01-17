package workspace.michlala.HDHM.Loaders.JSON;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import workspace.michlala.HDHM.Loaders.Loader;
import workspace.michlala.HDHM.RawData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class JSON_Loader extends Loader {

    public JSON_Loader(HashMap<String, Object> settings) {
        super(settings);
    }

    public JSON_Loader() {
    }

    public void setPath(String path){
        setSettings(new HashMap<>(){{put("path", path);}});
    }

    public String getPath(){
        return (String) getSettings().get("path");
    }

    @Override
    public void load(RawData data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        write(mapper.writeValueAsString(data.getVariables()));
        /*Set<String> keys = data.keySet();
        for (String key : keys){
            if (data.get(key).getClass().equals(RawData.class)){
                load((RawData) data.get(key));
            }
            else{
                write(mapper.writeValueAsString(data.getVariables()));
                break;
            }
        }*/
    }

    public void write(String toWrite) throws IOException {
        FileWriter writer = new FileWriter(getPath());

        writer.append(toWrite);
        writer.flush();
        writer.close();

    }
}
