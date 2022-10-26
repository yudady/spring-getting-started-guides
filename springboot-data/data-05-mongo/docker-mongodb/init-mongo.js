db.createUser(
    {
        user: "test",
        pwd: "test",
        roles: [
            {
                role: "readWrite",
                db: "test"
            }
        ]
    }
);
db.createCollection("test"); //MongoDB creates the database when you first store data in that database