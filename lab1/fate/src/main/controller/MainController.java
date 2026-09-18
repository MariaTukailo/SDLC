package main.controller;

import main.model.DateBirth;
import main.view.DateInputDialog;
import main.view.MainForm;

public class MainController {

    private final DateBirth model;
    private final MainForm view;

    public MainController(DateBirth model, MainForm view) {
        this.model = model;
        this.view = view;

        view.addInputDateListener(e -> onInputDate());
    }

    private void onInputDate() {
        DateInputDialog dialog = new DateInputDialog(view);
        dialog.setValues(model.getDay(), model.getMonth(), model.getYear());

        while (true) {
            dialog.setVisible(true);

            if (!dialog.isConfirmed()) {
                return;
            }

            try {
                int day = Integer.parseInt(dialog.getDayText());
                int month = Integer.parseInt(dialog.getMonthText());
                int year = Integer.parseInt(dialog.getYearText());

                DateBirth candidate = new DateBirth(day, month, year);
                if (!candidate.isValid()) {
                    view.showError("Введена некорректная дата.");
                    dialog.resetConfirmed();
                    continue;
                }

                model.setDate(day, month, year);
                return;

            } catch (NumberFormatException e) {
                view.showError("Все поля должны быть числами.");
                dialog.resetConfirmed();
            }
        }
    }


}