package org.testTask.UI;

import com.vaadin.ui.*;
import org.testTask.DAO.AuthorDAO;
import org.testTask.DAO.GenreDAO;
import org.testTask.DAO.PublisherDAO;
import org.testTask.DTO.*;
import org.vaadin.csvalidation.CSValidator;

import java.sql.SQLException;

public class EditAddWindow<E> extends Window {
    private VerticalLayout layout = new VerticalLayout();

    private Button okButton = new Button("Ok");

    private Button cancelButton = new Button("Cancel");

    private HorizontalLayout horizontalLayout = new HorizontalLayout();

    private TextField nameField;

    private TextField surnameField;

    private TextField patronymicField;

    private ComboBox publisherBox;

    private ComboBox genreBox;

    private ComboBox authorBox;

    private TextField yearField;

    private TextField cityField;

    public EditAddWindow(final AbstractUI abstractUI) {
        if (abstractUI instanceof AuthorUI) {
            createAuthorLayout();
            if (validAuthor()) {
                okButton.addClickListener(clickEvent -> {
                    Author author =
                            new Author(abstractUI.getEntity().size() + 1,
                                        nameField.getValue(),
                                        surnameField.getValue(),
                                        patronymicField.getValue());
                    try {
                        abstractUI.getDAO().add(author);
                        abstractUI.refreshGrid();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    close();
                });
            }
        } else if (abstractUI instanceof BookUI) {
            createBookLayout();
            if (validBook()) {
                okButton.addClickListener(clickEvent -> {
                    Book book =
                            new Book(abstractUI.getEntity().size(),
                                    nameField.getValue(),
                                    (Author) authorBox.getSelectedItem().get(),
                                    (Genre) genreBox.getSelectedItem().get(),
                                    (Publisher) publisherBox.getSelectedItem().get(),
                                    Integer.parseInt(yearField.getValue()),
                                    cityField.getValue());
                    try {
                        abstractUI.getDAO().add(book);
                        abstractUI.refreshGrid();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    close();
                });
            }
        } else {
            createGenresLayout();
            if (validGenre()) {
                okButton.addClickListener(clickEvent -> {
                    Genre genre =
                            new Genre(abstractUI.getEntity().size(),
                                    nameField.getValue());
                    try {
                        abstractUI.getDAO().add(genre);
                        abstractUI.refreshGrid();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    close();
                });
            }
        }
        createButtons();
        setModal(true);
        setContent(layout);
    }

    public EditAddWindow(final AbstractUI abstractUI, E object) {
        if (abstractUI instanceof AuthorUI) {
            Author author = (Author) object;
            createAuthorLayout(author.getName(), author.getPatronymic(), author.getSurname());
            if (validAuthor()) {
                okButton.addClickListener(clickEvent -> {
                    try {
                        if (patronymicField.getValue() == null) patronymicField.setValue("");
                        Author newAuthor = new Author(author.getId(),
                                nameField.getValue(),
                                patronymicField.getValue(),
                                surnameField.getValue());
                        abstractUI.getDAO().update(newAuthor, newAuthor.getId());
                        abstractUI.refreshGrid();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    close();
                });
            }
        } else if (abstractUI instanceof BookUI) {
            Book book = (Book) object;
            createBookLayout(book.getTitle(), book.getAuthor(),
                    book.getPublisher(), book.getGenre(),
                    book.getYear(), book.getCity());
            if (validBook()) {
                okButton.addClickListener(clickEvent -> {
                    Book newBook =
                            new Book(book.getId(),
                                    nameField.getValue(),
                                    (Author) authorBox.getSelectedItem().get(),
                                    (Genre) genreBox.getSelectedItem().get(),
                                    (Publisher) publisherBox.getSelectedItem().get(),
                                    Integer.parseInt(yearField.getValue()),
                                    cityField.getValue());
                    try {
                        abstractUI.getDAO().update(newBook, newBook.getId());
                        abstractUI.refreshGrid();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    close();
                });
            }
        } else {
            Genre genre = (Genre) object;
            createGenresLayout(genre.getTitle());
            if (validGenre()) {
                okButton.addClickListener(clickEvent -> {
                    Genre newGenre =
                            new Genre(genre.getId(),
                                    nameField.getValue());
                    try {
                        abstractUI.getDAO().update(newGenre, newGenre.getId());
                        abstractUI.refreshGrid();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    close();
                });
            }
        }
        createButtons();
        setModal(true);
        setContent(layout);
    }

    private void createAuthorLayout() {
        nameField = new TextField("Name");
        nameField.setRequiredIndicatorVisible(true);
        patronymicField = new TextField("Patronymic");
        surnameField = new TextField("Surname");
        surnameField.setRequiredIndicatorVisible(true);
        layout.addComponents(nameField, patronymicField, surnameField, horizontalLayout);
    }

    private void createAuthorLayout(String name, String patronymic, String surname) {
        nameField = new TextField("Name");
        nameField.setValue(name);
        nameField.setRequiredIndicatorVisible(true);
        patronymicField = new TextField("Patronymic");
        patronymicField.setValue(patronymic);
        surnameField = new TextField("Surname");
        surnameField.setRequiredIndicatorVisible(true);
        surnameField.setValue(surname);
        layout.addComponents(nameField, patronymicField, surnameField, horizontalLayout);
    }

    private boolean validAuthor() {
        if (nameField.isEmpty()) {
            return false;
        }
        if (surnameField.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean validBook() {
        if (nameField.isEmpty()) {
            return false;
        }
        if (authorBox.isEmpty()) {
            return false;
        }
        if (publisherBox.isEmpty()) {
            return false;
        }
        if (genreBox.isEmpty()) {
            return false;
        }
        if (yearField.isEmpty()) {
            return false;
        }
        if (cityField.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean validGenre() {
        if (nameField.isEmpty()) {
            return false;
        }
        return true;
    }

    private void createBookLayout() {
        nameField = new TextField("Title");
        nameField.setRequiredIndicatorVisible(true);
        setAuthorsBox();
        setPublishersBox();
        setGenresBox();
        yearField = new TextField("Year");
        yearField.setMaxLength(4);
        yearField.setRequiredIndicatorVisible(true);
        CSValidator csValidator = new CSValidator();
        String regexp = "[0-9]*";
        csValidator.extend(yearField);
        csValidator.setRegExp(regexp);
        csValidator.setPreventInvalidTyping(true);
        cityField = new TextField("City");
        cityField.setRequiredIndicatorVisible(true);
        layout.addComponents(nameField, authorBox, publisherBox, genreBox, yearField, cityField, horizontalLayout);
    }

    private void createBookLayout(String name, Author author, Publisher publisher, Genre genre, int year, String city) {
        nameField = new TextField("Title");
        nameField.setValue(name);
        nameField.setRequiredIndicatorVisible(true);
        setAuthorsBox();
        authorBox.setSelectedItem(author);
        setPublishersBox();
        publisherBox.setSelectedItem(publisher);
        setGenresBox();
        genreBox.setSelectedItem(genre);
        yearField = new TextField("Year");
        yearField.setValue(String.valueOf(year));
        yearField.setMaxLength(4);
        yearField.setRequiredIndicatorVisible(true);
        CSValidator csValidator = new CSValidator();
        String regexp = "[0-9]*";
        csValidator.extend(yearField);
        csValidator.setRegExp(regexp);
        csValidator.setPreventInvalidTyping(true);
        cityField = new TextField("City");
        cityField.setValue(city);
        cityField.setRequiredIndicatorVisible(true);
        layout.addComponents(nameField, authorBox, publisherBox, genreBox, yearField, cityField, horizontalLayout);
    }

    private void createGenresLayout() {
        nameField = new TextField("Title");
        nameField.setRequiredIndicatorVisible(true);
        layout.addComponents(nameField, horizontalLayout);
    }

    private void createGenresLayout(String name) {
        nameField = new TextField("Title");
        nameField.setValue(name);
        nameField.setRequiredIndicatorVisible(true);
        layout.addComponents(nameField, horizontalLayout);
    }

    private void createButtons() {
        horizontalLayout.addComponents(okButton, cancelButton);
        cancelButton.addClickListener(clickEvent -> {close();});
    }

    private void setAuthorsBox() {
        authorBox = new ComboBox("Author", new AuthorDAO().getAll());
        authorBox.setRequiredIndicatorVisible(true);
        authorBox.setItemCaptionGenerator(new ItemCaptionGenerator<Author>() {
            @Override
            public String apply(final Author author) {
                return author.getFIO();
            }
        });
    }

    private void setGenresBox() {
        genreBox = new ComboBox("Genre", new GenreDAO().getAll());
        genreBox.setRequiredIndicatorVisible(true);
        genreBox.setItemCaptionGenerator(new ItemCaptionGenerator<Genre>() {
            @Override
            public String apply(final Genre genre) {
                return genre.getTitle();
            }
        });
    }

    private void setPublishersBox() {
        publisherBox = new ComboBox("Publisher", new PublisherDAO().getAll());
        publisherBox.setRequiredIndicatorVisible(true);
        publisherBox.setItemCaptionGenerator(new ItemCaptionGenerator<Publisher>() {
            @Override
            public String apply(final Publisher publisher) {
                return publisher.getName();
            }
        });
    }
}
