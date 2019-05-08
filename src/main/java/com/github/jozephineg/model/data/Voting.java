package com.github.jozephineg.model.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.lang.NonNull;

@Document(indexName = "voting", type = "voting")
public class Voting {

    @Id
    @Field(type = FieldType.Text)
    private String votingId;
    @Field(type = FieldType.Text)
    private String rm;
    @Field(type = FieldType.Text)
    private String label;
    @Field(type = FieldType.Text)
    private String point;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String intressentId;
    @Field(type = FieldType.Text)
    private String party;
    @Field(type = FieldType.Text)
    private String constituent;
    @Field(type = FieldType.Text)
    private String vote;
    @Field(type = FieldType.Text)
    private String regarding;
    @Field(type = FieldType.Text)
    private String benchNumber;
    @Field(type = FieldType.Text)
    private String gender;
    @Field(type = FieldType.Text)
    private String yearOfBirth;
    @Field(type = FieldType.Text)
    private String date;

    private Voting() {
    }

    public Voting(String votingId, String rm, String label, String point, String name, String intressentId, String party, String constituent, String vote, String regarding, String benchNumber, String gender, String yearOfBirth, String date) {
        this.votingId = votingId;
        this.rm = rm;
        this.label = label;
        this.point = point;
        this.name = name;
        this.intressentId = intressentId;
        this.party = party;
        this.constituent = constituent;
        this.vote = vote;
        this.regarding = regarding;
        this.benchNumber = benchNumber;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
        this.date = date;
    }

    public static class Builder {

        private String votingId;
        private String rm;
        private String label;
        private String point;
        private String name;
        private String intressentId;
        private String party;
        private String constituent;
        private String vote;
        private String regarding;
        private String benchNumber;
        private String gender;
        private String yearOfBirth;
        private String date;

        public Builder() {}

        @NonNull
        public Builder withVotingId(String votingId) {
            this.votingId = votingId;
            return this;
        }

        @NonNull
        public Builder withRm(String rm) {
            this.rm = rm;
            return this;
        }

        @NonNull
        public Builder withLabel(String label) {
            this.label = label;
            return this;
        }

        @NonNull
        public Builder withPoint(String point) {
            this.point = point;
            return this;
        }

        @NonNull
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        @NonNull
        public Builder withIntressentId(String intressentId) {
            this.intressentId = intressentId;
            return this;
        }

        @NonNull
        public Builder withRegarding(String regarding) {
            this.regarding = regarding;
            return this;
        }

        @NonNull
        public Builder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        @NonNull
        public Builder withYearOfBirth(String yearOfBirth) {
            this.yearOfBirth = yearOfBirth;
            return this;
        }

        @NonNull
        public Builder withDate(String date) {
            this.date = date;
            return this;
        }

        @NonNull
        public Builder withParty(String party) {
            this.party = party;
            return this;
        }

        @NonNull
        public Builder withConstituent(String constituent) {
            this.constituent = constituent;
            return this;
        }

        @NonNull
        public Builder withBenchNumber(String benchNumber) {
            this.benchNumber = benchNumber;
            return this;
        }

        @NonNull
        public Builder withVote(String vote) {
            this.vote = vote;
            return this;
        }

        @NonNull
        public Voting build() {
            return new Voting(
                votingId, rm, label, point, name, intressentId, party, constituent, vote, regarding, benchNumber, gender, yearOfBirth, date
            );
        }
    }

    public String getVotingId() {
        return votingId;
    }

    public String getRm() {
        return rm;
    }

    public String getLabel() {
        return label;
    }

    public String getPoint() {
        return point;
    }

    public String getName() {
        return name;
    }

    public String getIntressentId() {
        return intressentId;
    }

    public String getParty() {
        return party;
    }

    public String getConstituent() {
        return constituent;
    }

    public String getVote() {
        return vote;
    }

    public String getRegarding() {
        return regarding;
    }

    public String getBenchNumber() {
        return benchNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public String getDate() {
        return date;
    }
}

