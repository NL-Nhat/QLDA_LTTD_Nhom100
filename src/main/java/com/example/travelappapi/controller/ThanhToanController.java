package com.example.travelappapi.controller;

import com.example.travelappapi.entity.ThanhToan;
import com.example.travelappapi.service.ThanhToanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class ThanhToanController {
    private final ThanhToanService thanhToanService;

    @PostMapping
    public ThanhToan createPayment(@RequestBody ThanhToan thanhToan) {
        return thanhToanService.createPayment(thanhToan);
    }

    @GetMapping("/booking/{maDatTour}")
    public List<ThanhToan> getPaymentsByBooking(@PathVariable Integer maDatTour) {
        return thanhToanService.getPaymentsByDatTour(maDatTour);
    }
}
