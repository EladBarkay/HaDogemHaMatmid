package workspace.michlala.HDHM.Transformers;

import health_care_provider.HealthCareInfoProvider;
import health_care_provider.errors.InvalidIdException;
import health_care_provider.models.PersonInsured;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Properties;

public class AddDataToLabTest implements TransformAble{

    @Override
    public Properties transform(Properties oldData) {
        Properties toRet = new Properties();
        ArrayList<Properties> records;

        //getting the key for the table records
        Object tableKey = null;
        for (Object tempKey : oldData.keySet()){
            if (oldData.get(tempKey) instanceof Collection<?>){
                tableKey = tempKey;
                break;
            }
        }
        if (tableKey == null)
            throw new NoSuchElementException("No \"rows\" at the properties data");


        //getting the table records
        records = (ArrayList<Properties>) oldData.get(tableKey);

        //adding the data to the records
        HealthCareInfoProvider infoProvider = new HealthCareInfoProvider();
        for (Properties record : records){
            try {
                PersonInsured person = infoProvider.fetchInfo((Integer) record.get("IDNum"), (Integer) record.get("IDType"));
                record.put("JOIN_DATE", person.getJoinDate());
                record.put("HEALTH_CARE_ID", person.getHealthCareId());
                record.put("HEALTH_CARE_NAME", person.getHealthCareName());
            } catch (InvalidIdException e) {
                e.printStackTrace();
            }
        }

        toRet.put(tableKey, records);
        return toRet;
    }
}
