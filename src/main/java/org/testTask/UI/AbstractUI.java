package org.testTask.UI;

import com.vaadin.ui.*;
import org.testTask.DAO.AbstractDAO;
import org.testTask.DTO.AbstractDTO;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractUI<E extends AbstractDTO> extends VerticalLayout {
    protected Grid<E> grid;

    private Button addButton = new Button("Add");

    private Button editButton = new Button("Edit");

    private Button delButton = new Button("Delete");

    private AbstractDAO<E> dao;

    private List<E> entity;

    private HorizontalLayout horizontalLayout = new HorizontalLayout();

    public abstract Grid<E> createGrid();

    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    public AbstractUI(AbstractDAO<E> dao) {
        this.dao = dao;
        addButton.addClickListener(clickEvent -> {EditAddWindow<AbstractDTO> editAddWindow =
                new EditAddWindow<>(this);
        getUI().addWindow(editAddWindow);
        });
        delButton.addClickListener(clickEvent -> {
            if (!grid.getSelectedItems().isEmpty()) {
                try {
                    dao.delete(getSelectedItem().getId());
                    refreshGrid();
                }  catch (SQLIntegrityConstraintViolationException ex) {
                    Notification.show("This object is used");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        editButton.addClickListener(clickEvent -> {
            if (!grid.getSelectedItems().isEmpty()) {
            EditAddWindow<AbstractDTO> editAddWindow =
                    new EditAddWindow<>(this, getSelectedItem());
            getUI().addWindow(editAddWindow);
            }
        });
        grid = createGrid();
        grid.setWidthFull();
        horizontalLayout.addComponent(grid);
        horizontalLayout.setSizeFull();
        buttonsLayout.addComponents(addButton, editButton, delButton);
        addComponents(buttonsLayout, horizontalLayout);
    }

    protected void addFilter(VerticalLayout layout) {
        horizontalLayout.addComponent(layout);
        grid.setWidth("100%");
    }

    protected void addStat(HorizontalLayout layout) {
        horizontalLayout.addComponent(layout);
    }

    public void setEntity(final List<E> entity) {
        this.entity = entity;
    }

    public AbstractDAO<?> getDAO() {
        return dao;
    }

    public List<E> getEntity() {
        return entity;
    }

    public void refreshGrid() {
        entity.clear();
        entity.addAll(dao.getAll());
        grid.getDataProvider().refreshAll();
    }

    public E getSelectedItem() {
        try {
            E selectEntity = grid.getSelectedItems().iterator().next();
            return selectEntity;
        } catch (NoSuchElementException e) {
            Notification.show(e.getMessage());
        }
        return null;
    }

}
