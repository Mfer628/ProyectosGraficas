import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.TimerTask;

public class Navidad extends JFrame {
    private boolean mostrarCopos = true;
    private BufferedImage buffer;
    private Graphics graPixel;

    private int yCopoNieve;
    public Navidad(){
        setTitle("Navidad");
        //getContentPane().setBackground(Color.black);
        //reproducirMusica();
        setSize(500, 500);
        buffer = new BufferedImage(1,1 , BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics) buffer.createGraphics();
        yCopoNieve = 90;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void putPixel(int x , int y, Color c){
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }


    @Override
    public void paint(Graphics g) {
        //super.paint(g);
    }

    public void pintandoCopos() {

            int[][] pixelesx = new int[20][2];
            int coorInix = 20;
            int coorfinx = 50;
            for (int i = 0; i < 20; i++) {
                int x = coorInix + i * 24;
                int y = coorfinx;
                pixelesx[i][0] = x;
                pixelesx[i][1] = y;
            }
            for (int i = 0; i < pixelesx.length; i++) {
                int x = pixelesx[i][0];
                int y = pixelesx[i][1];
                putPixel(x, y, Color.blue);
            }


    }


}
