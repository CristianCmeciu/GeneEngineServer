package geneEngine;

public class Disease extends Entity<String> {
    private final String name;
    private final String parent;

    public Disease(String name, String id, String parent) {
        this.name = name;
        this.parent = parent;
        setId(id);
    }

    public Disease(String name, String id) {
        this(name, id, null);
    }

    public String getName() {
        return name;
    }

    public String getParent() {
        return parent;
    }
}
