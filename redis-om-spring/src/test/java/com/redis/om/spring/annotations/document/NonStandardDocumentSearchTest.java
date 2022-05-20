package com.redis.om.spring.annotations.document;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.redis.om.spring.AbstractBaseDocumentTest;
import com.redis.om.spring.annotations.document.fixtures.Custom;
import com.redis.om.spring.annotations.document.fixtures.CustomRepository;

public class NonStandardDocumentSearchTest extends AbstractBaseDocumentTest {
  @Autowired CustomRepository repository;

  @Autowired
  RedisTemplate<String, String> template;
  
  long id1;
  long id2;
  long id3;

  @BeforeEach
  public void loadTestData() {
    Custom c1 = Custom.of("foo");
    Custom c2 = Custom.of("bar");
    Custom c3 = Custom.of("baz");
    c2.setTaken(true);

    repository.saveAll(List.of(c1, c2, c3));
    
    id1 = c1.getId();
    id2 = c2.getId();
    id3 = c3.getId();
  }

  @AfterEach
  public void cleanUp() {
    repository.deleteAll();
  }

  @Test
  public void testGetByName() {
    assertEquals(3, repository.count());

    Optional<Custom> maybeCustom = repository.findFirstByName("bar");
    assertThat(maybeCustom).isPresent();
  }
  
  @Test
  public void testBasicCrudOperations() {
    Optional<Custom> maybeC1 = repository.findById(id1);
    Optional<Custom> maybeC2 = repository.findById(id2);
    Optional<Custom> nonExistent = repository.findById(8675309L);

    assertTrue(maybeC1.isPresent());
    assertTrue(maybeC2.isPresent());
    assertTrue(nonExistent.isEmpty());
    
    // delete given an entity
    repository.delete(maybeC1.get());
    
    assertEquals(2, repository.count());
    
    // delete given an id
    repository.deleteById(id2);
    
    assertEquals(1, repository.count());
  }
  
}