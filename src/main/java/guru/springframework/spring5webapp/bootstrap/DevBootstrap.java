package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import java.util.Optional;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository,
        BookRepository bookRepository,
        PublisherRepository publisherRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        // Eric DDD
        Publisher harperCollins = new Publisher();
        harperCollins.setName("Harper Collins");
        harperCollins.setAddress("1234 this sucks Ave.");
        publisherRepository.save(harperCollins);
//        "Harper Collins", ""
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", harperCollins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);


        // Rod NoEJB
        Publisher worx = new Publisher();
        worx.setName("Worx");
        worx.setAddress("WTF is this BS");
        publisherRepository.save(worx);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        saveFields(eric, ddd,Optional.empty());
        saveFields(rod, noEJB,Optional.empty());
    }

    private void saveFields(Author author, Book book, Optional<Publisher> publisher) {
        authorRepository.save(author);
        bookRepository.save(book);
        if (publisher.isPresent()) {
            publisherRepository.save(publisher.get());
        }
    }
}
