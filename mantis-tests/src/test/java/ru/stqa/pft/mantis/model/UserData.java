package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

public class UserData {

    @Entity
    @Table(name = "mantis_user_table")
    public class UserData {
        @Id
        private int id;
        @Column(name = "username")
        private String username;
        @Column(name = "realname")
        private String realname;
        @Column(name = "email")
        private String email;
        @Column(name = "password")
        private String password;

        public int getId() {
            return id;
        }

        public ru.stqa.pft.mantis.model.UserData withId(int id) {
            this.id = id;
            return this;
        }

        public String getUsername() {
            return username;
        }

        public ru.stqa.pft.mantis.model.UserData withUsername(String username) {
            this.username = username;
            return this;
        }

        public String getRealname() {
            return realname;
        }

        public ru.stqa.pft.mantis.model.UserData withRealname(String realname) {
            this.realname = realname;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public ru.stqa.pft.mantis.model.UserData withEmail(String email) {
            this.email = email;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public ru.stqa.pft.mantis.model.UserData withPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ru.stqa.pft.mantis.model.UserData userData = (ru.stqa.pft.mantis.model.UserData) o;
            return id == userData.id && Objects.equals(username, userData.username) && Objects.equals(email, userData.email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, username, email);
        }

        @Override
        public String toString() {
            return "UserData{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", realname='" + realname + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}
