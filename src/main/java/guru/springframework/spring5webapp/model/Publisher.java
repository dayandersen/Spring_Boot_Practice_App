package guru.springframework.spring5webapp.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    Long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Publisher publisher = (Publisher) o;
        return id.equals(publisher.id) &&
            name.equals(publisher.name) &&
            address.equals(publisher.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    @Override
    public String toString() {
        return "Publisher{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}
