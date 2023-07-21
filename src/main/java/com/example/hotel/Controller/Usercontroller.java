package com.example.hotel.Controller;

import com.example.hotel.Dto.*;
import com.example.hotel.Services.userservices;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class Usercontroller {
  userservices Userservices;

    public Usercontroller(userservices userservices) {
        Userservices = userservices;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterDto registerDto){
        return Userservices.registeruser(registerDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto){
        return Userservices.checkin(loginDto);
    }
    @PostMapping("/hoteladd")
    public String addhotel(@RequestBody HoteladdDto hoteladdDto){
        return Userservices.addhoteldetails(hoteladdDto);
    }

    @PostMapping("/roomadd/{id}")
    public String addroom(@RequestBody RoomsaddDto roomsaddDto,@PathVariable("id") int id){
       return Userservices.addroomdetails(roomsaddDto,id);
    }

    @GetMapping("/city/{city}")
    public  List<HotelprintDto>  cities(@PathVariable("city") String city){
       return Userservices.showhotelsofcity(city);
    }

    @GetMapping("/hotels/{id}")
    public List<RoomsprintDto> hotels (@PathVariable("id") int id){
        return Userservices.showroomsofhotel(id);
    }

    @PostMapping("/bookingdate/{id}")
    public List<RoomsprintDto> availablerooms(@RequestBody DateAvailableDto dateAvailableDto,@PathVariable("id") int id){
       return Userservices.showavailableroomsforparticulardates(dateAvailableDto,id);

    }

    @PostMapping("/book/{id}")
    public String book(@PathVariable("id") int id,@RequestBody BookDto bookDto){
       return Userservices.bookroom(id,bookDto);
    }

    @GetMapping("/pay/{id}")
    public String payment(@PathVariable("id") int id){
        return Userservices.paymentdetails(id);
    }

    @PostMapping("/confirmpay/{id}")
    public String status(@PathVariable("id") int id,@RequestBody paymentStatusDto paymentStatusdto){
        return Userservices.paymentstatus(id,paymentStatusdto);
    }

    @GetMapping("/bookingstatus/{id}")
    public BookingStatus bookingstatus(@PathVariable("id") int id){
        return Userservices.showstatus(id);
    }

    @PostMapping("cancel/{id}")
    public String cancelbooking(@PathVariable ("id") int id){
        return Userservices.cancelticket(id);
    }
}
