package org.testTask.UI;

import com.vaadin.ui.*;
import org.testTask.DAO.AbstractDAO;
import org.testTask.DTO.AbstractDTO;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Abstract ui.
 *
 * @param <E> the type parameter
 */
public abstract class AbstractUI<E extends AbstractDTO> extends VerticalLayout {
    /**
     * The Grid.
     */
    protected Grid<E> grid;

    /**
     * The add button
     */
    private Button addButton = new Button("Add");

    /**
     * The edit button
     */
    private Button editButton = new Button("Edit");

    /**
     * The delete button
     */
    private Button delButton = new Button("Delete");

    /**
     * The dao
     */
    private AbstractDAO<E> dao;

    /**
     * The entity
     */
    private List<E> entity;

    /**
     * The horizontal layout
     */
    private HorizontalLayout horizontalLayout = new HorizontalLayout();

    /**
     * Create grid grid.
     *
     * @return the grid
     */
    public abstract Grid<E> createGrid();

    /**
     * The buttons layout
     */
    private HorizontalLayout buttonsLayout = new HorizontalLayout();

    /**
     * Instantiates a new Abstract ui.
     *
     * @param dao the dao
     */
    public AbstractUI(AbstractDAO<E> dao) {
        this.dao = dao;
        addButton.addClickListener( clickEvent -> { EditAddWindow<AbstractDTO> editAddWindow =
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

    /**
     * Add filter.
     *
     * @param layout the layout
     */
    protected void addFilter(final VerticalLayout layout) {
        horizontalLayout.addComponent(layout);
        grid.setWidth("100%");
    }

    /**
     * Add stat.
     *
     * @param layout the layout
     */
    protected void addStat(final HorizontalLayout layout) {
        horizontalLayout.addComponent(layout);
    }

    /**
     * Sets entity.
     *
     * @param entity the entity
     */
    public void setEntity(final List<E> entity) {
        this.entity = entity;
    }

    /**
     * Gets dao.
     *
     * @return the dao
     */
    public AbstractDAO<?> getDAO() {
        return dao;
    }

    /**
     * Gets entity.
     *
     * @return the entity
     */
    public List<E> getEntity() {
        return entity;
    }

    /**
     * Refresh grid.
     */
    public void refreshGrid() {
        entity.clear();
        entity.addAll(dao.getAll());
        grid.getDataProvider().refreshAll();
    }

    /**
     * Gets selected item.
     *
     * @return the selected item
     */
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
