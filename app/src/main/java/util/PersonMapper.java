package util;

import jakarta.enterprise.context.ApplicationScoped;
import dto.PersonDTO;
import model.Person;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PersonMapper {

    public Person mapToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setVorname(personDTO.getVorname());
        person.setNachname(personDTO.getNachname());
        person.setEmail(personDTO.getEmail());
        person.setTelefonnummer(personDTO.getTelefonnummer());
        person.setStrasse(personDTO.getStrasse());
        person.setHausnummer(personDTO.getHausnummer());
        person.setPlz(personDTO.getPlz());

        return person;
    }

    public PersonDTO mapToDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setVorname(person.getVorname());
        personDTO.setNachname(person.getNachname());
        personDTO.setEmail(person.getEmail());
        personDTO.setTelefonnummer(person.getTelefonnummer());
        personDTO.setStrasse(person.getStrasse());
        personDTO.setHausnummer(person.getHausnummer());
        personDTO.setPlz(person.getPlz());

        return personDTO;
    }

    public List<PersonDTO> mapListToDTO(List<Person> persons) {
        return persons.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}
