package br.com.doit.recordshop.migration;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.jdbc.ERXSQLHelper.ColumnIndex;
import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationIndex;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class RecordShopModel2 extends ERXMigrationDatabase.Migration {
	@Override
	public NSArray<ERXModelVersion> modelDependencies() {
		return null;
	}
  
	@Override
	public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		// DO NOTHING
	}

	@Override
	public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		ERXMigrationTable albumTable = database.newTableNamed("album");
		albumTable.newIntegerColumn("artist_id", NOT_NULL);
		albumTable.newIntegerColumn("id", NOT_NULL);
		albumTable.newLargeStringColumn("name", NOT_NULL);
		albumTable.newTimestampColumn("release_date", NOT_NULL);
		albumTable.create();
	 	albumTable.setPrimaryKey("id");
	 	
		albumTable.addForeignKey("artist_id", "artist", "id");
	}
}