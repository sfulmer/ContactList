package net.draconia.ui.menu.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.draconia.contactlist.ui.ContactListDetailDialog;

@Component
public class NewList extends AbstractAction
{	
	private static final long serialVersionUID = -6319103892162337199L;
	
	@Autowired
	private ContactListDetailDialog mDlgContactListDetails;
	
	public NewList()
	{
		super("New List...");
		
		putValue(MNEMONIC_KEY, KeyEvent.VK_N);
	}
	
	public void actionPerformed(final ActionEvent objActionEvent)
	{
		getDetailDialog().setVisible(true);
	}
	
	protected ContactListDetailDialog getDetailDialog()
	{
		return(mDlgContactListDetails);
	}
	
	protected void setDetailDialog(final ContactListDetailDialog dlgContactListDetails)
	{
		mDlgContactListDetails = dlgContactListDetails;
	}
}