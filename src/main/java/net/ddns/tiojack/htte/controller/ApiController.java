package net.ddns.tiojack.htte.controller;

import net.ddns.tiojack.htte.model.Lineup;
import net.ddns.tiojack.htte.model.PlayerTrainingRQ;
import net.ddns.tiojack.htte.model.Ratings;
import net.ddns.tiojack.htte.model.TeamTrainingRQ;
import net.ddns.tiojack.htte.model.TeamTrainingRS;
import net.ddns.tiojack.htte.service.PlayerTrainingService;
import net.ddns.tiojack.htte.service.RatingService;
import net.ddns.tiojack.htte.service.TeamTrainingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ApiController {

    private final RatingService ratingService;
    private final PlayerTrainingService playerTrainingService;
    private final TeamTrainingService teamTrainingService;

    public ApiController(
            final RatingService ratingService,
            final PlayerTrainingService playerTrainingService,
            final TeamTrainingService teamTrainingService
    ) {
        this.ratingService = ratingService;
        this.playerTrainingService = playerTrainingService;
        this.teamTrainingService = teamTrainingService;
    }

    @PostMapping("/rating")
    public Ratings rating(@RequestBody final Lineup startingLineup) {
        return this.ratingService.getRatings(startingLineup);
    }

    @PostMapping("/training")
    public double training(@RequestBody final PlayerTrainingRQ playerTrainingRQ) {
        return this.playerTrainingService.getPlayerTraining(playerTrainingRQ);
    }

    @PostMapping("/teamTraining")
    public TeamTrainingRS teamTraining(@RequestBody final TeamTrainingRQ teamTrainingRQ) {
        return this.teamTrainingService.getTeamTraining(teamTrainingRQ);
    }
}
