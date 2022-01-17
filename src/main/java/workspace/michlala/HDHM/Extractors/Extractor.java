package workspace.michlala.HDHM.Extractors;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public abstract class Extractor {
    HashMap<String, Object> settings;

    protected Extractor(HashMap<String, Object> settings) {
        this.settings = settings;
    }

    protected Extractor() {
    }

    protected HashMap<String, Object> getSettings() {
        return settings;
    }

    protected void setSettings(HashMap<String, Object> settings) {
        this.settings = settings;
    }

    public Properties extract() throws IOException {
        return null;
    }

}
