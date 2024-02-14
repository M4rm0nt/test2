package repository;

import jakarta.enterprise.context.ApplicationScoped;
import model.Person;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.PersonService;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@ApplicationScoped
public class PersonRepository {

    private static final Logger logger = LogManager.getLogger(PersonRepository.class);

    public Person addPerson(Person person) {
        String sql = "INSERT INTO person (vorname, nachname, email, telefonnummer, strasse, hausnummer, plz) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, person.getVorname());
            pstmt.setString(2, person.getNachname());
            pstmt.setString(3, person.getEmail());
            pstmt.setString(4, person.getTelefonnummer());
            pstmt.setString(5, person.getStrasse());
            pstmt.setString(6, person.getHausnummer());
            pstmt.setString(7, person.getPlz());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                person.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
           logger.error("Fehler beim Hinzufügen der Person: " + e.getMessage());
        }
        return person;
    }

    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<>();
        String sql = "SELECT id, vorname, nachname, email, telefonnummer, strasse, hausnummer, plz FROM person";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getLong("id"));
                person.setVorname(rs.getString("vorname"));
                person.setNachname(rs.getString("nachname"));
                person.setEmail(rs.getString("email"));
                person.setTelefonnummer(rs.getString("telefonnummer"));
                person.setStrasse(rs.getString("strasse"));
                person.setHausnummer(rs.getString("hausnummer"));
                person.setPlz(rs.getString("plz"));

                persons.add(person);
            }
        } catch (SQLException e) {
            logger.error("Fehler beim Abrufen aller Personen: " + e.getMessage());
        }
        return persons;
    }
}
