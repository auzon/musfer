package me.oguzhanozer.musfer.views;

import com.vaadin.flow.component.login.AbstractLogin.LoginEvent;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import me.oguzhanozer.musfer.base.ui.MainLayout;
import me.oguzhanozer.musfer.client.AuthClient;

import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.UI;

@Route(value = "login", layout = MainLayout.class)
@Menu(order = 1, icon = "vaadin:sign-in", title = "Sign in")
@PageTitle("Sign in | Musfer")
public class LoginView extends VerticalLayout {

    private final AuthClient authClient;

    public LoginView(AuthClient authClient) {
        this.authClient = authClient;

        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(this::handleLogin);

        add(loginForm);
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void handleLogin(LoginEvent event) {
        String username = event.getUsername();
        String password = event.getPassword();

        boolean authenticated = authClient.login(username, password);

        if (authenticated) {
            UI.getCurrent().navigate(""); // logged in
        } else {
            event.getSource().setError(true); // show login error
        }
    }
}
