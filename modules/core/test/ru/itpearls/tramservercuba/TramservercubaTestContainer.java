package ru.itpearls.tramservercuba;

import com.haulmont.bali.util.Dom4j;
import com.haulmont.cuba.testsupport.TestContainer;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class TramservercubaTestContainer extends TestContainer {

    public TramservercubaTestContainer() {
        super();
        appComponents = Arrays.asList(
                "com.haulmont.cuba",
                "com.haulmont.charts",
                "com.haulmont.reports",
                "com.haulmont.addon.dashboardchart",
                "com.haulmont.addon.dashboard");
        appPropertiesFiles = Arrays.asList(
                // List the files defined in your web.xml
                // in appPropertiesConfig context parameter of the core module
                "ru/itpearls/tramservercuba/app.properties",
                // Add this file which is located in CUBA and defines some properties
                // specifically for test environment. You can replace it with your own
                // or add another one in the end.
                "test-app.properties");
        initDbProperties();
    }

    private void initDbProperties() {
        File contextXmlFile = new File("modules/core/web/META-INF/context.xml");
        if (!contextXmlFile.exists()) {
            contextXmlFile = new File("web/META-INF/context.xml");
        }
        if (!contextXmlFile.exists()) {
            throw new RuntimeException("Cannot find 'context.xml' file to read database connection properties. " +
                    "You can set them explicitly in this method.");
        }
        Document contextXmlDoc = Dom4j.readDocument(contextXmlFile);
        Element resourceElem = contextXmlDoc.getRootElement().element("Resource");

        dbDriver = resourceElem.attributeValue("driverClassName");
        dbUrl = resourceElem.attributeValue("url");
        dbUser = resourceElem.attributeValue("username");
        dbPassword = resourceElem.attributeValue("password");
    }

    public static class Common extends TramservercubaTestContainer {

        public static final TramservercubaTestContainer.Common INSTANCE = new TramservercubaTestContainer.Common();

        private static volatile boolean initialized;

        private Common() {
        }

        @Override
        public void before() throws Throwable {
            if (!initialized) {
                super.before();
                initialized = true;
            }
            setupContext();
        }

        @Override
        public void after() {
            cleanupContext();
            // never stops - do not call super
        }
    }
}