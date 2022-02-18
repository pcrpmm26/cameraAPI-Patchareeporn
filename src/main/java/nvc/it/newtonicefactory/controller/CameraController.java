package nvc.it.newtonicefactory.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nvc.it.newtonicefactory.model.Camera;
import nvc.it.newtonicefactory.model.Event;
import nvc.it.newtonicefactory.service.CameraService;

@RestController
@RequestMapping("/camera")
public class CameraController {

    @Autowired
    private CameraService cameraService;

    //get:/camera
    @GetMapping("")
    public ResponseEntity<Object> getAllCamera(){
        List<Camera> cameras = cameraService.getCameras();
        HashMap<String, Object> map = new HashMap<>();
        map.put("Msg", "Search Success!");
        map.put("data", cameras);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    //get:/camera{id}
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCameraById(@PathVariable String id){
        HashMap<String, Object> map = new HashMap<>();
        map.put("Msg", "Search By Id Success!");
        map.put("data", cameraService.findById(id));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    //post:/camera
    @PostMapping("")
    public ResponseEntity<Object> addCamera(@RequestBody Camera camera){
        HashMap<String, Object> map = new HashMap<>();
        map.put("Msg", "Add Camera Success!");
        map.put("data", cameraService.addCamera(camera));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCamera(@PathVariable String id, @RequestBody Camera camera){
        HashMap<String, Object> map = new HashMap<>();
        map.put("Msg", "Update Camera Success!");
        map.put("data", cameraService.updateCamera(id, camera));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    //patch:/camera{id}
    @PatchMapping("/{id}")
    public ResponseEntity<Object> addEvent(@PathVariable String id, @RequestBody Event event){
        HashMap<String, Object> map = new HashMap<>();
        map.put("Msg", "Add Event Success!");
        map.put("data", cameraService.addEvent(id, event));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCamera(@PathVariable String id){
        HashMap<String, Object> map = new HashMap<>();
        if (cameraService.deleteCamera(id)) {
            map.put("Msg", "Delete Success");
            return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            map.put("Msg", "Error Delete");
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }
    }
}
