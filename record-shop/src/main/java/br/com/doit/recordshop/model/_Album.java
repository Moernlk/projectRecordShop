// $LastChangedRevision$ DO NOT EDIT.  Make changes to Album.java instead.
package br.com.doit.recordshop.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

@SuppressWarnings("all")
public abstract class _Album extends  EOGenericRecord {
	public static final String ENTITY_NAME = "Album";

	// Attributes
	public static final String NAME_KEY = "name";
	public static final String RELEASE_DATE_KEY = "releaseDate";

	// Relationships
	public static final String ARTIST_KEY = "artist";

  private static Logger LOG = Logger.getLogger(_Album.class);

  public Album localInstanceIn(EOEditingContext editingContext) {
    Album localInstance = (Album)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String name() {
    return (String) storedValueForKey("name");
  }

  public void setName(String value) {
    if (_Album.LOG.isDebugEnabled()) {
    	_Album.LOG.debug( "updating name from " + name() + " to " + value);
    }
    takeStoredValueForKey(value, "name");
  }

  public NSTimestamp releaseDate() {
    return (NSTimestamp) storedValueForKey("releaseDate");
  }

  public void setReleaseDate(NSTimestamp value) {
    if (_Album.LOG.isDebugEnabled()) {
    	_Album.LOG.debug( "updating releaseDate from " + releaseDate() + " to " + value);
    }
    takeStoredValueForKey(value, "releaseDate");
  }

  public br.com.doit.recordshop.model.Artist artist() {
    return (br.com.doit.recordshop.model.Artist)storedValueForKey("artist");
  }

  public void setArtistRelationship(br.com.doit.recordshop.model.Artist value) {
    if (_Album.LOG.isDebugEnabled()) {
      _Album.LOG.debug("updating artist from " + artist() + " to " + value);
    }
    if (value == null) {
    	br.com.doit.recordshop.model.Artist oldValue = artist();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, "artist");
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, "artist");
    }
  }
  

  public static Album createAlbum(EOEditingContext editingContext, String name
, NSTimestamp releaseDate
, br.com.doit.recordshop.model.Artist artist) {
    Album eo = (Album) EOUtilities.createAndInsertInstance(editingContext, _Album.ENTITY_NAME);    
		eo.setName(name);
		eo.setReleaseDate(releaseDate);
    eo.setArtistRelationship(artist);
    return eo;
  }

  public static NSArray<Album> fetchAllAlbums(EOEditingContext editingContext) {
    return _Album.fetchAllAlbums(editingContext, null);
  }

  public static NSArray<Album> fetchAllAlbums(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Album.fetchAlbums(editingContext, null, sortOrderings);
  }

  public static NSArray<Album> fetchAlbums(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    EOFetchSpecification fetchSpec = new EOFetchSpecification(_Album.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Album> eoObjects = (NSArray<Album>)editingContext.objectsWithFetchSpecification(fetchSpec);
    return eoObjects;
  }

  public static Album fetchAlbum(EOEditingContext editingContext, String keyName, Object value) {
    return _Album.fetchAlbum(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Album fetchAlbum(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Album> eoObjects = _Album.fetchAlbums(editingContext, qualifier, null);
    Album eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = (Album)eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Album that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Album fetchRequiredAlbum(EOEditingContext editingContext, String keyName, Object value) {
    return _Album.fetchRequiredAlbum(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Album fetchRequiredAlbum(EOEditingContext editingContext, EOQualifier qualifier) {
    Album eoObject = _Album.fetchAlbum(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Album that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Album localInstanceIn(EOEditingContext editingContext, Album eo) {
    Album localInstance = (eo == null) ? null : (Album)EOUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
