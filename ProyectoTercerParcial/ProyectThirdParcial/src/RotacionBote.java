import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Math.round;

public class RotacionBote extends JPanel {

    private BufferedImage buffer;
    private Graphics graPixel;
//Matrices de Vertices
    private double vertex1[][][][] = {
            {{{-1, -1, -1}, {1, -1, -1}}, {{-1, 1, -1}, {1, 1, -1}}},
            {{{-1, -1, 1}, {1, -1, 1}}, {{-1, 1, 1}, {1, 1, 1}}}
    };

    private double vertex2[][][][] = {
            {{{0, 0, -1}, {2, 0, -1}}, {{0, 2, -1}, {2, 2, -1}}},
            {{{0, 0, 1}, {2, 0, 1}}, {{0, 2, 1}, {2, 2, 1}}}
    };

    private double vertex3[][][][] = {
            {{{-5, -5, -5}, {5, -5, -5}}, {{-5, 5, -5}, {5, 5, -5}}},
            {{{-5, -5, 5}, {5, -5, 5}}, {{-5, 5, 5}, {5, 5, 5}}}
    };




    private double xyR = 0.005, xzR = 0.005, yzR = 0.005;
    private double xyRDirection = 0.001, xzRDirection = 0.001, yzRDirection = 0.001;

    private final static int IMAGE_SIZE = 500, SCALE = 30, OFFSET = 200, DIAMETER = 10;



    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, IMAGE_SIZE, IMAGE_SIZE);

        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        graPixel = buffer.createGraphics();
        Color c1 = Color.RED;
        Color c2 = Color.BLUE;
        Color c3 = Color.CYAN;

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                for (int z = 0; z < 2; z++) {
                    xyRotate(vertex1[x][y][z], Math.sin(xyR), Math.cos(xyR));
                    xzRotate(vertex1[x][y][z], Math.sin(xzR), Math.cos(xzR));
                    yzRotate(vertex1[x][y][z], Math.sin(yzR), Math.cos(yzR));

                    xyRotate(vertex2[x][y][z], Math.sin(xyR), Math.cos(xyR));
                    xzRotate(vertex2[x][y][z], Math.sin(xzR), Math.cos(xzR));
                    yzRotate(vertex2[x][y][z], Math.sin(yzR), Math.cos(yzR));

                    xyRotate(vertex3[x][y][z], Math.sin(xyR), Math.cos(xyR));
                    xzRotate(vertex3[x][y][z], Math.sin(xzR), Math.cos(xzR));
                    yzRotate(vertex3[x][y][z], Math.sin(yzR), Math.cos(yzR));
                }
            }
        }

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                drawEdge(vertex1[x][y][0][0], vertex1[x][y][0][1], vertex1[x][y][1][0], vertex1[x][y][1][1], buffer, c1);
                drawEdge(vertex1[x][0][y][0], vertex1[x][0][y][1], vertex1[x][1][y][0], vertex1[x][1][y][1], buffer, c1);
                drawEdge(vertex1[0][x][y][0], vertex1[0][x][y][1], vertex1[1][x][y][0], vertex1[1][x][y][1], buffer, c1);

                drawEdge(vertex2[x][y][0][0], vertex2[x][y][0][1], vertex2[x][y][1][0], vertex2[x][y][1][1], buffer, c2);
                drawEdge(vertex2[x][0][y][0], vertex2[x][0][y][1], vertex2[x][1][y][0], vertex2[x][1][y][1], buffer, c2);
                drawEdge(vertex2[0][x][y][0], vertex2[0][x][y][1], vertex2[1][x][y][0], vertex2[1][x][y][1], buffer, c2);


                drawEdge(vertex3[x][y][0][0], vertex3[x][y][0][1], vertex3[x][y][1][0], vertex3[x][y][1][1], buffer, c3);
                drawEdge(vertex3[x][0][y][0], vertex3[x][0][y][1], vertex3[x][1][y][0], vertex3[x][1][y][1], buffer, c3);
                drawEdge(vertex3[0][x][y][0], vertex3[0][x][y][1], vertex3[1][x][y][0], vertex3[1][x][y][1], buffer, c3);
            }
        }

        graphics.drawImage(buffer, 0, 0, this);

        // Actualizar las rotaciones para el siguiente cuadro
        xyR += xyRDirection;
        xzR += xzRDirection;
        yzR += yzRDirection;

        // Controlar el rebote para el primer cubo
        if (vertex1[0][0][0][0] < -1 || vertex1[1][1][1][1] > 1) {
            xyRDirection *= -1;
            xzRDirection *= -1;
            yzRDirection *= -1;
        }

        // Controlar el rebote para el segundo cubo
        if (vertex2[0][0][0][0] < 0 || vertex2[1][1][1][1] > 2) {
            xyRDirection *= -1;
            xzRDirection *= -1;
            yzRDirection *= -1;
        }

       // estrella.dibujandoEstrella();
        // Mover los cubos hacia la orilla
        moveCubes();
    }
    //Funcion que mueve los cubos cuando llega a los limites cambia el giro en diferente direccion

    private void moveCubes() {
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                for (int z = 0; z < 2; z++) {
                    vertex1[x][y][z][0] += 0.01;
                    vertex2[x][y][z][0] += 0.01;

                    // Modifica la lógica para invertir la dirección si llegan a los límites
                    if (vertex1[x][y][z][0] < -1 || vertex1[x][y][z][0] > 1) {
                        xyRDirection *= -1;
                        xzRDirection *= -1;
                        yzRDirection *= -1;
                    }

                    if (vertex2[x][y][z][0] < 0 || vertex2[x][y][z][0] > 2) {
                        xyRDirection *= -1;
                        xzRDirection *= -1;
                        yzRDirection *= -1;
                    }
                }
            }
        }
    }


    final void xyRotate(double p[], double sin, double cos) {
        double temp;
        temp = cos * p[0] + sin * p[1];
        p[1] = -sin * p[0] + cos * p[1];
        p[0] = temp;
    }

    final void xzRotate(double p[], double sin, double cos) {
        double temp;
        temp = cos * p[0] + sin * p[2];
        p[2] = -sin * p[0] + cos * p[2];
        p[0] = temp;
    }

    final void yzRotate(double p[], double sin, double cos) {
        double temp;
        temp = cos * p[1] + sin * p[2];
        p[2] = -sin * p[1] + cos * p[2];
        p[1] = temp;
    }

    final void drawEdge(double x1, double y1, double x2, double y2, BufferedImage bu, Color col) {
        dibujaLinea((int) (x1 * SCALE) + OFFSET, (int) (-y1 * SCALE) + OFFSET, (int) (x2 * SCALE) + OFFSET, (int) (-y2 * SCALE) + OFFSET, col, bu);
    }

    public static void dibujaLinea(int x0, int y0, int x1, int y1, Color col, BufferedImage bu) {
        Color c = col;
        float adyacente = Math.abs(x1 - x0);
        float opuesto = Math.abs(y1 - y0);
        float pendiente = opuesto / adyacente;

        int sigX = (x0 < x1) ? 1 : -1;
        int sigY = (y0 < y1) ? 1 : -1;

        pendiente = Math.abs(pendiente);

        if (Math.toDegrees(Math.atan(pendiente)) > 0) {
            pendiente = Math.abs(adyacente / opuesto);
            for (int i = 0; i <= Math.abs(opuesto); i++) {
                int x = x0 + (int) (i * pendiente * sigX);
                int y = y0 + (i * sigY);
                if (x >= 0 && x < bu.getWidth() && y >= 0 && y < bu.getHeight()) {
                    bu.setRGB(x, y, c.getRGB());
                }
            }
        } else {
            for (int h = 0; h <= Math.abs(adyacente); h++) {
                int x = x0 + h * sigX;
                int y = y0 + (int) (h * pendiente * sigY);
                if (x >= 0 && x < bu.getWidth() && y >= 0 && y < bu.getHeight()) {
                    bu.setRGB(x, y, c.getRGB());
                }
            }
        }
    }
}


