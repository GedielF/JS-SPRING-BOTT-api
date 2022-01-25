package br.com.erudio.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.BreakfastVO;
import br.com.erudio.services.BreakfastServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Breakfast Endpoint") 
@RestController
@RequestMapping("/api/breakfast/v1")
public class BreakfastController {
	
	@Autowired
	private BreakfastServices service;
	
	@Operation(summary = "Find all breakfast" )
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<CollectionModel<BreakfastVO>> findAll(
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "12") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction) {
		

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));
		
		Page<BreakfastVO> breakfast =  service.findAll(pageable);
		breakfast
			.stream()
			.forEach(p -> p.add(
					linkTo(methodOn(BreakfastController.class).findById(p.getKey())).withSelfRel()
				)
			);

        return ResponseEntity.ok(CollectionModel.of(breakfast));
	}	
	
	@Operation(summary = "Find a specific breakfast by your ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BreakfastVO findById(@PathVariable("id") Long id) {
		BreakfastVO breakfastVO = service.findById(id);
		breakfastVO.add(linkTo(methodOn(BreakfastController.class).findById(id)).withSelfRel());
		return breakfastVO;
	}	
	
	@Operation(summary = "Create a new breakfast")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BreakfastVO create(@RequestBody BreakfastVO breakfast) {
		BreakfastVO breakfastVO = service.create(breakfast);
		breakfastVO.add(linkTo(methodOn(BreakfastController.class).findById(breakfastVO.getKey())).withSelfRel());
		return breakfastVO;
	}
	
	@Operation(summary = "Update a specific breakfast")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BreakfastVO update(@RequestBody BreakfastVO breakfast) {
		BreakfastVO brakfastVO = service.update(breakfast);
		brakfastVO.add(linkTo(methodOn(BreakfastController.class).findById(brakfastVO.getKey())).withSelfRel());
		return brakfastVO;
	}	
	
	@Operation(summary = "Delete a specific breakfast by your ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}	
	
}