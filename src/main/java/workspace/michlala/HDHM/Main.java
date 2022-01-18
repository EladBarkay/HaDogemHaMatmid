package workspace.michlala.HDHM;

import workspace.michlala.HDHM.ETLs.ETL;
import workspace.michlala.HDHM.Extractors.CSV.CSV_Extractor;
import workspace.michlala.HDHM.Extractors.CSV.CSV_Extractor_common_csv;
import workspace.michlala.HDHM.Loaders.JSON.JSON_Loader;
import workspace.michlala.HDHM.Transformers.AddDataToLabTest;

import javax.naming.NoInitialContextException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ETL test = new ETL();
        CSV_Extractor_common_csv csv_extractor = new CSV_Extractor_common_csv("src/main/resources/MadaReports.csv");
        AddDataToLabTest addDataToLabTest = new AddDataToLabTest();
        JSON_Loader json_loader = new JSON_Loader("src/main/resources/MadaReports.json");

        test.setExtractor(csv_extractor);
        test.addTransformer(addDataToLabTest);
        test.setLoader(json_loader);

        try {
            test.start();
        } catch (NoInitialContextException | IOException e) {
            e.printStackTrace();
            System.out.println("nt");
        }
    }
}
