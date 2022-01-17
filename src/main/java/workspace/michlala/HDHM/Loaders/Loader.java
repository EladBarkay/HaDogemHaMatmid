package workspace.michlala.HDHM.Loaders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

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

    public void load(Properties data) throws IOException {

    }
}
