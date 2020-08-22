package org.testTask.DTO;

public class Genre extends AbstractDTO {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre(long id, String title) {
        super(id);
        this.title = title;
    }
}
