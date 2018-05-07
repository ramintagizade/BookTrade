package com.ramin.Entity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRequestRepository extends MongoRepository<UserRequest,String> {

}
