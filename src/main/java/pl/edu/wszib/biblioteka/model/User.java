package pl.edu.wszib.biblioteka.model;

public class User {
    private int id;
    private String login;
    private String passwd;
    private Role role;
    private String name;
    private String surname;

    public User() {
        this.id = 0;
        this.role=Role.USER;
    }

    public User(int id, String login, String passwd, Role role, String name, String surname) {
        this.id =  id;
        this.login = login;
        this.passwd = passwd;
        this.role = role;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static User connectLoginAndName(User u1, User u2){
        User user = new User();
        user.setLogin(u1.getLogin());
        user.setPasswd(u1.getPasswd());
        user.setName(u2.getName());
        user.setSurname(u2.getSurname());
        return user;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.id)
                .append("\t | \t")
                .append(this.login)
                .append("\t | \t")
                .append(this.name)
                .append("\t | \t")
                .append(this.surname)
                .toString();
    }
}
