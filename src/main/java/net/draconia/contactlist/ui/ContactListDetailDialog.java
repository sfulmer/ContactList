package net.draconia.contactlist.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.annotation.PostConstruct;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.contactlist.domain.Model;

@Component
public class ContactListDetailDialog extends JDialog
{
	private static final long serialVersionUID = 2608285664540062356L;
	
	@Autowired
	private Model mObjMainModel;
	
	@Autowired
	private static ContactListMainForm msWndParent;
	
	public ContactListDetailDialog()
	{
		super(msWndParent);
	}
	
	public ContactListDetailDialog(final ContactListMainForm wndParent)
	{
		super(wndParent);
	}
	
	protected Model getMainModel()
	{
		return(mObjMainModel);
	}
	
	protected JPanel getMainPanel()
	{
		JPanel pnlMain = new JPanel(new BorderLayout(5, 5));
		
		return(pnlMain);
	}
	
	protected void initControls()
	{
		add(getMainPanel(), BorderLayout.CENTER);
	}
	
	@PostConstruct
	protected void initDialog()
	{
		Dimension scrSize = getToolkit().getScreenSize();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
		
		initControls();
		
		pack();
		
		setLocation(new Point((scrSize.width - getWidth())/2, (scrSize.height - getHeight())/2));
	}
	
	protected void setMainModel(final Model objMainModel)
	{
		mObjMainModel = objMainModel;
	}
}