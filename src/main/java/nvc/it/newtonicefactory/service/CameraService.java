package nvc.it.newtonicefactory.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nvc.it.newtonicefactory.model.Camera;
import nvc.it.newtonicefactory.model.Event;
import nvc.it.newtonicefactory.repository.CameraRepository;

@Service
public class CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    public List<Camera> getCameras(){
        return cameraRepository.findAll();
    }

    public Optional<Camera> findById(String id){
        return cameraRepository.findById(id);
    }

    public Camera addCamera(Camera camera){
        return cameraRepository.save(camera);
    }

    public Optional<Camera> updateCamera(String id, Camera camera){
        // ดึงข้อมูล Camera เดิมจาก Id
        Camera currenCamera = cameraRepository.findById(id).get();
        currenCamera.setName(camera.getName());
        currenCamera.setLocation(camera.getLocation());
        return Optional.of(cameraRepository.save(currenCamera));
    }

    public Optional<Camera> addEvent(String id, Event event){
        Camera currenCamera = cameraRepository.findById(id).get();
        List<Event> events = currenCamera.getEvents();
        event.setCreatedAt(new Date(System.currentTimeMillis()));
        events.add(event);
        currenCamera.setEvents(events);
        currenCamera.setCreatedAt(System.currentTimeMillis());
        return Optional.of(cameraRepository.save(currenCamera));
    }

    public boolean deleteCamera(String id){
        try {
            cameraRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Object setCreatedAt(long currentTimeMillis) {
        return null;
    }

}
