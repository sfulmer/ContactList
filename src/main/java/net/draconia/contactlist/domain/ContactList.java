package net.draconia.contactlist.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class ContactList extends Observable implements Serializable
{
	private static final long serialVersionUID = 2221524280509508146L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(columnDefinition="int", insertable=true, name="Id", nullable=false, unique=true, updatable=false)
	private Integer miId;
	
	@OneToMany(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY, mappedBy="ContactList")
	private List<Contact> mLstContacts;
	
	public ContactList()
	{ }
	
	public ContactList(final Integer iId, final Contact[] arrContacts)
	{
		setId(iId);
		setContacts(Arrays.asList(arrContacts));
	}
	
	public boolean addContact(final Contact objContact)
	{
		boolean bReturnValue = getContactsInternal().add(objContact);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public List<Contact> getContacts()
	{
		return(Collections.unmodifiableList(getContactsInternal()));
	}
	
	protected List<Contact> getContactsInternal()
	{
		if(mLstContacts == null)
			mLstContacts = Collections.synchronizedList(new ArrayList<Contact>());
		
		return(mLstContacts);
	}
	
	public Integer getId()
	{
		return(miId);
	}
	
	public boolean removeContact(final Contact objContact)
	{
		boolean bReturnValue = getContactsInternal().remove(objContact);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public void setContacts(final List<Contact> lstContacts)
	{
		if(lstContacts == null)
			mLstContacts = Collections.synchronizedList(new ArrayList<Contact>());
		else
			mLstContacts = lstContacts;
		
		setChanged();
		notifyObservers();
	}
	
	public void setId(final Integer iId)
	{
		miId = iId;
		
		setChanged();
		notifyObservers();
	}
}