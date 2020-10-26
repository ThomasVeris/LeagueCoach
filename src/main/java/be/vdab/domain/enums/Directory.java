package be.vdab.domain.enums;

public enum Directory {
    JSON_DIRECTORY("./src/main/resources/json/");

    private final String path;

    Directory(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
