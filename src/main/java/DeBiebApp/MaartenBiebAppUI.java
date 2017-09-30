package DeBiebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.SwingConstants.CENTER;

@EnableSwagger2
@SpringBootApplication
public class MaartenBiebAppUI {
    public void run(){
        JFrame frame = new JFrame("Maarten's Biebapp");
        frame.setPreferredSize(new Dimension(450,150));
        frame.setIconImage(new ImageIcon(getClass().getResource("/static/img/favicon.png")).getImage());

        createComponents(frame.getContentPane());
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        SpringApplication.run(MaartenBiebAppUI.class);
    }
    @EventListener(ApplicationReadyEvent.class)
    private void openPage(){
        if(Desktop.isDesktopSupported()){
            try {
                Desktop.getDesktop().browse(new URI("http://localhost:8080"));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void createComponents(Container container){
        JPanel panel1 = new JPanel(new GridLayout(2,1));
        panel1.setBackground(new Color(22,111,255));
        container.add(panel1,BorderLayout.CENTER);

        JLabel lbl0 = new JLabel("De app wordt nu opgestart, gelieve een ogenblik geduld",CENTER);
        lbl0.setForeground(new Color(255,255,255));
        lbl0.setFont(new Font("Sans-Serif",Font.BOLD,14));
        panel1.add(lbl0);

        JLabel lbl1 = new JLabel("Sluit dit scherm om de bibliotheekapplicatie te sluiten", CENTER);
        lbl1.setForeground(new Color(255,255,255));
        lbl1.setFont(new Font("Sans-Serif",Font.BOLD,12));
        panel1.add(lbl1);
    }
}
