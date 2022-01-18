package workspace.michlala.HDHM.Extractors.CSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import workspace.michlala.HDHM.Extractors.Extractor;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class CSV_Extractor_common_csv extends Extractor {

    public CSV_Extractor_common_csv(HashMap<String, Object> settings) {
        super(settings);
    }

    public CSV_Extractor_common_csv() {
        super();
    }

    public CSV_Extractor_common_csv(String path){
        super();
        setPath(path);
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
        ArrayList<CSVRecord> CSVData;
        try {
            CSVData = getRecords(path);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("error 0_0");
            throw e;
        }
        properties = CSVToProperties(CSVData);
        return properties;
    }

    private Properties CSVToProperties(ArrayList<CSVRecord> csvData){
        Properties allData = new Properties();
        ArrayList<Properties> addHere = new ArrayList<>();
        for (CSVRecord record : csvData){
            Properties tempProperty = new Properties();
            tempProperty.putAll(record.toMap());
            addHere.add(tempProperty);
        }
        allData.put("rows", addHere);
        return allData;
    }

    public ArrayList<CSVRecord> getRecords(String path) throws IOException {
        CSVParser parser = new CSVParser(new FileReader(path), CSVFormat.DEFAULT.withHeader());
        ArrayList<CSVRecord> toRet = new ArrayList<>();
        parser.forEach(toRet::add);
        return toRet;
    }
}

