package edu.codoacodo.domain.models;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private String email;

    public Usuario() {
    }

    public Usuario( int id, String password, String username, String email) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
