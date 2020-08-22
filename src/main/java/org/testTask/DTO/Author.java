package org.testTask.DTO;

/**
 * The type Author.
 */
public class Author extends AbstractDTO {
    private String name;
    private String surname;
    private String patronymic;


    /**
     * Instantiates a new Author.
     *
     * @param id         the id
     * @param name       the name
     * @param patronymic the patronymic
     * @param surname    the surname
     */
    public Author(final long id, final String name, final String patronymic, final String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(final String surname) {
        this.surname = surname;
    }

    /**
     * Gets patronymic.
     *
     * @return the patronymic
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Sets patronymic.
     *
     * @param patronymic the patronymic
     */
    public void setPatronymic(final String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * Gets fio.
     *
     * @return the fio
     */
    public String getFIO() {
        return surname + " " + name + " " + patronymic;
    }
}
