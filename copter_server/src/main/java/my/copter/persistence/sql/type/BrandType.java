package my.copter.persistence.sql.type;

public enum BrandType {
    DJI("DJI"),
    PARROT("Parrot"),
    WALKERA("Walkera"),
    AUTEL("Autel"),
    OTHER("Other");

    private final String name;

    BrandType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
