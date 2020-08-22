package org.testTask.DTO;

public class Author extends AbstractDTO {
    private String name;
    private String surname;
    private String patronymic;


    public Author(long id, String name, String patronymic, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getFIO() {
        return surname + " " + name + " " + patronymic;
    }
}
