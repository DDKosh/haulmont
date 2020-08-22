package org.testTask.UI;

import com.vaadin.ui.*;
import org.testTask.DAO.AbstractDAO;
import org.testTask.DAO.AuthorDAO;
import org.testTask.DAO.BookDAO;
import org.testTask.DAO.PublisherDAO;
import org.testTask.DTO.Author;
import org.testTask.DTO.Book;
import org.testTask.DTO.Publisher;

public class BookUI extends AbstractUI {

    TextField titleField = new TextField("Title");

    ComboBox<Author> authorComboBox = new ComboBox<>("Autors", new AuthorDAO().getAll());

    ComboBox<Publisher> publisherComboBox = new ComboBox<>("Publishers", new PublisherDAO().getAll());

    Button applyButton = new Button("Apply");

    Button resetButton = new Button("Reset");

    VerticalLayout layout = new VerticalLayout();

    public BookUI() {
        super(new BookDAO());
        authorComboBox.setItemCaptionGenerator(new ItemCaptionGenerator<Author>() {
            @Override
            public String apply(final Author author) {
                return author.getFIO();
            }
        });
        publisherComboBox.setItemCaptionGenerator(new ItemCaptionGenerator<Publisher>() {
            @Override
            public String apply(final Publisher publisher) {
                return publisher.getName();
            }
        });

        applyButton.addClickListener(clickEvent -> {
           if (!titleField.isEmpty()) {
               getEntity().clear();
               getEntity().addAll(getDAO().getAllByTitle(titleField.getValue()));
               grid.getDataProvider().refreshAll();
           }
            if (!authorComboBox.isEmpty()) {
                getEntity().clear();
                getEntity().addAll(getDAO().getAllByAuthor(authorComboBox.getSelectedItem().get()));
                grid.getDataProvider().refreshAll();
            }
            if (!publisherComboBox.isEmpty()) {
                getEntity().clear();
                getEntity().addAll(getDAO().getAllByPublisher(publisherComboBox.getSelectedItem().get()));
                grid.getDataProvider().refreshAll();
            }
        });

        resetButton.addClickListener(clickEvent -> {
            refreshGrid();
            titleField.setValue("");
            authorComboBox.setSelectedItem(null);
            publisherComboBox.setSelectedItem(null);
        });
        layout.addComponents(titleField, authorComboBox, publisherComboBox, applyButton, resetButton);
        addFilter(layout);
    }

    @Override
    public Grid createGrid() {
        Grid<Book> grid = new Grid<>();
        grid.asSingleSelect();
        grid.addColumn(Book::getTitle).setCaption("Title");
        grid.addColumn(a -> a.getAuthor().getFIO()).setCaption("Author");
        grid.addColumn(g -> g.getGenre().getTitle()).setCaption("Genre");
        grid.addColumn(p -> p.getPublisher().getName()).setCaption("Publisher");
        grid.addColumn(Book::getYear).setCaption("Year");
        grid.addColumn(Book::getCity).setCaption("City");
        setEntity(getDAO().getAll());
        grid.setItems(getEntity());
        grid.getDataProvider().refreshAll();
        return grid;
    }
}
