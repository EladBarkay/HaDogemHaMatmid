package workspace.michlala.HDHM.Transformers;
import workspace.michlala.HDHM.RawData;

public interface TransformAble {

    default RawData transform(RawData oldData) {
        return oldData;
    }
}