package controller;

import domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.PersonService;

import java.util.List;

@Controller
public class PersonController {

        @Autowired
        private PersonService service;


        @RequestMapping(value = "/people", method = RequestMethod.POST)
        @ResponseStatus(HttpStatus.CREATED)
        @ResponseBody
        public void createPerson(@RequestBody Person person) {
            service.addPerson(person);
        }


        @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
        @ResponseStatus(HttpStatus.OK)
        public void updatePerson(@PathVariable( "id" ) Long id, @RequestBody Person person) {
            service.findById();
            service.updatePerson(person);
        }


        @RequestMapping(value = "/people", method = RequestMethod.POST)
        @ResponseBody
        public Person findOne(@PathVariable("id") Long id) {
            return service.findById(id);
        }

        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        @ResponseBody
        public List<Person> findAll() {
            return service.findAll();
        }

        @RequestMapping(value = "/{mobile}", method = RequestMethod.GET)
        @ResponseBody
        public Person findByMobile(@PathVariable("mobile") String mobile) {

            return service.findById(id);
        }


        @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
        @ResponseStatus(HttpStatus.OK)
        public void delete(@PathVariable("id") Long id) {
            service.deletePerson(id);
        }

    }