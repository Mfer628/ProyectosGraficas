import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import java.util.Random;

import static java.lang.Math.round;

public class Libreria extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    private int yCopoNieve = 90;
    private Random random= new Random();
    public Libreria(){

        setTitle("Navidad");

        getContentPane().setBackground(Color.black);
        reproducirMusica();
        setSize(500, 500);
        buffer = new BufferedImage(1,1 , BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics) buffer.createGraphics();
        yCopoNieve = 90;
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setVisible(true);


        Thread hilo1 = new Thread(new PintandoCoposThread());
        hilo1.start();

    }
    public void putPixel(int x , int y, Color c){
        buffer.setRGB(0,0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }
    public void pintandoArbol(){

        int x0 = 150, y0 = 50;
        int x1 = 50, y1 = 350;
        int x2 = 250, y2 = 350;

        // Dibujar las tres líneas del triángulo utilizando el algoritmo de Bresenham
        dibujarLineaBresenham(x0, y0, x1, y1);
        dibujarLineaBresenham(x1, y1, x2, y2);
        dibujarLineaBresenham(x2, y2, x0, y0);

        int yMin = Math.min(y0, Math.min(y1, y2));
        int yMax = Math.max(y0, Math.max(y1, y2));

        // Rellena el triángulo utilizando la técnica de escaneo de líneas
        for (int y = yMin + 1; y < yMax; y++) {
            int xl = InterseccionX(x0, y0, x1, y1, y);
            int xr = InterseccionX(x0, y0, x2, y2, y);

            // Dibuja la línea horizontal desde xl hasta xr
            dibujarLineaBresenham(xl, y, xr, y);
        }
    }
    public void pintandoCopoNieve() {
        dibujandoCopoNieve(yCopoNieve);
    }


    public int InterseccionX(int x0, int y0, int x1, int y1, int y) {
        if (y0 == y1) {
            return x0;
        }
        return x0 + (int) (1.0 * (x1 - x0) * (y - y0) / (y1 - y0));
    }

    public void pintandoEsferas (){
        dibujarCirculoConGrosor(142, 70, 5, 2,Color.red);
        dibujarCirculoConGrosor(128, 115, 5, 2,Color.red);
        dibujarCirculoConGrosor(114, 155, 5, 2,Color.red);
        dibujarCirculoConGrosor(100, 200, 5, 2,Color.red);
        dibujarCirculoConGrosor(85, 245, 5, 2,Color.red);
        dibujarCirculoConGrosor(70, 290, 5, 2,Color.red);
        dibujarCirculoConGrosor(55, 335, 5, 2,Color.red);
        //Lado derecho del arbol
        dibujarCirculoConGrosor(160, 80, 5, 2,Color.red);
        dibujarCirculoConGrosor(172, 115, 5, 2,Color.red);
        dibujarCirculoConGrosor(186, 155, 5, 2,Color.red);
        dibujarCirculoConGrosor(202, 200, 5, 2,Color.red);
        dibujarCirculoConGrosor(216, 245, 5, 2,Color.red);
        dibujarCirculoConGrosor(232, 290, 5, 2,Color.red);
        dibujarCirculoConGrosor(248, 335, 5, 2,Color.red);
        drawCuadrado(100, 352, 200, 452);
    }
    public void drawCuadrado(int x0 , int y0, int x1, int y1){
        Color fillColor = new Color(139, 69, 19);
        dibujarLineaBresenhamC(x0, y0, x1, y0);
        dibujarLineaBresenhamC(x0, y0, x0, y1);
        dibujarLineaBresenhamC(x0, y1, x1, y1);
        dibujarLineaBresenhamC(x1,y0, x1, y1);
        rellenarRectanguloScanLine(x0, y0, x1, y1, fillColor);
    }

    public void rellenarRectanguloScanLine(int x0, int y0, int x1, int y1, Color color) {
        int xMin = Math.min(x0, x1);
        int xMax = Math.max(x0, x1);
        int yMin = Math.min(y0, y1);
        int yMax = Math.max(y0, y1);

        for (int y = yMin + 1; y < yMax; y++) {
            for (int x = xMin + 1; x < xMax; x++) {
                putPixel(x, y, color); // Dibuja el píxel en el interior del rectángulo
            }
        }
    }
    public void dibujarLineaBresenham(int x0, int y0, int x1, int y1) {

        Color colorVerdeOscuro = new Color(0, 100, 0);
        putPixel(x0, y0, colorVerdeOscuro); //Dibujar el primer pixel en el punto de incio

        int dx = Math.abs(x1 - x0); //Diferencia absoluta en la coordenada x
        int dy = Math.abs(y1 - y0); //Diferencia abosoluta en la coordenada y

        int sx = (x0 < x1) ? 1 : -1; //Direccion en el eje x (1 si x1 > x0, de lo contrario, -1)
        int sy = (y0 < y1) ? 1 : -1; //Direccion en el eje y (1 si y1 > y0, de lo contrario, -1)

        int err = dx - dy; //Inicializar el error
        int err2; //variable para el doble error

        //Bucle principal para trazar la linea
        while (x0 != x1 || y0 != y1) {
            putPixel(x0, y0, colorVerdeOscuro);//Dibujar el pixel actual
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
    private void reproducirMusica() {
        try {
            // Cargar el archivo de música (debes proporcionar la ruta correcta al archivo)
            InputStream inputStream = getClass().getResourceAsStream("/Musica/musica.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            // Configurar el Clip para reproducir la música
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            // Reproducir la música en bucle
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }


    public void dibujarCirculoConGrosor(int centerX, int centerY, int radius, int grosor, Color color) {
        for (int i = 0; i < grosor; i++) {
            int radioInterior = radius + i;
            int radioExterior = radius + i + 1;

            // Dibuja las curvas interna y externa
            AlgoritmoBresenhamCircle(centerX, centerY, radioInterior, color);
            AlgoritmoBresenhamCircle(centerX, centerY, radioExterior, color);

            // Rellena el espacio entre las curvas (líneas horizontales o verticales)
            for (int y = centerY - radioExterior; y <= centerY + radioExterior; y++) {
                for (int x = centerX - radioExterior; x <= centerX + radioExterior; x++) {
                    double distancia = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
                    if (distancia >= radioInterior && distancia <= radioExterior) {
                        putPixel(x, y, color);
                    }
                }
            }

            // Utiliza una brocha virtual en la curva
            for (double angulo = 0; angulo <= 360; angulo += 1) {
                double radianes = Math.toRadians(angulo);
                int xBrocha = (int) (centerX + radioInterior * Math.cos(radianes));
                int yBrocha = (int) (centerY + radioInterior * Math.sin(radianes));
                // Dibuja la brocha virtual en (xBrocha, yBrocha)
                putPixel(xBrocha, yBrocha, color);
            }
        }
    }


    public void AlgoritmoBresenhamCircle(int centerX, int centerY, int radius, Color color) {
        int x = radius;
        int y = 0;
        int p = 1 - radius;

        while (x >= y) {
            putPixel( centerX + x, centerY + y, color); // Octante 1
            putPixel( centerX - x, centerY + y, color); // Octante 2
            putPixel( centerX + x, centerY - y, color); // Octante 3
            putPixel( centerX - x, centerY - y, color); // Octante 4
            putPixel( centerX - y, centerY + x, color); // Octante 5
            putPixel( centerX + y, centerY + x, color); // Octante 6
            putPixel( centerX - y, centerY - x, color); // Octante 7
            putPixel( centerX + y, centerY - x, color); // Octante 8

            y++;

            if (p <= 0) {
                p += 2 * y + 1;
            } else {
                x--;
                p += 2 * (y - x) + 1;
            }
        }
    }

    public void dibujarLineaBresenhamC(int x0, int y0, int x1, int y1) {


        Color colorCafe = new Color(139, 69, 19);
        putPixel(x0, y0, colorCafe); //Dibujar el primer pixel en el punto de incio

        int dx = Math.abs(x1 - x0); //Diferencia absoluta en la coordenada x
        int dy = Math.abs(y1 - y0); //Diferencia abosoluta en la coordenada y

        int sx = (x0 < x1) ? 1 : -1; //Direccion en el eje x (1 si x1 > x0, de lo contrario, -1)
        int sy = (y0 < y1) ? 1 : -1; //Direccion en el eje y (1 si y1 > y0, de lo contrario, -1)

        int err = dx - dy; //Inicializar el error
        int err2; //variable para el doble error

        //Bucle principal para trazar la linea
        while (x0 != x1 || y0 != y1) {
            putPixel(x0, y0, colorCafe);//Dibujar el pixel actual
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

    public void dibujarLineaBresenhamC(int x0, int y0, int x1, int y1, Color color) {


        Color colorCafe = new Color(139, 69, 19);
        putPixel(x0, y0, colorCafe); //Dibujar el primer pixel en el punto de incio

        int dx = Math.abs(x1 - x0); //Diferencia absoluta en la coordenada x
        int dy = Math.abs(y1 - y0); //Diferencia abosoluta en la coordenada y

        int sx = (x0 < x1) ? 1 : -1; //Direccion en el eje x (1 si x1 > x0, de lo contrario, -1)
        int sy = (y0 < y1) ? 1 : -1; //Direccion en el eje y (1 si y1 > y0, de lo contrario, -1)

        int err = dx - dy; //Inicializar el error
        int err2; //variable para el doble error

        //Bucle principal para trazar la linea
        while (x0 != x1 || y0 != y1) {
            putPixel(x0, y0, colorCafe);//Dibujar el pixel actual
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
//Animacion de pixeles

    public void dibujarLineaBresenhamCoposNieve(int x0, int y0, int x1, int y1, Color color) {
        // Aquí puedes usar tu función para dibujar una línea con el algoritmo de Bresenham
        // Asegúrate de usar el color proporcionado

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        int err = dx - dy;
        int err2;

        while (x0 != x1 || y0 != y1) {
            putPixel(x0, y0, color);
            err2 = 2 * err;

            if (err2 > -dy) {
                err -= dy;
                x0 += sx;
            }

            if (err2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

    public void dibujandoEStrella() {


        dibujarLineaBresenhamCoposNieve(140, 50, 160, 50, new Color(255, 215, 0));
        dibujarLineaBresenhamCoposNieve(150, 35, 150, 75, new Color(255, 215, 0));
        dibujarLineaBresenhamCoposNieve(143, 38, 157, 63, new Color(255, 215, 0));
        dibujarLineaBresenhamCoposNieve(157, 38, 143, 63, new Color(255, 215, 0));




    }




    public void dibujandoCopoNieve(int y) {
        dibujarLineaBresenhamCoposNieve(10, y, 60, y, Color.white);
        dibujarLineaBresenhamCoposNieve(35, y - 25, 35, y + 25, Color.white);
        dibujarLineaBresenhamCoposNieve(23, y - 12, 48, y + 13, Color.white);
        dibujarLineaBresenhamCoposNieve(48, y - 12, 23, y + 13, Color.white);
    }



    public void pintandoCoposDerechos(){
        int[][] pixelesx = new int[20][2];
        int coorInix = 480;
        int coorfinx = 50;
        for (int i = 0; i < 20;i++){
            int x = coorInix;
            int y = coorfinx =+ i * 24;
            pixelesx[i][0]=x;
            pixelesx[i][1]=y;
        }
        for (int i = 0; i<pixelesx.length; i++){
            int x = pixelesx[i][0];
            int y = pixelesx[i][1];
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            putPixel(x,y,color);
        }
    }

    public void pintandoCoposSuper(){
        int[][] pixelesx = new int[20][2];
        int coorInix = 20;
        int coorfinx = 40;
        for (int i = 0; i < 20;i++){
            int x = coorInix =+ i * 24;
            int y = coorfinx ;
            pixelesx[i][0]=x;
            pixelesx[i][1]=y;
        }
        for (int i = 0; i<pixelesx.length; i++){
            int x = pixelesx[i][0];
            int y = pixelesx[i][1];
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            putPixel(x,y,color);
        }
    }

    public void pintandoCoposIzq(){
        int[][] pixelesx = new int[20][2];
        int coorInix = 20;
        int coorfinx = 40;
        for (int i = 0; i < 20;i++){
            int x = coorInix;
            int y = coorfinx = + i * 24;
            pixelesx[i][0]=x;
            pixelesx[i][1]=y;
        }
        for (int i = 0; i<pixelesx.length; i++){
            int x = pixelesx[i][0];
            int y = pixelesx[i][1];
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

            putPixel(x,y,color);
        }
    }

    public void pintandoCoposInfer(){
        int[][] pixelesx = new int[20][2];
        int[][] pixeles2 = new int[20][2];

        int coorInix = 20;
        int coorfinx = 480;
        for (int i = 0; i < 20;i++){
            int x = coorInix = + i * 24;
            int y = coorfinx;
            pixelesx[i][0]=x;
            pixelesx[i][1]=y;
        }
        for (int i = 0; i<pixelesx.length; i++){
            int x = pixelesx[i][0];
            int y = pixelesx[i][1];
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            putPixel(x,y,color);
        }

    }
    private class PintandoCoposThread extends Thread {
        @Override
        public void run() {
            while (true) {
                pintandoCoposDerechos();
                pintandoCoposSuper();
                pintandoCoposIzq();
                pintandoCoposInfer();// Llama al método pintandoCopos en el hilo
                try {
                    Thread.sleep(999); // Pausa de 100 milisegundos entre animaciones
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void pintandoSantaClaus() {
        // Dibuja el cuerpo de Santa
        dibujarCirculoConGrosor(340, 360, 60, 10, Color.white);
        dibujarCirculoConGrosor(340, 360, 50, 10, Color.white);
        dibujarCirculoConGrosor(340, 360, 40, 10, Color.white);
        dibujarCirculoConGrosor(340, 360, 30, 10, Color.white);
        dibujarCirculoConGrosor(340, 360, 20, 10, Color.white);
        dibujarCirculoConGrosor(340, 360, 10, 10, Color.white);
        dibujarCirculoConGrosor(340, 360, 5, 10, Color.white);

        // Dibuja la cabeza de Santa
        dibujarCirculoConGrosor(340, 250, 30, 10, Color.white);

        // Dibuja los ojos de Santa
        dibujarCirculoConGrosor(330, 240, 5, 1, Color.red);
        dibujarCirculoConGrosor(350, 240, 5, 1, Color.red);

        // Dibuja la pupila de los ojos
        dibujarCirculoConGrosor(330, 240, 2, 1, Color.white);
        dibujarCirculoConGrosor(350, 240, 2, 1, Color.white);

        // Dibuja la nariz de Santa
        dibujarCirculoConGrosor(340, 255, 5, 1, Color.ORANGE);

        // Dibuja la boca de Santa
        //dibujarSonrisa(250, 185, 20, 10, Color.white);

        //Dibujando brazos

        //dibujarLineaBresenhamC(250, 250, 300, 300 );
        lineaGrosor(250, 300, 250, 300, 6);
        //lineaGrosor(250, 300, 250, 300, 6);
        putPixel(380, 300, Color.blue);
        //dibujarLineaBresenhamC(420, 250 , 380, 300 );
        lineaGrosor(420, 380, 250,300, 6);
    }



    public void lineaGrosor(int x0, int x1, int y0, int y1, int grosor) {
        // Calcular la pendiente
        float dx = x1 - x0;
        float dy = y1 - y0;
        float pendiente = Math.abs(dy / dx);

        if (pendiente < 1) {
            // La pendiente es menor que 1, línea horizontal o casi horizontal
            for (int i = -(grosor / 2); i <= grosor / 2; i++) {
                lineaDDA(x0, x1, y0 + i, y1 + i);
            }
        } else {
            // La pendiente es mayor que 1, línea vertical o casi vertical
            for (int i = -(grosor / 2); i <= grosor / 2; i++) {
                if (dy > 0) {
                    lineaDDA(x0 + i, x1 + i, y0, y1);
                } else {
                    lineaDDA(x0 - i, x1 - i, y0, y1);
                }
            }
        }
    }


    public void lineaDDA(int x0,int x1, int y0, int y1){
        float dx = x1 - x0; //Diferencia en la coordenada x
        float dy = y1 - y0; //Diferencia en la coordenada y
        float steps; //Numero de pasos necesarios para mejorar la linea
        float x, y; //coordenadas actuales de dibujo
        float xinc, yinc; //Incrementos para x e y
        //Determinar el numero de pasos segun la direccion dominante (x o y)
        if (dx > dy){
            steps = dx;
        }else{
            steps = dy;
        }
        //Calcular los incrementos para x e y
        xinc = dx /steps;
        yinc = dy /steps;

        x = x0; //Inicializar x con la coordenada x de inicio
        y = y0; //Inicializar y con la coordenada y de inicio
        //Dibujando el primer pixel en la cooerdenada de inicio (redondeado)
        putPixel(round(x), round(y), Color.red);
        //Realizando el trazado de la line utilizando los incrementos
        for (float k = 1; k <=steps; k++){
            x = x + xinc;
            y = y + yinc;
            putPixel(round(x), round(y), Color.red); //Dibujar el pixel actual(redondeado)
        }
    }
}



