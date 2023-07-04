package my.copter.persistance.sql.type;

public enum BrandType {
    DJI("DJI"),
    PARROT("Parrot"),
    WALKERA("Walkera"),
    GOPRO("GoPro");

    private final String name;

    BrandType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
