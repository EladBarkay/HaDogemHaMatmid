package workspace.michlala.HDHM.Extractors.CSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyCSVParser {
    public ArrayList<String[]> getRecords(String path) throws IOException {
        ArrayList<String[]> toRet = new ArrayList<>();
        Scanner s = new Scanner(new File(path));
        while (s.hasNext()){
            toRet.add(s.nextLine().split(","));
        }
        return toRet;
    }
}
