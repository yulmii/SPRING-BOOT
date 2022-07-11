package com.example.first.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.first.model.TodoEntity;
import com.example.first.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그
@Service
public class TodoService {
	
	@Autowired
	private TodoRepository repository;
	
	//public String testService() {
	//	return "Test Service";
	//}

	public String testService() {
		// TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		// TodoEntity 저장
		repository.save(entity);
		// TodoEntity 검색
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}
	
	//리팩토링한 메서드
	//검증 : 넘어온 엔티티가 유효한지 검사하는 로직
	private void validate(final TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}

		if(entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}
	
	public List<TodoEntity> create(final TodoEntity entity) {
		// Validations 검증
		validate(entity);
		
		// 엔티티를 데이터베이스에 저장하고 로그를 남긴다.
		repository.save(entity);
		log.info("Entity Id : {} is saved.", entity.getId());
		
		//저장된 엔티티를 포함하는 새 리스트를 리턴한다.
		return repository.findByUserId(entity.getUserId());
	}
	
	public List<TodoEntity> output(final String userId){
		return repository.findByUserId(userId);
	}
}
