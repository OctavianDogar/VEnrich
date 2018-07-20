package com.octavian.vEnrich;

import com.octavian.vEnrich.model.House;
import com.octavian.vEnrich.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonBTest {

    private static Logger LOG = LoggerFactory.getLogger(JsonBTest.class);
    private Person person;
    private List<Person> people;
    private House house;
    Jsonb jsonb;

    public JsonBTest() {
    }

    @Before
    public void init() {
        this.jsonb = JsonbBuilder.create();
        this.initPerson();
        this.initPeople();
        this.initHouse();
    }

    private void initPerson() {
        this.person = new Person(35, "Jack");
    }

    private void initPeople() {
        Person jim = new Person(23, "Jim");
        Person joe = new Person(23, "Joe");
        Person mark = new Person(23, "Mark");
        this.people = new ArrayList();
        this.people.addAll(Arrays.asList(jim, joe, mark));
    }

    private void initHouse() {
        this.house = new House(this.person, this.people);
    }

    @Test
    public void testPerson() {
        Person deserialisedJson = null;
        String result = this.jsonb.toJson(this.person);
        LOG.info("Marshalled person: " + result);
        deserialisedJson = (Person)this.jsonb.fromJson(result, Person.class);
        Assert.assertEquals(this.person, deserialisedJson);
    }

    @Test
    public void testPeople() {
        String result = this.jsonb.toJson(this.people);
        List<Person> deserializedPeople = (List)this.jsonb.fromJson(result, (new ArrayList<Person>() {
        }).getClass().getGenericSuperclass());
        Assert.assertEquals(this.people, deserializedPeople);
    }

    @Test
    public void testHouse() {
        String result = this.jsonb.toJson(this.house);
        System.out.println(result);
        House deserializedHouse = (House)this.jsonb.fromJson(result, House.class);
        Assert.assertEquals(this.house, deserializedHouse);
    }

}
