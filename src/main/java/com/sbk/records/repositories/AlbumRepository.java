package com.sbk.records.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sbk.records.entities.AlbumEntity;

@Repository
public interface AlbumRepository extends CrudRepository<AlbumEntity, String> {

	public List<AlbumEntity> findByTitleIgnoreCase(String title);
}
