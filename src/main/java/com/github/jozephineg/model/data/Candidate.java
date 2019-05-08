package com.github.jozephineg.model.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.lang.NonNull;

@Document(indexName = "candidate", type = "candidate")
public class Candidate {

    @Id
    private String intressentId;
    @Field(type = FieldType.Text)
    private String firstName;
    @Field(type = FieldType.Text)
    private String lastName;
    @Field(type = FieldType.Text)
    private String party;
    @Field(type = FieldType.Text)
    private String gender;
    @Field(type = FieldType.Text)
    private String yearOfBirth;
    @Field(type = FieldType.Text)
    private String constituent;
    @Field(type = FieldType.Text)
    private String status;
    @Field(type = FieldType.Text)
    private String webAddress;
    @Field(type = FieldType.Text)
    private String email;
    @Field(type = FieldType.Text)
    private String phone;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String organ;
    @Field(type = FieldType.Text)
    private String role;
    @Field(type = FieldType.Text)
    private String roleStatus;
    @Field(type = FieldType.Text)
    private String assignmentFrom;
    @Field(type = FieldType.Text)
    private String assignmentTo;

    // to deserialize from ES
    private Candidate(){}

    private Candidate(String intressentId,
                      String firstName,
                      String lastName,
                      String party,
                      String gender, String yearOfBirth, String constituent, String status, String webAddress, String email, String phone, String title, String organ, String role, String roleStatus, String assignmentFrom, String assignmentTo) {
        this.intressentId = intressentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.party = party;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
        this.constituent = constituent;
        this.status = status;
        this.webAddress = webAddress;
        this.email = email;
        this.phone = phone;
        this.title = title;
        this.organ = organ;
        this.role = role;
        this.roleStatus = roleStatus;
        this.assignmentFrom = assignmentFrom;
        this.assignmentTo = assignmentTo;
    }

    public static class Builder {

        private String intressentId;
        private String firstName;
        private String lastName;
        private String party;
        private String gender;
        private String yearOfBirth;
        private String constituent;
        private String status;
        private String webAddress;
        private String email;
        private String phone;
        private String title;
        private String organ;
        private String role;
        private String roleStatus;
        private String assignmentFrom;
        private String assignmentTo;

        public Builder() {
        }

        @NonNull
        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        @NonNull
        public Builder withOrgan(String organ) {
            this.organ = organ;
            return this;
        }

        @NonNull
        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        @NonNull
        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        @NonNull
        public Builder withWebAddress(String webAddress) {
            this.webAddress = webAddress;
            return this;
        }

        @NonNull
        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        @NonNull
        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        @NonNull
        public Builder withRoleStatus(String roleStatus) {
            this.roleStatus = roleStatus;
            return this;
        }

        @NonNull
        public Builder withAssignmentTo(String assignmentTo) {
            this.assignmentTo = assignmentTo;
            return this;
        }

        @NonNull
        public Builder withAssignmentFrom(String assignmentFrom) {
            this.assignmentFrom = assignmentFrom;
            return this;
        }

        @NonNull
        public Builder withIntressentId(String intressentId) {
            this.intressentId = intressentId;
            return this;
        }

        @NonNull
        public Builder withYearOfBirth(String yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
            return this;
        }

        @NonNull
        public Builder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        @NonNull
        public Builder withConstituent(String constituent) {
            this.constituent = constituent;
            return this;
        }


        @NonNull
        public Builder withParty(String party) {
            this.party = party;
            return this;
        }

        @NonNull
        public Builder withRole(String role) {
            this.role = role;
            return this;
        }

        @NonNull
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }


        public Candidate build() {
            return new Candidate(
                    intressentId, firstName, lastName, party, gender, yearOfBirth, constituent, status, webAddress, email, phone, title, organ, role, roleStatus, assignmentFrom, assignmentTo);
        }
    }

    public String getIntressentId() {
        return intressentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getParty() {
        return party;
    }

    public String getGender() {
        return gender;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public String getConstituent() {
        return constituent;
    }

    public String getStatus() {
        return status;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getTitle() {
        return title;
    }

    public String getOrgan() {
        return organ;
    }

    public String getRole() {
        return role;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public String getAssignmentFrom() {
        return assignmentFrom;
    }

    public String getAssignmentTo() {
        return assignmentTo;
    }
}

