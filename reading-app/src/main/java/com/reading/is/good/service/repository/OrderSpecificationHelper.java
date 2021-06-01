package com.reading.is.good.service.repository;

import static org.springframework.data.jpa.domain.Specification.where;

import java.time.Instant;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.reading.is.good.service.repository.command.SearchOrderCommand;
import com.reading.is.good.service.repository.jpa.OrderEntity;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderSpecificationHelper {
	
	public Specification<OrderEntity> getFilter(SearchOrderCommand command) {
		return (root, query, cb) -> {
			query.distinct(true); 
			return where(addIdIfExist(command.getId()))
							.and(addTransactionDateIfExist(command.getStartDate() , command.getFinishDate()))
							.toPredicate(root, query, cb);
		};
	}

	private Specification<OrderEntity> addIdIfExist(Long id) {
		return (root, query, cb) -> {
			if(Objects.isNull(id)) {
				return null;
			}
			return cb.equal(root.get("id"), id);
			
		};
	}
	
	private Specification<OrderEntity> addTransactionDateIfExist(Instant startDate, Instant finishDate) {
		return (root, query, cb) -> {
			if(Objects.isNull(startDate) || Objects.isNull(finishDate) ) {
				return null;
			}
			return cb.between(root.get("createdAt"), startDate, finishDate);
			
		};
	}

}
