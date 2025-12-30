package splitable.backend.billscanner.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//import splitable.backend.billscanner.service.VisionService;



@CrossOrigin(origins = "http://localhost:5173") //Allows all requests from http://localhost:5173
@RestController
@RequestMapping("/api/bill")
public class BillController {
    // private final VisionService visionService;

    // //initializing the service
    // public BillController(VisionService visionService) {
    //     this.visionService = visionService;
    // }

    //Post Request Mapping 
    @PostMapping("/scan")
    //binds the bill sent from front end
    public String scanBill(@RequestParam("bill") MultipartFile file) throws IOException { 
        System.out.println("Received file: " + file.getOriginalFilename());
        return "File received successfully";
    }
}
