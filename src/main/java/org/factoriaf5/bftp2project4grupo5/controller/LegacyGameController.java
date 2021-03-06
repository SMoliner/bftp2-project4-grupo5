package org.factoriaf5.bftp2project4grupo5.controller;

import org.factoriaf5.bftp2project4grupo5.repositories.Game;
import org.factoriaf5.bftp2project4grupo5.repositories.LegacyGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


@Controller
public class LegacyGameController {

    private LegacyGameRepository legacyGameRepository;

    @Autowired
    public LegacyGameController(LegacyGameRepository legacyGameRepository) {
        this.legacyGameRepository = legacyGameRepository;

    }

    @GetMapping("/games")
    String listGames(Model model) {
        List<Game> games = legacyGameRepository.findAll();
        model.addAttribute("title", "Game list");
        model.addAttribute("games", games);
        return "games/all";
    }

    @GetMapping("/games/new")
    String newGame(Model model) {
        Game game = new Game();
        model.addAttribute("game", game);
        model.addAttribute("title", "Create new game");
        return "games/edit";
    }

    @PostMapping("/games/new")
    String addGame(@ModelAttribute Game game) {
        legacyGameRepository.save(game);
        return "redirect:/games";
    }
    @GetMapping("/games/{id}/edit")
    String editGame(Model model, @PathVariable Long id) {
        Game game = legacyGameRepository.findById(id).get();
        model.addAttribute("game", game);
        model.addAttribute("title", "Edit game");
        return "games/edit";
    }

    @GetMapping("/games/{id}/delete")
    String deleteGame(@PathVariable Long id){
        legacyGameRepository.deleteById(id);
        return "redirect:/games";
    }
}




