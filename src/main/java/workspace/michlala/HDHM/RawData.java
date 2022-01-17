package workspace.michlala.HDHM;

import javax.naming.NamingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RawData {
    final String IGNORE = "IGNORE";
    private HashMap<String, Object> variables;
    int ignoredCount;

    public RawData() {
        this.variables = new HashMap<>();
        this.ignoredCount = 0;
    }

    public HashMap<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(HashMap<String, Object> variables) {
        this.variables = variables;
    }

    private int getIgnoredCount() {
        return ignoredCount;
    }

    private void setIgnoredCount(int ignoredCount) {
        this.ignoredCount = ignoredCount;
    }

    private String getIgnoreName() {
        return IGNORE + getIgnoredCount();
    }

    public Object putWithIgnoredName(Object value) {
        Object toRet = this.variables.put(getIgnoreName(), value);
        this.ignoredCount++;
        return toRet;
    }

    public Object put(String name, Object value) throws NamingException {
        if (name.startsWith(IGNORE))
            throw new NamingException("CANT CREATE VARIABLE WITH NAME START WITH " + IGNORE);
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

    public Set<String> ignoredKeySet() {
        Set<String> toRet = new HashSet<>();
        for (String key : keySet()) {
            if (key.startsWith(IGNORE))
                toRet.add(key);
        }
        return toRet;
    }

    public Set<String> namedKeySet() {
        Set<String> toRet = new HashSet<>();
        for (String key : keySet()) {
            if (!key.startsWith(IGNORE))
                toRet.add(key);
        }
        return toRet;
    }
}
