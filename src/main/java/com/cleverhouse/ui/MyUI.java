package com.cleverhouse.ui;

import javax.servlet.annotation.WebServlet;

import com.cleverhouse.service.SomeService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    SomeService service = SomeService.getInstance();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final GridLayout horizontalLayout = new GridLayout(2,1);
        horizontalLayout.setWidth("90%");
        horizontalLayout.addStyleName("v-gray-bordered");
        horizontalLayout.addStyleName("v-left-margined");
        horizontalLayout.setMargin(true);


        final VerticalLayout rightLayout = buildRightLayout();

        final VerticalLayout leftLayout = buildLeftLayout();


        horizontalLayout.addComponents(leftLayout, rightLayout);
        horizontalLayout.addStyleName("v-gray-bordered");
        setContent(horizontalLayout);


    }

    private VerticalLayout buildLeftLayout() {
        final VerticalLayout leftLayout = new VerticalLayout();
        leftLayout.setWidth("50%");

        TextField firstTextField = new TextField("Geshafts-identifikation");
        firstTextField.setWidth("100%");
        TextField secondTextField = new TextField("SecondText");
        secondTextField.setWidth("100%");
        TextField thirdTextField = new TextField("ThirdText");
        thirdTextField.setWidth("100%");

        OptionGroup options = new OptionGroup();
        options.setMultiSelect(true);
        options.addItems(
        "Ohne Titel nur text (neu) 1",
        "Ohne Titel nur text (neu) 2",
        "Ohne Titel nur text (neu) 3",
        "Ohne Titel nur text (neu) 4");

        CheckBox checkbox1 = new CheckBox("Cobertura di base",true);
        checkbox1.addStyleName("v-green-checker");
        CheckBox checkbox2 = new CheckBox("Ohne Titel nur text (neu)",true);
        checkbox2.addStyleName("v-green-checker");
        CheckBox checkbox3 = new CheckBox("Somme di Assicurazione (OCC)",true);
        checkbox3.addStyleName("v-green-checker");
        CheckBox checkbox4 = new CheckBox("Limiti per CC",true);
        checkbox4.addStyleName("v-green-checker");
        CheckBox checkbox5 = new CheckBox("Franchigie",true);
        CheckBox checkbox6 = new CheckBox("Premi",true);
        CheckBox checkbox7 = new CheckBox("Exclusioni generali");
        CheckBox checkbox8 = new CheckBox("Partecipazione all'eccedenza di premio (Domestic)");
        CheckBox checkbox9 = new CheckBox("Selezione destinatari copie");

        leftLayout.addComponents(firstTextField, secondTextField, thirdTextField);
        leftLayout.addComponents(options,checkbox1,checkbox2,checkbox3,checkbox4,checkbox5,checkbox6,checkbox7,checkbox8,checkbox9);
        return leftLayout;
    }

    private VerticalLayout buildRightLayout() {
        final VerticalLayout rightMainLayout = new VerticalLayout();

        final Panel rightPanel = new Panel("Ohne Titel nur text (neu)");
        rightPanel.addStyleName("v-gray-bordered");
        rightPanel.addStyleName("v-gray-background");

        VerticalLayout vertical = new VerticalLayout();

        final FormLayout rightForm = new FormLayout();
        rightForm.setStyleName("v-gray-background");

        final TextField tf1 = buildTextField("VN; Nome","100%","v-mandatory-field");
        final TextField tf2 = buildTextField("VN; Addresszeile 1","100%","v-mandatory-field");
        final TextField tf3 = buildTextField("VN; Addresszeile 2","100%",null);
        final TextField tf4 = buildTextField("VN; PLZ/Ort","100%","v-mandatory-field");

        ComboBox cb1 = new ComboBox("VN; Landcode");

        final TextField tf5 = buildTextField("Risikogruppe","100%",null);
        final TextField tf6 = buildTextField("Nogacode","100%",null);
        tf6.setEnabled(false);


        final TextField tf7 = buildTextField("Nogacode","100%",null);
        tf7.setEnabled(false);

        final TextField tf8 = buildTextField("scopo dell'impresa","100%",null);
        final TextField tf9 = buildTextField("Anzahl Mitarbeiter","100%",null);
        final TextField tf10 = buildTextField("Anzahl Mitarbeiter","100%",null);
        final TextField tf11 = buildTextField("Lohnsumme","100%",null);
        final TextField tf12 = buildTextField("Umsatz","100%","v-mandatory-field");

        rightForm.addComponents(tf1,tf2,tf3,tf4,cb1,tf5,tf6,tf7,tf8,tf9,tf10,tf11,tf12);


        vertical.addComponent(rightForm);
        rightPanel.setContent(vertical);

        rightMainLayout.addComponent(rightPanel);

        HorizontalLayout hl = new HorizontalLayout();
        hl.addComponent(new Button("Zuruck"));
        hl.addComponent(new Button("weiter"));
        hl.addStyleName("v-left-margined");

        rightMainLayout.addComponent(hl);

        return rightMainLayout;
    }

    private TextField buildTextField(final String caption,final String width, final String styleName) {
        final TextField tf1 = new TextField(caption);
        tf1.setWidth("100%");
        tf1.addStyleName(styleName);
        return tf1;
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
