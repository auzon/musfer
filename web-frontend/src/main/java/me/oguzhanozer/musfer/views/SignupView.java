package me.oguzhanozer.musfer.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.component.UI;

import me.oguzhanozer.musfer.base.ui.MainLayout;
import me.oguzhanozer.musfer.client.AuthClient;

@Route(value = "signup", layout = MainLayout.class)
@Menu(order = 2, icon = "vaadin:user-plus", title = "Sign up")
@PageTitle("Sign up | Musfer")
public class SignupView extends VerticalLayout {

    private final AuthClient authClient;

    public SignupView(AuthClient authClient) {
        this.authClient = authClient;

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H2 title = new H2("Create your account");

        TextField usernameField = new TextField("Username");
        EmailField emailField = new EmailField("Email");
        PasswordField passwordField = new PasswordField("Password");
        PasswordField confirmPasswordField = new PasswordField("Confirm Password");

        Button signupButton = new Button("Sign Up");
        signupButton.addClickListener(e -> {
            if (!passwordField.getValue().equals(confirmPasswordField.getValue())) {
                Notification.show("Passwords do not match");
                return;
            }

            boolean success = signup(
                    usernameField.getValue(),
                    emailField.getValue(),
                    passwordField.getValue());

            if (success) {
                Notification.show("Signup successful!");
                UI.getCurrent().navigate("login");
            } else {
                Notification.show("Signup failed. Try again.");
            }
        });

        FormLayout formLayout = new FormLayout();
        formLayout.add(usernameField, emailField, passwordField, confirmPasswordField, signupButton);
        formLayout.setMaxWidth("400px");

        add(title, formLayout);
    }

    private boolean signup(String username, String email, String password) {
        // Replace with your real API call
        System.out.println(authClient.login(email, password));
        return true; // For now, always succeed
    }
}
