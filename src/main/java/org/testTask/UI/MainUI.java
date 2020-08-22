package org.testTask.UI;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 * The type Main ui.
 */
public class MainUI extends VerticalLayout {
    /**
     * Instantiates a new Main ui.
     */
    public MainUI() {
        TabSheet tabSheet = new TabSheet();

        AuthorUI authorUI = new AuthorUI();
        BookUI bookUI = new BookUI();
        GenreUI genreUI = new GenreUI();
        tabSheet.addTab(bookUI, "Books");
        tabSheet.addTab(authorUI, "Authors");
        tabSheet.addTab(genreUI, "Genres");
        addComponents(tabSheet);
    }
}
