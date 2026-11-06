package br.com.doit.recordshop.migration;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.migration.ERXMigrationColumn;
import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class RecordShopModel3 extends ERXMigrationDatabase.Migration {

	@Override
	public NSArray<ERXModelVersion> modelDependencies() {
		return null;
	}

	@Override
	public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		ERXMigrationTable artistTable = database.existingTableNamed("artist");
		ERXMigrationColumn secondaryGenreColumn = artistTable.existingColumnNamed("secondary_genre");
		
		// Modifica a coluna para aceitar valores nulos (NULL)
		secondaryGenreColumn.setAllowsNull(true);
	}

	@Override
	public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		// TODO Auto-generated method stub
		
	}

}
