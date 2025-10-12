package com.example.travelappapi.controller;

import com.example.travelappapi.entity.DatTour;
import com.example.travelappapi.service.DatTourService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class DatTourController {
    private final DatTourService datTourService;

    @PostMapping
    public DatTour createBooking(@RequestBody DatTour datTour) {
        return datTourService.createBooking(datTour);
    }

    @GetMapping("/user/{maNguoiDung}")
    public List<DatTour> getBookingsByUser(@PathVariable Integer maNguoiDung) {
        return datTourService.getBookingsByUser(maNguoiDung);
    }

    @GetMapping("/{maDatTour}")
    public DatTour getBookingById(@PathVariable Integer maDatTour) {
        return datTourService.getBookingById(maDatTour);
    }
}

