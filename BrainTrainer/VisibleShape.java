//imports

import java.awt.*;


public interface VisibleShape {
    void draw(Graphics2D g2);

    void setVisibilityPolicy();

    boolean overlaps(VisibleShape other);
}
