package com.kushagra.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String username;
    @NonNull
    private String password;
    private String email;
    private String sentimentAnalysis;
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private List<String> roles;
}
