package frames;

import rectClasses.ColoredRect;
import rectClasses.DrawableRect;
import rectClasses.Rectangle;
import rectClasses.RectanglesPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class MainFrame extends JFrame implements ActionListener {
    private final JButton createRectangleButton;
    private final JButton createDrawableRectangleButton;
    private final JButton createColoredRectangleButton;
    private final RectanglesPresenter rectanglesPresenter;
    private static final int MIN_SIZE = 20;
    private static final int MAX_SIZE = 110;
    private static final int OFFSET = 30;

    public MainFrame() {
        rectanglesPresenter = new RectanglesPresenter();
        createRectangleButton = new JButton("Rectangle");
        createDrawableRectangleButton = new JButton("DrawableRect");
        createColoredRectangleButton = new JButton("ColoredRect");

        createRectangleButton.addActionListener(this);
        createDrawableRectangleButton.addActionListener(this);
        createColoredRectangleButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(createRectangleButton);
        panel.add(createDrawableRectangleButton);
        panel.add(createColoredRectangleButton);

        setTitle("Lab №4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        getContentPane().add(BorderLayout.EAST, panel);
        getContentPane().add(rectanglesPresenter);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createRectangleButton) {
            Rectangle rectangle = new Rectangle(OFFSET, OFFSET, ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE) + OFFSET, ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE) + OFFSET);
            rectanglesPresenter.AddRectangle(rectangle);
        }
        if (e.getSource() == createDrawableRectangleButton) {
            Rectangle rectangle = new DrawableRect(OFFSET, OFFSET, ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE) + OFFSET, ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE) + OFFSET);
            rectanglesPresenter.AddRectangle(rectangle);
        }
        if (e.getSource() == createColoredRectangleButton) {
            Rectangle rectangle = new ColoredRect(OFFSET, OFFSET, ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE) + OFFSET, ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE) + OFFSET);
            rectanglesPresenter.AddRectangle(rectangle);
        }
    }
}
