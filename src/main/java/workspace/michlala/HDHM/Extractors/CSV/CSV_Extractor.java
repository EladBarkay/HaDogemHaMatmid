package workspace.michlala.HDHM.Extractors.CSV;

import workspace.michlala.HDHM.Extractors.Extractor;
import workspace.michlala.HDHM.RawData;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
    public RawData extract() throws IOException {
        RawData rawData;
        String path = (String) getSettings().get("path");
        ArrayList<String[]> CSVData;
        try {
            CSVData = new MyCSVParser().getRecords(path);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("error 0_0");
            throw e;
        }
        rawData = CSVToRaw(CSVData);
        return rawData;
    }

    private RawData CSVToRaw(ArrayList<String[]> csvData) {
        String[] firstLine = csvData.remove(0);
        RawData toRet = new RawData();
        int lineVarsLen = firstLine.length;
        for (String[] lineVars : csvData){
            RawData toAdd = new RawData();
            for (int i = 0; i < lineVarsLen; i++) {
                try {
                    toAdd.put(firstLine[i], lineVars[i]);
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
            toRet.putWithIgnoredName(toAdd);
        }
        return toRet;
    }
}
