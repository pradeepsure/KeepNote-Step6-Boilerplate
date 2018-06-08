package com.stackroute.keepnote.model;

import java.util.Date;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  */

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
	

    public String getId() {
        return null;
    }

    public void setId(String id) {
       
    }

    public String getCategoryName() {
        return null;
    }

    public void setCategoryName(String categoryName) {
       
    }

    public String getCategoryDescription() {
        return null;
    }

    public void setCategoryDescription(String categoryDescription) {
      
    }

    public String getCategoryCreatedBy() {
        return null;
    }

    public void setCategoryCreatedBy(String categoryCreatedBy) {
        
    }

    public Date getCategoryCreationDate() {
        return null;
    }

    public void setCategoryCreationDate(Date categoryCreationDate) {
       
    }


}
