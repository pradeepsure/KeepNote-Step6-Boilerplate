package com.stackroute.keepnote.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  */
@Document
public class Category {

	/*
	 * This class should have five fields
	 * (categoryId,categoryName,categoryDescription,
	 * categoryCreatedBy,categoryCreationDate). Out of these five fields, the field
	 * categoryId should be annotated with @Id. This class should also contain the
	 * getters and setters for the fields along with the no-arg , parameterized
	 * constructor and toString method. The value of categoryCreationDate should not
	 * be accepted from the user but should be always initialized with the system
	 * date. 
	 */
	
	@Id
	private String id;

	private String categoryName;

	private String categoryDescription;

	private String categoryCreatedBy;

	private Date categoryCreationDate;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(String id, String categoryName, String categoryDescription, Date categoryCreationDate,
			String categoryCreatedBy) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.categoryCreatedBy = categoryCreatedBy;
		this.categoryCreationDate = categoryCreationDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String is) {
		this.id = is;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getCategoryCreatedBy() {
		return categoryCreatedBy;
	}

	public void setCategoryCreatedBy(String categoryCreatedBy) {
		this.categoryCreatedBy = categoryCreatedBy;
	}

	public Date getCategoryCreationDate() {
		return categoryCreationDate;
	}

	public void setCategoryCreationDate(Date categoryCreationDate) {
		this.categoryCreationDate = categoryCreationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryCreatedBy == null) ? 0 : categoryCreatedBy.hashCode());
		result = prime * result + ((categoryCreationDate == null) ? 0 : categoryCreationDate.hashCode());
		result = prime * result + ((categoryDescription == null) ? 0 : categoryDescription.hashCode());
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryCreatedBy == null) {
			if (other.categoryCreatedBy != null)
				return false;
		} else if (!categoryCreatedBy.equals(other.categoryCreatedBy))
			return false;
		if (categoryCreationDate == null) {
			if (other.categoryCreationDate != null)
				return false;
		} else if (!categoryCreationDate.equals(other.categoryCreationDate))
			return false;
		if (categoryDescription == null) {
			if (other.categoryDescription != null)
				return false;
		} else if (!categoryDescription.equals(other.categoryDescription))
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
