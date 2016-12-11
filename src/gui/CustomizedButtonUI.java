package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicButtonUI;


/** This class is designed for buttons to be look good
 * The code is modified and taken from http://codelife.de/2014/08/13/swing-jbutton-with-customized-look/
 * @author Ali Semi YENIMOL
 * @date 29.04.2016
 * @version 1.00
 * 
 */
public class CustomizedButtonUI extends BasicButtonUI implements MouseListener
{
	private Color     hoverColor;
	private Color     normalColor;
	private Color     pressedColor;
	private Color     btnFontColor;
	private ImageIcon normalIcon;
	private ImageIcon hoverIcon;
	private ImageIcon pressedIcon;
	private Font      btnFont;
	
	public CustomizedButtonUI() {
		super();
		btnFont = BaseGamePlayPanel.FONT;
		Image img;
		
		
		pressedColor = new Color(240, 240, 240);
		normalColor  = new Color(240, 240, 240);
		hoverColor   = new Color(240, 240, 240);
		btnFontColor = Color.WHITE;
		
		img = BaseScreen.loadImage("images/buttonNormal.png");
        img = img.getScaledInstance(180, 100, Image.SCALE_DEFAULT);
        normalIcon = new ImageIcon( img);
        
        img = BaseScreen.loadImage("images/buttonHovered.png");
        img = img.getScaledInstance(180, 100, Image.SCALE_DEFAULT);
        hoverIcon = new ImageIcon( img);
        
        img = BaseScreen.loadImage("images/buttonPressed.png");
        img = img.getScaledInstance(180, 100, Image.SCALE_DEFAULT);
        pressedIcon = new ImageIcon( img);
	}
	
	
	@Override
	public void installUI(JComponent comp) {
		super.installUI(comp);
		comp.addMouseListener(this);
	}
	
	@Override
	public void uninstallUI(JComponent comp) {
		super.uninstallUI(comp); 
		comp.removeMouseListener(this);
	}
	
	@Override
	protected void installDefaults(AbstractButton btn) {
		super.installDefaults(btn);
		btn.setIcon(this.normalIcon);
		btn.setBackground(this.normalColor);
		btn.setForeground(this.btnFontColor);
		btn.setFont(this.btnFont);
		btn.setHorizontalTextPosition(SwingConstants.CENTER);
	}
	
	@Override
	public Dimension getPreferredSize(JComponent comp) {
		Dimension dim = super.getPreferredSize(comp);
		if(comp.getBorder() != null){
		    Insets insets = comp.getBorder().getBorderInsets(comp);
		    dim.setSize(dim.width + insets.left + insets.right, dim.height + insets.top + insets.bottom);
		}
	return dim;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		changeButtonStyle((JButton) e.getComponent(), this.pressedColor, this.pressedIcon);
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		changeButtonStyle((JButton) e.getComponent(), this.pressedColor, this.pressedIcon);
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		changeButtonStyle((JButton)e.getComponent(), this.normalColor, this.normalIcon);
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		changeButtonStyle((JButton) e.getComponent(), this.hoverColor, this.hoverIcon);
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		changeButtonStyle((JButton)e.getComponent(), this.normalColor, this.normalIcon);
	}
	
	private void changeButtonStyle(JButton btn, Color color, ImageIcon icon){
		btn.setBackground(color);
		btn.setIcon(icon);
	}

}


