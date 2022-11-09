package com.keena.dbrelationships.repository;

import com.keena.dbrelationships.entity.Author;
import com.keena.dbrelationships.entity.Book;
import com.keena.dbrelationships.entity.Category;
import com.keena.dbrelationships.entity.Photo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setupEach() {

    }

    @AfterEach
    public void teardownEach() {

    }

    @Test
    public void testCascadeSaveCategory() {
        Category rootCategory = createCategory("Science");
        Category layerOneCategoryElectronics = createCategory("Electronics");
        Category layerOneCategoryBiology = createCategory("Biology");
        Category layerTwoCategoryMobilePhone = createCategory("mobile phone");
        Category layerTwoCategoryPC = createCategory("PC");
        Category layerThreeCategoryIPhone = createCategory("iPhone");
        Category layerThreeCategoryAndroid = createCategory("Android");
        Category layerThreeCategoryDesktop = createCategory("Desktop");
        Category layerThreeCategoryLaptop = createCategory("Laptop");

        rootCategory.addChildCategory(layerOneCategoryElectronics);
        rootCategory.addChildCategory(layerOneCategoryBiology);

        layerOneCategoryElectronics.addChildCategory(layerTwoCategoryMobilePhone);
        layerOneCategoryElectronics.addChildCategory(layerTwoCategoryPC);

        layerTwoCategoryMobilePhone.addChildCategory(layerThreeCategoryIPhone);
        layerTwoCategoryMobilePhone.addChildCategory(layerThreeCategoryAndroid);

        layerTwoCategoryPC.addChildCategory(layerThreeCategoryDesktop);
        layerTwoCategoryPC.addChildCategory(layerThreeCategoryLaptop);

        Category savedCategory = categoryRepository.saveAndFlush(rootCategory);

        assertNotNull(savedCategory.getId(), "The saved category should have Id being assigned");

        displayCategory(rootCategory);

    }

    @Test
    public void testCascadeSaveCategoryIncludingAllRelationship() {
        Category rootCategory = createCategory("Science");
        Category layerOneCategoryProgramming = createCategory("Programming");
        Category layerOneCategoryBiology = createCategory("Biology");
        Category layerTwoCategorySpringFramework = createCategory("Spring Framework");
        Category layerTwoCategoryORM = createCategory("ORM");

        rootCategory.addChildCategory(layerOneCategoryProgramming);
        rootCategory.addChildCategory(layerOneCategoryBiology);

        layerOneCategoryProgramming.addChildCategory(layerTwoCategorySpringFramework);
        layerOneCategoryProgramming.addChildCategory(layerTwoCategoryORM);

        /*
         * Now start building books
         */
        Book springInActionBook = createBook("Spring in Action", 8.5, 350);
        Book hibernateBook = createBook("Java Persistence with Hibernate", 8.8, 500);
        Book proSpringFveBook = createBook("Pro Spring 5", 9.1, 350);
        layerTwoCategorySpringFramework.addBook(springInActionBook);
        layerTwoCategorySpringFramework.addBook(proSpringFveBook);
        layerTwoCategoryORM.addBook(hibernateBook);

        /*
         * Now start setting up authors
         */
        Author springInActionAuthor = createAuthor("Craig", "Walls");
        Author hibernateBookAuthorOne = createAuthor("Christian", "Bauer");
        Author hibernateBookAuthorTwo = createAuthor("Gary", "Gregory");
        Author proSpringFiveAuthorOne = createAuthor("Chris", "Schaefer");
        Author proSpringFiveAuthorTwo = createAuthor("Clarence", "Ho");
        springInActionBook.addAuthor(springInActionAuthor);
        hibernateBook.addAuthor(hibernateBookAuthorOne);
        hibernateBook.addAuthor(hibernateBookAuthorTwo);
        proSpringFveBook.addAuthor(proSpringFiveAuthorOne);
        proSpringFveBook.addAuthor(proSpringFiveAuthorTwo);

        /*
         * Now start building Photos
         */
        Photo springInActonPhoto = createPhoto("http://book.com/image/spring_in_action/");
        Photo hibernatePhoto = createPhoto("http://book.com/image/java_persistence_with_hinernate/");
        Photo proSpringFivePhoto = createPhoto("http://book.com/image/pro_spring_5/");
        springInActionBook.addPhoto(springInActonPhoto);
        hibernateBook.addPhoto(hibernatePhoto);
        proSpringFveBook.addPhoto(proSpringFivePhoto);

        Category savedCategory = categoryRepository.saveAndFlush(rootCategory);

        assertNotNull(savedCategory.getId(), "The saved category should have Id being assigned");

        displayCategory(rootCategory);

    }

    private Photo createPhoto(String urlBase) {
        Photo photo = Photo.builder()
                .urlLarge(urlBase + "large")
                .urlMedium(urlBase + "medium")
                .urlSmall(urlBase + "small")
                .build();
        return photo;
    }

    private Author createAuthor(String firstName, String lastName) {
        Author author = Author.builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(new Date())
                .build();
        return author;
    }

    private Book createBook(String title, double rating, int totalPageBaseValue) {
        Book book = Book.builder()
                .isbn(9780136019701L + generateRandomInt(2000))
                .title(title)
                .rating(rating)
                .totalPages(generateRandomInt(totalPageBaseValue, totalPageBaseValue + 500))
                .publishedDate(new Date())
                .build();
        return book;
    }

    private void displayCategory(Category category) {
        log.info("===== Category = {}", category);
    }

    private Category createCategory(String categoryName) {
        Category category = Category.builder()
                .category(categoryName)
                .build();
        return category;
    }

    private int generateRandomInt(int base, int upperLimit) {
        Random random = new Random();
        int intValue = random.nextInt(base, upperLimit);
        return intValue;
    }

    private int generateRandomInt(int upperLimit) {
        return generateRandomInt(0, upperLimit);
    }
}
