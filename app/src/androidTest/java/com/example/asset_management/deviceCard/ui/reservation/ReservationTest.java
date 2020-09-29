package com.example.asset_management.deviceCard.ui.reservation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.IOException;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.*;

public class ReservationTest {

    @Before
    public void setUp() throws Exception {
    }
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void isCorrectFilled() throws IOException {
        Reservation reservation = new Reservation();
        String start = "Wednesday, September 23, 2020";
        String end = "Wednesday, September 30, 2020";
        Assert.assertTrue(reservation.isCorrectFilled(start, end, reservation, getApplicationContext()));
    }
    @Test
    public void isNotCorrectFilled() throws IOException {
        Reservation reservation = new Reservation();
        String end = "";
        String start = "";
        Assert.assertFalse(reservation.isCorrectFilled(start, end, reservation, getApplicationContext()));
    }
}