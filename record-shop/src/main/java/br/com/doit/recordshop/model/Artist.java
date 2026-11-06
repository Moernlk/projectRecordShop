package br.com.doit.recordshop.model;

import java.time.LocalDate;

import org.apache.log4j.Logger;

import com.webobjects.foundation.NSTimestamp;

public class Artist extends _Artist {
  private static Logger log = Logger.getLogger(Artist.class);
  
//  @Override
//  public LocalDate birthday() {
//      Object value = storedValueForKey(BIRTHDAY_KEY);
//      if (value instanceof NSTimestamp) {
//          return ((NSTimestamp) value).toLocalDateTime().toLocalDate();
//      }
//      return (LocalDate) value;
//  }
//
//  @Override
//  public void setBirthday(LocalDate value) {
//      takeStoredValueForKey(
//          value == null ? null : new NSTimestamp(java.sql.Timestamp.valueOf(value.atStartOfDay())),
//          BIRTHDAY_KEY);
//  }
}
