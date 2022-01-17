package workspace.michlala.HDHM.Extractors.CSV;

import workspace.michlala.HDHM.Extractors.Extractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class CSV_Extractor extends Extractor {

    public CSV_Extractor(HashMap<String, Object> settings) {
        super(settings);
    }

    public CSV_Extractor() {
    }

    public void setPath(String path){
        setSettings(new HashMap<>(){{put("path", path);}});
    }

    public String getPath(){
        return (String) getSettings().get("path");
    }

    @Override
    public Properties extract() throws IOException {
        Properties properties;
        String path = (String) getSettings().get("path");
        ArrayList<String[]> CSVData;
        try {
            CSVData = new MyCSVParser().getRecords(path);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("error 0_0");
            throw e;
        }
        properties = CSVToProperties(CSVData);
        return properties;
    }

    private Properties CSVToProperties(ArrayList<String[]> csvData){
        String[] firstLine = csvData.remove(0);
        Properties allData = new Properties();
        ArrayList<Properties> toInsert = new ArrayList<>();
        for (String[] line : csvData){
            Properties lineData = new Properties();
            for (int i = 0; i < line.length; i++) {
                lineData.put(firstLine[i], line[i]);
            }
            toInsert.add(lineData);
        }
        allData.put("rows", toInsert);
        return allData;
    }
}
