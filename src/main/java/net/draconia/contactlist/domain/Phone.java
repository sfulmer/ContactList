package net.draconia.contactlist.domain;

import java.io.Serializable;

import java.util.Observable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Phone")
public class Phone extends Observable implements Serializable
{
	private static final long serialVersionUID = 7570885581830907784L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(columnDefinition="int", insertable=true, name="Id", nullable=false, unique=true, updatable=false)
	private Integer miId;
	
	@OneToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(columnDefinition="int", insertable=true, name="PhoneType", nullable=false, unique=false, updatable=true)
	private PhoneType mObjPhoneType;
	
	@Column(columnDefinition="varchar", insertable=true, name="PhoneNumber", nullable=false, unique=false, updatable=true)
	private String msPhoneNumber;
	
	public Phone()
	{ }
	
	public Phone(final Integer iId, final String sPhoneNumber, final PhoneType objPhoneType)
	{
		setId(iId);
		setPhoneNumber(sPhoneNumber);
		setPhoneType(objPhoneType);
	}
	
	public Integer getId()
	{
		return(miId);
	}
	
	public String getPhoneNumber()
	{
		return(msPhoneNumber);
	}
	
	public PhoneType getPhoneType()
	{
		return(mObjPhoneType);
	}
	
	public void setId(final Integer iId)
	{
		miId = iId;
		
		setChanged();
		notifyObservers();
	}
	
	public void setPhoneNumber(final String sPhoneNumber)
	{
		msPhoneNumber = sPhoneNumber;
		
		setChanged();
		notifyObservers();
	}
	
	public void setPhoneType(final PhoneType objPhoneType)
	{
		mObjPhoneType = objPhoneType;
		
		setChanged();
		notifyObservers();
	}
}