package com.devskiller.race;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


/**
 * Author: Isaac MUGISHA
 */
record Race(List<Leg> legs) {

	Race() {
		this(new ArrayList<>());
	}

	/**
	 * Adds leg to the race
	 *
	 * @param leg to add
	 * @throws IllegalStartPointException when start point of the leg is different from the finish point of the last leg
	 */
	void addLeg(Leg leg) throws IllegalStartPointException {
		if (!legs.isEmpty() && !legs.get(legs.size() - 1).finishLocation().equals(leg.startLocation())) {
			throw new IllegalStartPointException();
		}
		legs.add(leg);
	}

	/**
	 * @return number of legs in the race
	 */
	int getLegsCount() {
		return legs.size();
	}

	Duration getTotalDuration() {
		return legs.stream()
				.map(Leg::duration)
				.reduce(Duration.ZERO, Duration::plus);
	}

	Duration getAverageLegDuration() {
		return Duration.ofSeconds((long) (getTotalDuration().getSeconds() / getLegsCount()));
	}

	double getTotalDistance() {
		return legs.stream()
				.mapToDouble(Leg::distance)
				.sum();
	}

	double getAverageLegDistance() {
		return getTotalDistance() / getLegsCount();
	}

}
