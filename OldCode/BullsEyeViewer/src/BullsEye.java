import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BullsEye {

	public static final int DEFAULT_SHRINKAMOUNT = 10;
	
	private Color color1 = Color.BLACK; // default color
	private Color color2 = Color.WHITE; // default color
	
	private double x; // X coord of circle
	private double y; // Y coord of circle
	private double h; // height of circle
	private double w; // width of circle
	private double radius; // radius of circle
	private double shrinkAmount; // amount by which to reduce the radius
	private ArrayList<Ellipse2D.Double> circles; // ArrayList of circles

	// Use colors other than the default?
	private boolean colorful = false; // Always use a random color
	private boolean semiColorful = true; // sometimes use a random color

	/**
	 * Construct BullsEye
	 * @param x x coord
	 * @param y y coord
	 * @param radius Initial radius
	 * @param shrinkAmount Amount to shrink radius
	 */
	public BullsEye(double x, double y, double radius, double shrinkAmount)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.w = radius + radius;
		this.h = radius + radius;
		circles = new ArrayList<Ellipse2D.Double>();
		this.shrinkAmount = shrinkAmount;
	}

	public BullsEye(double x, double y, double radius)
	{
		this(x, y, radius, DEFAULT_SHRINKAMOUNT);
	}

	public void draw(Graphics2D g2)
	{
		while(radius > shrinkAmount)
		{
			adjust();
			Ellipse2D.Double circle = new Ellipse2D.Double(x, y, h, w);
			circles.add(circle);
		}

		for (int i = 0; i < circles.size(); i++)
		{
			if(colorful)
			{
				int rand[] = new int[3];
				for(int j = 0; j < rand.length; j++)
					rand[j] = (int)(255 * Math.random());
				Color c = new Color(rand[0], rand[1], rand[2]);
				g2.setColor(c);
			}
			else
			{
				if (i % 2 == 0) {
					g2.setColor(color1);
				}
				else {
					g2.setColor(color2);
				}
			}
			if(semiColorful)
			{
				double doColors = Math.random();
				if (doColors > 0.8)
				{
					int rand[] = new int[3];
					for(int j = 0; j < rand.length; j++)
						rand[j] = (int)(255 * Math.random());
					Color c = new Color(rand[0], rand[1], rand[2]);
					g2.setColor(c);
				}
			}

			g2.fill(circles.get(i));
		}
	}

	private void adjust()
	{
		radius = radius - shrinkAmount;
		w = radius + radius;
		h = radius + radius;
		x = x + shrinkAmount;
		y = y + shrinkAmount;
	}
}
