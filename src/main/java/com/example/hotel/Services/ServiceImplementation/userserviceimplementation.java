package com.example.hotel.Services.ServiceImplementation;

import com.example.hotel.Dto.*;
import com.example.hotel.Entity.*;
import com.example.hotel.Repoistry.*;
import com.example.hotel.Services.userservices;
import org.springframework.stereotype.Service;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class userserviceimplementation implements userservices {

    BookingRepoistry bookingRepoistry;
    HotelRepoistry hotelRepoistry;
    PaymentRepoistry paymentRepoistry;
    UserRepoistry userRepoistry;
    RoomsRepoistry roomsRepoistry;

    PassengerDetailsRepositry passengerDetailsRepositry;


    public userserviceimplementation(BookingRepoistry bookingRepoistry, HotelRepoistry hotelRepoistry, PaymentRepoistry paymentRepoistry, UserRepoistry userRepoistry, RoomsRepoistry roomsRepoistry, PassengerDetailsRepositry passengerDetailsRepositry) {
        this.bookingRepoistry = bookingRepoistry;
        this.hotelRepoistry = hotelRepoistry;
        this.paymentRepoistry = paymentRepoistry;
        this.userRepoistry = userRepoistry;
        this.roomsRepoistry = roomsRepoistry;
        this.passengerDetailsRepositry = passengerDetailsRepositry;
    }

    @Override
    public int getWeekendDaysBetweenTwoDates(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int weekendDays = 0;

        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }

        do {
            if (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                ++weekendDays;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

        return weekendDays;
    }

    @Override
    public String registeruser(RegisterDto registerDto) {
        User user=new User();
        user.setAddress(registerDto.getAddress());
        user.setRole(registerDto.getRole());
        user.setEmail(registerDto.getEmail());
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        userRepoistry.save(user);
        return "New User added";
    }

    @Override
    public String checkin(LoginDto loginDto) {
        User user=userRepoistry.findByEmail(loginDto.getEmail());
        if(user==null)
            return "Email not found";
        else if (user.getPassword().equals(loginDto.getPassword())) {
            return "Successful login";
        }
        else
            return "Invalid Credentials";
    }

    @Override
    public String addhoteldetails(HoteladdDto hoteladdDto) {
        Hotels hotels=new Hotels();
        hotels.setHotelName(hoteladdDto.getHotelName());
        hotels.setCity(hoteladdDto.getCity());
        hotels.setNearbyspots(hoteladdDto.getNearbyspots());
        hotelRepoistry.save(hotels);
        return "New Hotel Added";
    }

    @Override
    public String addroomdetails(RoomsaddDto roomsaddDto,int id) {
        Rooms rooms=new Rooms();
        Hotels hotels=hotelRepoistry.findById(id);
        rooms.setRoomtype(roomsaddDto.getRoomtype());
        rooms.setNoofpersons(roomsaddDto.getNoofpersons());
        rooms.setServices(roomsaddDto.getServices());
        rooms.setHotels(hotels);
        roomsRepoistry.save(rooms);
        return "New room Added";
    }

    @Override
    public List<HotelprintDto> showhotelsofcity(String city) {
        List<Hotels> hotels= hotelRepoistry.findByCity(city);
        List<HotelprintDto> hoteladdDtos=new ArrayList<>();
        for(Hotels i:hotels){
            HotelprintDto  dto=new HotelprintDto();
            dto.setCity(i.getCity());
            dto.setNearbyspots(i.getNearbyspots());
            dto.setHotelName(i.getHotelName());
            dto.setId(i.getId());
            hoteladdDtos.add(dto);
        }
        return hoteladdDtos;
    }

    @Override
    public List<RoomsprintDto> showroomsofhotel(int id) {
        Hotels hotel = hotelRepoistry.findById(id);
        List<RoomsprintDto> roomsprintDtos=new ArrayList<>();
        for( Rooms i:hotel.getRooms()){
            RoomsprintDto dto=new RoomsprintDto();
            dto.setId(i.getId());
            dto.setRoomtype(i.getRoomtype());
            dto.setServices(i.getServices());
            dto.setNoofpersons(i.getNoofpersons());
            roomsprintDtos.add(dto);
        }
        return roomsprintDtos;
    }

    @Override
    public List<RoomsprintDto> showavailableroomsforparticulardates(DateAvailableDto dateAvailableDto, int id) {
        Hotels hotels=hotelRepoistry.findById(id);
        List<Rooms> rooms =hotels.getRooms();
        List<Rooms> availablrooms=new ArrayList<>();
        for (Rooms i:rooms) {
            List<Booking> bookings= i.getBooking();
            boolean value=true;
            for(Booking j:bookings){
                if(dateAvailableDto.getStartDate().isAfter(j.getEndDate()))
                    value=true;
                if(dateAvailableDto.getEndDate().isBefore(j.getStartDate())) {
                    if (j.isPaid())
                        value = false;
                    if (j.isBooked())
                        value = false;
                }
                else
                    value=false;
            }
            if(value)
                availablrooms.add(i);
        }
        List<RoomsprintDto> roomsprintDtos=new ArrayList<>();
        for( Rooms i:availablrooms){
            RoomsprintDto dto=new RoomsprintDto();
            dto.setId(i.getId());
            dto.setRoomtype(i.getRoomtype());
            dto.setServices(i.getServices());
            dto.setNoofpersons(i.getNoofpersons());
            roomsprintDtos.add(dto);
        }
        return roomsprintDtos;
    }

    @Override
    public String bookroom(int id,BookDto bookDto) {
        Booking booking=new Booking();
        Rooms rooms=roomsRepoistry.findById(id);
        booking.setServices(bookDto.getServices());
        booking.setEndDate(bookDto.getEndDate());
        booking.setStartDate(bookDto.getStartDate());
        booking.setRoomsList(rooms);
        booking.setBooked(false);
        booking.setPaid(false);
        for(PassengerDetailsDto i: bookDto.getPassengerDetailsDtos())
        {
            PassengerDetails passengerDetails=new PassengerDetails();
            passengerDetails.setAddress(i.getAddress());
            passengerDetails.setName(i.getName());
            passengerDetails.setBooking(booking);
            passengerDetails.setIsAdult(i.getIsAdult());
            passengerDetails.setGender(i.getGender());
            passengerDetailsRepositry.save(passengerDetails);
        }
        bookingRepoistry.save(booking);
        return "Your Booking is processed proceed to payment page";
    }

    @Override
    public String paymentdetails(int id) {
        Booking booking=bookingRepoistry.findById(id);
        int days=((int)(ChronoUnit.DAYS.between(booking.getStartDate(),booking.getEndDate())))+1;
        Rooms rooms=booking.getRoomsList();
        Date startdate = java.sql.Date.valueOf(booking.getStartDate());
        Date enddate = java.sql.Date.valueOf(booking.getEndDate());
        int weekenddays=getWeekendDaysBetweenTwoDates(startdate,enddate);
        double price=(days*1000)+(weekenddays*100);
        if(rooms.getServices().equals("AC"))
            price+=200;
        else if (rooms.getServices().equals("PREMIMUM SUITE")) {
            price+=500;
        }
        else if(rooms.getServices().equals("DELUXE")){
            price+=350;
        }
        else
            price+=0;
        Payment payment=new Payment();
        payment.setBook(booking);
        payment.setCost(price);
        paymentRepoistry.save(payment);
        return "The amount for booking is" + price +" rupees.";
    }

    @Override
    public String paymentstatus(int id, paymentStatusDto paymentStatusDTo) {
        Payment payment=paymentRepoistry.findPaymentById(id);
        payment.setStatus(paymentStatusDTo.isPaid());
        if(paymentStatusDTo.isPaid()){
            Booking booking=payment.getBook();
            booking.setPaid(true);
            booking.setBooked(true);
            return "Your Payment is recieved";
        }
        else
        {
            return "Please Try Again";
        }
    }

    @Override
    public BookingStatus showstatus(int id) {
        Booking booking=bookingRepoistry.findById(id);
        Payment payment=booking.getPayments();
        User user=booking.getUser();
        BookingStatus bookingStatus=new BookingStatus();
        bookingStatus.setBookId(booking.getId());
        bookingStatus.setPaid(booking.isPaid());
        bookingStatus.setCost(payment.getCost());
        bookingStatus.setBooked(booking.isBooked());
        bookingStatus.setUsername(user.getUsername());
        bookingStatus.setStartDate(booking.getStartDate());
        bookingStatus.setEndDate(booking.getEndDate());
        bookingStatus.setServices(booking.isServices());
        bookingStatus.setRoomNo(bookingStatus.getRoomNo());
        bookingStatus.setPaymentid(payment.getId());
        return bookingStatus;
    }

    @Override
    public String cancelticket(int id) {
        Booking booking=bookingRepoistry.findById(id);
        booking.setBooked(false);
        return "Your booking is cancelled Successfully";
    }
}



