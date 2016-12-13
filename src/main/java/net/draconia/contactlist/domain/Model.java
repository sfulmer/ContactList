package net.draconia.contactlist.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import org.springframework.stereotype.Component;

@Component
public class Model extends Observable implements Serializable
{
	private static final long serialVersionUID = 8316067917363156718L;
	
	private ContactList mObjCurrentContactList;
	private List<ContactList> mLstContactLists;
	
	public Model()
	{ }
	
	public boolean addContactList(final ContactList objContactList)
	{
		boolean bReturnValue = getContactListsInternal().add(objContactList);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public ContactList getCurrentContactList()
	{
		return(mObjCurrentContactList);
	}
	
	public List<ContactList> getContactLists()
	{
		return(Collections.unmodifiableList(getContactListsInternal()));
	}
	
	protected List<ContactList> getContactListsInternal()
	{
		if(mLstContactLists == null)
			mLstContactLists = Collections.synchronizedList(new ArrayList<ContactList>());
		
		return(mLstContactLists);
	}
	
	public boolean removeConactList(final ContactList objContactList)
	{
		boolean bReturnValue = getContactListsInternal().remove(objContactList);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public void setCurrentContactList(final ContactList objContactList)
	{
		mObjCurrentContactList = objContactList;
		
		setChanged();
		notifyObservers();
	}
}