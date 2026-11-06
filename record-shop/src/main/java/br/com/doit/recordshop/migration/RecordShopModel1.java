package br.com.doit.recordshop.migration;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.jdbc.ERXSQLHelper.ColumnIndex;
import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationIndex;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class RecordShopModel1 extends ERXMigrationDatabase.Migration {
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
		ERXMigrationTable artistTable = database.existingTableNamed("artist");
		artistTable.newTimestampColumn("birthday", NOT_NULL);
		artistTable.newLargeStringColumn("country", NOT_NULL);
		artistTable.newStringColumn("main_genre", 50, NOT_NULL);
		artistTable.newLargeStringColumn("name", NOT_NULL);
		artistTable.newIntegerColumn("record_label_id", ALLOWS_NULL);
		artistTable.newStringColumn("secondary_genre", 50, NOT_NULL);
		
		ERXMigrationTable recordLabelTable = database.existingTableNamed("record_label");
		artistTable.addForeignKey("record_label_id", "record_label", "id");
	}
}