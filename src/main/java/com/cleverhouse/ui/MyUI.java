package com.cleverhouse.ui;

import javax.servlet.annotation.WebServlet;

import com.cleverhouse.model.SomeData;
import com.cleverhouse.service.SomeService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import java.util.Date;

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
        VerticalLayout mainLayout = new VerticalLayout();

        final GridLayout horizontalLayout = new GridLayout(2, 1);
        horizontalLayout.setWidth("90%");
        horizontalLayout.addStyleName("v-gray-bordered");
        horizontalLayout.addStyleName("v-left-margined");
        horizontalLayout.setMargin(true);


        final VerticalLayout rightLayout = buildRightLayout();

        final VerticalLayout leftLayout = buildLeftLayout();


        horizontalLayout.addComponents(leftLayout, rightLayout);
        horizontalLayout.addStyleName("v-gray-bordered");

        mainLayout.addComponent(horizontalLayout);


        buildSecondPage(mainLayout);


        setContent(mainLayout);

    }

    void buildSecondPage(VerticalLayout layout){
        //addGrid(layout);
        layout.setMargin(true);

        final TextField textField = new TextField();
        textField.setCaption("Standard text field");

        final DateField dateField = new DateField();
        dateField.setCaption("Date field");
        dateField.setValue(new Date());

        final TextField numberField = new TextField();
        numberField.setConverter(Integer.class);
        numberField.setCaption("Integer field");
        numberField.setValue("0");
        numberField.setStyleName("v-mandatory-field");

        final ListSelect selectList = new ListSelect("List");
        selectList.addItems("Entry A", "Entry B", "Entry C");
        selectList.setNullSelectionAllowed(false);
        selectList.setRows(3);

        final Button moveLeftButton = new Button();
        moveLeftButton.setIcon(FontAwesome.ARROW_LEFT);
        moveLeftButton.setStyleName("v-move-left-button");
        final Button moveRightButton = new Button("-->");
        moveRightButton.setCaption("Move item right");
        final Button selectButton = new Button("SELECT");
        selectButton.setCaption("Normal button");
        final ComboBox comboBox = new ComboBox("Combobox");

        final OptionGroup checkboxGroup = new OptionGroup("Checkboxes");
        checkboxGroup.addItems("One","Two","Three");

        final OptionGroup checkboxMultiselect = new OptionGroup("Checkboxes");
        checkboxMultiselect.addItems("One","Two","Three");
        checkboxMultiselect.setMultiSelect(true);

        final MenuBar menuBar = new MenuBar();
        menuBar.setCaption("MenuBar");
        MenuBar.Command command = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                Notification.show(menuItem.getText() + "selected");
            }
        };
        MenuBar.MenuItem item1 = menuBar.addItem("Item1", null, null);
        menuBar.addItem("Item2",command);
        menuBar.addItem("Item3",command);

        MenuBar.MenuItem subItem = item1.addItem("Subitem 1", null,null);
        item1.addItem("Subitem 2",command);

        subItem.addItem("SubSubitem",command);

        CustomLayout loginLayout = buildLoginLayout();

        CustomLayout complexLayout = buildComplexLayout();


        layout.addComponent(textField);
        layout.addComponent(dateField);
        layout.addComponent(numberField);
        layout.addComponent(selectList);
        layout.addComponent(moveLeftButton);
        layout.addComponent(moveLeftButton);
        layout.addComponent(moveRightButton);
        layout.addComponent(selectButton);
        layout.addComponent(comboBox);
        layout.addComponent(checkboxGroup);
        layout.addComponent(checkboxMultiselect);
        layout.addComponent(menuBar);
        layout.addComponent(new Label("Grid example"));
        addGrid(layout);
        layout.addComponent(loginLayout);
        layout.addComponent(complexLayout);
        setContent(layout);
    }

    private CustomLayout buildComplexLayout() {
        CustomLayout complexFormLayout = new CustomLayout("complexFormLayout");
        complexFormLayout.addStyleName("v-gray-bordered");

        complexFormLayout.addComponent(new TextField(), "username");
        complexFormLayout.addComponent(new TextField(), "password");
        complexFormLayout.addComponent(new Button("Login"), "okbutton");
        complexFormLayout.addComponent(new TextField(), "l1");
        complexFormLayout.addComponent(new TextField(), "l2");
        complexFormLayout.addComponent(new Button("Other"), "l3");

        complexFormLayout.setSizeUndefined();
        return complexFormLayout;
    }


    private CustomLayout buildLoginLayout() {
        CustomLayout loginLayout = new CustomLayout("loginLayout");

        loginLayout.addComponent(new TextField(), "username");
        loginLayout.addComponent(new TextField(), "password");
        loginLayout.addComponent(new Button("Login"), "okbutton");

        loginLayout.setSizeUndefined();
        return loginLayout;
    }


    private void addGrid(VerticalLayout layout) {
        Grid grid = new Grid();
        grid.setContainerDataSource(new BeanItemContainer<>(SomeData.class, service.spamData(7)));
        layout.addComponent(grid);
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

        CheckBox checkbox1 = new CheckBox("Cobertura di base", true);
        checkbox1.addStyleName("v-green-checker");
        CheckBox checkbox2 = new CheckBox("Ohne Titel nur text (neu)", true);
        checkbox2.addStyleName("v-green-checker");
        CheckBox checkbox3 = new CheckBox("Somme di Assicurazione (OCC)", true);
        checkbox3.addStyleName("v-green-checker");
        CheckBox checkbox4 = new CheckBox("Limiti per CC", true);
        checkbox4.addStyleName("v-green-checker");
        CheckBox checkbox5 = new CheckBox("Franchigie", true);
        CheckBox checkbox6 = new CheckBox("Premi", true);
        CheckBox checkbox7 = new CheckBox("Exclusioni generali");
        CheckBox checkbox8 = new CheckBox("Partecipazione all'eccedenza di premio (Domestic)");
        CheckBox checkbox9 = new CheckBox("Selezione destinatari copie");

        leftLayout.addComponents(firstTextField, secondTextField, thirdTextField);
        leftLayout.addComponents(options, checkbox1, checkbox2, checkbox3, checkbox4, checkbox5, checkbox6, checkbox7, checkbox8, checkbox9);
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

        final TextField tf1 = buildTextField("VN; Nome", "100%", "v-mandatory-field");
        final TextField tf2 = buildTextField("VN; Addresszeile 1", "100%", "v-mandatory-field");
        final TextField tf3 = buildTextField("VN; Addresszeile 2", "100%", null);
        final TextField tf4 = buildTextField("VN; PLZ/Ort", "100%", "v-mandatory-field");

        ComboBox cb1 = new ComboBox("VN; Landcode");

        final TextField tf5 = buildTextField("Risikogruppe", "100%", null);
        final TextField tf6 = buildTextField("Nogacode", "100%", null);
        tf6.setEnabled(false);


        final TextField tf7 = buildTextField("Nogacode", "100%", null);
        tf7.setEnabled(false);

        final TextField tf8 = buildTextField("scopo dell'impresa", "100%", null);
        final TextField tf9 = buildTextField("Anzahl Mitarbeiter", "100%", null);
        final TextField tf10 = buildTextField("Anzahl Mitarbeiter", "100%", null);
        final TextField tf11 = buildTextField("Lohnsumme", "100%", null);
        final TextField tf12 = buildTextField("Umsatz", "100%", "v-mandatory-field");

        rightForm.addComponents(tf1, tf2, tf3, tf4, cb1, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12);


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

    private TextField buildTextField(final String caption, final String width, final String styleName) {
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
