package com.wrestlingapp.service;

import com.wrestlingapp.entity.*;
import com.wrestlingapp.repository.WrestlerRepository;
import com.wrestlingapp.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataInitializationService implements ApplicationRunner {

    @Autowired
    private WrestlerRepository wrestlerRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (wrestlerRepository.count() == 0) {
            initializeWrestlers();
        }
        
        if (tournamentRepository.count() == 0) {
            initializeTournaments();
        }
    }

    private void initializeWrestlers() {
        // Men's wrestlers
        Wrestler wrestler1 = new Wrestler("John", "Smith", LocalDate.of(1995, 3, 15), 
                                        74.0, WeightCategory.MEN_74KG, "USA");
        wrestler1.setClub("Team USA Wrestling");
        wrestler1.setCoach("Mike Johnson");
        wrestler1.setWins(25);
        wrestler1.setLosses(5);
        wrestler1.setDraws(2);
        wrestler1.setTotalPoints(450);
        wrestler1.setRanking(1);

        Wrestler wrestler2 = new Wrestler("Ivan", "Petrov", LocalDate.of(1993, 7, 22), 
                                        86.0, WeightCategory.MEN_86KG, "Russia");
        wrestler2.setClub("Dynamo Moscow");
        wrestler2.setCoach("Vladimir Kozlov");
        wrestler2.setWins(30);
        wrestler2.setLosses(3);
        wrestler2.setDraws(1);
        wrestler2.setTotalPoints(520);
        wrestler2.setRanking(1);

        Wrestler wrestler3 = new Wrestler("Hassan", "Ali", LocalDate.of(1994, 11, 8), 
                                        65.0, WeightCategory.MEN_65KG, "Iran");
        wrestler3.setClub("Iran National Team");
        wrestler3.setCoach("Ahmad Reza");
        wrestler3.setWins(28);
        wrestler3.setLosses(4);
        wrestler3.setDraws(0);
        wrestler3.setTotalPoints(485);
        wrestler3.setRanking(1);

        // Women's wrestlers
        Wrestler wrestler4 = new Wrestler("Sarah", "Johnson", LocalDate.of(1996, 5, 12), 
                                        57.0, WeightCategory.WOMEN_57KG, "USA");
        wrestler4.setClub("Team USA Wrestling");
        wrestler4.setCoach("Linda Williams");
        wrestler4.setWins(22);
        wrestler4.setLosses(3);
        wrestler4.setDraws(1);
        wrestler4.setTotalPoints(380);
        wrestler4.setRanking(1);

        Wrestler wrestler5 = new Wrestler("Yuki", "Tanaka", LocalDate.of(1995, 9, 18), 
                                        50.0, WeightCategory.WOMEN_50KG, "Japan");
        wrestler5.setClub("Japan Wrestling Federation");
        wrestler5.setCoach("Hiroshi Sato");
        wrestler5.setWins(26);
        wrestler5.setLosses(2);
        wrestler5.setDraws(0);
        wrestler5.setTotalPoints(420);
        wrestler5.setRanking(1);

        wrestlerRepository.save(wrestler1);
        wrestlerRepository.save(wrestler2);
        wrestlerRepository.save(wrestler3);
        wrestlerRepository.save(wrestler4);
        wrestlerRepository.save(wrestler5);
    }

    private void initializeTournaments() {
        Tournament tournament1 = new Tournament("World Championship 2024", 
                                               LocalDate.of(2024, 9, 15), 
                                               LocalDate.of(2024, 9, 22), 
                                               WrestlingStyle.FREESTYLE);
        tournament1.setDescription("The premier international wrestling championship");
        tournament1.setVenue("Wrestling Palace");
        tournament1.setCity("Belgrade");
        tournament1.setCountry("Serbia");
        tournament1.setStatus(TournamentStatus.UPCOMING);
        tournament1.setMaxParticipants(200);
        tournament1.setPrizeMoney(100000.0);
        tournament1.setOrganizer("United World Wrestling");

        Tournament tournament2 = new Tournament("Olympic Games 2024", 
                                               LocalDate.of(2024, 7, 26), 
                                               LocalDate.of(2024, 8, 11), 
                                               WrestlingStyle.FREESTYLE);
        tournament2.setDescription("Olympic Wrestling Competition");
        tournament2.setVenue("Champ-de-Mars Arena");
        tournament2.setCity("Paris");
        tournament2.setCountry("France");
        tournament2.setStatus(TournamentStatus.COMPLETED);
        tournament2.setMaxParticipants(300);
        tournament2.setOrganizer("International Olympic Committee");

        tournamentRepository.save(tournament1);
        tournamentRepository.save(tournament2);
    }
}