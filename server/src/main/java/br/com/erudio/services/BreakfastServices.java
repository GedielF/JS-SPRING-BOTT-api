package br.com.erudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Breakfast;
import br.com.erudio.data.vo.v1.BreakfastVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.BreakfastRepository;

@Service
public class BreakfastServices {
	
	@Autowired
	BreakfastRepository repository;
		
	public BreakfastVO create(BreakfastVO breakfast) {
		var entity = DozerConverter.parseObject(breakfast, Breakfast.class);
		var vo = DozerConverter.parseObject(repository.save(entity), BreakfastVO.class);
		return vo;
	}
	
	public Page<BreakfastVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToBreakfastVO);
	}	
	
	private BreakfastVO convertToBreakfastVO(Breakfast entity) {
		return DozerConverter.parseObject(entity, BreakfastVO.class);
	}
	
	public BreakfastVO findById(Long id) {

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, BreakfastVO.class);
	}
		
	public BreakfastVO update(BreakfastVO breakfast) {
		var entity = repository.findById(breakfast.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setNome(breakfast.getNome());
		entity.setDataatual(breakfast.getDataatual());
		entity.setCpf(breakfast.getCpf());
		
		var vo = DozerConverter.parseObject(repository.save(entity), BreakfastVO.class);
		return vo;
	}	
	
	public void delete(Long id) {
		Breakfast entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}
