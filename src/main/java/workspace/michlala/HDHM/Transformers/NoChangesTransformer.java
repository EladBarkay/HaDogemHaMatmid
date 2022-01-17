package workspace.michlala.HDHM.Transformers;

import workspace.michlala.HDHM.RawData;

public class NoChangesTransformer implements TransformAble{
    @Override
    public RawData transform(RawData oldData) {
        return TransformAble.super.transform(oldData);
    }
}
