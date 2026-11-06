// $LastChangedRevision$ DO NOT EDIT.  Make changes to RecordLabel.java instead.
package br.com.doit.recordshop.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _RecordLabel extends  EOGenericRecord {
	public static final String ENTITY_NAME = "RecordLabel";

	// Attributes
	public static final String COUNTRY_KEY = "country";
	public static final String NAME_KEY = "name";

	// Relationships

  private static Logger LOG = Logger.getLogger(_RecordLabel.class);

  public RecordLabel localInstanceIn(EOEditingContext editingContext) {
    RecordLabel localInstance = (RecordLabel)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String country() {
    return (String) storedValueForKey("country");
  }

  public void setCountry(String value) {
    if (_RecordLabel.LOG.isDebugEnabled()) {
    	_RecordLabel.LOG.debug( "updating country from " + country() + " to " + value);
    }
    takeStoredValueForKey(value, "country");
  }

  public String name() {
    return (String) storedValueForKey("name");
  }

  public void setName(String value) {
    if (_RecordLabel.LOG.isDebugEnabled()) {
    	_RecordLabel.LOG.debug( "updating name from " + name() + " to " + value);
    }
    takeStoredValueForKey(value, "name");
  }


  public static RecordLabel createRecordLabel(EOEditingContext editingContext, String country
, String name
) {
    RecordLabel eo = (RecordLabel) EOUtilities.createAndInsertInstance(editingContext, _RecordLabel.ENTITY_NAME);    
		eo.setCountry(country);
		eo.setName(name);
    return eo;
  }

  public static NSArray<RecordLabel> fetchAllRecordLabels(EOEditingContext editingContext) {
    return _RecordLabel.fetchAllRecordLabels(editingContext, null);
  }

  public static NSArray<RecordLabel> fetchAllRecordLabels(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _RecordLabel.fetchRecordLabels(editingContext, null, sortOrderings);
  }

  public static NSArray<RecordLabel> fetchRecordLabels(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_RecordLabel.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<RecordLabel> eoObjects = (NSArray<RecordLabel>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static RecordLabel fetchRecordLabel(EOEditingContext editingContext, String keyName, Object value) {
    return _RecordLabel.fetchRecordLabel(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static RecordLabel fetchRecordLabel(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<RecordLabel> eoObjects = _RecordLabel.fetchRecordLabels(editingContext, qualifier, null);
    RecordLabel eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (RecordLabel)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one RecordLabel that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static RecordLabel fetchRequiredRecordLabel(EOEditingContext editingContext, String keyName, Object value) {
    return _RecordLabel.fetchRequiredRecordLabel(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static RecordLabel fetchRequiredRecordLabel(EOEditingContext editingContext, EOQualifier qualifier) {
    RecordLabel eoObject = _RecordLabel.fetchRecordLabel(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no RecordLabel that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static RecordLabel localInstanceIn(EOEditingContext editingContext, RecordLabel eo) {
    RecordLabel localInstance = (eo == null) ? null : (RecordLabel)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
