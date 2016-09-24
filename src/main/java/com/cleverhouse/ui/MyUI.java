package com.cleverhouse.ui;

import javax.servlet.annotation.WebServlet;

import com.cleverhouse.model.SomeData;
import com.cleverhouse.service.SomeService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
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
        final VerticalLayout layout = new VerticalLayout();
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
        menuBar.addItem("Item1",command);
        menuBar.addItem("Item2",command);
        menuBar.addItem("Item3",command);

        CustomLayout loginLayout = new CustomLayout("loginLayout");

        loginLayout.addComponent(new TextField(), "username");
        loginLayout.addComponent(new TextField(), "password");
        loginLayout.addComponent(new Button("Login"), "okbutton");

        loginLayout.setSizeUndefined();

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
        addGrid(layout);
        layout.addComponent(loginLayout);
        setContent(layout);
    }

    private void addGrid(VerticalLayout layout) {
        Grid grid = new Grid();
        grid.setContainerDataSource(new BeanItemContainer<>(SomeData.class, service.spamData(7)));
        layout.addComponent(grid);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
