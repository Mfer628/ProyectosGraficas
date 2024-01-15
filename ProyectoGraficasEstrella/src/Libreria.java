import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Libreria extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    public Libreria(){
        setTitle("Estrella");
        setSize(500, 500);
        buffer = new BufferedImage(1,1 , BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics) buffer.createGraphics();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Thread animationThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000); // Espera 1 segundo antes de iniciar la animación
                    dibujandoPixelesSuperIzqu();
                    repaint(); // Vuelve a dibujar la ventana

                    Thread.sleep(1000); // Espera 1 segundo antes de mostrar el siguiente método

                    dibujandoPixelesInferIzq(); // Muestra el segundo método
                    repaint();

                    Thread.sleep(1000);

                    dibujandoPixelesSuperDere(); // Muestra el tercer método
                    repaint();

                    Thread.sleep(1000);

                    dibujandoPixelesInfeDere(); // Muestra el cuarto método
                    repaint();

                    Thread.sleep(1000);

                    dibujandoEstrella(); // Muestra el quinto método
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        animationThread.start(); // Inicia el hilo de animación

    }
    public void putPixel(int x , int y, Color c){
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
    }
    public void dibujandoPixelesSuperIzqu() {
        //               x    y
        //     x         y
        int[][] pixelesx = new int[21][2];
        int[][] pixelesy = new int[21][2];


        //pintando los pixeles en las coordenadas x

        int coorInix = 20;
        int coorfinx = 50;

        for (int i = 0; i < 21; i++) {
            int x = coorInix + i * 10;
            int y = coorfinx;
            pixelesx[i][0] = x;
            pixelesx[i][1] = y;
        }
        for (int i = 0; i < pixelesx.length; i++) {
            int x = pixelesx[i][0];
            int y = pixelesx[i][1];
            putPixel(x, y, Color.blue);
        }
        //

        int coorIniy = 20;
        int coorfiny = 50;
        for (int i = 0; i < 21; i++) {
            int x = coorIniy;
            int y = coorfiny + i * 10;
            pixelesy[i][0] = x;
            pixelesy[i][1] = y;
        }
        for (int i = 0; i < pixelesy.length; i++) {
            int x = pixelesy[i][0];
            int y = pixelesy[i][1];
            putPixel(x, y, Color.blue);
        }

        for (int i = 0; i < pixelesx.length; i++) {
            int x1 = pixelesx[i][0];
            int y1 = pixelesx[i][1];
            int x2 = pixelesy[20 - i][0];
            int y2 = pixelesy[20 - i][1];

            dibujarLineaBresenham(x1, y1, x2, y2);
        }
    }


    public void dibujandoPixelesSuperDere(){
        //               x    y
        //     x         y
        int[][] pixelesx = new int[21][2];
        int[][] pixelesy = new int[21][2];


        //pintando los pixeles en las coordenadas x

        int coorInix = 290;
        int coorfinx = 50;

        for (int i = 0; i < 20;i++){
            int x = coorInix + i * 10;
            int y = coorfinx;
            pixelesx[i][0]=x;
            pixelesx[i][1]=y;
        }
        for (int i = 0; i<pixelesx.length; i++){
            int x = pixelesx[i][0];
            int y = pixelesx[i][1];
            putPixel(x,y,Color.blue);
        }
        //

        int coorIniy= 480;
        int coorfiny = 50;
        for (int i = 0; i < 20;i++){
            int x = coorIniy;
            int y = coorfiny + i*10;
            pixelesy[i][0]=x;
            pixelesy[i][1]=y;
        }
        for (int i = 0; i<pixelesy.length; i++){
            int x = pixelesy[i][0];
            int y = pixelesy[i][1];
            putPixel(x,y,Color.blue);
        }

        dibujarLineaBresenham(pixelesx[19][0], pixelesx[19][1], pixelesy[19][0], pixelesy[19][1]);
        dibujarLineaBresenham(pixelesx[18][0], pixelesx[18][1], pixelesy[19][0], pixelesy[19][1]);
        dibujarLineaBresenham(pixelesx[17][0], pixelesx[17][1], pixelesy[18][0], pixelesy[18][1]);
        dibujarLineaBresenham(pixelesx[16][0], pixelesx[16][1], pixelesy[17][0], pixelesy[17][1]);
        dibujarLineaBresenham(pixelesx[15][0], pixelesx[15][1], pixelesy[16][0], pixelesy[16][1]);
        dibujarLineaBresenham(pixelesx[14][0], pixelesx[14][1], pixelesy[15][0], pixelesy[15][1]);
        dibujarLineaBresenham(pixelesx[13][0], pixelesx[13][1], pixelesy[14][0], pixelesy[14][1]);
        dibujarLineaBresenham(pixelesx[12][0], pixelesx[12][1], pixelesy[13][0], pixelesy[13][1]);
        dibujarLineaBresenham(pixelesx[11][0], pixelesx[11][1], pixelesy[12][0], pixelesy[12][1]);
        dibujarLineaBresenham(pixelesx[10][0], pixelesx[10][1], pixelesy[11][0], pixelesy[11][1]);
        dibujarLineaBresenham(pixelesx[9][0], pixelesx[9][1], pixelesy[10][0], pixelesy[10][1]);
        dibujarLineaBresenham(pixelesx[8][0], pixelesx[8][1], pixelesy[9][0], pixelesy[9][1]);
        dibujarLineaBresenham(pixelesx[7][0], pixelesx[7][1], pixelesy[8][0], pixelesy[8][1]);
        dibujarLineaBresenham(pixelesx[6][0], pixelesx[6][1], pixelesy[7][0], pixelesy[7][1]);
        dibujarLineaBresenham(pixelesx[5][0], pixelesx[5][1], pixelesy[6][0], pixelesy[6][1]);
        dibujarLineaBresenham(pixelesx[4][0], pixelesx[4][1], pixelesy[5][0], pixelesy[5][1]);
        dibujarLineaBresenham(pixelesx[3][0], pixelesx[3][1], pixelesy[4][0], pixelesy[4][1]);
        dibujarLineaBresenham(pixelesx[2][0], pixelesx[2][1], pixelesy[3][0], pixelesy[3][1]);
        dibujarLineaBresenham(pixelesx[1][0], pixelesx[1][1], pixelesy[2][0], pixelesy[2][1]);
        dibujarLineaBresenham(pixelesx[0][0], pixelesx[0][1], pixelesy[1][0], pixelesy[1][1]);
        dibujarLineaBresenham(pixelesx[0][0], pixelesx[0][1], pixelesy[0][0], pixelesy[0][1]);



/*
        for (int i = 0; i < 20; i++) {
            int x1 = pixelesx[19 - i][0];
            int y1 = pixelesx[19 - i][1];
            int x2 = pixelesy[i][0];
            int y2 = pixelesy[i][1];

            dibujarLineaBresenham(x1, y1, x2, y2);
            dibujarLineaBresenham(x1, y1, x2, y2); // Dibuja la línea adicional
        }



 */
    }

    public void dibujandoPixelesInfeDere(){
        //               x    y
        //     x         y
        int[][] pixelesx = new int[21][2];
        int[][] pixelesy = new int[21][2];


        //pintando los pixeles en las coordenadas x

        int coorInix = 290;
        int coorfinx = 450;

        for (int i = 0; i < 20;i++){
            int x = coorInix + i * 10;
            int y = coorfinx;
            pixelesx[i][0]=x;
            pixelesx[i][1]=y;
        }
        for (int i = 0; i<pixelesx.length; i++){
            int x = pixelesx[i][0];
            int y = pixelesx[i][1];
            putPixel(x,y,Color.blue);
        }
        //

        int coorIniy= 480;
        int coorfiny = 450;



        for (int i = 0; i < 20;i++){
            int x = coorIniy;
            int y = coorfiny - i*10;
            pixelesy[i][0]=x;
            pixelesy[i][1]=y;
        }
        for (int i = 0; i<pixelesy.length; i++){
            int x = pixelesy[i][0];
            int y = pixelesy[i][1];
            putPixel(x,y,Color.blue);
        }
        //dibujarLineaBresenham(pixelesx[18][0], pixelesx[18][1], pixelesy[19][0], pixelesy[19][1]);
        dibujarLineaBresenham(pixelesx[19][0], pixelesx[19][1], pixelesy[19][0], pixelesy[19][1]);

        dibujarLineaBresenham(pixelesx[18][0], pixelesx[18][1], pixelesy[19][0], pixelesy[19][1]);
        dibujarLineaBresenham(pixelesx[17][0], pixelesx[17][1], pixelesy[18][0], pixelesy[18][1]);
        dibujarLineaBresenham(pixelesx[16][0], pixelesx[16][1], pixelesy[17][0], pixelesy[17][1]);
        dibujarLineaBresenham(pixelesx[15][0], pixelesx[15][1], pixelesy[16][0], pixelesy[16][1]);
        dibujarLineaBresenham(pixelesx[14][0], pixelesx[14][1], pixelesy[15][0], pixelesy[15][1]);
        dibujarLineaBresenham(pixelesx[13][0], pixelesx[13][1], pixelesy[14][0], pixelesy[14][1]);
        dibujarLineaBresenham(pixelesx[12][0], pixelesx[12][1], pixelesy[13][0], pixelesy[13][1]);
        dibujarLineaBresenham(pixelesx[11][0], pixelesx[11][1], pixelesy[12][0], pixelesy[12][1]);
        dibujarLineaBresenham(pixelesx[10][0], pixelesx[10][1], pixelesy[11][0], pixelesy[11][1]);
        dibujarLineaBresenham(pixelesx[9][0], pixelesx[9][1], pixelesy[10][0], pixelesy[10][1]);
        dibujarLineaBresenham(pixelesx[8][0], pixelesx[8][1], pixelesy[9][0], pixelesy[9][1]);
        dibujarLineaBresenham(pixelesx[7][0], pixelesx[7][1], pixelesy[8][0], pixelesy[8][1]);
        dibujarLineaBresenham(pixelesx[6][0], pixelesx[6][1], pixelesy[7][0], pixelesy[7][1]);
        dibujarLineaBresenham(pixelesx[5][0], pixelesx[5][1], pixelesy[6][0], pixelesy[6][1]);
        dibujarLineaBresenham(pixelesx[4][0], pixelesx[4][1], pixelesy[5][0], pixelesy[5][1]);
        dibujarLineaBresenham(pixelesx[3][0], pixelesx[3][1], pixelesy[4][0], pixelesy[4][1]);
        dibujarLineaBresenham(pixelesx[2][0], pixelesx[2][1], pixelesy[3][0], pixelesy[3][1]);
        dibujarLineaBresenham(pixelesx[1][0], pixelesx[1][1], pixelesy[2][0], pixelesy[2][1]);
        dibujarLineaBresenham(pixelesx[0][0], pixelesx[0][1], pixelesy[1][0], pixelesy[1][1]);


    }

    public void dibujandoPixelesInferIzq(){
        //               x    y
        //     x         y
        int[][] pixelesx = new int[21][2];
        int[][] pixelesy = new int[21][2];


        //pintando los pixeles en las coordenadas x

        int coorInix = 20;
        int coorfinx = 450;

        for (int i = 0; i < 21;i++){
            int x = coorInix + i * 10;
            int y = coorfinx;
            pixelesx[i][0]=x;
            pixelesx[i][1]=y;
        }
        for (int i = 0; i<pixelesx.length; i++){
            int x = pixelesx[i][0];
            int y = pixelesx[i][1];
            putPixel(x,y,Color.blue);
        }
        //

        int coorIniy= 20;
        int coorfiny = 450;
        for (int i = 0; i < 21;i++){
            int x = coorIniy;
            int y = coorfiny - i*10;
            pixelesy[i][0]=x;
            pixelesy[i][1]=y;
        }
        for (int i = 0; i<pixelesy.length; i++){
            int x = pixelesy[i][0];
            int y = pixelesy[i][1];
            putPixel(x,y,Color.blue);

        }

        for (int i = 0; i < pixelesx.length; i++) {
            int x1 = pixelesx[i][0];
            int y1 = pixelesx[i][1];
            int x2 = pixelesy[20 - i][0];
            int y2 = pixelesy[20 - i][1];

            dibujarLineaBresenham(x1, y1, x2, y2);
        }
    }


    public void dibujarLineaBresenham(int x0, int y0, int x1, int y1) {

        putPixel(x0, y0, Color.black); //Dibujar el primer pixel en el punto de incio

        int dx = Math.abs(x1 - x0); //Diferencia absoluta en la coordenada x
        int dy = Math.abs(y1 - y0); //Diferencia abosoluta en la coordenada y

        int sx = (x0 < x1) ? 1 : -1; //Direccion en el eje x (1 si x1 > x0, de lo contrario, -1)
        int sy = (y0 < y1) ? 1 : -1; //Direccion en el eje y (1 si y1 > y0, de lo contrario, -1)

        int err = dx - dy; //Inicializar el error
        int err2; //variable para el doble error

        //Bucle principal para trazar la linea
        while (x0 != x1 || y0 != y1) {
            putPixel(x0, y0, Color.black);//Dibujar el pixel actual
            err2 = 2 * err; //calculando el doble error
            //Actulizandp el error y la coordenada x si el error es mayor que -dy
            if (err2 > -dy) {
                err -= dy;
                x0 += sx; //moviendo la direccion de x
            }

            //Actulizando el error y la coordenada y si el error es menor que dx
            if (err2 < dx) {
                err += dx;
                y0 += sy; //Mover en la direccion de y
            }
        }
    }


    public void dibujandoEstrella(){
        int[][] pixelesx = new int[20][2];
        int[][] pixelesy = new int[20][2];


        int coorInix = 20;
        int coorfinx = 250;

        for (int i = 0; i < 20;i++){
            int x = coorInix + i * 24;
            int y = coorfinx;
            pixelesx[i][0]=x;
            pixelesx[i][1]=y;
        }
        for (int i = 0; i<pixelesx.length; i++){
            int x = pixelesx[i][0];
            int y = pixelesx[i][1];
            putPixel(x,y,Color.blue);
        }
        //

        int coorIniy= 250;
        int coorfiny = 50;
        for (int i = 0; i < 20;i++){
            int x = coorIniy;
            int y = coorfiny + i*21;
            pixelesy[i][0]=x;
            pixelesy[i][1]=y;
        }
        for (int i = 0; i<pixelesy.length; i++){
            int x = pixelesy[i][0];
            int y = pixelesy[i][1];
            putPixel(x,y,Color.blue);
        }


        //SuperiorDerecha

        dibujarLineaBresenham(pixelesx[10][0], pixelesx[10][1], pixelesy[0][0], pixelesy[0][1]);
        dibujarLineaBresenham(pixelesx[11][0], pixelesx[11][1], pixelesy[1][0], pixelesy[1][1]);
        dibujarLineaBresenham(pixelesx[12][0], pixelesx[12][1], pixelesy[2][0], pixelesy[2][1]);
        dibujarLineaBresenham(pixelesx[13][0], pixelesx[13][1], pixelesy[3][0], pixelesy[3][1]);
        dibujarLineaBresenham(pixelesx[14][0], pixelesx[14][1], pixelesy[4][0], pixelesy[4][1]);
        dibujarLineaBresenham(pixelesx[15][0], pixelesx[15][1], pixelesy[5][0], pixelesy[5][1]);
        dibujarLineaBresenham(pixelesx[16][0], pixelesx[16][1], pixelesy[6][0], pixelesy[6][1]);
        dibujarLineaBresenham(pixelesx[17][0], pixelesx[17][1], pixelesy[7][0], pixelesy[7][1]);
        dibujarLineaBresenham(pixelesx[18][0], pixelesx[18][1], pixelesy[8][0], pixelesy[8][1]);
        dibujarLineaBresenham(pixelesx[19][0], pixelesx[19][1], pixelesy[9][0], pixelesy[9][1]);


        //InferiorDerecha


        dibujarLineaBresenham(pixelesx[10][0], pixelesx[10][1], pixelesy[19][0], pixelesy[19][1]);
        dibujarLineaBresenham(pixelesx[11][0], pixelesx[11][1], pixelesy[18][0], pixelesy[18][1]);
        dibujarLineaBresenham(pixelesx[12][0], pixelesx[12][1], pixelesy[17][0], pixelesy[17][1]);
        dibujarLineaBresenham(pixelesx[13][0], pixelesx[13][1], pixelesy[16][0], pixelesy[16][1]);
        dibujarLineaBresenham(pixelesx[14][0], pixelesx[14][1], pixelesy[15][0], pixelesy[15][1]);
        dibujarLineaBresenham(pixelesx[15][0], pixelesx[15][1], pixelesy[14][0], pixelesy[14][1]);
        dibujarLineaBresenham(pixelesx[16][0], pixelesx[16][1], pixelesy[13][0], pixelesy[13][1]);
        dibujarLineaBresenham(pixelesx[17][0], pixelesx[17][1], pixelesy[12][0], pixelesy[12][1]);
        dibujarLineaBresenham(pixelesx[18][0], pixelesx[18][1], pixelesy[11][0], pixelesy[11][1]);
        dibujarLineaBresenham(pixelesx[19][0], pixelesx[19][1], pixelesy[10][0], pixelesy[10][1]);


        //Superior Izquierda


        dibujarLineaBresenham(pixelesx[9][0], pixelesx[9][1], pixelesy[0][0], pixelesy[0][1]);
        dibujarLineaBresenham(pixelesx[8][0], pixelesx[8][1], pixelesy[1][0], pixelesy[1][1]);
        dibujarLineaBresenham(pixelesx[7][0], pixelesx[7][1], pixelesy[2][0], pixelesy[2][1]);
        dibujarLineaBresenham(pixelesx[6][0], pixelesx[6][1], pixelesy[3][0], pixelesy[3][1]);
        dibujarLineaBresenham(pixelesx[5][0], pixelesx[5][1], pixelesy[4][0], pixelesy[4][1]);
        dibujarLineaBresenham(pixelesx[4][0], pixelesx[4][1], pixelesy[5][0], pixelesy[5][1]);
        dibujarLineaBresenham(pixelesx[3][0], pixelesx[3][1], pixelesy[6][0], pixelesy[6][1]);
        dibujarLineaBresenham(pixelesx[2][0], pixelesx[2][1], pixelesy[7][0], pixelesy[7][1]);
        dibujarLineaBresenham(pixelesx[1][0], pixelesx[1][1], pixelesy[8][0], pixelesy[8][1]);
        dibujarLineaBresenham(pixelesx[0][0], pixelesx[0][1], pixelesy[9][0], pixelesy[9][1]);

        //Inferior Izquierda

        dibujarLineaBresenham(pixelesx[9][0], pixelesx[9][1], pixelesy[19][0], pixelesy[19][1]);
        dibujarLineaBresenham(pixelesx[8][0], pixelesx[8][1], pixelesy[18][0], pixelesy[18][1]);
        dibujarLineaBresenham(pixelesx[7][0], pixelesx[7][1], pixelesy[17][0], pixelesy[17][1]);
        dibujarLineaBresenham(pixelesx[6][0], pixelesx[6][1], pixelesy[16][0], pixelesy[16][1]);
        dibujarLineaBresenham(pixelesx[5][0], pixelesx[5][1], pixelesy[15][0], pixelesy[15][1]);
        dibujarLineaBresenham(pixelesx[4][0], pixelesx[4][1], pixelesy[14][0], pixelesy[14][1]);
        dibujarLineaBresenham(pixelesx[3][0], pixelesx[3][1], pixelesy[13][0], pixelesy[13][1]);
        dibujarLineaBresenham(pixelesx[2][0], pixelesx[2][1], pixelesy[12][0], pixelesy[12][1]);
        dibujarLineaBresenham(pixelesx[1][0], pixelesx[1][1], pixelesy[11][0], pixelesy[11][1]);
        dibujarLineaBresenham(pixelesx[0][0], pixelesx[0][1], pixelesy[10][0], pixelesy[10][1]);

    }
}
