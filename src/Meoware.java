import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.*;

public class Meoware {
    private static final ArrayList<URL> gifUrls = new ArrayList<>();
    private static final Random random = new Random();
    private static int windowCount = 0;

    public static void main(String[] args) {
        for (int i = 1; i <= 22; i++) {
            try {
                URL gifUrl = Meoware.class.getResource("/resources/" + i + ".gif");
                if (new ImageIcon(gifUrl).getImageLoadStatus() == 8) {
                    gifUrls.add(gifUrl);
                }
            } catch (Exception e) {
                System.out.println("Failed to load: " + e.getMessage());
            }
        }

        for (int i = 0; i < 3; i++) {
            createGifWindow();
        }

        new Timer(3000, e -> {
            createGifWindow();
            showErrorDialog();
        }).start();
    }

    private static void createGifWindow() {
        if (gifUrls.isEmpty() || windowCount >= 15) return;

        try {
            JFrame gifWindow = new JFrame();
            gifWindow.setAlwaysOnTop(true);
            gifWindow.setResizable(false);
            gifWindow.add(new JLabel(new ImageIcon(gifUrls.get(random.nextInt(gifUrls.size())))));
            gifWindow.pack();

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            gifWindow.setLocation(
                    random.nextInt(screenSize.width - gifWindow.getWidth()),
                    random.nextInt(screenSize.height - gifWindow.getHeight())
            );

            gifWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            gifWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosed(WindowEvent e) {
                    windowCount--;
                    for (int i = 0; i < 3 + random.nextInt(4); i++) {
                        createGifWindow();
                    }
                }
            });

            windowCount++;
            gifWindow.setVisible(true);
        } catch (Exception e) {
            System.out.println("Failed to create window: " + e.getMessage());
        }
    }

    private static void showErrorDialog() {
        String[] errorMessages = {
                "MEOW! System Overload",
                "PURR-ocessor Failure",
                "HISS-terical Error",
                "WHISKER Malfunction",
                "PAW-sitive Crash",
                "FUR-ocious Warning",
                "CLAW-strophic Failure",
                "TAIL Spin Error",
                "SNOOT BSOD",
                "ZOOM-ie Panic"
        };

        String[] buttonTexts = {
                "OK", "NO", "WHY", "STOP",
                "HELP", "ACK", "SRSLY",
                "PLS", "NOPE", "UGH"
        };

        JDialog errorDialog = new JDialog();
        errorDialog.setTitle(errorMessages[random.nextInt(errorMessages.length)]);
        errorDialog.setLayout(new BorderLayout());

        errorDialog.add(new JLabel(
                "<html><center>" +
                        errorMessages[random.nextInt(errorMessages.length)] +
                        "<br>Error Code: CAT-" + random.nextInt(999) +
                        "</center></html>",
                JLabel.CENTER
        ), BorderLayout.CENTER);

        JButton closeButton = new JButton(buttonTexts[random.nextInt(buttonTexts.length)]);
        closeButton.addActionListener(e -> errorDialog.dispose());
        errorDialog.add(closeButton, BorderLayout.SOUTH);

        errorDialog.setSize(300 + random.nextInt(100), 100 + random.nextInt(50));
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        errorDialog.setLocation(
                random.nextInt(screen.width - errorDialog.getWidth()),
                random.nextInt(screen.height - errorDialog.getHeight())
        );

        errorDialog.setAlwaysOnTop(true);
        errorDialog.setModal(true);
        errorDialog.setVisible(true);
    }
}