package com.sbk.records.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sbk.records.entities.AlbumEntity;
import com.sbk.records.repositories.AlbumRepository;

@RestController
@RequestMapping(path = "/v1")
@PreAuthorize("hasAuthority('ALBUMS_ADMIN')")
public class AlbumsControllerV1 {

	private AlbumRepository albumRepository;

	public AlbumsControllerV1(AlbumRepository albumRepository) {
		super();
		this.albumRepository = albumRepository;
	}

	@PreAuthorize("hasAuthority('ALBUMS_USER')")
	@GetMapping("/albums")
	public Iterable<AlbumEntity> findAlbums() {
		return albumRepository.findAll();
	}


	@GetMapping(path = "/albums", params = "title")
	public List<AlbumEntity> findAlbumsByTitle(@RequestParam String title) {
		return albumRepository.findByTitleIgnoreCase(title);
	}
}
