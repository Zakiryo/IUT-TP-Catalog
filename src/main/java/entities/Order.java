package entities;

import jakarta.persistence.*;

import java.util.Date;

import static org.hibernate.type.descriptor.java.JdbcDateJavaType.DATE_FORMAT;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "id")
    private Client client;

    public Order() {
    }

    public Order(Client client) {
        this.client = client;
        this.creationDate = new Date();
    }
}
