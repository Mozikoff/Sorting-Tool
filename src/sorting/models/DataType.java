package sorting.models;

public enum DataType {
    LONG("long"),
    WORD("word"),
    LINE("line");
    private final String type;
    DataType(String type) {
        this.type = type;
    }
}
