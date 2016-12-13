package net.draconia.ui.menu.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.contactlist.ui.ContactListMainForm;

@Component
public class Exit extends AbstractAction
{
	private static final long serialVersionUID = 2675616640962278768L;
	
	@Autowired
	private ContactListMainForm mWndMainForm;
	
	public Exit()
	{
		super("Exit");
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_X);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK, false));
	}
	
	public Exit(final ContactListMainForm wndMainForm)
	{
		super("Exit");
		
		setMainForm(wndMainForm);
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_X);
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK, false));
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		getMainForm().dispose();
	}
	
	protected ContactListMainForm getMainForm()
	{
		return(mWndMainForm);
	}
	
	protected void setMainForm(final ContactListMainForm wndMainForm)
	{
		mWndMainForm = wndMainForm;
	}
}