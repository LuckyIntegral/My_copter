package my.copter.persistence.sql.type;

public enum CategoryType {
    RACING("Racing"),
    SELFIE("Selfie"),
    PROFESSIONAL("Professional");

    private final String value;

    CategoryType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
