databaseChangeLog = {

	changeSet(author: "Андрейка (generated)", id: "1429724923444-1") {
		dropNotNullConstraint(columnDataType: "varchar(9)", columnName: "KPP", tableName: "ACCOUNT")
	}
}
