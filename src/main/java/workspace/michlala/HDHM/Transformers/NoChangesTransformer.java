package workspace.michlala.HDHM.Transformers;

import java.util.Properties;

public class NoChangesTransformer implements TransformAble{
    @Override
    public Properties transform(Properties oldData) {
        return TransformAble.super.transform(oldData);
    }
}
