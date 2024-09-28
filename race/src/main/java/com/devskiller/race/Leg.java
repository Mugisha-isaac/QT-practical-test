package com.devskiller.race;

import java.time.Duration;
import java.time.LocalDateTime;


/**
 * Author: Isaac MUGISHA
 */
record Leg(Location startLocation, Location finishLocation, Duration duration, double distance) {

    Leg(Location startLocation, LocalDateTime startLocalTime, Location finishLocation, LocalDateTime finishLocalTime) {
        this(startLocation, finishLocation,
                calculateDuration(startLocation, startLocalTime, finishLocation, finishLocalTime),
                DistanceCalculator.betweenPoints(startLocation.point(), finishLocation.point()));
    }

    private static Duration calculateDuration(Location startLocation, LocalDateTime startLocalTime,
                                              Location finishLocation, LocalDateTime finishLocalTime) {
        return Duration.between(startLocalTime.atZone(startLocation.zone()), finishLocalTime.atZone(finishLocation.zone()));
    }
}
