package ru.itpearls.tramservercuba.web.screens;

import com.haulmont.cuba.gui.Route;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.web.app.login.LoginScreen;
import com.haulmont.cuba.web.gui.screen.ScreenDependencyUtils;
import com.vaadin.ui.Dependency;
import javax.inject.Inject;


@Route(path = "login", root = true)
@UiController("loginTramServerCuba")
@UiDescriptor("app-login-screen.xml")
public class AppLoginScreen extends LoginScreen {

    @Inject
    protected HBoxLayout bottomPanel;

    @Inject
    protected Label<String> poweredByLink;
    @Inject
    private Image backgroundImage;

    @Subscribe
    public void onAppLoginScreenInit(InitEvent event) {
        loadStyles();

        initBottomPanel();
    }

    private void initLoginImage() {
        int count = (int) (Math.random() * 5 + 1);
        backgroundImage
                .setSource(RelativePathResource.class)
                .setPath("VAADIN/brand-login-screen/system-100-" + count + ".jpg");
    }

    @Subscribe("submit")
    public void onSubmit(Action.ActionPerformedEvent event) {
        login();
    }

    protected void loadStyles() {
        ScreenDependencyUtils.addScreenDependency(this,
                "vaadin://brand-login-screen/login.css", Dependency.Type.STYLESHEET);
    }

    protected void initBottomPanel() {
        if (!globalConfig.getLocaleSelectVisible()) {
            poweredByLink.setAlignment(Component.Alignment.MIDDLE_CENTER);

            if (!webConfig.getLoginDialogPoweredByLinkVisible()) {
                bottomPanel.setVisible(false);
            }
        }
    }

    @Override
    protected void initLogoImage() {
        logoImage.setSource(RelativePathResource.class)
                .setPath("VAADIN/brand-login-screen/tramIcon.png");
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        initLoginImage();
    }
}