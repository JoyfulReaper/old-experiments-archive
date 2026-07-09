import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

public class BullsEyeComponent extends JComponent implements MouseListener {
	private double radius = 0;  // radius of circle
	private boolean autoRadius; // Automatically pick radius value
	private int shrinkAmount;   // Amount by which to shrink radius
	
	public BullsEyeComponent(){
		this(0, BullsEye.DEFAULT_SHRINKAMOUNT, true);
	}
	
	public BullsEyeComponent(double radius) {
		this(radius, BullsEye.DEFAULT_SHRINKAMOUNT, false);
	}
	
	public BullsEyeComponent(int shrinkAmount)
	{
		this(0, shrinkAmount, true);
	}
	
	public BullsEyeComponent(double radius, int shrinkAmount)
	{
		this(radius, shrinkAmount, false);
	}
	
	/**
	 * 
	 * @param radius Initial radius
	 * @param shrinkAmount Amount by which to shrink radius
	 * @param autoRadius if true radius is picked automatically, ignoring the radius parameter
	 */
	public BullsEyeComponent(double radius, int shrinkAmount, boolean autoRadius)
	{
		if((radius < 1 && !autoRadius) || shrinkAmount < 1)
			throw new IllegalArgumentException("radius and/or shrinkAmount must be >= 1");
		this.autoRadius = autoRadius;
		this.radius = radius;
		this.shrinkAmount = shrinkAmount;
		addMouseListener(this);
	}
	
	
	public void update(Graphics g) // We don't need to clear what was already on the screen, since we are just going to draw over it... (reduce flicker)
	{
		paint(g);
	}
	
	public void paintComponent(Graphics g)
	{
		BullsEye bullsEye;
		Graphics2D g2 = (Graphics2D) g;
		
		if(autoRadius)
		{
			// Automatically determine optimal radius
			int maxWidth = getWidth() / 2;
			int maxHeight = getHeight() / 2;
			radius = maxHeight >= maxWidth ? maxWidth : maxHeight;
		}
		
		// Center the bullsEye
		double x = getWidth() / 2 - radius; // (Width / 2 - radius)
		double y = getHeight() / 2 - radius; // (Height / 2 - radius)
		
		if(shrinkAmount != BullsEye.DEFAULT_SHRINKAMOUNT)
			bullsEye = new BullsEye(x, y, radius, shrinkAmount); // set shrinkAmount
		else
			bullsEye = new BullsEye(x, y, radius, BullsEye.DEFAULT_SHRINKAMOUNT);
		
		bullsEye.draw(g2);
	}
	
    public void mousePressed(MouseEvent e)  {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e)  {}
    public void mouseExited(MouseEvent e)   {}
    
    public void mouseClicked(MouseEvent e) {
        repaint();
    }
}
