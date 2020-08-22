package org.testTask.UI;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class MainUI extends VerticalLayout {
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
