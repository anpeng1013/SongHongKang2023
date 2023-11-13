package java_bean.day02;

import java.util.Objects;

/**
 * @ClassName: User.java
 * @Author: anpeng
 * @Date: 2023/10/31 9:55
 */
public class User implements Cloneable{
    private String host;
    private String username;
    private String password;
    private Authority authority;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {//直接Alt+insert使用模板
        return "User{" +
                "host='" + host + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authority=" + authority +
                '}';
    }

    @Override
    public boolean equals(Object o) {//直接Alt+insert使用模板，成员变量都可能为空，即select non null时全不选。
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(host, user.host) && Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) && Objects.equals(authority, user.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, username, password, authority);
    }

    @Override
    public User clone() throws CloneNotSupportedException{
        //重写clone的写法
        User user = (User) super.clone();
        user.authority = (Authority) authority.clone();
        return user;
    }
}
