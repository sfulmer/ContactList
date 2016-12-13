package net.draconia.contactlist.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Contact")
public class Contact extends Observable implements Serializable
{
	private static final long serialVersionUID = -1072770217469335146L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(columnDefinition="int", insertable=true, name="contactListId", nullable=false, referencedColumnName="Id", unique=false, updatable=true)
	private ContactList mObjContactList;
	
	@Column(columnDefinition="int", insertable=true, name="Birthdate", nullable=true, unique=true, updatable=true)
	private Date mDtBirthdate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(columnDefinition="int", insertable=true, name="Id", nullable=false, unique=true, updatable=false)
	private Integer miId;
	
	@Column(columnDefinition="varchar", insertable=true, length=150, name="Email", nullable=false, unique=false, updatable=true)
	private String msEmail;
	
	@Column(columnDefinition="varchar", insertable=true, length=100, name="Name", nullable=false, unique=false, updatable=true)
	private String msName;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(inverseJoinColumns={@JoinColumn(columnDefinition="int", insertable=true, name="AddressId", nullable=false, unique=false, updatable=true)},joinColumns={@JoinColumn(columnDefinition="int", insertable=true, name="ContactId", nullable=false, unique=false, updatable=true)},name="ContactPhone")
	private List<Address> mLstAddresses;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(inverseJoinColumns={@JoinColumn(columnDefinition="int", insertable=true, name="PhoneId", nullable=false, unique=false, updatable=true)},joinColumns={@JoinColumn(columnDefinition="int", insertable=true, name="ContactId", nullable=false, unique=false, updatable=true)},name="ContactPhone")
	private List<Phone> mLstPhoneNumbers;
	
	public Contact()
	{ }
	
	public Contact(final ContactList objContactList)
	{
		setContactList(objContactList);
	}
	
	public Contact(final String sName)
	{
		setName(sName);
	}
	
	public Contact(final ContactList objContactList, final String sName)
	{
		setContactList(objContactList);
		setName(sName);
	}
	
	public Contact(final Integer iId, final String sName)
	{
		setId(iId);
		setName(sName);
	}
	
	public Contact(final ContactList objContactList, final Integer iId, final String sName)
	{
		setContactList(objContactList);
		setId(iId);
		setName(sName);
	}
	
	public Contact(final Integer iId, final String sName, final String sEmail)
	{
		setId(iId);
		setEmail(sEmail);
		setName(sName);
	}
		
	public Contact(final ContactList objContactList, final Integer iId, final String sName, final String sEmail)
	{
		setContactList(objContactList);
		setId(iId);
		setEmail(sEmail);
		setName(sName);
	}
	
	public Contact(final Integer iId, final String sName, final String sEmail, final Address[] arrAddresses)
	{
		setAddresses(Arrays.asList(arrAddresses));
		setId(iId);
		setEmail(sEmail);
		setName(sName);
	}
	
	public Contact(final Integer iId, final String sName, final String sEmail, final List<Address> lstAddresses)
	{
		setAddresses(lstAddresses);
		setId(iId);
		setEmail(sEmail);
		setName(sName);
	}
	
	public Contact(final Integer iId, final String sName, final String sEmail, final Address[] arrAddresses, final Phone[] arrPhoneNumbers)
	{
		setAddresses(Arrays.asList(arrAddresses));
		setPhoneNumbers(Arrays.asList(arrPhoneNumbers));
		setId(iId);
		setEmail(sEmail);
		setName(sName);
	}
	
	public Contact(final Integer iId, final String sName, final String sEmail, final List<Address> lstAddresses, final Phone[] arrPhoneNumbers)
	{
		setAddresses(lstAddresses);
		setPhoneNumbers(Arrays.asList(arrPhoneNumbers));
		setId(iId);
		setEmail(sEmail);
		setName(sName);
	}
	
	public boolean addAddress(final Address objAddress)
	{
		boolean bReturnValue = getAddressesInternal().add(objAddress);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public boolean addPhoneNumber(final Phone objPhone)
	{
		boolean bReturnValue = getPhoneNumbersInternal().add(objPhone);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public List<Address> getAddresses()
	{
		return(Collections.unmodifiableList(getAddressesInternal()));
	}
	
	protected List<Address> getAddressesInternal()
	{
		if(mLstAddresses == null)
			mLstAddresses = Collections.synchronizedList(new ArrayList<Address>());
		
		return(mLstAddresses);
	}
	
	public Date getBirthdate()
	{
		return(mDtBirthdate);
	}
	
	public ContactList getContactList()
	{
		return(mObjContactList);
	}
	
	public String getEmail()
	{
		return(msEmail);
	}
	
	public Integer getId()
	{
		return(miId);
	}
	
	public String getName()
	{
		return(msName);
	}
	
	public List<Phone> getPhoneNumbers()
	{
		return(Collections.unmodifiableList(getPhoneNumbersInternal()));
	}
	
	protected List<Phone> getPhoneNumbersInternal()
	{
		if(mLstPhoneNumbers == null)
			mLstPhoneNumbers = Collections.synchronizedList(new ArrayList<Phone>());
		
		return(mLstPhoneNumbers);
	}
	
	public boolean removeAddress(final Address objAddress)
	{
		boolean bReturnValue = getAddressesInternal().remove(objAddress);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public boolean removePhoneNumber(final Phone objPhoneNumber)
	{
		boolean bReturnValue = getPhoneNumbersInternal().remove(objPhoneNumber);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public void setAddresses(final List<Address> lstAddresses)
	{
		if(lstAddresses == null)
			mLstAddresses = Collections.synchronizedList(new ArrayList<Address>());
		else
			mLstAddresses = lstAddresses;
	}
	
	public void setBirthdate(final Date dtBirthdate)
	{
		mDtBirthdate = dtBirthdate;
		
		setChanged();
		notifyObservers();
	}
	
	public void setContactList(final ContactList objContactList)
	{
		mObjContactList = objContactList;
		
		setChanged();
		notifyObservers();
	}
	
	public void setEmail(final String sEmail)
	{
		msEmail = sEmail;
		
		setChanged();
		notifyObservers();
	}
	
	public void setId(final Integer iId)
	{
		miId = iId;
		
		setChanged();
		notifyObservers();
	}
	
	public void setName(final String sName)
	{
		msName = sName;
		
		setChanged();
		notifyObservers();
	}
	
	public void setPhoneNumbers(final List<Phone> lstPhoneNumbers)
	{
		if(lstPhoneNumbers == null)
			mLstPhoneNumbers = Collections.synchronizedList(new ArrayList<Phone>());
		else
			mLstPhoneNumbers = lstPhoneNumbers;
		
		setChanged();
		notifyObservers();
	}
}