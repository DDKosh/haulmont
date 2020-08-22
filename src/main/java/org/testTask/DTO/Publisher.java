package org.testTask.DTO;

public enum Publisher{
    MOSCOW("МОСКВА"),
    ST_PETERSBURG("ПИТЕР"),
    O_REILLY("O’REILLY");

    private String name;

    private long id;

    Publisher(final String name) {
        this.name = name;
        this.id = ordinal();
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
