package com.cleverhouse.ui;

import javax.servlet.annotation.WebServlet;

import com.cleverhouse.model.SomeData;
import com.cleverhouse.service.SomeService;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@StyleSheet({"https://fonts.googleapis.com/css?family=Open+Sans"})
public class MyUI extends UI {

    SomeService service = SomeService.getInstance();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setWidth("100%");
        mainLayout.setMargin(true);

        buildNerwSecondPage(mainLayout);


        setContent(mainLayout);

    }


    void buildNerwSecondPage(VerticalLayout layout) {
        layout.setMargin(true);
        final Button dangerButton = new Button("Danger");
        dangerButton.setStyleName("v-button-danger");

        final Button primaryButton = new Button("Primary");
        primaryButton.setStyleName("v-button-primary");

        final Button grayedOutButton = new Button("Grayed Out");
        grayedOutButton.setStyleName("v-button-grayedout");

        final Button successButton = new Button("Success");
        successButton.setStyleName("v-button-success");

        final Label normalLabel = new Label("Normal label");
        normalLabel.setStyleName("v-label-normal");

        final Label grayedOutLabel = new Label("Grayed out");
        grayedOutLabel.setStyleName("v-label-mandatory");

        final TextField textFieldGrayCaption = new TextField("Caption");
        textFieldGrayCaption.setInputPrompt("Placeholder");
        textFieldGrayCaption.setStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        textFieldGrayCaption.addStyleName("v-textfield-normal");

        final TextField mandatoryTextField = new TextField("Caption");
        mandatoryTextField.setInputPrompt("Placeholder");
        mandatoryTextField.setStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        mandatoryTextField.addStyleName("v-textfield-mandatory");


        final ComboBox combobox = new ComboBox("Caption");
        combobox.addStyleName(ValoTheme.COMBOBOX_BORDERLESS);
        combobox.addStyleName("v-combobox-normal");
        combobox.addItem("One");
        combobox.addItem("Two");
        combobox.addItem("Drei");
        combobox.setNullSelectionAllowed(false);
        combobox.setValue(combobox.getItemIds().iterator().next());

        final ComboBox grayCombobox = new ComboBox("Caption");
        grayCombobox.addStyleName(ValoTheme.COMBOBOX_BORDERLESS);
        grayCombobox.addStyleName("v-combobox-mandatory");
        grayCombobox.addItem("One");
        grayCombobox.addItem("Two");
        grayCombobox.addItem("Drei");
        grayCombobox.setNullSelectionAllowed(false);
        grayCombobox.setValue(grayCombobox.getItemIds().iterator().next());

        final DateField datefield = new DateField();
        datefield.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
        datefield.setCaption("Caption");
        datefield.addStyleName("v-datefield-normal");

        final DateField datefield2 = new DateField();
        datefield2.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
        datefield2.setCaption("Caption");
        datefield2.addStyleName("v-datefield-mandatory");

        final Tree tree = new Tree("Tree");
        tree.addStyleName("v-tree-normal");
        for (int i = 0; i < 10; i++) { // 10*10 elements
            tree.addItem(makeTree(tree, i, 0));
        }

        final MenuBar menuBar = new MenuBar();
        menuBar.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
        menuBar.addStyleName("v-menubar-normal");
        menuBar.setCaption("Multi menu");
        MenuBar.Command command = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                Notification.show(menuItem.getText() + "selected");
            }
        };
        MenuBar.MenuItem item1 = menuBar.addItem("Item1", null, null);
        menuBar.addItem("Item2", command);
        menuBar.addItem("Item3", command);
        MenuBar.MenuItem subItem = item1.addItem("Subitem 1", null, null);
        item1.addItem("Subitem 2", command);
        subItem.addItem("SubSubitem", command);

        Table table = new Table("Table");
        table.setContainerDataSource(new BeanItemContainer<>(SomeData.class, service.spamData(25)));
        table.addStyleName(ValoTheme.TABLE_BORDERLESS);
        table.addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
        table.addStyleName(ValoTheme.TABLE_NO_STRIPES);
        table.addStyleName("v-table-normal");




        BuildComplexForm buildComplexForm = new BuildComplexForm(combobox).invoke();
        Panel panel = buildComplexForm.getPanel();
        FormLayout form = buildComplexForm.getForm();

        panel.setContent(form);

        layout.addComponents(dangerButton, primaryButton, grayedOutButton, successButton);
        layout.addComponents(normalLabel, grayedOutLabel);
        layout.addComponents(combobox, grayCombobox);
        layout.addComponents(textFieldGrayCaption, mandatoryTextField);
        layout.addComponents(datefield, datefield2);
        layout.addComponent(tree);
        layout.addComponent(menuBar);
        layout.addComponent(table);
        layout.addComponent(panel);


        Panel formPanel = new Panel("Form Panel");
        formPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setMargin(true);
        hLayout.setSizeFull();
        FormLayout form1 = buildLeftLayout();
        FormLayout form2 = buildRightLayout();
        hLayout.addComponents(form1, form2);
        formPanel.setContent(hLayout);

        layout.addComponents(formPanel);
    }


    // NEW 161004
    private int makeTree(Tree tree, int i, int k) {
        String icon = "boxed-blue.png";

        tree.setChildrenAllowed(i, (k < 3 ? true : false));
        tree.setItemCaption(i, "Component " + i);
        if (k < 3)
            for (int j = 1; j < 10; j++) {
                int id = makeTree(tree, i * 10 + j, k + 1);
                tree.addItem(id);
                tree.setParent(id, i);
                tree.setItemIcon(id, new ThemeResource("newicons/" + icon));
            }
        return i;
    }


    private FormLayout buildLeftLayout() {
        final FormLayout leftLayout = new FormLayout();

        TextField firstTextField = new TextField("Geshafts-identifikation");
        firstTextField.setWidth("100%");
        firstTextField.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        firstTextField.addStyleName("v-textfield-mandatory");
        TextField secondTextField = new TextField("SecondText");
        secondTextField.setWidth("100%");
        secondTextField.addStyleName("v-textfield-normal");
        secondTextField.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        TextField thirdTextField = new TextField("ThirdText");
        thirdTextField.setWidth("100%");
        thirdTextField.addStyleName("v-textfield-normal");
        thirdTextField.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);

        DateField datefield = new DateField("Date");
        datefield.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
        datefield.addStyleName("v-datefield-normal");


//        OptionGroup options = new OptionGroup();
//        options.setMultiSelect(true);
//        options.addItems(
//                "Ohne Titel nur text (neu) 1",
//                "Ohne Titel nur text (neu) 2",
//                "Ohne Titel nur text (neu) 3",
//                "Ohne Titel nur text (neu) 4");
//        // NEW 161004
        final Tree tree = new Tree();
        tree.addStyleName(ValoTheme.TREETABLE_BORDERLESS);
        tree.addStyleName("v-tree-normal");
        for (int i = 0; i < 10; i++) { // 10*10 elements
            tree.addItem(makeTree(tree, i, 0));
        }

        leftLayout.addComponents(firstTextField, secondTextField, thirdTextField, datefield);
        leftLayout.addComponent(tree);

        return leftLayout;


    }

    private FormLayout buildRightLayout() {
        final FormLayout rightMainLayout = new FormLayout();

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
        cb1.addStyleName(ValoTheme.COMBOBOX_BORDERLESS);
        cb1.addStyleName("v-combobox-normal");

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
        rightPanel.addStyleName(ValoTheme.PANEL_BORDERLESS);
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
        tf1.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        tf1.addStyleName("v-textfield-normal");
        tf1.addStyleName(styleName);
        return tf1;
    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    private class BuildComplexForm {
        private ComboBox combobox;
        private Panel panel;
        private FormLayout form;

        public BuildComplexForm(ComboBox combobox) {
            this.combobox = combobox;
        }

        public Panel getPanel() {
            return panel;
        }

        public FormLayout getForm() {
            return form;
        }

        public BuildComplexForm invoke() {
            panel = new Panel("PANEL 1");
            TextField txtForPanel = new TextField("Txt 1");
            txtForPanel.setStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
            txtForPanel.addStyleName("v-textfield-normal");

            final ComboBox comboboxForPanel = new ComboBox("Caption");
            comboboxForPanel.addStyleName(ValoTheme.COMBOBOX_BORDERLESS);
            comboboxForPanel.addStyleName("v-combobox-normal");
            comboboxForPanel.addItem("One");
            comboboxForPanel.addItem("Two");
            comboboxForPanel.addItem("Drei");
            comboboxForPanel.setNullSelectionAllowed(false);
            comboboxForPanel.setValue(combobox.getItemIds().iterator().next());


            Panel panel2 = new Panel("PANEL 2");
            FormLayout secondForm = new FormLayout();
            secondForm.setMargin(true);
            TextField txtForPanel2 = new TextField("Txt 2");
            txtForPanel2.setStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
            txtForPanel2.addStyleName("v-textfield-mandatory");
            secondForm.addComponent(txtForPanel2);


            Panel panel3 = new Panel("PANEL 3");
            FormLayout thirdLayout = new FormLayout();
            thirdLayout.setMargin(true);
            TextField txt3 = new TextField("Txt 3");
            txt3.setStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
            txt3.addStyleName("v-textfield-normal");
            thirdLayout.addComponent(txt3);
            panel3.setContent(thirdLayout);

            final DateField datefield = new DateField();
            datefield.addStyleName(ValoTheme.DATEFIELD_BORDERLESS);
            datefield.setCaption("A date");
            datefield.addStyleName("v-datefield-mandatory");
            secondForm.addComponent(panel3);
            secondForm.addComponent(datefield);

            panel2.setContent(secondForm);

            form = new FormLayout();
            form.setMargin(true);
            form.addComponent(txtForPanel);
            form.addComponent(comboboxForPanel);
            form.addComponent(panel2);
            return this;
        }
    }
}
