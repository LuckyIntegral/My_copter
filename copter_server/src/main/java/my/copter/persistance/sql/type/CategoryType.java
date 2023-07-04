package my.copter.persistance.sql.type;

public enum CategoryType {
    RACING("Racing drone"),
    PHOTOGRAPHY("Aerial photography drone"),
    PROFESSIONAL("Professional drone"),
    CONSUMER("Consumer drone");

    private final String value;

    CategoryType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
