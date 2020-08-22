package org.testTask.UI;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import org.testTask.DAO.AbstractDAO;
import org.testTask.DAO.AuthorDAO;
import org.testTask.DTO.Author;

public class AuthorUI extends AbstractUI {

    public AuthorUI() {
        super(new AuthorDAO());
    }

    @Override
    public Grid createGrid() {
        Grid<Author> grid = new Grid<>();
        grid.asSingleSelect();
        grid.addColumn(Author::getName).setCaption("Name");
        grid.addColumn(Author::getPatronymic).setCaption("Patronymic");
        grid.addColumn(Author::getSurname).setCaption("Surname");
        setEntity(getDAO().getAll());
        grid.setItems(getEntity());
        grid.getDataProvider().refreshAll();
        return grid;
    }
}
