/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Academy;
import javax.swing.*;
import java.awt.*;
import java.awt.print.*;

/**
 *
 * @author mdnif
 */
public class TextAreaPrint implements Printable {

    private JTextArea textArea;

    public TextAreaPrint(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        // Get the text to print from JTextArea
        String text = textArea.getText();

        // FontMetrics to calculate the height of the text
        FontMetrics metrics = g2d.getFontMetrics();

        int x = 50;  // X coordinate to start printing
        int y = 50;  // Y coordinate to start printing

        String[] lines = text.split("\n");
        for (String line : lines) {
            g2d.drawString(line, x, y);
            y += metrics.getHeight(); // Move to the next line
        }

        return Printable.PAGE_EXISTS;
    }
}