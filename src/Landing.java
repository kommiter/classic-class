import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class Landing extends JPanel {
    private BufferedImage pianoImage;
    private Font titleFont;
    private Font subFont;
    private Font guideFont;

    public Landing() {
        // image
        try {
            pianoImage = javax.imageio.ImageIO.read(new File("image/piano.png"));
        } catch (IOException e) {
            pianoImage = null;
        }
        // font
        try {
            titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/NT-SEAWAVE-ALTERNATIVE.otf")).deriveFont(160f);
            subFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/Montserrat-Regular.ttf")).deriveFont(16f);
            guideFont = subFont.deriveFont(28f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(titleFont);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(subFont);
        } catch (Exception e) {
            titleFont = new Font("Serif", Font.BOLD, 160);
            subFont = new Font("SansSerif", Font.PLAIN, 16);
            guideFont = new Font("SansSerif", Font.PLAIN, 28);
        }
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();

        int marginX = (int)(w * 0.06);
        int marginY = (int)(h * 0.033);

        int rectW = w - 2 * marginX;
        int rectH = h - 2 * marginY;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(new BasicStroke(2f));
        g2.setColor(Color.BLACK);
        g2.drawRect(marginX, marginY, rectW, rectH);

        // piano
        if (pianoImage != null) {
            int imgW = 363;
            int imgH = 388;
            int imgX = marginX + rectW / 2 - imgW / 2;
            int imgY = marginY + rectH / 2 - imgH / 3;
            g2.drawImage(pianoImage, imgX, imgY, imgW, imgH, null);
        }

        // title-text
        g2.setFont(titleFont);
        String mainTitle = "THE CLASSIC";
        FontMetrics fmTitle = g2.getFontMetrics();
        Rectangle2D titleBounds = fmTitle.getStringBounds(mainTitle, g2);
        int titleX = marginX + (rectW - (int)titleBounds.getWidth()) / 2;
        int titleY = marginY + (int)(rectH * 0.30);

        g2.setColor(Color.BLACK);
        g2.drawString(mainTitle, titleX, titleY);

        // sub-text
        g2.setFont(subFont);
        String subTitle = "with Java Class";
        FontMetrics fmSub = g2.getFontMetrics();
        int subX = marginX + (rectW - fmSub.stringWidth(subTitle)) / 2;
        int subY = titleY + fmSub.getHeight() + 4;
        g2.drawString(subTitle, subX, subY);

        // guide-text
        g2.setFont(guideFont);
        String guide = "To Start, Press Any Button";
        FontMetrics fmGuide = g2.getFontMetrics();
        int guideX = marginX + (rectW - fmGuide.stringWidth(guide)) / 2;
        int guideY = marginY + (int)(rectH * 0.83);

        g2.setStroke(new BasicStroke(12f));
        g2.setColor(new Color(255,255,255,128));
        g2.drawString(guide, guideX-1, guideY+1);
        g2.setColor(Color.BLACK);
        g2.drawString(guide, guideX, guideY);
    }

    // frame
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        JFrame frame = new JFrame("The Classic - Landing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Landing landingPanel = new Landing();
        frame.setContentPane(landingPanel);
        frame.setMinimumSize(new Dimension(1024, 600));
        frame.setSize(1024, 600);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}