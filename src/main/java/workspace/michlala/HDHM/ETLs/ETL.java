package workspace.michlala.HDHM.ETLs;

import workspace.michlala.HDHM.Extractors.Extractor;
import workspace.michlala.HDHM.Loaders.Loader;
import workspace.michlala.HDHM.Transformers.TransformAble;

import javax.naming.NoInitialContextException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class ETL {

    private Extractor extractor;
    private ArrayList<TransformAble> transformers;
    private Loader loader;

    public ETL(Extractor extractor, ArrayList<TransformAble> transformers, Loader loader) {
        this.extractor = extractor;
        this.transformers = transformers;
        this.loader = loader;
    }

    public ETL() {
        this.transformers = new ArrayList<>();
    }

    public Extractor getExtractor() {
        return extractor;
    }

    public void setExtractor(Extractor extractor) {
        this.extractor = extractor;
    }

    public ArrayList<TransformAble> getTransformers() {
        return transformers;
    }

    public void setTransformers(ArrayList<TransformAble> transformers) {
        this.transformers = transformers;
    }

    public void addTransformer(TransformAble toAdd){
        this.transformers.add(toAdd);
    }
    
    public Loader getLoader() {
        return loader;
    }

    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    public void start() throws NoInitialContextException, IOException {
        Properties data;
        if (extractor == null){
            throw new NoInitialContextException("No Excecuter is set");
        }
        data = extractor.extract();
        for (TransformAble transformer : transformers){
            data = transformer.transform(data);
        }
        if (loader == null){
            throw new NoInitialContextException("No Loader is set");
        }
        loader.load(data);
    }
}
