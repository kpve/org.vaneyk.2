package org.vaneyk.rugby.data.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.vaneyk.rugby.common.domain.Task;
import org.vaneyk.rugby.data.domain.repository.custom.TaskRepositoryCustom;

@Repository
public interface TaskRepository extends MongoRepository<Task, String>, TaskRepositoryCustom
{
}
