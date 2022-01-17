package workspace.michlala.HDHM;

import java.util.HashMap;
import java.util.Set;

public class RawData {
    private HashMap<String, Object> variables;

    public RawData(HashMap<String, Object> variables) {
        this.variables = variables;
    }

    public RawData() {
        this.variables = new HashMap<>();
    }

    public HashMap<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(HashMap<String, Object> variables) {
        this.variables = variables;
    }

    public Object put(String name, Object value) {
        return this.variables.put(name, value);
    }

    public Object get(String name) {
        return this.variables.get(name);
    }

    public Object remove(String name) {
        return this.variables.remove(name);
    }

    public int size() {
        return this.variables.size();
    }

    public boolean containsKey(String name) {
        return this.variables.containsKey(name);
    }

    public Set<String> keySet() {
        return this.variables.keySet();
    }
}
