package net.draconia;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.stereotype.Component;

import net.draconia.contactlist.ui.ContactListMainForm;

@Component
public class ContactListMain implements Runnable
{
	ContactListMainForm mWndMainForm;
	
	public ContactListMain()
	{ }
	
	public ContactListMain(final ContactListMainForm wndMainForm)
	{
		setMainForm(wndMainForm);
	}
	
	protected ContactListMainForm getMainForm()
	{
		return(mWndMainForm);
	}
	
	public void run()
	{
		try
			{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			}
		catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException objException)
			{
			objException.printStackTrace(System.err);
			}
		
		getMainForm().setVisible(true);
	}
	
	protected void setMainForm(final ContactListMainForm wndMainForm)
	{
		mWndMainForm = wndMainForm;
	}
	
	public static void main(final String[] args)
	{
		try (ClassPathXmlApplicationContext objContext = new ClassPathXmlApplicationContext("Beans.xml"))
			{
			objContext.getBean(ContactListMainForm.class);
			}
	}
}