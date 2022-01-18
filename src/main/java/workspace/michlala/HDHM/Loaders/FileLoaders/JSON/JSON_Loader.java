package workspace.michlala.HDHM.Loaders.FileLoaders.JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import workspace.michlala.HDHM.Loaders.FileLoaders.FileLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class JSON_Loader extends FileLoader {
    public JSON_Loader(String path, String type) {
        super(path, type);
    }

    public JSON_Loader(String path, String type, int maxLines) {
        super(path, type, maxLines);
    }

    @Override
    public void load(Properties data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Properties> dividedData = getDividedData(data, mapper);
        for (Properties subData : dividedData) {
            for (Object key : subData.keySet()) {
                if (key == "rows") {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(getFileWriter(), subData.get("rows"));
                }
            }
        }
    }
}
