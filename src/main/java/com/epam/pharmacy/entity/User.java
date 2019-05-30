package com.epam.pharmacy.entity;

import java.util.Objects;

public class User implements Identifiable {

    public static final String USER = "user";
    public static final String ID = "user_id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String USER_ROLE_ID = "user_role_id";
    public static final String BLOCKED = "blocked";

    public static final int CLIENT_ID = 3;

    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private UserRole role;
    private boolean blocked;

    public User(Integer id, String login, String password, String name, String surname, UserRole role, boolean blocked) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.blocked = blocked;
    }

    public User(String login, String password, String name, String surname, UserRole role, boolean blocked) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.blocked = blocked;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getUserRoleId() {
        switch (role){
            case ADMIN: return 1;
            case PHARMACIST: return 4;
            case DOCTOR: return 2;
            default: return  3;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return blocked == user.blocked &&
                Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, login, password, role, blocked);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", blocked=" + blocked +
                '}';
    }
}
