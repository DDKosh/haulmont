package org.testTask.DTO;

/**
 * The enum Publisher.
 */
public enum Publisher {
    /**
     * Moscow publisher.
     */
    MOSCOW("МОСКВА"),
    /**
     * St petersburg publisher.
     */
    ST_PETERSBURG("ПИТЕР"),
    /**
     * O reilly publisher.
     */
    O_REILLY("O’REILLY");

    /**
     * The name
     */
    private String name;

    /**
     * The identifier
     */
    private long id;

    Publisher(final String name) {
        this.name = name;
        this.id = ordinal();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }
}
