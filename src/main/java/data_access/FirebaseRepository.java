package data_access;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRepository {
    private final DatabaseReference database;

    public FirebaseRepository() {
        this.database = FirebaseDatabase.getInstance().getReference();
    }
}