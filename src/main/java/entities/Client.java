package entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String city;

    /*
    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    private List<Order> orders;
    */

    public Client() {
    }

    public Client(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "<tr><td>" + this.id + " - " + this.name + " - " + this.city +
                "    <form method=\"post\" action=\"/action\">\n" +
                "        <label for=\"city\">Modifier la ville</label>\n" +
                "        <input type=\"text\" id=\"city\" name=\"city\">\n" +
                "        <input type=\"hidden\" id=\"function\" name=\"function\" value=\"changeClientCity\">\n" +
                "        <input type=\"hidden\" id=\"id\" name=\"id\" value=" + this.id + ">\n" +
                "        <input type=\"submit\" value=\"Changer\">\n" +
                "    </form>" +
                "</td></tr>";
    }
}
