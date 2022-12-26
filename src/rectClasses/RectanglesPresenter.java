package rectClasses;

import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class RectanglesPresenter extends JPanel implements MouseListener, MouseMotionListener {
    private final ArrayList<Rectangle> rectangles;
    private Rectangle target;
    private static final int BOX_HEIGHT = 560;

    private int shiftX;
    private int shiftY;

    public RectanglesPresenter() {
        target = null;
        setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, BOX_HEIGHT));
        rectangles = new ArrayList<>();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width + 50, BOX_HEIGHT);
        for (Rectangle rectangle : rectangles) {
            try {
                rectangle.draw(g);
            } catch (ExecutionControl.NotImplementedException ignored) {
            }
        }
    }

    public void AddRectangle(Rectangle rectangle) {
        if (rectangle instanceof DrawableRect)
            rectangles.add(rectangle);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        rectangles.stream().filter(rectangle -> rectangle.pointWithinTheRectangle(e.getX(), e.getY()))
                .reduce((first, second) -> second).ifPresent(result -> {
                    target = result;
                    shiftX = e.getX() - target.getX(1);
                    shiftY = e.getY() - target.getY(1);
                });
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        target = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (target == null)
            return;
        int newX = e.getX() - shiftX;
        int newY = e.getY() - shiftY;
        if (newX < 0 || newX + target.getX(2) - target.getX(1) > getBounds().width || newY < 0 || newY +
                target.getY(2) - target.getY(1) > BOX_HEIGHT)
            return;
        target.moveTo(newX, newY);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
