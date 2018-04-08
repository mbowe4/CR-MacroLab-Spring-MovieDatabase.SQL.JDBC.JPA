import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
        jdbcTemplate.execute("Update Person Set LAST_NAME = " + person.getLastname() + ", " + person.getFirstname() + ", " + person.getMobile() + ", " + person.getBirthday() +" WHERE ID = " + person.getId() + ";");
    }

    public void deletePerson(Person person) {
        jdbcTemplate.execute("DELETE FROM Person WHERE ID =" + person.getId() + ";");
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
        jdbcTemplate.query("SELECT * FROM person WHERE FIRST_NAME=" + firstName + " OR LAST_NAME= " + lastName + " OR BIRTHDAY = " + birthday + ";");
    }

    public void findById(Long id) {
        jdbcTemplate.query("SELECT * FROM PERSON WHERE ID=" + id);
    }



}
//        find all people with a particular first name, last name, or birthdate
//        Find a single person by ID
//        Generate a map of surnames to lists of people with that surname
//        Generate a map of first names to the number of times they occur.


//    Create the following REST endpoints to interact with the application. You can use postman to confirm your program's behavior.
//        POST /people -- create a person
//        PUT /people/{id} -- update person with id. 404 error if that person doesn't exist yet
//        GET /people/{id} -- get the person with the specified ID
//        DELETE /people/{id} -- Delete the person with the specified ID
//        GET /people -- get all people in the database
//        GET /people/reverselookup/{mobileNumber} -- find all people with the specified mobile number
//        GET /people/surname/{lastName} -- Find all people with a particular last name
//        GET /people/surname -- Get the result of the surname report above
//        GET /people/firstname/stats -- Get the report of first name frequencies