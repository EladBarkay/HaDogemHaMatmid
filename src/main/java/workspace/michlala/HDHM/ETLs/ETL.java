package workspace.michlala.HDHM.ETLs;

import workspace.michlala.HDHM.Extractors.Extractor;
import workspace.michlala.HDHM.Loaders.Loader;
import workspace.michlala.HDHM.RawData;
import workspace.michlala.HDHM.Transformers.TransformAble;

import javax.naming.NoInitialContextException;
import java.io.IOException;

public class ETL {

    private Extractor extractor;
    private TransformAble[] transformers;
    private Loader loader;

    public ETL(Extractor extractor, TransformAble[] transformers, Loader loader) {
        this.extractor = extractor;
        this.transformers = transformers;
        this.loader = loader;
    }

    public ETL() {
    }

    public Extractor getExtractor() {
        return extractor;
    }

    public void setExtractor(Extractor extractor) {
        this.extractor = extractor;
    }

    public TransformAble[] getTransformers() {
        return transformers;
    }

    public void setTransformers(TransformAble[] transformers) {
        this.transformers = transformers;
    }

    public Loader getLoader() {
        return loader;
    }

    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    public void start() throws NoInitialContextException, IOException {
        RawData data;
        if (extractor == null){
            throw new NoInitialContextException("No Excecuter is set");
        }
        data = extractor.extract();
        if (transformers != null){
            for (TransformAble transformer : transformers){
                data = transformer.transform(data);
            }
        }
        if (loader == null){
            throw new NoInitialContextException("No Loader is set");
        }
        loader.load(data);
    }
}
