package net.draconia.contactlist.domain;

import java.io.Serializable;

import java.util.Observable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="AddressLine")
public class AddressLine extends Observable implements Serializable
{
	private static final long serialVersionUID = -9168231418126080903L;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="AddressId")
	@JoinColumn(columnDefinition="int", insertable=true, name="AddressId", nullable=false, unique=false, updatable=true)
	private Address mObjAddress;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(columnDefinition="int", insertable=true, name="Id", nullable=false, unique=true, updatable=false)
	private Integer miId;
	
	@Column(columnDefinition="varchar", insertable=true, name="AddressLine", nullable=false, unique=false, updatable=true)
	private String msAddressLine;
	
	public AddressLine()
	{ }
	
	public AddressLine(final Address objAddress, final String sAddressLine)
	{
		setAddress(objAddress);
		setAddressLine(sAddressLine);
	}
	
	public AddressLine(final Address objAddress, final Integer iId, final String sAddressLine)
	{
		setAddress(objAddress);
		setId(iId);
		setAddressLine(sAddressLine);
	}
	
	public Address getAddress()
	{
		return(mObjAddress);
	}
	
	public String getAddressLine()
	{
		return(msAddressLine);
	}
	
	public Integer getId()
	{
		return(miId);
	}
	
	public void setAddress(final Address objAddress)
	{
		mObjAddress = objAddress;
		
		setChanged();
		notifyObservers();
	}
	
	public void setAddressLine(final String sAddressLine)
	{
		msAddressLine = sAddressLine;
		
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