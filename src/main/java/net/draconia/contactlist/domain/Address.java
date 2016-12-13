package net.draconia.contactlist.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Address")
public class Address extends Observable implements Serializable
{
	private static final long serialVersionUID = 1330125963880895658L;
	
	@OneToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(columnDefinition="int", insertable=true, name="AddressType", nullable=false, unique=false, updatable=true)
	private AddressType mObjAddressType;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(columnDefinition="int", insertable=true, name="ContactId", nullable=false, unique=false, updatable=true)
	private Contact mObjContact;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(columnDefinition="int", insertable=true, name="Id", nullable=false, unique=true, updatable=false)
	private Integer miId;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(inverseJoinColumns={@JoinColumn(columnDefinition="int", insertable=true, name="AddressId", nullable=false, unique=false, updatable=true)},joinColumns={@JoinColumn(columnDefinition="int", insertable=true, name="AddressLineId", nullable=false, unique=false, updatable=true)},name="AddressLine")
	private List<AddressLine> mLstAddressLines;
	
	public Address()
	{ }
	
	public Address(final Integer iId, final Contact objContact, final AddressType objAddressType, final AddressLine[] arrAddressLines)
	{
		setId(iId);
		setContact(objContact);
		setAddressType(objAddressType);
		setAddressLines(Arrays.asList(arrAddressLines));
	}
	
	public Address(final Integer iId, final Contact objContact, final AddressType objAddressType, final List<AddressLine> lstAddressLines)
	{
		setId(iId);
		setContact(objContact);
		setAddressType(objAddressType);
		setAddressLines(lstAddressLines);
	}
	
	public boolean addAddressLine(final AddressLine objAddressLine)
	{
		boolean bReturnValue = getAddressLinesInternal().add(objAddressLine);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public boolean addAddressLine(final String sAddressLine)
	{
		return(addAddressLine(new AddressLine(this, sAddressLine)));
	}
	
	public List<AddressLine> getAddressLines()
	{
		return(Collections.unmodifiableList(getAddressLinesInternal()));
	}
	
	protected List<AddressLine> getAddressLinesInternal()
	{
		if(mLstAddressLines == null)
			mLstAddressLines = Collections.synchronizedList(new ArrayList<AddressLine>());
		
		return(mLstAddressLines);
	}
	
	public AddressType getAddressType()
	{
		return(mObjAddressType);
	}
	
	public Contact getContact()
	{
		return(mObjContact);
	}
	
	public Integer getId()
	{
		return(miId);
	}
	
	public boolean removeAddressLine(final AddressLine objAddressLine)
	{
		boolean bReturnValue = getAddressLinesInternal().remove(objAddressLine);
		
		setChanged();
		notifyObservers();
		
		return(bReturnValue);
	}
	
	public void setAddressLines(final List<AddressLine> lstAddressLines)
	{
		if(lstAddressLines == null)
			mLstAddressLines = Collections.synchronizedList(new ArrayList<AddressLine>());
		else
			mLstAddressLines = lstAddressLines;
		
		setChanged();
		notifyObservers();
	}
	
	public void setAddressType(final AddressType objAddressType)
	{
		mObjAddressType = objAddressType;
		
		setChanged();
		notifyObservers();
	}
	
	public void setContact(final Contact objContact)
	{
		mObjContact = objContact;
		
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