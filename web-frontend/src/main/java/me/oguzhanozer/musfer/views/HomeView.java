package me.oguzhanozer.musfer.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import me.oguzhanozer.musfer.base.ui.MainLayout;

@Route(value = "", layout = MainLayout.class)
@Menu(order = 0, icon = "vaadin:home", title = "Home")
@PageTitle("Home | Musfer")
public class HomeView extends VerticalLayout {

    public HomeView() {

        add(new H1("Musfer"));
        add(new Paragraph("Transfer your playlists from one platform to another easily."));

    }
}
