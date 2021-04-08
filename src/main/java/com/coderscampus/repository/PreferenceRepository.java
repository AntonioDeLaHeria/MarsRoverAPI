package com.coderscampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.dto.HomeDto;

// this has to do with our database...currernly dealing with one entity...the form tag with all the choices(preferences)


// type of the primary key is long
public interface PreferenceRepository extends JpaRepository<HomeDto, Long> {

	HomeDto findByUserId(Long userId);

}
