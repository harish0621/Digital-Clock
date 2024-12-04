import java.awt.*;  
import java.awt.event.*;  
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class DigitalClockAWT extends Frame {  
    private Label timeLabel;  
    private Choice formatChoice;  
    private String timeFormat = "HH:mm:ss"; // Default 24-hour format  

    public DigitalClockAWT() {  
        // Set up the frame
        setTitle("Digital Clock");  
        setSize(300, 150);  
        setLayout(new BorderLayout());  
        setResizable(false);  
        setVisible(true);  
        setBackground(Color.BLACK);  

        // Time Label
        timeLabel = new Label("", Label.CENTER);  
        timeLabel.setFont(new Font("Arial", Font.BOLD, 30));  
        timeLabel.setForeground(Color.GREEN);  
        add(timeLabel, BorderLayout.CENTER);  

        // Format Choice Panel
        Panel choicePanel = new Panel();  
        choicePanel.setLayout(new FlowLayout());  
        formatChoice = new Choice();  
        formatChoice.add("24-Hour Format");  
        formatChoice.add("12-Hour Format");  
        choicePanel.add(new Label("Select Time Format:"));  
        choicePanel.add(formatChoice);  
        add(choicePanel, BorderLayout.SOUTH);  

        // Add a listener for format choice changes
        formatChoice.addItemListener(new ItemListener() {  
            public void itemStateChanged(ItemEvent e) {  
                if (formatChoice.getSelectedItem().equals("12-Hour Format")) {  
                    timeFormat = "hh:mm:ss a"; // 12-hour format  
                } else {  
                    timeFormat = "HH:mm:ss"; // 24-hour format  
                }  
            }  
        });  

        // Start the clock thread
        Thread clockThread = new Thread(() -> {  
            while (true) {  
                updateTime();  
                try {  
                    Thread.sleep(1000); // Update every second  
                } catch (InterruptedException ex) {  
                    ex.printStackTrace();  
                }  
            }  
        });  
        clockThread.start();  

        // Close event for the window
        addWindowListener(new WindowAdapter() {  
            public void windowClosing(WindowEvent we) {  
                System.exit(0);  
            }  
        });  
    }  

    // Method to update the time
    private void updateTime() {  
        SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);  
        Date date = new Date();  
        timeLabel.setText(formatter.format(date));  
    }  

    public static void main(String[] args) {  
        new DigitalClockAWT();  
    }  
}