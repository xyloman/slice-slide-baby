package com.sbk.records.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.sbk.records.entities.AlbumEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AlbumRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private AlbumRepository repository;

	@Test
	public void findByTileShouldReturnTile() {
		this.entityManager.persist(new AlbumEntity("To the Extreme", 646876800000L, "Hip Hop", "1989", "SBK Records"));
		
		List<AlbumEntity> albums = this.repository.findByTitleIgnoreCase("to the extreme");
		
		assertThat(albums).isNotEmpty();
		assertThat(albums).extracting("title", "releasedTimestamp")
				.containsOnly(tuple("To the Extreme", 646876800000L));
	}
}
