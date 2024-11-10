package me.awli.templejt;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    // WAŻNE
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // OBRAZEK
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // Piksele z obrazka. Nie
    // tworzymy tutaj kopii! Modyfikując ten array modyfikujemy obrazek
    // typ DataBufferInt odnosi się bezpośrednio do BufferedImage.TYPE_INT_RGB, mówi to że piksel ma format RRGGBB, bez
    // przeźroczystości, w int-cie

    public Game() {
        Dimension windowDimensions = new Dimension(WIDTH, HEIGHT);
        // ustaw rozmiar canvasu(czyli tego)
        this.setMinimumSize(windowDimensions);
        this.setPreferredSize(windowDimensions);
        this.setMaximumSize(windowDimensions);

        JFrame frame = new JFrame("nazwa ogna"); // nowe okno
        frame.add(this); // dodaj canvas
        frame.pack(); // zastosuj rozmiar
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // zabij program po naciśnięciu X
        frame.setResizable(false); // nie można zmieniać rozmiaru okna
        frame.setVisible(true); // ustaw widoczność
    }

    // inne gówna
    private void tick() {

    }

    // rysowanie
    private void render() {
        // weź warstwę
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            // jeśli nie ma żadnej to stwórz 3 tymczasowe
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        // i tutaj możemy coś zrobić z OBRAZKIEM
        for(int i = 0; i < pixels.length; i++)
            // np. zmienić jego kolor na zielony
            pixels[i] = 0x00FF00; // hex

        // rysuje OBRAZEK do jednej z warstw
        g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
        g.dispose();
        bs.show(); // pokazujemy warstwe na oknie
    }

    public void run() {
        // pętla, to się robi co klatkę
        while (true) {
            tick();
            render();
        }
    }

    public static void main(String[] args) {
        new Game().run();
    }
}