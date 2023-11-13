package java_bean.day02;

import java.util.Objects;

/**
 * @ClassName: Administer.java
 * @Author: anpeng
 * @Date: 2023/10/31 17:20
 */
public class Administer implements Cloneable{
    private String name;
    private String password;
    private Authority authority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administer that = (Administer) o;
        return Objects.equals(name, that.name) && Objects.equals(password, that.password) && Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, authority);
    }

    @Override
    public String toString() {
        return "Administer{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", authority=" + authority +
                '}';
    }

    @Override
    public Administer clone() throws CloneNotSupportedException{
        return (Administer)super.clone();//不重写clone的写法
    }
}
