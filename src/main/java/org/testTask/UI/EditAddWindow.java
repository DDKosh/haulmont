package org.testTask.UI;

import com.vaadin.ui.*;
import org.testTask.DAO.AuthorDAO;
import org.testTask.DAO.GenreDAO;
import org.testTask.DAO.PublisherDAO;
import org.testTask.DTO.*;
import org.vaadin.csvalidation.CSValidator;

import java.sql.SQLException;

/**
 * The type Edit add window.
 *
 * @param <E> the type parameter
 */
public class EditAddWindow<E> extends Window {
    /**
     * The vertical layout
     */
    private VerticalLayout layout = new VerticalLayout();

    /**
     * The okay button
     */
    private Button okButton = new Button("Ok");

    /**
     * The cancel button
     */
    private Button cancelButton = new Button("Cancel");

    /**
     * The horizontal layout
     */
    private HorizontalLayout horizontalLayout = new HorizontalLayout();

    /**
     * The name field
     */
    private TextField nameField;

    /**
     * The surname field
     */
    private TextField surnameField;

    /**
     * The patronymic textfield
     */
    private TextField patronymicField;

    /**
     * The publisher combobox
     */
    private ComboBox publisherBox;

    /**
     * The genre combobox
     */
    private ComboBox genreBox;

    /**
     * The author combobox
     */
    private ComboBox authorBox;

    /**
     * The year field
     */
    private TextField yearField;

    /**
     * The city field
     */
    private TextField cityField;

    /**
     * Instantiates a new Edit add window.
     *
     * @param abstractUI the abstract ui
     */
    public EditAddWindow(final AbstractUI abstractUI) {
        if (abstractUI instanceof AuthorUI) {
            createAuthorLayout();

                okButton.addClickListener(clickEvent -> {
                    if (validAuthor()) {
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
                }});
        } else if (abstractUI instanceof BookUI) {
            createBookLayout();
                okButton.addClickListener(clickEvent -> {
                    if (validBook()) {
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
                }
            });
        } else {
            createGenresLayout();
                okButton.addClickListener(clickEvent -> {
                    if (validGenre()) {
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
                }
            });
        }
        createButtons();
        setModal(true);
        setContent(layout);
    }

    /**
     * Instantiates a new Edit add window.
     *
     * @param abstractUI the abstract ui
     * @param object     the object
     */
    public EditAddWindow(final AbstractUI abstractUI, E object) {
        if (abstractUI instanceof AuthorUI) {
            Author author = (Author) object;
            createAuthorLayout(author.getName(), author.getPatronymic(), author.getSurname());
                okButton.addClickListener(clickEvent -> {
                    if (validAuthor()) {
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
                }
            });
        } else if (abstractUI instanceof BookUI) {
            Book book = (Book) object;
            createBookLayout(book.getTitle(), book.getAuthor(),
                    book.getPublisher(), book.getGenre(),
                    book.getYear(), book.getCity());

                okButton.addClickListener(clickEvent -> {
                    if (validBook()) {
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
                }
            });
        } else {
            Genre genre = (Genre) object;
            createGenresLayout(genre.getTitle());
                okButton.addClickListener(clickEvent -> {
                    if (validGenre()) {
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
                }
            });
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
        if (nameField.getValue().isEmpty()) {
            return false;
        }
        if (surnameField.getValue().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean validBook() {
        if (nameField.getValue().isEmpty()) {
            return false;
        }
        if (!authorBox.getSelectedItem().isPresent()) {
            return false;
        }
        if (!publisherBox.getSelectedItem().isPresent()) {
            return false;
        }
        if (!genreBox.getSelectedItem().isPresent()) {
            return false;
        }
        if (yearField.getValue().isEmpty()) {
            return false;
        }
        if (cityField.getValue().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean validGenre() {
        if (nameField.getValue().isEmpty()) {
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
        layout.addComponents(
                nameField, authorBox, publisherBox, genreBox, yearField, cityField, horizontalLayout);
    }

    private void createBookLayout(
            String name, Author author, Publisher publisher, Genre genre, int year, String city) {
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
        layout.addComponents(nameField,
                authorBox,
                publisherBox,
                genreBox,
                yearField,
                cityField,
                horizontalLayout);
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
        cancelButton.addClickListener(clickEvent ->  close() );
    }

    private void setAuthorsBox() {
        authorBox = new ComboBox("Author", new AuthorDAO().getAll());
        authorBox.setEmptySelectionAllowed(false);
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
        genreBox.setEmptySelectionAllowed(false);
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
        publisherBox.setEmptySelectionAllowed(false);
        publisherBox.setRequiredIndicatorVisible(true);
        publisherBox.setItemCaptionGenerator(new ItemCaptionGenerator<Publisher>() {
            @Override
            public String apply(final Publisher publisher) {
                return publisher.getName();
            }
        });
    }
}
