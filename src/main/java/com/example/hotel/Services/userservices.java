package com.example.hotel.Services;

import com.example.hotel.Dto.*;
import com.example.hotel.Entity.Hotels;
import com.example.hotel.Entity.Rooms;
import com.example.hotel.Entity.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface userservices {

    public  int getWeekendDaysBetweenTwoDates(Date startDate, Date endDate);

    public String registeruser(RegisterDto registerDto);

    public String checkin(LoginDto loginDto);

    public String addhoteldetails(HoteladdDto hoteladdDto);

    public String addroomdetails(RoomsaddDto roomsaddDto,int id);

    public List<HotelprintDto> showhotelsofcity(String city);

    public List<RoomsprintDto> showroomsofhotel(int id);

    public List<RoomsprintDto> showavailableroomsforparticulardates(DateAvailableDto dateAvailableDto,int id);

    public String bookroom(int id,BookDto bookDto);

    public String paymentdetails(int id);

    public String paymentstatus(int id,paymentStatusDto paymentStatusDto);

    public BookingStatus showstatus(int id);

    public String cancelticket(int id);

}
