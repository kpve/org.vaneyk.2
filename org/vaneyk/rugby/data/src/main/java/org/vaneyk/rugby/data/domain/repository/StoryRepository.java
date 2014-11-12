package org.vaneyk.rugby.data.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.vaneyk.rugby.data.domain.entity.Story;
import org.vaneyk.rugby.data.domain.repository.custom.StoryRepositoryCustom;

@Repository
public interface StoryRepository extends MongoRepository<Story, String>, StoryRepositoryCustom
{
}
