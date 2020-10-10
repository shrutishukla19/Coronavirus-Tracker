package io.shrula.coronavirustracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import models.LocationStats;



@Controller
public class HomeController {
	@Autowired
	CoronaVirusData coronaVirusData;
@GetMapping("/")
	public String home(Model model)
	{
		List<LocationStats> allStats=coronaVirusData.getAllStats();
		int totalReportedCases=allStats.stream().mapToInt(stat -> stat.getTotalCases()).sum();
		int totalNewCases=allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats",allStats);
		model.addAttribute("totalReportedCases",totalReportedCases);
		model.addAttribute("totalNewCases",totalNewCases);
		return "home";
	}
}
