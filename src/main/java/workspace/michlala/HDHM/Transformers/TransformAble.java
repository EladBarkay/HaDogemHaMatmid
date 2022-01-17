package workspace.michlala.HDHM.Transformers;

import java.util.Properties;

public interface TransformAble {

    default Properties transform(Properties oldData) {
        return oldData;
    }
}