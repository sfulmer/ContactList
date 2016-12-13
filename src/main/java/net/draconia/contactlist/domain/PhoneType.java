package net.draconia.contactlist.domain;

import java.io.Serializable;
import java.util.Observable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PhoneType extends Observable implements Serializable
{
	private static final long serialVersionUID = 8792618318155126877L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(columnDefinition="int", insertable=true, name="Id", nullable=false, unique=true, updatable=false)
	private Integer miId;
	
	@Column(columnDefinition="varchar", insertable=true, name="TypeName", nullable=false, unique=true, updatable=true)
	private String msTypeName;
	
	public PhoneType()
	{ }
	
	public PhoneType(final Integer iId, final String sTypeName)
	{
		setId(iId);
		setTypeName(sTypeName);
	}
	
	public Integer getId()
	{
		return(miId);
	}
	
	public String getTypeName()
	{
		return(msTypeName);
	}
	
	public void setId(final Integer iId)
	{
		miId = iId;
		
		setChanged();
		notifyObservers();
	}
	
	public void setTypeName(final String sTypeName)
	{
		msTypeName = sTypeName;
	}
}