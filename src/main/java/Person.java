public class Person {
    private long id;
    private String firstname;
    private String lastname;
    private String mobile;
    private String birthday;

    public Person(long id, String firstname, String lastname, String mobile, String birthday) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.birthday = birthday;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
