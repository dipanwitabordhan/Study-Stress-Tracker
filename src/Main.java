import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        showWelcomePage();
    }

    private static void showWelcomePage() {
        JFrame welcome = new JFrame("Welcome");
        welcome.setSize(600, 400);
        welcome.setLocationRelativeTo(null);
        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcome.setLayout(null);

        welcome.getContentPane().setBackground(Color.black);

        GradientLabel title = new GradientLabel("Daily Study & Stress Tracker", new Color(255, 182, 193), new Color(128, 0, 128));
        title.setBounds(50, 80, 500, 60);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        welcome.add(title);

        JLabel subtitle = new JLabel("Track your day, understand your mind", SwingConstants.CENTER);
        subtitle.setBounds(50, 140, 500, 30);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitle.setForeground(Color.white);
        welcome.add(subtitle);

        GradientButton start = new GradientButton("Start", new Color(0, 200, 255), new Color(128, 0, 255));
        start.setBounds(220, 240, 150, 45);
        start.setFont(new Font("Arial", Font.BOLD, 18));
        start.addActionListener(e -> {
            welcome.dispose();
            showMainInputPage();
        });
        welcome.add(start);

        welcome.setVisible(true);
    }

    private static void showMainInputPage() {
        JFrame frame = new JFrame();
        frame.setTitle("Daily Study & Stress Tracker");
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(5, 20, 45)); 

     
        GradientLabel nameLabel = new GradientLabel("Student Name:", new Color(255, 182, 193), new Color(128, 0, 128));
        nameLabel.setBounds(50, 50, 120, 30);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(180, 50, 200, 30);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14)); 
        frame.add(nameField);

        GradientLabel studyLabel = new GradientLabel("Study Hours:", new Color(255, 182, 193), new Color(128, 0, 128));
        studyLabel.setBounds(50, 100, 120, 30);
        studyLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(studyLabel);

        JSpinner studySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 16, 1));
        studySpinner.setBounds(180, 100, 70, 30);
        studySpinner.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(studySpinner);

        GradientLabel moodLabel = new GradientLabel("Mood:", new Color(255, 182, 193), new Color(128, 0, 128));
        moodLabel.setBounds(50, 150, 120, 30);
        moodLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(moodLabel);

        JRadioButton stressedButton = new JRadioButton("Stressed");
        stressedButton.setBounds(180, 150, 90, 30);
        stressedButton.setForeground(Color.red);
        stressedButton.setOpaque(false);
        stressedButton.setFont(new Font("Arial", Font.PLAIN, 14));

        JRadioButton neutralButton = new JRadioButton("Neutral");
        neutralButton.setBounds(270, 150, 80, 30);
        neutralButton.setForeground(Color.orange);
        neutralButton.setOpaque(false);
        neutralButton.setFont(new Font("Arial", Font.PLAIN, 14));

        JRadioButton relaxedButton = new JRadioButton("Relaxed");
        relaxedButton.setBounds(350, 150, 80, 30);
        relaxedButton.setForeground(new Color(128, 0, 255)); 
        relaxedButton.setOpaque(false);
        relaxedButton.setFont(new Font("Arial", Font.PLAIN, 14));

        ButtonGroup moodGroup = new ButtonGroup();
        moodGroup.add(stressedButton);
        moodGroup.add(neutralButton);
        moodGroup.add(relaxedButton);

        frame.add(stressedButton);
        frame.add(neutralButton);
        frame.add(relaxedButton);

        GradientLabel stressLabel = new GradientLabel("Stress Level (1-10):", new Color(255, 182, 193), new Color(128, 0, 128));
        stressLabel.setBounds(50, 200, 150, 30);
        stressLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(stressLabel);

        JSlider stressSlider = new JSlider(1, 10, 5);
        stressSlider.setBounds(180, 200, 200, 50);
        stressSlider.setMajorTickSpacing(1);
        stressSlider.setPaintTicks(true);
        stressSlider.setPaintLabels(true);
        stressSlider.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(stressSlider);

        stressedButton.addActionListener(e -> {
            if (stressSlider.getValue() < 5) stressSlider.setValue(5);
            stressSlider.setMinimum(5);
            stressSlider.setMaximum(10);
        });

        relaxedButton.addActionListener(e -> {
            if (stressSlider.getValue() > 5) stressSlider.setValue(5);
            stressSlider.setMinimum(1);
            stressSlider.setMaximum(5);
        });

        neutralButton.addActionListener(e -> {
            stressSlider.setMinimum(4);
            stressSlider.setMaximum(5);
        });

        GradientButton submitButton = new GradientButton("Submit", new Color(0, 200, 255), new Color(128, 0, 255));
        submitButton.setBounds(200, 280, 150, 40);
        frame.add(submitButton);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            int studyHours = (Integer) studySpinner.getValue();
            int stress = stressSlider.getValue();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (studyHours == 0) {
                JOptionPane.showMessageDialog(frame, "Please select your study hours.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!stressedButton.isSelected() && !neutralButton.isSelected() && !relaxedButton.isSelected()) {
                JOptionPane.showMessageDialog(frame, "Please select your mood.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String mood;
            if (stressedButton.isSelected()) mood = "Stressed";
            else if (neutralButton.isSelected()) mood = "Neutral";
            else mood = "Relaxed";

            String comment;
            if (studyHours < 2) comment = "Study time too low.";
            else if (mood.equals("Relaxed") && stress < 4) comment = "Calm and focused.";
            else if (mood.equals("Stressed") && stress > 6) comment = "Overloaded — take a break.";
            else if (stress >= 7) comment = "High stress — consider resting.";
            else comment = "Good balance today.";

            showResultWindow(name, studyHours, mood, stress, comment);
        });

        frame.setVisible(true);
    }

    private static void showResultWindow(String name, int studyHours, String mood, int stress, String comment) {
        JFrame resultFrame = new JFrame("Your Daily Report");
        resultFrame.setSize(500, 350);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setLayout(null);

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                if (mood.equals("Stressed")) {
                    g2.setPaint(new GradientPaint(0, 0, new Color(128, 0, 128), 0, getHeight(), new Color(200, 30, 30)));
                } else if (mood.equals("Neutral")) {
                    g2.setPaint(new GradientPaint(0, 0, new Color(255, 182, 193), 0, getHeight(), new Color(255, 200, 200))); 
                } else { // Relaxed
                    g2.setPaint(new GradientPaint(0, 0, new Color(160, 82, 255), 0, getHeight(), new Color(128, 0, 128))); 
                }
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };
        panel.setBounds(0, 0, 500, 350);
        panel.setLayout(null);

        Color textColor;
        if (mood.equals("Stressed")) textColor = Color.white;
        else if (mood.equals("Neutral")) textColor = new Color(102, 0, 51);
        else textColor = Color.white; 

        JLabel resultLabel = new JLabel("<html>Name: " + name +
                "<br>Study Hours: " + studyHours +
                "<br>Mood: " + mood +
                "<br>Stress: " + stress +
                "<br><br><b>Feedback:</b> " + comment + "</html>");
        resultLabel.setBounds(30, 30, 420, 240);
        resultLabel.setForeground(textColor);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16)); 
        panel.add(resultLabel);

        resultFrame.add(panel);
        resultFrame.setVisible(true);
    }

    static class GradientButton extends JButton {
        private Color color1;
        private Color color2;

        public GradientButton(String text, Color c1, Color c2) {
            super(text);
            color1 = c1;
            color2 = c2;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(Color.white);
            setFont(new Font("Arial", Font.BOLD, 16));
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(new GradientPaint(0, 0, color1, getWidth(), 0, color2));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            super.paintComponent(g2);
            g2.dispose();
        }

        protected void paintBorder(Graphics g) {}
    }

    static class GradientLabel extends JLabel {
        private Color color1;
        private Color color2;

        public GradientLabel(String text, Color c1, Color c2) {
            super(text);
            color1 = c1;
            color2 = c2;
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setPaint(new GradientPaint(0, 0, color1, getWidth(), 0, color2));
            g2.setFont(getFont());
            g2.drawString(getText(), 0, getHeight() - 5);
            g2.dispose();
        }
    }
}  