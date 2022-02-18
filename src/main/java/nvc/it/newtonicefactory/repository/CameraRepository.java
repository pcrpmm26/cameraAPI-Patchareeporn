package nvc.it.newtonicefactory.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import nvc.it.newtonicefactory.model.Camera;

public interface CameraRepository extends MongoRepository<Camera, String> {
    
}
