package service;

import domain.Person;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class PersonService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addPerson(Person person) {
        jdbcTemplate.execute("INSERT INTO PERSON ( ID, LAST_NAME, FIRST_NAME, MOBILE, BIRTHDAY ) VALUES (" + person.getId() + ", " + person.getFirstname() + ", "
                + person.getLastname() + ", "
                + person.getMobile() + ", "
                + person.getBirthday() + ")");
    }

    public void updatePerson(Person person) {
        jdbcTemplate.execute("Update Person Set LAST_NAME = " + person.getLastname() + ", FIRST_NAME=" + person.getFirstname() + ", MOBILE=" + person.getMobile() + ", BIRTHDAY=" + person.getBirthday() +" WHERE ID =" + person.getId() + ";");
    }

    public void deletePerson(Long id) {
        jdbcTemplate.execute("DELETE FROM Person WHERE ID =" + id + ";");
    }

    public void batchRemove(List<Person> people) {
        StringBuilder ids = new StringBuilder("(");
        for (Person person: people) {
            ids.append(person.getId()).append(", ");
        }
        ids.append(");");
        jdbcTemplate.batchUpdate("DELETE FROM person WHERE ID IN =" + ids);
    }

    public void findParticularPerson(String firstName, String lastName, String birthday) {
        jdbcTemplate.execute("SELECT * FROM person WHERE FIRST_NAME=" + firstName + " OR LAST_NAME= " + lastName + " OR BIRTHDAY = " + birthday + ";");
    }

    public void findById(Long id) {
      jdbcTemplate.execute("SELECT * FROM person WHERE ID=" +  id);

    }

    public List<Person> findAll() {
        List<Person> entries = new ArrayList<>();
        jdbcTemplate.query("SELECT * FROM PERSON",
                                    new Object[]{},
                (rs, row) -> new Person(rs.getLong("ID"),
                        rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"),
                        rs.getString("MOBILE"),
                        rs.getString("BIRTHDAY")))
                .forEach(entry -> entries.add(entry));
        return entries;
    }

    public Map<String, List<Person>> surnamesMap() {
        return null;

    }

    public Map<String, Integer> firstnameOccurences() {
        return null;
    }
}
