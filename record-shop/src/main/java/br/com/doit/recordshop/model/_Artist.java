// $LastChangedRevision$ DO NOT EDIT.  Make changes to Artist.java instead.
package br.com.doit.recordshop.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _Artist extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Artist";

	// Attributes
	public static final String BIRTHDAY_KEY = "birthday";
	public static final String COUNTRY_KEY = "country";
	public static final String MAIN_GENRE_KEY = "mainGenre";
	public static final String NAME_KEY = "name";
	public static final String SECONDARY_GENRE_KEY = "secondaryGenre";

	// Relationships
	public static final String ALBUMS_KEY = "albums";
	public static final String RECORD_LABEL_KEY = "recordLabel";

  private static Logger LOG = Logger.getLogger(_Artist.class);

  public Artist localInstanceIn(EOEditingContext editingContext) {
    Artist localInstance = (Artist)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public NSTimestamp birthday() {
    return (NSTimestamp) storedValueForKey("birthday");
  }

  public void setBirthday(NSTimestamp value) {
    if (_Artist.LOG.isDebugEnabled()) {
    	_Artist.LOG.debug( "updating birthday from " + birthday() + " to " + value);
    }
    takeStoredValueForKey(value, "birthday");
  }

  public String country() {
    return (String) storedValueForKey("country");
  }

  public void setCountry(String value) {
    if (_Artist.LOG.isDebugEnabled()) {
    	_Artist.LOG.debug( "updating country from " + country() + " to " + value);
    }
    takeStoredValueForKey(value, "country");
  }

  public br.com.doit.recordshop.model.Genre mainGenre() {
    return (br.com.doit.recordshop.model.Genre) storedValueForKey("mainGenre");
  }

  public void setMainGenre(br.com.doit.recordshop.model.Genre value) {
    if (_Artist.LOG.isDebugEnabled()) {
    	_Artist.LOG.debug( "updating mainGenre from " + mainGenre() + " to " + value);
    }
    takeStoredValueForKey(value, "mainGenre");
  }

  public String name() {
    return (String) storedValueForKey("name");
  }

  public void setName(String value) {
    if (_Artist.LOG.isDebugEnabled()) {
    	_Artist.LOG.debug( "updating name from " + name() + " to " + value);
    }
    takeStoredValueForKey(value, "name");
  }

  public br.com.doit.recordshop.model.Genre secondaryGenre() {
    return (br.com.doit.recordshop.model.Genre) storedValueForKey("secondaryGenre");
  }

  public void setSecondaryGenre(br.com.doit.recordshop.model.Genre value) {
    if (_Artist.LOG.isDebugEnabled()) {
    	_Artist.LOG.debug( "updating secondaryGenre from " + secondaryGenre() + " to " + value);
    }
    takeStoredValueForKey(value, "secondaryGenre");
  }

  public br.com.doit.recordshop.model.RecordLabel recordLabel() {
    return (br.com.doit.recordshop.model.RecordLabel)storedValueForKey("recordLabel");
  }

  public void setRecordLabelRelationship(br.com.doit.recordshop.model.RecordLabel value) {
    if (_Artist.LOG.isDebugEnabled()) {
      _Artist.LOG.debug("updating recordLabel from " + recordLabel() + " to " + value);
    }
    if (value == null) {
    	br.com.doit.recordshop.model.RecordLabel oldValue = recordLabel();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "recordLabel");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "recordLabel");
    }
  }
  
  public NSArray<br.com.doit.recordshop.model.Album> albums() {
    return (NSArray<br.com.doit.recordshop.model.Album>)storedValueForKey("albums");
  }

  public NSArray<br.com.doit.recordshop.model.Album> albums(EOQualifier qualifier) {
    return albums(qualifier, null, false);
  }

  public NSArray<br.com.doit.recordshop.model.Album> albums(EOQualifier qualifier, boolean fetch) {
    return albums(qualifier, null, fetch);
  }

  public NSArray<br.com.doit.recordshop.model.Album> albums(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<br.com.doit.recordshop.model.Album> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(br.com.doit.recordshop.model.Album.ARTIST_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray qualifiers = new NSMutableArray();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = br.com.doit.recordshop.model.Album.fetchAlbums(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = albums();
      if (qualifier != null) {
        results = (NSArray<br.com.doit.recordshop.model.Album>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<br.com.doit.recordshop.model.Album>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToAlbumsRelationship(br.com.doit.recordshop.model.Album object) {
    if (_Artist.LOG.isDebugEnabled()) {
      _Artist.LOG.debug("adding " + object + " to albums relationship");
    }
    addObjectToBothSidesOfRelationshipWithKey(object, "albums");
  }

  public void removeFromAlbumsRelationship(br.com.doit.recordshop.model.Album object) {
    if (_Artist.LOG.isDebugEnabled()) {
      _Artist.LOG.debug("removing " + object + " from albums relationship");
    }
    removeObjectFromBothSidesOfRelationshipWithKey(object, "albums");
  }

  public br.com.doit.recordshop.model.Album createAlbumsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName("Album");
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, "albums");
    return (br.com.doit.recordshop.model.Album) eo;
  }

  public void deleteAlbumsRelationship(br.com.doit.recordshop.model.Album object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, "albums");
    editingContext().deleteObject(object);
  }

  public void deleteAllAlbumsRelationships() {
    Enumeration objects = albums().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteAlbumsRelationship((br.com.doit.recordshop.model.Album)objects.nextElement());
    }
  }


  public static Artist createArtist(EOEditingContext editingContext, NSTimestamp birthday
, String country
, br.com.doit.recordshop.model.Genre mainGenre
, String name
) {
    Artist eo = (Artist) EOUtilities.createAndInsertInstance(editingContext, _Artist.ENTITY_NAME);    
		eo.setBirthday(birthday);
		eo.setCountry(country);
		eo.setMainGenre(mainGenre);
		eo.setName(name);
    return eo;
  }

  public static NSArray<Artist> fetchAllArtists(EOEditingContext editingContext) {
    return _Artist.fetchAllArtists(editingContext, null);
  }

  public static NSArray<Artist> fetchAllArtists(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Artist.fetchArtists(editingContext, null, sortOrderings);
  }

  public static NSArray<Artist> fetchArtists(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Artist.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Artist> eoObjects = (NSArray<Artist>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Artist fetchArtist(EOEditingContext editingContext, String keyName, Object value) {
    return _Artist.fetchArtist(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Artist fetchArtist(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Artist> eoObjects = _Artist.fetchArtists(editingContext, qualifier, null);
    Artist eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Artist)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Artist that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Artist fetchRequiredArtist(EOEditingContext editingContext, String keyName, Object value) {
    return _Artist.fetchRequiredArtist(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Artist fetchRequiredArtist(EOEditingContext editingContext, EOQualifier qualifier) {
    Artist eoObject = _Artist.fetchArtist(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Artist that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Artist localInstanceIn(EOEditingContext editingContext, Artist eo) {
    Artist localInstance = (eo == null) ? null : (Artist)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
