package main.view;

import javax.swing.*;
import java.awt.*;

public class DateInputDialog extends JDialog {

    private static final Color BG_DARK = new Color(20, 25, 45);
    private static final Color ACCENT_YELLOW = new Color(255, 205, 60);
    private static final Color FIELD_LIGHT = new Color(190, 185, 230);
    private static final Color TEXT_DARK = new Color(30, 25, 60);

    private JTextField dayField;
    private JTextField monthField;
    private JTextField yearField;
    private JButton okButton;
    private boolean confirmed = false;

    public DateInputDialog(JFrame parent) {
        super(parent, "Ввод даты рождения", true);
        setSize(350, 260);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));
        panel.setBackground(BG_DARK); // сплошной тёмный фон вместо картинки
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(panel);

        addStyledLabel(panel, "День:");
        dayField = createStyledField();
        panel.add(dayField);

        addStyledLabel(panel, "Месяц:");
        monthField = createStyledField();
        panel.add(monthField);

        addStyledLabel(panel, "Год:");
        yearField = createStyledField();
        panel.add(yearField);

        okButton = new JButton("OK");
        styleButton(okButton);

        panel.add(new JLabel());
        panel.add(okButton);

        okButton.addActionListener(e -> {
            confirmed = true;
            setVisible(false);
        });
    }

    private void addStyledLabel(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setForeground(ACCENT_YELLOW);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label);
    }

    private JTextField createStyledField() {
        JTextField field = new JTextField();
        field.setBackground(FIELD_LIGHT);
        field.setForeground(TEXT_DARK);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        return field;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(ACCENT_YELLOW);
        button.setBackground(FIELD_LIGHT);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setValues(int day, int month, int year) {
        dayField.setText(String.valueOf(day));
        monthField.setText(String.valueOf(month));
        yearField.setText(String.valueOf(year));
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void resetConfirmed() {
        confirmed = false;
    }

    public String getDayText() { return dayField.getText().trim(); }
    public String getMonthText() { return monthField.getText().trim(); }
    public String getYearText() { return yearField.getText().trim(); }
}