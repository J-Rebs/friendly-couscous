package com.example.musictonic.repository;

import com.example.musictonic.model.Analytics;
import com.example.musictonic.model.AnalyticsSong;
import com.example.musictonic.model.AnalyticsUser;
import com.example.musictonic.model.Song;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * "Spring Data JPA focuses on using JPA to store data in a relational database.
 * Its most compelling feature is the ability to create repository implementations automatically,
 * at runtime, from a repository interface."
 *
 * "In a typical Java application, you might expect to write a class that implements
 * CustomerRepository. However, that is what makes Spring Data JPA so powerful: You need not write
 * an implementation of the repository interface. Spring Data JPA creates an implementation when
 * you run the application."
 * Src: https://spring.io/guides/gs/accessing-data-jpa/
 * */

/**
 * Interface for AnalyticsSongRepository.
 */
public interface AnalyticsSongRepository extends JpaRepository<AnalyticsSong, Long> {

  //Note: your naming conventions must match how you establish the entity
  //e.g., code will fail if you write findByReal_Name;

  AnalyticsSong deleteByAnalytics(Analytics a);


}
