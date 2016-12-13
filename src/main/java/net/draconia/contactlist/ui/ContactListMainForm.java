package net.draconia.contactlist.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.draconia.ui.menu.actions.Exit;
import net.draconia.ui.menu.actions.NewList;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class ContactListMainForm extends JFrame implements Serializable
{
	private static final long serialVersionUID = 4364495400064668072L;
	
	@Autowired
	private Exit mActExit;
	
	@Autowired
	private NewList mActNewList;
	
	//private Action mActRemoveList, mActSelectList;
	
	public ContactListMainForm()
	{
		super("Contact List Maintenance Application");
	}
	
	protected Exit getExitAction()
	{
		return(mActExit);
	}
	
	protected JMenu getListMenu()
	{
		JMenu mnuList = new JMenu("List");
		
		mnuList.setMnemonic(KeyEvent.VK_L);
		mnuList.add(new JMenuItem(getNewListAction()));
		//mnuList.add(new JMenuItem(getSelectListAction()));
		//mnuList.add(new JMenuItem(getRemoveListAction()));
		mnuList.addSeparator();
		mnuList.add(new JMenuItem(getExitAction()));
		
		return(mnuList);
	}
	
	protected NewList getNewListAction()
	{
		return(mActNewList);
	}
	
	protected void initControls()
	{ }
	
	@PostConstruct
	protected void initFrame()
	{
		Dimension szScreen = getToolkit().getScreenSize();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
		
		initControls();
		initMenus();
		
		pack();
		
		setLocation(new Point((szScreen.width - getWidth()) / 2, (szScreen.height - getHeight())/2));
	}
	
	protected void initMenus()
	{
		JMenuBar objJMenuBar = new JMenuBar();
		
		setJMenuBar(objJMenuBar);
		
		objJMenuBar.add(getListMenu());
	}
	
	protected void setNewListAction(final NewList actNewList)
	{
		mActNewList = actNewList;
	}
}