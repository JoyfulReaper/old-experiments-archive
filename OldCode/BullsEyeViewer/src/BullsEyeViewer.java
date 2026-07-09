import javax.swing.JFrame;

public class BullsEyeViewer {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setTitle("Trippy BullsEye Viewer");
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BullsEyeComponent component = new BullsEyeComponent(); // Default settings
		// BullsEyeComponent component = new BullsEyeComponent(0, 10, true); // BullsEyeComponent(radius, shrinkAmount, autoRadius); // Full Constructor
		frame.add(component);
		frame.setVisible(true);
		
		boolean autoRepaint = true;
		if(autoRepaint)
		{
			while (true)
			{
				try {
					Thread.sleep(100);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					System.exit(0);
				}
				frame.repaint();
			}
		}
	}
}
