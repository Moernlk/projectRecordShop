package br.com.doit.recordshop.migration;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.jdbc.ERXSQLHelper.ColumnIndex;
import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationIndex;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class RecordShopModel0 extends ERXMigrationDatabase.Migration {
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
		ERXMigrationTable artistTable = database.newTableNamed("Artist");
		artistTable.newIntegerColumn("id", NOT_NULL);
		artistTable.create();
	 	artistTable.setPrimaryKey("id");

		ERXMigrationTable recordLabelTable = database.newTableNamed("Record_Label");
		recordLabelTable.newStringColumn("country", 10, NOT_NULL);
		recordLabelTable.newIntegerColumn("id", NOT_NULL);
		recordLabelTable.newStringColumn("name", 100, NOT_NULL);
		recordLabelTable.create();
	 	recordLabelTable.setPrimaryKey("id");

	}
}