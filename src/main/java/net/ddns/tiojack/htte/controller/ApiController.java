package net.ddns.tiojack.htte.controller;

import net.ddns.tiojack.htte.model.Lineup;
import net.ddns.tiojack.htte.model.Ratings;
import net.ddns.tiojack.htte.service.RatingService;
import net.ddns.tiojack.htte.service.TrainingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ApiController {

    private final RatingService ratingService;
    private final TrainingService trainingService;

    public ApiController(final RatingService ratingService, final TrainingService trainingService) {
        this.ratingService = ratingService;
        this.trainingService = trainingService;
    }

    @PostMapping("/rating")
    public Ratings rating(@RequestBody final Lineup startingLineup) {
        return this.ratingService.getRatings(startingLineup);
    }

    @PostMapping("/training")
    public String training() {
        return this.trainingService.getTraining();
    }

}
