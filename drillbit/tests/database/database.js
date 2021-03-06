describe("Ti.Database tests", {
	testModuleMethods: function() {
		valueOf(Ti.Database).shouldNotBeNull();
		valueOf(Ti.Database).shouldBeObject();
		valueOf(Ti.Database.open).shouldBeFunction();
		valueOf(Ti.Database.install).shouldBeFunction();
	},
	testDatabaseMethods : function() {
		var db = Ti.Database.open("Test");
		try {
			valueOf(db).shouldNotBeNull();
			valueOf(db).shouldBeObject();
			valueOf(db.close).shouldBeFunction();
			valueOf(db.execute).shouldBeFunction();
			valueOf(db.getLastInsertRowId).shouldBeFunction();
			valueOf(db.getName).shouldBeFunction();
			valueOf(db.getRowsAffected).shouldBeFunction();
			valueOf(db.remove).shouldBeFunction();
		
			// Properties
			valueOf(db.lastInsertRowId).shouldBeNumber();
			valueOf(db.name).shouldBeString();
			valueOf(db.name).shouldBe("Test");
			valueOf(db.rowsAffected).shouldBeNumber();
		} finally {
			db.close();
		}
	},
	// https://appcelerator.lighthouseapp.com/projects/32238-titanium-mobile/tickets/2147-android-pragma-and-non-select-statements-return-null-from-tidatabasedbexecute-instead-of-resultset
	testDatabaseLH2147 : function() {
		var db = Ti.Database.open("Test");
		try {
			valueOf(db).shouldNotBeNull();
		
			var rs = db.execute("drop table if exists Test");
			valueOf(rs).shouldBeNull();

			rs = db.execute("create table if not exists Test(row text)");
			valueOf(rs).shouldBeNull();
		
			rs = db.execute("pragma table_info(Test)");
			valueOf(rs).shouldNotBeNull();
			valueOf(rs.fieldCount).shouldBeGreaterThan(0);
			rs.close();
		
			rs = db.execute("select * from Test");
			valueOf(rs).shouldNotBeNull();
			valueOf(rs.getFieldCount()).shouldBe(1);
			valueOf(rs.rowCount).shouldBe(0);
			rs.close();
		} finally {
			db.close();
			db.remove();
		}
		
		var f = Ti.Filesystem.getFile("file:///data/data/org.appcelerator.titanium.testharness/databases/Test");
		valueOf(f.exists()).shouldBeFalse();
	},
	testDatabaseInsert : function() {
		var db = Ti.Database.open("Test");
		try {
			valueOf(db).shouldNotBeNull();
		
			var rs = db.execute("drop table if exists Test");
			valueOf(rs).shouldBeNull();

			rs = db.execute("create table if not exists Test(row text)");
			valueOf(rs).shouldBeNull();

			db.execute("insert into Test(row) values(?)", "My TestRow");
		
			rs = db.execute("select * from Test");
			valueOf(rs).shouldNotBeNull();
			valueOf(rs.getFieldCount()).shouldBe(1);
			valueOf(rs.rowCount).shouldBe(1);
			valueOf(rs.getField(0)).shouldBe("My TestRow");
			rs.close();
		} finally {
			db.close();
			db.remove();
		}
		
		var f = Ti.Filesystem.getFile("file:///data/data/org.appcelerator.titanium.testharness/databases/Test");
		valueOf(f.exists()).shouldBeFalse();
	}
});