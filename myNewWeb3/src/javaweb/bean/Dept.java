package javaweb.bean;

import java.util.Objects;

public class Dept {
    private int no = 0;
    private String name = null;
    private String loc = null;
    private int num = 0;

    public Dept() {
    }

    public Dept(int no, String name, String loc, int num) {
        this.no = no;
        this.name = name;
        this.loc = loc;
        this.num = num;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", loc='" + loc + '\'' +
                ", num=" + num +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dept dept = (Dept) o;
        return no == dept.no && num == dept.num && Objects.equals(name, dept.name) && Objects.equals(loc, dept.loc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, name, loc, num);
    }
}
