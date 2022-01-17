package workspace.michlala.HDHM;

import workspace.michlala.HDHM.ETLs.ETL;
import workspace.michlala.HDHM.Extractors.CSV.CSV_Extractor;
import workspace.michlala.HDHM.Loaders.JSON.JSON_Loader;
import workspace.michlala.HDHM.Transformers.NoChangesTransformer;
import workspace.michlala.HDHM.Transformers.TransformAble;

import javax.naming.NoInitialContextException;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ETL test = new ETL();
        CSV_Extractor csv_extractor = new CSV_Extractor(); csv_extractor.setPath("src/main/resources/MadaReports.csv");
        NoChangesTransformer transformer = new NoChangesTransformer();
        JSON_Loader json_loader = new JSON_Loader(); json_loader.setPath("src/main/resources/MadaReports.json");

        test.setExtractor(csv_extractor);
        test.setLoader(json_loader);

        try {
            test.start();
        } catch (NoInitialContextException | IOException e) {
            e.printStackTrace();
            System.out.println("nt");
        }
    }
}
