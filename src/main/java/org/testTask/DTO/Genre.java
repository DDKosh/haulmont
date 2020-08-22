package org.testTask.DTO;

/**
 * The type Genre.
 */
public class Genre extends AbstractDTO {
    /**
     * The title
     */
    private String title;

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Instantiates a new Genre.
     *
     * @param id    the id
     * @param title the title
     */
    public Genre(long id, String title) {
        super(id);
        this.title = title;
    }
}
