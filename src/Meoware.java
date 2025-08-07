import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.*;

public class Meoware {
    private static final ArrayList<URL> gifUrls = new ArrayList<>();
    private static final Random random = new Random();

    public static void main(String[] args) {
        for (int i = 1; i <= 22; i++) {
            try {
                URL gifUrl = Meoware.class.getResource("/resources/" + i + ".gif");
                gifUrls.add(gifUrl);
            } catch (Exception ignored) {}
        }

        for (int i = 0; i < 3; i++) {
            createGifWindow();
        }

        new Timer(200, e -> createGifWindow()).start();
    }

    private static void createGifWindow() {
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
                    for (int i = 0; i < 1 + random.nextInt(3); i++) {
                        createGifWindow();
                    }
                }
            });

            gifWindow.setVisible(true);
        } catch (Exception ignored) {}
    }
}