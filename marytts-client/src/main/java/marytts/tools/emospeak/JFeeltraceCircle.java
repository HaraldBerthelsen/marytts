/**
 * Copyright 2000-2006 DFKI GmbH.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * This file is part of MARY TTS.
 *
 * MARY TTS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package marytts.tools.emospeak;

/**
 * 
 * @author schroed
 */
public class JFeeltraceCircle extends javax.swing.JPanel {

	private boolean isCircular = true; // global setting
	private int circleSize;

	private int cursorDiameter = 30;

	public int getCursorDiameter() {
		return cursorDiameter;
	}

	public void setCursorDiameter(int d) {
		cursorDiameter = d;
	}

	private java.awt.geom.RectangularShape feeltraceShape;

	private TwoDimensionalModel normalizedModel = new RectangularTwoDimensionalModel(0, 0, -100, 100, -100, 100);

	private java.awt.Dimension requestedSize = new java.awt.Dimension(200, 200);

	public void setRequestedSize(java.awt.Dimension requestedSize) {
		this.requestedSize = requestedSize;
	}

	public java.awt.Dimension getRequestedSize() {
		return requestedSize;
	}

	/** Creates new form JFeeltraceCircle */
	public JFeeltraceCircle(boolean isCircular, java.awt.Dimension requestedSize) {
		this.isCircular = isCircular;
		this.requestedSize = requestedSize;
		initComponents();
		customInitComponents();
	}

	/** Creates new form JFeeltraceCircle */
	public JFeeltraceCircle() {
		initComponents();
		customInitComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of
	 * this method is always regenerated by the Form Editor.
	 */
	private void initComponents() {// GEN-BEGIN:initComponents

		setLayout(new java.awt.BorderLayout());

		addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				formMousePressed(evt);
			}
		});

		addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				formMouseDragged(evt);
			}
		});

	}// GEN-END:initComponents

	private void formMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMousePressed
		setFeeltraceCursor(evt.getPoint());
		repaint();
	}// GEN-LAST:event_formMousePressed

	private void formMouseDragged(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseDragged
		setFeeltraceCursor(evt.getPoint());
		repaint();
	}// GEN-LAST:event_formMouseDragged

	private void customInitComponents() {
		if (isCircular) {
			feeltraceShape = new java.awt.geom.Ellipse2D.Float();
		} else { // square
			feeltraceShape = new java.awt.geom.Rectangle2D.Float();
		}
		normalizedModel.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent e) {
				repaint();
			}
		});

	}

	protected void paintComponent(java.awt.Graphics graphics) {
		super.paintComponent(graphics);
		java.awt.Insets insets = getInsets();
		int currentWidth = getWidth() - insets.left - insets.right;
		int currentHeight = getHeight() - insets.top - insets.bottom;
		int offsetX = insets.left;
		int offsetY = insets.top;
		if (currentWidth >= currentHeight) {
			circleSize = currentHeight;
			offsetX += (currentWidth - circleSize) / 2;
		} else {
			circleSize = currentWidth;
			offsetY += (currentHeight - circleSize) / 2;
		}
		if (isCircular) {
			graphics.setColor(java.awt.Color.white);
			graphics.fillOval(offsetX, offsetY, circleSize, circleSize);
			graphics.setColor(java.awt.Color.black);
			graphics.drawOval(offsetX, offsetY, circleSize, circleSize);
			graphics.drawLine(offsetX, offsetY + circleSize / 2, offsetX + circleSize, offsetY + circleSize / 2);
			graphics.drawLine(offsetX + circleSize / 2, offsetY, offsetX + circleSize / 2, offsetY + circleSize);
		} else { // square
			graphics.setColor(java.awt.Color.white);
			graphics.fillRect(offsetX, offsetY, circleSize, circleSize);
			graphics.setColor(java.awt.Color.black);
			graphics.drawRect(offsetX, offsetY, circleSize, circleSize);
			graphics.drawLine(offsetX, offsetY + circleSize / 2, offsetX + circleSize, offsetY + circleSize / 2);
			graphics.drawLine(offsetX + circleSize / 2, offsetY, offsetX + circleSize / 2, offsetY + circleSize);
		}
		feeltraceShape.setFrame(offsetX, offsetY, circleSize, circleSize);

		// And now the cursor
		int x = (int) (feeltraceShape.getCenterX() + normalizedModel.getX()
				* (feeltraceShape.getMaxX() - feeltraceShape.getCenterX()) / normalizedModel.getMaxX());
		int y = (int) (feeltraceShape.getCenterY() - normalizedModel.getY()
				* (feeltraceShape.getMaxY() - feeltraceShape.getCenterY()) / normalizedModel.getMaxY());
		graphics.setColor(java.awt.Color.green);
		graphics.fillOval(x - cursorDiameter / 2, y - cursorDiameter / 2, cursorDiameter, cursorDiameter);

	}

	/**
	 * Define the location of the cursor; the actual drawing is done in paintComponents().
	 */
	private void setFeeltraceCursor(java.awt.Point p) {
		java.awt.Point newLocation;
		if (feeltraceShape.contains(p)) {
			newLocation = p;
		} else {
			if (isCircular) {
				double px = p.getX() - feeltraceShape.getCenterX();
				double py = p.getY() - feeltraceShape.getCenterY();
				// Determine angle, paint cursor at extreme possible
				// value at that angle
				if (py == 0) {
					newLocation = new java.awt.Point((int) feeltraceShape.getCenterX() + circleSize,
							(int) feeltraceShape.getCenterY());
				} else {
					// these formulae follow from a little drawing:
					double y = (circleSize / 2) / Math.sqrt(1 + px * px / (py * py));
					if (py < 0)
						y = -y;
					double x = px / py * y;
					newLocation = new java.awt.Point((int) (feeltraceShape.getCenterX() + x),
							(int) (feeltraceShape.getCenterY() + y));
				}
			} else {
				// ignore out-of-bounds for rectangle
				return;
			}
		}
		// In the Y normalisation, add a - sign, because top=+100, bottom=-100
		normalizedModel.setXY(
				(int) ((newLocation.getX() - feeltraceShape.getCenterX())
						/ (feeltraceShape.getMaxX() - feeltraceShape.getCenterX()) * normalizedModel.getMaxX()),
				-(int) ((newLocation.getY() - feeltraceShape.getCenterY())
						/ (feeltraceShape.getMaxY() - feeltraceShape.getCenterY()) * normalizedModel.getMaxY()));

	}

	public void setNormalizedLocation(int x, int y) {
		normalizedModel.setXY(x, y);
	}

	public void setNormalizedX(int x) {
		normalizedModel.setX(x);
	}

	public void setNormalizedY(int y) {
		normalizedModel.setY(y);
	}

	public int getNormalizedX() {
		return normalizedModel.getX();
	}

	public int getNormalizedY() {
		return normalizedModel.getY();
	}

	public TwoDimensionalModel getNormalizedModel() {
		return normalizedModel;
	}

}
