package guru.springframework.spring5webapp.model;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    Long id;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String isbn;

    @OneToOne
//    (cascade = CascadeType.ALL)
//    @JoinTable(name = "publisher_book", joinColumns = @JoinColumn(name = "book_id"),
//    inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    @Getter @Setter
    private Publisher publisher;

    @ManyToMany
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id"))
    @Getter @Setter
    private Set<Author> authors;

    public Book() {

    }

    public Book(String title, String isbn, Publisher publisher) {
        this(title, isbn, publisher, new HashSet<>());
    }

    public Book(String title, String isbn, Publisher publisher, Set<Author> authors) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", isbn='" + isbn + '\'' +
            ", publisher='" + publisher + '\'' +
            ", authors=" + authors +
            '}';
    }
}
