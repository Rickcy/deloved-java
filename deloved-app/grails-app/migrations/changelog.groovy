databaseChangeLog = {

	changeSet(author: "Андрейка (generated)", id: "1429724618119-1") {
		createTable(tableName: "ACCOUNT") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_E")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ADDRESS", type: "VARCHAR(255)")

			column(name: "BRAND_NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "CITY_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "VARCHAR(1500)")

			column(name: "EMAIL", type: "VARCHAR(255)")

			column(name: "FAX1", type: "VARCHAR(255)")

			column(name: "FAX2", type: "VARCHAR(255)")

			column(name: "FULL_NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "INN", type: "VARCHAR(12)") {
				constraints(nullable: "false")
			}

			column(name: "KEYWORDS", type: "VARCHAR(255)")

			column(name: "KPP", type: "VARCHAR(9)") {
				constraints(nullable: "false")
			}

			column(name: "LAST_UPDATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "LEGAL_ADDRESS", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "LOGO_ID", type: "BIGINT")

			column(name: "LOGO_THUMB_ID", type: "BIGINT")

			column(name: "MANAGER", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "ORG_FORM_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "PHONE1", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "PHONE2", type: "VARCHAR(255)")

			column(name: "PHONE3", type: "VARCHAR(255)")

			column(name: "PUBLIC_STATUS", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "RATING", type: "INT")

			column(name: "REG_DATE", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "REG_NUMBER", type: "VARCHAR(15)") {
				constraints(nullable: "false")
			}

			column(name: "VERIFY_STATUS", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "WEB_ADDRESS", type: "VARCHAR(255)")

			column(name: "WORK_TIME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-2") {
		createTable(tableName: "ACCOUNT_AFFILIATE") {
			column(name: "ACCOUNT_AFFILIATES_ID", type: "BIGINT")

			column(name: "AFFILIATE_ID", type: "BIGINT")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-3") {
		createTable(tableName: "ACCOUNT_CATEGORY") {
			column(name: "ACCOUNT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CATEGORY_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-4") {
		createTable(tableName: "ACCOUNT_DEAL_RATING") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_3")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "BAD_CLAIM", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "BAD_DISPUTE", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "BAD_REVIEW", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "DEAL_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DEAL_FAIL", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "DEAL_SUCCESS", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "ENABLE", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-5") {
		createTable(tableName: "ACCOUNT_PROFILE") {
			column(name: "ACCOUNT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-6") {
		createTable(tableName: "ACCOUNT_STAT") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_CC")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "MONTH_VIEW_ACCOUNT", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "MONTH_VIEW_GOODS", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "MONTH_VIEW_SERVICES", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "TOTAL_VIEW_ACCOUNT", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "TOTAL_VIEW_GOODS", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "TOTAL_VIEW_SERVICES", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-7") {
		createTable(tableName: "ADCONTENT") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_A")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "APPROVED", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "VARCHAR(1500)")

			column(name: "FILE_ID", type: "BIGINT")

			column(name: "FILE_THUMB_ID", type: "BIGINT")

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "TYPE", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "URL", type: "VARCHAR(1000)")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-8") {
		createTable(tableName: "AFFILIATE") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_4")
			}

			column(name: "ADDRESS", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "CITY_ID", type: "BIGINT")

			column(name: "EMAIL", type: "VARCHAR(255)")

			column(name: "PHONE", type: "VARCHAR(255)")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-9") {
		createTable(tableName: "ATTACHMENT") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_A7")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "FILE", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "MIME_TYPE", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "OWNER_ID", type: "BIGINT")

			column(name: "SIZE", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-10") {
		createTable(tableName: "CATEGORY") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_31")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "PARENT_ID", type: "BIGINT")

			column(name: "CATEGORYTYPE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-11") {
		createTable(tableName: "CATEGORY_TYPE") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_CF")
			}

			column(name: "CODE", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-12") {
		createTable(tableName: "CLAIM") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_3D")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "DEAL_ID", type: "BIGINT")

			column(name: "FAILED_BY_ID", type: "BIGINT")

			column(name: "JUDGE_ID", type: "BIGINT")

			column(name: "PARTNER_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "STATUS", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-13") {
		createTable(tableName: "CLAIM_LAST_VISIT") {
			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CLAIM_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "VISITED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-14") {
		createTable(tableName: "CLAIM_POST") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_F")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT")

			column(name: "CLAIM_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "PERSON_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "POST", type: "VARCHAR(1500)")

			column(name: "STATUS", type: "INT")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-15") {
		createTable(tableName: "CLAIM_POST_ATTACH") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_C6")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT")

			column(name: "ATTACHMENT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CLAIM_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CLAIM_POST_ID", type: "BIGINT")

			column(name: "PERSON_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-16") {
		createTable(tableName: "CONSULT") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_6")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "JURIST_ID", type: "BIGINT")

			column(name: "STATUS", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-17") {
		createTable(tableName: "CONSULT_LAST_VISIT") {
			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CONSULT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "VISITED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-18") {
		createTable(tableName: "CONSULT_POST") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_3D7")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT")

			column(name: "CONSULT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "PERSON_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "POST", type: "VARCHAR(1500)")

			column(name: "STATUS", type: "INT")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-19") {
		createTable(tableName: "CONSULT_POST_ATTACH") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_5")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT")

			column(name: "ATTACHMENT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CONSULT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CONSULT_POST_ID", type: "BIGINT")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-20") {
		createTable(tableName: "CONTACT_CONFIRM") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_B")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CONTACT", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "LAST_UPDATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "SECRET", type: "VARCHAR(255)")

			column(name: "STATUS", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "TYPE", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-21") {
		createTable(tableName: "CONTENT") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_63")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CODE", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "CONTENT", type: "VARCHAR(5000)") {
				constraints(nullable: "false")
			}

			column(name: "ENABLED", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-22") {
		createTable(tableName: "COUNTRY_DEFAULTS") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_ED")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "COUNTRY_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CURRENCY_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-23") {
		createTable(tableName: "DEAL") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_1")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "FAILED_BY_ID", type: "BIGINT")

			column(name: "PARTNER_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "STATUS", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-24") {
		createTable(tableName: "DEAL_LAST_VISIT") {
			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DEAL_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "VISITED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-25") {
		createTable(tableName: "DEAL_POST") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_57")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT")

			column(name: "CLAIM_ID", type: "BIGINT")

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "DEAL_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DISPUTE_ID", type: "BIGINT")

			column(name: "PERSON_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "POST", type: "VARCHAR(1500)")

			column(name: "STATUS", type: "INT")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-26") {
		createTable(tableName: "DEAL_POST_ATTACH") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_4D")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ATTACHMENT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DEAL_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DEAL_POST_ID", type: "BIGINT")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-27") {
		createTable(tableName: "DISPUTE") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_8E")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "DEAL_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "FAILED_BY_ID", type: "BIGINT")

			column(name: "MEDIATOR_ID", type: "BIGINT")

			column(name: "PARTNER_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "STATUS", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-28") {
		createTable(tableName: "DISPUTE_LAST_VISIT") {
			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DISPUTE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "VISITED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-29") {
		createTable(tableName: "DISPUTE_POST") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_5E")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT")

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "DISPUTE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "PERSON_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "POST", type: "VARCHAR(1500)")

			column(name: "STATUS", type: "INT")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-30") {
		createTable(tableName: "DISPUTE_POST_ATTACH") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_9")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT")

			column(name: "ATTACHMENT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DISPUTE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DISPUTE_POST_ID", type: "BIGINT")

			column(name: "PERSON_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-31") {
		createTable(tableName: "DOCUMENT") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_62")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ATTACHMENT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CATEGORY_ID", type: "BIGINT")

			column(name: "DESCRIPTION", type: "VARCHAR(1500)")

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-32") {
		createTable(tableName: "DOCUMENT_CATEGORY") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_19")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-33") {
		createTable(tableName: "DOCUMENT_INCOME") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_91")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "KEEPER_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "LAST_UPDATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "OPERATION_ID", type: "BIGINT")

			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "REQUEST_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "STATUS", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "VALUE", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-34") {
		createTable(tableName: "DOCUMENT_INVOICE") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_A71")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "KEEPER_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "LAST_UPDATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "OPERATION_ID", type: "BIGINT")

			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "STATUS", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "TARIFF_PRICE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "VALUE", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-35") {
		createTable(tableName: "ITEM") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_2")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "AVAILABILITY", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "CATEGORY_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CATEGORY_TYPE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CURRENCY_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "VARCHAR(1500)") {
				constraints(nullable: "false")
			}

			column(name: "KIND", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "MEASURE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "MEASURE_VALUE", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "PHOTO_ID", type: "BIGINT")

			column(name: "PRICE", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-36") {
		createTable(tableName: "ITEM_ATTACH") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_E7")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "IMAGE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "IMAGE_THUMB_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ITEM_ID", type: "BIGINT")

			column(name: "OWNER_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-37") {
		createTable(tableName: "KEEPER") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_83")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "BALANCE", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "CURRENCY_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "NUMBER", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-38") {
		createTable(tableName: "MEASURE") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_62B")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "FULLNAME", type: "VARCHAR(255)")

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "TYPE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-39") {
		createTable(tableName: "OPERATION") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_93")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "DOCUMENT", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "INIT_BALANCE", type: "DECIMAL(19,2)")

			column(name: "KEEPER_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "RESULT_BALANCE", type: "DECIMAL(19,2)")

			column(name: "TYPE", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "VALUE", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-40") {
		createTable(tableName: "ORG_FORM") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_7")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CODE", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-41") {
		createTable(tableName: "PAYMENT_METHOD") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_DD")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CODE", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "CURRENCY_ID", type: "BIGINT")

			column(name: "ENABLED", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "INCOME", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "OUTCOME", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "SYSTEM_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-42") {
		createTable(tableName: "PAYMENT_REQUEST") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_D9")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "KEEPER_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "LAST_UPDATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "METHOD_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "STATUS", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "VALUE", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-43") {
		createTable(tableName: "PAYMENT_SYSTEM") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_E8")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-44") {
		createTable(tableName: "PROFILE") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_18")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "AVATAR_ID", type: "BIGINT")

			column(name: "AVATAR_THUMB_ID", type: "BIGINT")

			column(name: "CELL_PHONE", type: "VARCHAR(20)")

			column(name: "CHARGE_STATUS", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "CHARGE_TILL", type: "TIMESTAMP")

			column(name: "CITY_ID", type: "BIGINT")

			column(name: "DATE_ACTIVITY", type: "TIMESTAMP")

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "EMAIL", type: "VARCHAR(50)")

			column(name: "FIO", type: "VARCHAR(50)")

			column(name: "USER_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-45") {
		createTable(tableName: "PROFILE_NEW_CLAIMS") {
			column(name: "CLAIM_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-46") {
		createTable(tableName: "PROFILE_NEW_CONSULTS") {
			column(name: "CONSULT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-47") {
		createTable(tableName: "PROFILE_NEW_DEALS") {
			column(name: "DEAL_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-48") {
		createTable(tableName: "PROFILE_NEW_DISPUTES") {
			column(name: "DISPUTE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-49") {
		createTable(tableName: "PROFILE_NEW_REVIEWS") {
			column(name: "REVIEW_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-50") {
		createTable(tableName: "PROFILE_REGION") {
			column(name: "PROFILE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "REGION_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-51") {
		createTable(tableName: "REGION") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_8F")
			}

			column(name: "FULL_NAME", type: "VARCHAR(255)")

			column(name: "LEVEL_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "PARENT_ID", type: "BIGINT")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-52") {
		createTable(tableName: "REGION_LEVEL") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_73")
			}

			column(name: "LEVEL", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "PARENT_ID", type: "BIGINT")

			column(name: "TYPE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-53") {
		createTable(tableName: "REGION_TYPE") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_24")
			}

			column(name: "CODE", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-54") {
		createTable(tableName: "REVIEW") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_8FE")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "AUTHOR_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CONTENT", type: "VARCHAR(1500)") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "DATE_PUBLISHED", type: "TIMESTAMP")

			column(name: "DEAL_ID", type: "BIGINT")

			column(name: "FINISHED", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "FROM_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "PUBLISHED", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "TO_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "VALUE", type: "INT")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-55") {
		createTable(tableName: "REVIEW_ATTACH") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_7F")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ACCOUNT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ATTACHMENT_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "REVIEW_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-56") {
		createTable(tableName: "ROLE") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_26")
			}

			column(name: "AUTHORITY", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-57") {
		createTable(tableName: "SYSTEM_CURRENCY") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_75")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CODE", type: "VARCHAR(3)") {
				constraints(nullable: "false")
			}

			column(name: "DIGIT3", type: "VARCHAR(3)") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-58") {
		createTable(tableName: "TARIFF_PRICE") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_60")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CURRENCY_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DAYS", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "MONTHS", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "PRICE", type: "DECIMAL(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "WEEKS", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "YEARS", type: "INT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-59") {
		createTable(tableName: "USERS") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_4D4")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DATE_CREATED", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "ENABLED", type: "BOOLEAN") {
				constraints(nullable: "false")
			}

			column(name: "PASSWORD", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "ROLE_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "ROLE_REQUEST_ID", type: "BIGINT")

			column(name: "USERNAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-60") {
		addPrimaryKey(columnNames: "ACCOUNT_ID, CATEGORY_ID", constraintName: "CONSTRAINT_C", tableName: "ACCOUNT_CATEGORY")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-61") {
		addPrimaryKey(columnNames: "ACCOUNT_ID, PROFILE_ID", constraintName: "CONSTRAINT_E3", tableName: "ACCOUNT_PROFILE")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-62") {
		addPrimaryKey(columnNames: "PROFILE_ID, CLAIM_ID", constraintName: "CONSTRAINT_8", tableName: "CLAIM_LAST_VISIT")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-63") {
		addPrimaryKey(columnNames: "PROFILE_ID, CONSULT_ID", constraintName: "CONSTRAINT_A3", tableName: "CONSULT_LAST_VISIT")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-64") {
		addPrimaryKey(columnNames: "PROFILE_ID, DEAL_ID", constraintName: "CONSTRAINT_B3", tableName: "DEAL_LAST_VISIT")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-65") {
		addPrimaryKey(columnNames: "PROFILE_ID, DISPUTE_ID", constraintName: "CONSTRAINT_D", tableName: "DISPUTE_LAST_VISIT")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-66") {
		addPrimaryKey(columnNames: "PROFILE_ID, CLAIM_ID", constraintName: "CONSTRAINT_C7", tableName: "PROFILE_NEW_CLAIMS")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-67") {
		addPrimaryKey(columnNames: "PROFILE_ID, CONSULT_ID", constraintName: "CONSTRAINT_EE", tableName: "PROFILE_NEW_CONSULTS")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-68") {
		addPrimaryKey(columnNames: "PROFILE_ID, DEAL_ID", constraintName: "CONSTRAINT_D4", tableName: "PROFILE_NEW_DEALS")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-69") {
		addPrimaryKey(columnNames: "PROFILE_ID, DISPUTE_ID", constraintName: "CONSTRAINT_21", tableName: "PROFILE_NEW_DISPUTES")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-70") {
		addPrimaryKey(columnNames: "PROFILE_ID, REVIEW_ID", constraintName: "CONSTRAINT_319", tableName: "PROFILE_NEW_REVIEWS")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-71") {
		addPrimaryKey(columnNames: "PROFILE_ID, REGION_ID", constraintName: "CONSTRAINT_EF", tableName: "PROFILE_REGION")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-210") {
		createIndex(indexName: "UK_1RU6HW2LPJ6BGM2WVFAPVFL53_INDEX_E", tableName: "ACCOUNT", unique: "true") {
			column(name: "INN")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-211") {
		createIndex(indexName: "UK_2A77QTLL93W15XUJJ1LJDTL2I_INDEX_C", tableName: "CATEGORY_TYPE", unique: "true") {
			column(name: "CODE")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-212") {
		createIndex(indexName: "UK_TLT8F6IR3C2WJWIPDGJU7JXYB_INDEX_8", tableName: "KEEPER", unique: "true") {
			column(name: "NUMBER")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-213") {
		createIndex(indexName: "UK_Q4MD6BBGVVHYQ6SAJP0UAWD3A_INDEX_7", tableName: "ORG_FORM", unique: "true") {
			column(name: "CODE")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-214") {
		createIndex(indexName: "UK_OVLVUN6VT07RMW2YPL4A0C6ME_INDEX_2", tableName: "REGION_TYPE", unique: "true") {
			column(name: "CODE")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-215") {
		createIndex(indexName: "UK_IRSAMGNERA6ANGM0PRQ1KEMT2_INDEX_2", tableName: "ROLE", unique: "true") {
			column(name: "AUTHORITY")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-216") {
		createIndex(indexName: "UK_R43AF9AP4EDM43MMTQ01ODDJ6_INDEX_4", tableName: "USERS", unique: "true") {
			column(name: "USERNAME")
		}
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-72") {
		addForeignKeyConstraint(baseColumnNames: "CITY_ID", baseTableName: "ACCOUNT", baseTableSchemaName: "PUBLIC", constraintName: "FK_IKRLWL56WXCJMOS3NIBPB03IT", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "REGION", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-73") {
		addForeignKeyConstraint(baseColumnNames: "LOGO_ID", baseTableName: "ACCOUNT", baseTableSchemaName: "PUBLIC", constraintName: "FK_659UCW4KH0UUTXIUAII0YUKUV", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-74") {
		addForeignKeyConstraint(baseColumnNames: "LOGO_THUMB_ID", baseTableName: "ACCOUNT", baseTableSchemaName: "PUBLIC", constraintName: "FK_2UL12Y7Y7JWTJ2F7IJ8OIUHOR", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-75") {
		addForeignKeyConstraint(baseColumnNames: "ORG_FORM_ID", baseTableName: "ACCOUNT", baseTableSchemaName: "PUBLIC", constraintName: "FK_56PB8MTN3NCYTW7AYRHWL0I5E", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ORG_FORM", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-76") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_AFFILIATES_ID", baseTableName: "ACCOUNT_AFFILIATE", baseTableSchemaName: "PUBLIC", constraintName: "FK_G0CQHGR9STFGH2QEBKW8X7VR1", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-77") {
		addForeignKeyConstraint(baseColumnNames: "AFFILIATE_ID", baseTableName: "ACCOUNT_AFFILIATE", baseTableSchemaName: "PUBLIC", constraintName: "FK_9G7OCB5XKAB1SHFD6YXPMUAF7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "AFFILIATE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-78") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "ACCOUNT_CATEGORY", baseTableSchemaName: "PUBLIC", constraintName: "FK_4FPMOPI5J5EUWE1TOD39JPV41", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-79") {
		addForeignKeyConstraint(baseColumnNames: "CATEGORY_ID", baseTableName: "ACCOUNT_CATEGORY", baseTableSchemaName: "PUBLIC", constraintName: "FK_80VXVXAQ4IHW2FQOHOQYK8841", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CATEGORY", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-80") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "ACCOUNT_DEAL_RATING", baseTableSchemaName: "PUBLIC", constraintName: "FK_GOYH7QC1C91LD2OFUHWCDCWOS", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-81") {
		addForeignKeyConstraint(baseColumnNames: "DEAL_ID", baseTableName: "ACCOUNT_DEAL_RATING", baseTableSchemaName: "PUBLIC", constraintName: "FK_K09H8F5SKMLB7XKQT9R54GD9U", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DEAL", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-82") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "ACCOUNT_PROFILE", baseTableSchemaName: "PUBLIC", constraintName: "FK_PFM098MQLIGO1WPD1403E4240", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-83") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "ACCOUNT_PROFILE", baseTableSchemaName: "PUBLIC", constraintName: "FK_K81L0BNJMX2AIF52FEBO38DC7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-84") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "ACCOUNT_STAT", baseTableSchemaName: "PUBLIC", constraintName: "FK_B0XHFFN3J7A8TKP9CHO35KBSH", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-85") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "ADCONTENT", baseTableSchemaName: "PUBLIC", constraintName: "FK_DBXD6AB0FT7EP2WVWKS96RT5S", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-86") {
		addForeignKeyConstraint(baseColumnNames: "FILE_ID", baseTableName: "ADCONTENT", baseTableSchemaName: "PUBLIC", constraintName: "FK_CAR0MHBMVBDAVP918QGKIDLUH", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-87") {
		addForeignKeyConstraint(baseColumnNames: "FILE_THUMB_ID", baseTableName: "ADCONTENT", baseTableSchemaName: "PUBLIC", constraintName: "FK_C6B5WDYACL7Y0O7CGQTGD2GVD", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-88") {
		addForeignKeyConstraint(baseColumnNames: "CITY_ID", baseTableName: "AFFILIATE", baseTableSchemaName: "PUBLIC", constraintName: "FK_WC8TUHSVX4PP85U5B07GJVKU", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "REGION", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-89") {
		addForeignKeyConstraint(baseColumnNames: "OWNER_ID", baseTableName: "ATTACHMENT", baseTableSchemaName: "PUBLIC", constraintName: "FK_6H7409C669SU9MMBWQ7CSL3S5", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-90") {
		addForeignKeyConstraint(baseColumnNames: "CATEGORYTYPE_ID", baseTableName: "CATEGORY", baseTableSchemaName: "PUBLIC", constraintName: "FK_GETUHXDUQQWR84TT1G1FKGWC2", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CATEGORY_TYPE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-91") {
		addForeignKeyConstraint(baseColumnNames: "PARENT_ID", baseTableName: "CATEGORY", baseTableSchemaName: "PUBLIC", constraintName: "FK_81THRBNB8C08GUA7TVQJ7XDQK", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CATEGORY", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-92") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "CLAIM", baseTableSchemaName: "PUBLIC", constraintName: "FK_A7XCDELGBIAH0TAJTBXYR4Y32", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-93") {
		addForeignKeyConstraint(baseColumnNames: "DEAL_ID", baseTableName: "CLAIM", baseTableSchemaName: "PUBLIC", constraintName: "FK_D1O4ACUMBP2RYKKUUU0XS9HOF", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DEAL", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-94") {
		addForeignKeyConstraint(baseColumnNames: "FAILED_BY_ID", baseTableName: "CLAIM", baseTableSchemaName: "PUBLIC", constraintName: "FK_N1W6TIQFMTBK6A2G81H7I2YV", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-95") {
		addForeignKeyConstraint(baseColumnNames: "JUDGE_ID", baseTableName: "CLAIM", baseTableSchemaName: "PUBLIC", constraintName: "FK_8RYY6YNPYS16O0FCLA082VE88", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-96") {
		addForeignKeyConstraint(baseColumnNames: "PARTNER_ID", baseTableName: "CLAIM", baseTableSchemaName: "PUBLIC", constraintName: "FK_4LRWWV6WLE1GXOFI5WBPV0IGF", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-97") {
		addForeignKeyConstraint(baseColumnNames: "CLAIM_ID", baseTableName: "CLAIM_LAST_VISIT", baseTableSchemaName: "PUBLIC", constraintName: "FK_R3LWT6MFJQLAXAVOSK3JTW127", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CLAIM", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-98") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "CLAIM_LAST_VISIT", baseTableSchemaName: "PUBLIC", constraintName: "FK_K3RWX63R40PO5FRWCK4RG4VP9", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-99") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "CLAIM_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_7S457FNPWMBU47DAWQNETBO1I", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-100") {
		addForeignKeyConstraint(baseColumnNames: "CLAIM_ID", baseTableName: "CLAIM_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_1XFDML5CJHOOBE68MSBJHUPIL", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CLAIM", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-101") {
		addForeignKeyConstraint(baseColumnNames: "PERSON_ID", baseTableName: "CLAIM_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_NOMBQB09XA2YSDSNTTDK8J161", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-102") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "CLAIM_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_A0X1TIKTNHUL1LGSO7TDIHXLQ", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-103") {
		addForeignKeyConstraint(baseColumnNames: "ATTACHMENT_ID", baseTableName: "CLAIM_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_28DO8KF79RHFJPOJ9FJQ5VNS4", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-104") {
		addForeignKeyConstraint(baseColumnNames: "CLAIM_ID", baseTableName: "CLAIM_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_6YQ527TUO4PA5QWM84IP7XNNY", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CLAIM", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-105") {
		addForeignKeyConstraint(baseColumnNames: "CLAIM_POST_ID", baseTableName: "CLAIM_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_4JM1RH94TN7NUT3JMPBSX4VH6", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CLAIM_POST", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-106") {
		addForeignKeyConstraint(baseColumnNames: "PERSON_ID", baseTableName: "CLAIM_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_4JAIFIWCALKE5NMO07EJLT2F6", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-107") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "CONSULT", baseTableSchemaName: "PUBLIC", constraintName: "FK_396Y8NYRFMM8T14NE9RYJO7UA", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-108") {
		addForeignKeyConstraint(baseColumnNames: "JURIST_ID", baseTableName: "CONSULT", baseTableSchemaName: "PUBLIC", constraintName: "FK_30AD3EN0KJEYORBF09NM2F5FC", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-109") {
		addForeignKeyConstraint(baseColumnNames: "CONSULT_ID", baseTableName: "CONSULT_LAST_VISIT", baseTableSchemaName: "PUBLIC", constraintName: "FK_7JF6V39D2AIHCOBA415ECG6ER", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CONSULT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-110") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "CONSULT_LAST_VISIT", baseTableSchemaName: "PUBLIC", constraintName: "FK_PY88NGWPG9DUBS2366XNBBDYW", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-111") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "CONSULT_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_HVR53LWH2VVF6P09EQRKKD5YR", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-112") {
		addForeignKeyConstraint(baseColumnNames: "CONSULT_ID", baseTableName: "CONSULT_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_7CQO16808DIIUWCC2HMBNHW1N", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CONSULT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-113") {
		addForeignKeyConstraint(baseColumnNames: "PERSON_ID", baseTableName: "CONSULT_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_9TKHH0Y1DDL5DU20VFKDSN8D5", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-114") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "CONSULT_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_46T1EOBJD2QMELD6L1CSMBJSL", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-115") {
		addForeignKeyConstraint(baseColumnNames: "ATTACHMENT_ID", baseTableName: "CONSULT_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_FIK6058Q5PJVON0FMKK07IMHS", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-116") {
		addForeignKeyConstraint(baseColumnNames: "CONSULT_ID", baseTableName: "CONSULT_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_AXIC5241O7KA4G15VSVVQ6WJ6", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CONSULT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-117") {
		addForeignKeyConstraint(baseColumnNames: "CONSULT_POST_ID", baseTableName: "CONSULT_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_1696JO7506HN24CMC90N10QMH", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CONSULT_POST", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-118") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "CONTACT_CONFIRM", baseTableSchemaName: "PUBLIC", constraintName: "FK_OD2LG5L1I6WNY4YP3CG223C6A", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-119") {
		addForeignKeyConstraint(baseColumnNames: "COUNTRY_ID", baseTableName: "COUNTRY_DEFAULTS", baseTableSchemaName: "PUBLIC", constraintName: "FK_6I6BNA5S29OLB0R98N397JFG5", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "REGION", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-120") {
		addForeignKeyConstraint(baseColumnNames: "CURRENCY_ID", baseTableName: "COUNTRY_DEFAULTS", baseTableSchemaName: "PUBLIC", constraintName: "FK_7S15F8U7QLE3P8DEHMBLO2PK7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SYSTEM_CURRENCY", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-121") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "DEAL", baseTableSchemaName: "PUBLIC", constraintName: "FK_HK3OPI84LH55QLGN4AYET6ME7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-122") {
		addForeignKeyConstraint(baseColumnNames: "FAILED_BY_ID", baseTableName: "DEAL", baseTableSchemaName: "PUBLIC", constraintName: "FK_I4RW2T1LG9493VRN4GQ5171VS", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-123") {
		addForeignKeyConstraint(baseColumnNames: "PARTNER_ID", baseTableName: "DEAL", baseTableSchemaName: "PUBLIC", constraintName: "FK_3AJ3UHQ1BO8H6N5U34S0A7419", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-124") {
		addForeignKeyConstraint(baseColumnNames: "DEAL_ID", baseTableName: "DEAL_LAST_VISIT", baseTableSchemaName: "PUBLIC", constraintName: "FK_SS5VWT3SIVYQV4F928JCUE360", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DEAL", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-125") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "DEAL_LAST_VISIT", baseTableSchemaName: "PUBLIC", constraintName: "FK_A0EY01H3MBEIJROM49HSNNXV0", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-126") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "DEAL_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_FQ9HHE6FFXMQVTG2XEKK6QVL9", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-127") {
		addForeignKeyConstraint(baseColumnNames: "CLAIM_ID", baseTableName: "DEAL_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_LYC4I0I0QTNICL4W5922S0NG3", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CLAIM", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-128") {
		addForeignKeyConstraint(baseColumnNames: "DEAL_ID", baseTableName: "DEAL_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_9P544I7EEOY1DGD5B29CQRGDI", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DEAL", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-129") {
		addForeignKeyConstraint(baseColumnNames: "DISPUTE_ID", baseTableName: "DEAL_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_2V6TBYDDSLAW0WFBJGAFQCEMT", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DISPUTE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-130") {
		addForeignKeyConstraint(baseColumnNames: "PERSON_ID", baseTableName: "DEAL_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_9606O3GWQFLHOAAS7CM92GJD6", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-131") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "DEAL_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_JINQC0YL8SH7VUGNGD1YWM7SI", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-132") {
		addForeignKeyConstraint(baseColumnNames: "ATTACHMENT_ID", baseTableName: "DEAL_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_HB54OSQSDY40UCV4GA3W8PUA1", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-133") {
		addForeignKeyConstraint(baseColumnNames: "DEAL_ID", baseTableName: "DEAL_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_F02YFHBJSBR27HVAG4K8V93WJ", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DEAL", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-134") {
		addForeignKeyConstraint(baseColumnNames: "DEAL_POST_ID", baseTableName: "DEAL_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_S21CCVM0MX6LIY2JI43OVL9OI", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DEAL_POST", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-135") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "DISPUTE", baseTableSchemaName: "PUBLIC", constraintName: "FK_R331D903C4RTE9X1A67JFC1VP", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-136") {
		addForeignKeyConstraint(baseColumnNames: "DEAL_ID", baseTableName: "DISPUTE", baseTableSchemaName: "PUBLIC", constraintName: "FK_DQ32ASLRFB086EDMLV8UX7J23", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DEAL", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-137") {
		addForeignKeyConstraint(baseColumnNames: "FAILED_BY_ID", baseTableName: "DISPUTE", baseTableSchemaName: "PUBLIC", constraintName: "FK_AYYY82EO9QD4C0QYUDBSPNVD3", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-138") {
		addForeignKeyConstraint(baseColumnNames: "MEDIATOR_ID", baseTableName: "DISPUTE", baseTableSchemaName: "PUBLIC", constraintName: "FK_GI9NFHEC55BDWR8UTPAX9LSCI", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-139") {
		addForeignKeyConstraint(baseColumnNames: "PARTNER_ID", baseTableName: "DISPUTE", baseTableSchemaName: "PUBLIC", constraintName: "FK_1PTDV7TEHB3PSOPI1MG5PO8LY", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-140") {
		addForeignKeyConstraint(baseColumnNames: "DISPUTE_ID", baseTableName: "DISPUTE_LAST_VISIT", baseTableSchemaName: "PUBLIC", constraintName: "FK_1QG3OCS076H8COJIG7KDPDPDI", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DISPUTE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-141") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "DISPUTE_LAST_VISIT", baseTableSchemaName: "PUBLIC", constraintName: "FK_VSV7BEB3X072K0GB8239POAM", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-142") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "DISPUTE_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_FRX1BL206F4UUUQ2C5KJDKOOI", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-143") {
		addForeignKeyConstraint(baseColumnNames: "DISPUTE_ID", baseTableName: "DISPUTE_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_BYH24S6EUC07P6UE3ULB0L6K2", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DISPUTE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-144") {
		addForeignKeyConstraint(baseColumnNames: "PERSON_ID", baseTableName: "DISPUTE_POST", baseTableSchemaName: "PUBLIC", constraintName: "FK_PJEIOL1DCCLAR4JWO4HKL1HA1", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-145") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "DISPUTE_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_NOPT1COQA74P3HCR59LNHMKQI", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-146") {
		addForeignKeyConstraint(baseColumnNames: "ATTACHMENT_ID", baseTableName: "DISPUTE_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_KW095DQJAAV18TY63AGTB9Y3F", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-147") {
		addForeignKeyConstraint(baseColumnNames: "DISPUTE_ID", baseTableName: "DISPUTE_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_MHIELQ1K9TN2TLMY5Q7ER0J8Y", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DISPUTE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-148") {
		addForeignKeyConstraint(baseColumnNames: "DISPUTE_POST_ID", baseTableName: "DISPUTE_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_D2MITPCGSWXIGJJQU5FWEO346", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DISPUTE_POST", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-149") {
		addForeignKeyConstraint(baseColumnNames: "PERSON_ID", baseTableName: "DISPUTE_POST_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_AQVWS6GV4SHXQTIVYOTF0M2NC", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-150") {
		addForeignKeyConstraint(baseColumnNames: "ATTACHMENT_ID", baseTableName: "DOCUMENT", baseTableSchemaName: "PUBLIC", constraintName: "FK_2TS6TBD3WF56QCG09PFIEC8LP", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-151") {
		addForeignKeyConstraint(baseColumnNames: "CATEGORY_ID", baseTableName: "DOCUMENT", baseTableSchemaName: "PUBLIC", constraintName: "FK_R0SRL053WUSQRWM07GG0JF4D6", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DOCUMENT_CATEGORY", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-152") {
		addForeignKeyConstraint(baseColumnNames: "KEEPER_ID", baseTableName: "DOCUMENT_INCOME", baseTableSchemaName: "PUBLIC", constraintName: "FK_7ENKWA6IVY3AVLC91WX8687OO", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "KEEPER", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-153") {
		addForeignKeyConstraint(baseColumnNames: "OPERATION_ID", baseTableName: "DOCUMENT_INCOME", baseTableSchemaName: "PUBLIC", constraintName: "FK_G83KOWV7ETDAXE9GYFEGVU7OO", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "OPERATION", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-154") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "DOCUMENT_INCOME", baseTableSchemaName: "PUBLIC", constraintName: "FK_BP7GBP5OJU6IB8W5UR4UWSNXR", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-155") {
		addForeignKeyConstraint(baseColumnNames: "REQUEST_ID", baseTableName: "DOCUMENT_INCOME", baseTableSchemaName: "PUBLIC", constraintName: "FK_F66915X0F8G02OTJ4SBB2G5S9", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PAYMENT_REQUEST", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-156") {
		addForeignKeyConstraint(baseColumnNames: "KEEPER_ID", baseTableName: "DOCUMENT_INVOICE", baseTableSchemaName: "PUBLIC", constraintName: "FK_3A216LDVH7XSF9BJJ0BUB1H17", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "KEEPER", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-157") {
		addForeignKeyConstraint(baseColumnNames: "OPERATION_ID", baseTableName: "DOCUMENT_INVOICE", baseTableSchemaName: "PUBLIC", constraintName: "FK_QHDFLCNB943X9HALC0MIGS29W", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "OPERATION", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-158") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "DOCUMENT_INVOICE", baseTableSchemaName: "PUBLIC", constraintName: "FK_5R9WBVBG5EMI0C905P9VCENJ5", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-159") {
		addForeignKeyConstraint(baseColumnNames: "TARIFF_PRICE_ID", baseTableName: "DOCUMENT_INVOICE", baseTableSchemaName: "PUBLIC", constraintName: "FK_LOHMRDOM15F7TYG2HMBBSRO48", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "TARIFF_PRICE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-160") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "ITEM", baseTableSchemaName: "PUBLIC", constraintName: "FK_B7GRHBSH5SR2IOY8UN4S95DD9", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-161") {
		addForeignKeyConstraint(baseColumnNames: "CATEGORY_ID", baseTableName: "ITEM", baseTableSchemaName: "PUBLIC", constraintName: "FK_O4NR676S6G7EOKRJSMT7UJS2P", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CATEGORY", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-162") {
		addForeignKeyConstraint(baseColumnNames: "CATEGORY_TYPE_ID", baseTableName: "ITEM", baseTableSchemaName: "PUBLIC", constraintName: "FK_EKHWFJJDTEW53QKW9W5NUQ9XX", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CATEGORY_TYPE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-163") {
		addForeignKeyConstraint(baseColumnNames: "CURRENCY_ID", baseTableName: "ITEM", baseTableSchemaName: "PUBLIC", constraintName: "FK_ALCJTYHX849VECXANXVL2UPKD", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SYSTEM_CURRENCY", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-164") {
		addForeignKeyConstraint(baseColumnNames: "MEASURE_ID", baseTableName: "ITEM", baseTableSchemaName: "PUBLIC", constraintName: "FK_3WCFX15RL12FO8OE3X11SO9UF", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "MEASURE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-165") {
		addForeignKeyConstraint(baseColumnNames: "PHOTO_ID", baseTableName: "ITEM", baseTableSchemaName: "PUBLIC", constraintName: "FK_FBTB58IRHVNYAN6OG35O545X7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ITEM_ATTACH", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-166") {
		addForeignKeyConstraint(baseColumnNames: "IMAGE_ID", baseTableName: "ITEM_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_2N6MK3NGSJP8GSSNVJ86IH6QP", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-167") {
		addForeignKeyConstraint(baseColumnNames: "IMAGE_THUMB_ID", baseTableName: "ITEM_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_RSC3RYHNKJTWNGMCGPX7Y2XV0", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-168") {
		addForeignKeyConstraint(baseColumnNames: "ITEM_ID", baseTableName: "ITEM_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_E1JA71GB4NB27K6VO7RJP5412", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ITEM", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-169") {
		addForeignKeyConstraint(baseColumnNames: "OWNER_ID", baseTableName: "ITEM_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_IC1RDY9MIGFVNUSORSQ9E2YMC", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-170") {
		addForeignKeyConstraint(baseColumnNames: "CURRENCY_ID", baseTableName: "KEEPER", baseTableSchemaName: "PUBLIC", constraintName: "FK_SY3YTTTG68JDGSO5EK9EQJAV2", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SYSTEM_CURRENCY", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-171") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "KEEPER", baseTableSchemaName: "PUBLIC", constraintName: "FK_LGCA45S5JQN429O9YS4O83UU7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-172") {
		addForeignKeyConstraint(baseColumnNames: "TYPE_ID", baseTableName: "MEASURE", baseTableSchemaName: "PUBLIC", constraintName: "FK_KFTBBOOHCVT17VT51AMC24295", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CATEGORY_TYPE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-173") {
		addForeignKeyConstraint(baseColumnNames: "KEEPER_ID", baseTableName: "OPERATION", baseTableSchemaName: "PUBLIC", constraintName: "FK_CAD4VARSCRI6DM14WS239RP72", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "KEEPER", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-174") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "OPERATION", baseTableSchemaName: "PUBLIC", constraintName: "FK_BLHG2FQGHNM1XOMMOXLK81U6E", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-175") {
		addForeignKeyConstraint(baseColumnNames: "CURRENCY_ID", baseTableName: "PAYMENT_METHOD", baseTableSchemaName: "PUBLIC", constraintName: "FK_24C3E96GIVGPSAFJNG43M2SQ9", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SYSTEM_CURRENCY", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-176") {
		addForeignKeyConstraint(baseColumnNames: "SYSTEM_ID", baseTableName: "PAYMENT_METHOD", baseTableSchemaName: "PUBLIC", constraintName: "FK_1RHTB56LD4ASLHUWPPGQ41TMG", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PAYMENT_SYSTEM", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-177") {
		addForeignKeyConstraint(baseColumnNames: "KEEPER_ID", baseTableName: "PAYMENT_REQUEST", baseTableSchemaName: "PUBLIC", constraintName: "FK_A4H9OICIBTBG4N883V4UYNQ7M", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "KEEPER", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-178") {
		addForeignKeyConstraint(baseColumnNames: "METHOD_ID", baseTableName: "PAYMENT_REQUEST", baseTableSchemaName: "PUBLIC", constraintName: "FK_56RTU6KKXMLTROJJ2Q01S3JAN", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PAYMENT_METHOD", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-179") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "PAYMENT_REQUEST", baseTableSchemaName: "PUBLIC", constraintName: "FK_6G5MR4VX4HRIXAQUVCHJ5CRR0", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-180") {
		addForeignKeyConstraint(baseColumnNames: "AVATAR_ID", baseTableName: "PROFILE", baseTableSchemaName: "PUBLIC", constraintName: "FK_NEWQPQ3U98OYGGGRJ758OKSOF", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-181") {
		addForeignKeyConstraint(baseColumnNames: "AVATAR_THUMB_ID", baseTableName: "PROFILE", baseTableSchemaName: "PUBLIC", constraintName: "FK_M3P1GDTSYS5T260MOKI30YKPS", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-182") {
		addForeignKeyConstraint(baseColumnNames: "CITY_ID", baseTableName: "PROFILE", baseTableSchemaName: "PUBLIC", constraintName: "FK_KEIY467N5H1QLWNTEEJMX2B2P", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "REGION", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-183") {
		addForeignKeyConstraint(baseColumnNames: "USER_ID", baseTableName: "PROFILE", baseTableSchemaName: "PUBLIC", constraintName: "FK_C1DKIAWNLJ6UOE6FNLWD6J83J", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "USERS", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-184") {
		addForeignKeyConstraint(baseColumnNames: "CLAIM_ID", baseTableName: "PROFILE_NEW_CLAIMS", baseTableSchemaName: "PUBLIC", constraintName: "FK_B03X85UTPHVXTSDEI23SFR99V", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CLAIM", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-185") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "PROFILE_NEW_CLAIMS", baseTableSchemaName: "PUBLIC", constraintName: "FK_OVMI84H4WT6SLBBHKWRBFY1KJ", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-186") {
		addForeignKeyConstraint(baseColumnNames: "CONSULT_ID", baseTableName: "PROFILE_NEW_CONSULTS", baseTableSchemaName: "PUBLIC", constraintName: "FK_53U591GI66SSIHKGWQF3VN7IV", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CONSULT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-187") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "PROFILE_NEW_CONSULTS", baseTableSchemaName: "PUBLIC", constraintName: "FK_62DX218RO25Y4EA4TARDUT0SG", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-188") {
		addForeignKeyConstraint(baseColumnNames: "DEAL_ID", baseTableName: "PROFILE_NEW_DEALS", baseTableSchemaName: "PUBLIC", constraintName: "FK_P3RTXBB2YC43NKJ4I31KNXSK8", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DEAL", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-189") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "PROFILE_NEW_DEALS", baseTableSchemaName: "PUBLIC", constraintName: "FK_JSIJ9GOXB2DKNY3O2K3XIC7OD", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-190") {
		addForeignKeyConstraint(baseColumnNames: "DISPUTE_ID", baseTableName: "PROFILE_NEW_DISPUTES", baseTableSchemaName: "PUBLIC", constraintName: "FK_8N7US2X95G5L5AASLHGG3JRCL", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DISPUTE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-191") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "PROFILE_NEW_DISPUTES", baseTableSchemaName: "PUBLIC", constraintName: "FK_J1N86I7O38U9KHFHOG0N4SNOU", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-192") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "PROFILE_NEW_REVIEWS", baseTableSchemaName: "PUBLIC", constraintName: "FK_KA7QFEU21JXARCGW60SNEF752", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-193") {
		addForeignKeyConstraint(baseColumnNames: "REVIEW_ID", baseTableName: "PROFILE_NEW_REVIEWS", baseTableSchemaName: "PUBLIC", constraintName: "FK_ICGMT1RUIE4X2XXIOPQSY7O7H", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "REVIEW", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-194") {
		addForeignKeyConstraint(baseColumnNames: "PROFILE_ID", baseTableName: "PROFILE_REGION", baseTableSchemaName: "PUBLIC", constraintName: "FK_NUKHGFTJHF5XT50SCFUJXX9G5", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-195") {
		addForeignKeyConstraint(baseColumnNames: "REGION_ID", baseTableName: "PROFILE_REGION", baseTableSchemaName: "PUBLIC", constraintName: "FK_NAQR96I546OR4W3AY60DVCH7H", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "REGION", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-196") {
		addForeignKeyConstraint(baseColumnNames: "LEVEL_ID", baseTableName: "REGION", baseTableSchemaName: "PUBLIC", constraintName: "FK_F84Y86M8C534IQ66QCMW63UIR", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "REGION_LEVEL", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-197") {
		addForeignKeyConstraint(baseColumnNames: "PARENT_ID", baseTableName: "REGION", baseTableSchemaName: "PUBLIC", constraintName: "FK_I8GJN02SS9RJORJCVVLBRO8BX", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "REGION", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-198") {
		addForeignKeyConstraint(baseColumnNames: "PARENT_ID", baseTableName: "REGION_LEVEL", baseTableSchemaName: "PUBLIC", constraintName: "FK_EIOR1MK5EO85765JACWVIX0G8", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "REGION_LEVEL", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-199") {
		addForeignKeyConstraint(baseColumnNames: "TYPE_ID", baseTableName: "REGION_LEVEL", baseTableSchemaName: "PUBLIC", constraintName: "FK_H5LPDGEY04AJV4JX4J6X9LH12", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "REGION_TYPE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-200") {
		addForeignKeyConstraint(baseColumnNames: "AUTHOR_ID", baseTableName: "REVIEW", baseTableSchemaName: "PUBLIC", constraintName: "FK_DLIAAPE2S8IX8YHSEP39HICP6", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PROFILE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-201") {
		addForeignKeyConstraint(baseColumnNames: "DEAL_ID", baseTableName: "REVIEW", baseTableSchemaName: "PUBLIC", constraintName: "FK_652T5YY99V5DM5VH81T07MK8Y", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "DEAL", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-202") {
		addForeignKeyConstraint(baseColumnNames: "FROM_ID", baseTableName: "REVIEW", baseTableSchemaName: "PUBLIC", constraintName: "FK_CEPC3XHKQXROOTY3HCBSGWJ8M", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-203") {
		addForeignKeyConstraint(baseColumnNames: "TO_ID", baseTableName: "REVIEW", baseTableSchemaName: "PUBLIC", constraintName: "FK_4M6O553YVO5X2JSAL2CANSWLA", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-204") {
		addForeignKeyConstraint(baseColumnNames: "ACCOUNT_ID", baseTableName: "REVIEW_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_830R5OR1K5AQYN4K6UFF7H58N", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ACCOUNT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-205") {
		addForeignKeyConstraint(baseColumnNames: "ATTACHMENT_ID", baseTableName: "REVIEW_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_RA8BVFG5T6OILD92BQ2A31BFE", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ATTACHMENT", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-206") {
		addForeignKeyConstraint(baseColumnNames: "REVIEW_ID", baseTableName: "REVIEW_ATTACH", baseTableSchemaName: "PUBLIC", constraintName: "FK_HI5WK6LBQVX8G7FTRNS6Y353T", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "REVIEW", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-207") {
		addForeignKeyConstraint(baseColumnNames: "CURRENCY_ID", baseTableName: "TARIFF_PRICE", baseTableSchemaName: "PUBLIC", constraintName: "FK_DEG1UMPIEYKBHDN3OQ0TH7SYP", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SYSTEM_CURRENCY", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-208") {
		addForeignKeyConstraint(baseColumnNames: "ROLE_ID", baseTableName: "USERS", baseTableSchemaName: "PUBLIC", constraintName: "FK_KRVOTBTIQHUDLKAMVLPAQUS0T", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ROLE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "Андрейка (generated)", id: "1429724618119-209") {
		addForeignKeyConstraint(baseColumnNames: "ROLE_REQUEST_ID", baseTableName: "USERS", baseTableSchemaName: "PUBLIC", constraintName: "FK_HL1FFC1YBBB2A1QTQE1IV7PS7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "ROLE", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}
}
