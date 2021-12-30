package net.ddns.tiojack.htte.controller;

import net.ddns.tiojack.htte.service.RatingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(final RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public String rating() {
        return this.ratingService.getRatings();
    }

}
