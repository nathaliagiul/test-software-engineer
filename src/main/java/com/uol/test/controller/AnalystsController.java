package com.uol.test.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/im-day")
public class AnalystsController {
	
	@GetMapping
	public Map<LocalDate, String> analystDay() {
		List<DayOfWeek> includedDays = Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY,DayOfWeek.WEDNESDAY,DayOfWeek.THURSDAY,DayOfWeek.FRIDAY);

		List<String> analysts = Arrays.asList("João", "Maria", "Zeca", "Mario", "Gustavo", "Camila", "Pedro", "Juliana", "Gisele");
		
		LocalDate start = LocalDate.now().minusDays(1);
		LocalDate end = LocalDate.now().plusDays(12);
		
		List<LocalDate> dates = Stream.iterate(start, date -> date.plusDays(1))
		    .limit(ChronoUnit.DAYS.between(start, end))
		    .filter(d -> includedDays.contains(d.getDayOfWeek()))
		    .collect(Collectors.toList());
		
		Map<LocalDate, String> map = 
				IntStream.range(0, Math.min(dates.size(), analysts.size()))
			    .boxed()
			    .collect(Collectors.toMap(dates::get, analysts::get));
		
		return map;
	}	
}
