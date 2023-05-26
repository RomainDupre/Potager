package blokus.Tools;

public abstract class Tools {
    private String name;
    private String description;

    public Tools(String name, String description) {
        super();
        this.name = name;
        this.description = description;
    }

    public String getLabel() {
        return this.name;
    }
}
