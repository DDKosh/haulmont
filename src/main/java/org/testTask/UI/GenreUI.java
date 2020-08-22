package org.testTask.UI;

import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import org.testTask.DAO.BookDAO;
import org.testTask.DAO.GenreDAO;
import org.testTask.DTO.Genre;

import java.util.*;


public class GenreUI extends AbstractUI{

    private Button statButton = new Button("Statistic");
    private Button resetButton = new Button("Reset");

    HorizontalLayout layout = new HorizontalLayout();

    public GenreUI() {
        super(new GenreDAO());
        layout.addComponents(statButton, resetButton);
        addStat(layout);
        statButton.addClickListener(clickEvent -> {
            Map<Genre, Integer> list = new HashMap<>();
            for(Genre g : (List<Genre>) getEntity()) {
                list.put(g, new BookDAO().getAllByGenre(g).size());
            }
            grid.addColumn(c -> list.get(c)).setCaption("Count").setId("Count");
        });

        resetButton.addClickListener(clickEvent -> {
            if (grid.getColumn("Count") != null)
            grid.removeColumn(grid.getColumn("Count"));
            refreshGrid();
        });
    }

    @Override
    public Grid createGrid() {
        Grid<Genre> grid = new Grid<>();
        grid.asSingleSelect();
        grid.addColumn(Genre::getTitle).setCaption("Title");
        setEntity(getDAO().getAll());
        grid.setItems(getEntity());
        grid.getDataProvider().refreshAll();
        return grid;
    }

    private void getCount() {

    }
}
