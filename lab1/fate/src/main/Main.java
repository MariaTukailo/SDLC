package main;

import main.controller.MainController;
import main.model.DateBirth;
import main.view.MainForm;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        DateBirth model = new DateBirth();
        MainForm view = new MainForm(model);
        MainController controller = new MainController(model, view);
        view.setVisible(true);
    }
}