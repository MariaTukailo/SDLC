package main.view;

import main.model.DateBirth;
import main.model.Fate;
import main.model.FateRepository;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainForm extends JFrame {

    private static final Color PANEL_PURPLE = new Color(70, 62, 130);
    private static final Color FIELD_LIGHT = new Color(190, 185, 230);
    private static final Color ACCENT_YELLOW = new Color(255, 205, 60);
    private static final Color TEXT_DARK = new Color(30, 25, 60);

    private JButton inputDateButton;
    private JTextArea resultArea;
    private Image backgroundImage;

    public MainForm(DateBirth model) {

        UIManager.put("OptionPane.background", new Color(20, 25, 45));
        UIManager.put("Panel.background", new Color(20, 25, 45));
        UIManager.put("OptionPane.messageForeground", new Color(255, 205, 60));
        UIManager.put("Button.background", new Color(190, 185, 230));
        UIManager.put("Button.foreground", new Color(30, 25, 60));


        setTitle("Рассчитать судьбу по дню недели");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loadBackgroundImage();

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 40, 40));
        setContentPane(backgroundPanel);


        JLabel title = new JLabel("РАССЧИТАТЬ СУДЬБУ ПО ДНЮ НЕДЕЛИ");
        title.setHorizontalAlignment(SwingConstants.CENTER); // добавить эту строку
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(ACCENT_YELLOW);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        backgroundPanel.add(title, BorderLayout.NORTH);


        RoundedPanel mainPanel = new RoundedPanel(30);
        mainPanel.setBackground(PANEL_PURPLE);
        mainPanel.setLayout(new BorderLayout(20, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));


        JPanel leftPanel = new JPanel(new BorderLayout(0, 15));
        leftPanel.setOpaque(false);

        RoundedPanel buttonWrapper = new RoundedPanel(20);
        buttonWrapper.setBackground(FIELD_LIGHT);
        buttonWrapper.setLayout(new BorderLayout());

        inputDateButton = new JButton("ВВЕСТИ ДАТУ РОЖДЕНИЯ");
        inputDateButton.setFont(new Font("Arial", Font.BOLD, 16));
        inputDateButton.setForeground(ACCENT_YELLOW);
        inputDateButton.setBackground(FIELD_LIGHT);
        inputDateButton.setBorderPainted(false);
        inputDateButton.setFocusPainted(false);
        inputDateButton.setContentAreaFilled(false);
        inputDateButton.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        inputDateButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonWrapper.add(inputDateButton, BorderLayout.CENTER);

        leftPanel.add(buttonWrapper, BorderLayout.NORTH);

        RoundedPanel resultWrapper = new RoundedPanel(20);
        resultWrapper.setBackground(FIELD_LIGHT);
        resultWrapper.setLayout(new BorderLayout());

        resultArea = new JTextArea("Судьба не вычислена");
        resultArea.setWrapStyleWord(true);
        resultArea.setLineWrap(true);
        resultArea.setEditable(false);
        resultArea.setOpaque(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setForeground(TEXT_DARK);
        resultArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        resultWrapper.add(scrollPane, BorderLayout.CENTER);

        leftPanel.add(resultWrapper, BorderLayout.CENTER);

        mainPanel.add(leftPanel, BorderLayout.CENTER);


        JLabel catLabel = createCatLabel();
        if (catLabel != null) {
            mainPanel.add(catLabel, BorderLayout.EAST);
        }

        backgroundPanel.add(mainPanel, BorderLayout.CENTER);

        model.addChangeListener(evt -> {
            DateBirth updated = (DateBirth) evt.getNewValue();
            Fate fate = FateRepository.findById(updated.getDayOfWeek());
            showResult(fate.getDescription());
        });
    }

    private void loadBackgroundImage() {
        URL url = getClass().getClassLoader().getResource("resources/sky.jpg");
        if (url != null) {
            backgroundImage = new ImageIcon(url).getImage();
        }
    }

    private JLabel createCatLabel() {
        URL url = getClass().getClassLoader().getResource("resources/cat.png");
        if (url == null) {
            return null;
        }
        ImageIcon icon = new ImageIcon(url);
        Image scaled = icon.getImage().getScaledInstance(180, -1, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(scaled));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        return label;
    }

    public void addInputDateListener(java.awt.event.ActionListener listener) {
        inputDateButton.addActionListener(listener);
    }

    public void showResult(String text) {
        resultArea.setText(text);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}