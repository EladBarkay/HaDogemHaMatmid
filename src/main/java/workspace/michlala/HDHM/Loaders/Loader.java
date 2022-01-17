package workspace.michlala.HDHM.Loaders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import workspace.michlala.HDHM.RawData;

import java.io.IOException;
import java.util.HashMap;

public abstract class Loader {
    HashMap<String, Object> settings;

    protected Loader(HashMap<String, Object> settings) {
        this.settings = settings;
    }

    protected Loader() {
    }

    protected HashMap<String, Object> getSettings() {
        return settings;
    }

    protected void setSettings(HashMap<String, Object> settings) {
        this.settings = settings;
    }

    public void load(RawData data) throws IOException {

    }
}
