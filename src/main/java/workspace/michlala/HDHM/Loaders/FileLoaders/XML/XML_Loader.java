package workspace.michlala.HDHM.Loaders.FileLoaders.XML;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import workspace.michlala.HDHM.Loaders.FileLoaders.FileLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class XML_Loader extends FileLoader {
    public XML_Loader(String path, String type) {
        super(path, type);
    }

    public XML_Loader(String path, String type, int maxLines) {
        super(path, type, maxLines);
    }

    @Override
    public void load(Properties data) throws IOException {
        XmlMapper mapper = new XmlMapper();
        ArrayList<Properties> dividedData = getDividedData(data, mapper);
        for (Properties subData : dividedData) {
            for (Object key : subData.keySet()) {
                if (subData.get(key) instanceof Collection<?>) {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(getFileWriter(), subData.get("rows"));
                }
            }
        }
    }
}
