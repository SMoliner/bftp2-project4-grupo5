package org.factoriaf5.bftp2project4grupo5.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SampleDataLoader {

    private org.factoriaf5.bftp2project4grupo5.repositories.LegacyGameRepository legacyGameRepository;

    @Autowired
    public SampleDataLoader(org.factoriaf5.bftp2project4grupo5.repositories.LegacyGameRepository legacyGameRepository) {
        this.legacyGameRepository = legacyGameRepository;
    }

    @PostConstruct
    public void loadSampleData() {
        legacyGameRepository.saveAll(List.of(

                new Game("Duck Hunt", "Nes", 1984, "Shooter", 12, 79.99, "https://i.ibb.co/ccL3wCV/DUCK-HUNT.jpg" ),
                new Game("Nintendogs", "DS", 2005, "Simulation", 3, 28.99,  "https://i.ibb.co/Jvk8H21/nintendogs.jpg"),
                new Game("Halo 3", "X360", 2007, "Shooter", 12 , 19.99, "https://i.ibb.co/fxM3RLX/halo3.jpg" ),
                new Game( "Super Mario Galaxy", "Wii",2007, "Fantasy",7, 50.99, "https://i.ibb.co/WV0D1cP/supermario-galaxi.jpg"),
                new Game("Grand Theft Auto IV","X360",2008,"Action",12, 40.99, "https://i.ibb.co/gVdrCpc/Grand-Theft-Auto-IV-The-Lost-and-Damned.png"),
                new Game("Wii Fit", "Wii", 2007, "Sports", 3, 20.99, "https://i.ibb.co/hYRgZwb/wiiFit.png"),
                new Game("Wii Fit Plus", "Wii", 2009, "Sports", 3, 39.99 , "https://i.ibb.co/R90njp2/wiifitplus-logo1.jpg" ),
                new Game( "Animal Crossing:Wild World", "DS", 2005, "Simulation", 3, 39.99, "https://i.ibb.co/1f5fzCZ/ANIMAL-CROSSING-WILD-WORLD.jpg"),
                new Game("Lego Harry Potter Collection","Ps4", 2016, "Fantasy", 7, 15.99 , "https://i.ibb.co/pZs7fqH/Lego-Harry-Potter-collection.png"),
                new Game("Gran Turismo 4", "PS2", 2004, "Racing", 12, 35.99, "https://i.ibb.co/bzsCnrw/GRAN-TURISMO-4.jpg"),
                new Game("Grand Theft Auto V", "X360", 2013, "Action", 18, 40.99 , "https://i.ibb.co/jwpgbTV/GRAN-THEFT-AUTO-5.jpg"),
                new Game("Grand Theft Auto Vice City", "PS2", 2002, "Action", 18, 22.99 , "https://i.ibb.co/KVSMB07/vicecity.jpg" ),
                new Game("Pokemon Rubby Pokemon Sapphire", "GBA", 2002, "Roye-Playing", 12, 40.99 , "https://i.ibb.co/kMc162L/pokemon.jpg"),
                new Game("Pokemon Black Pokemon White", "DS", 2010, "Role-Playing", 12, 10.49 , "https://i.ibb.co/s9JybhY/pokemon-black.png"),
                new Game("Brain Age 2 More training in minutes a day","DS", 2005, "Puzzle", 3, 10.98,"https://i.ibb.co/w4zHRQ1/BRAIN-AGE-2-MORE-TRAINING-IN-MINUTES-A-DAY-copia.png"),
                new Game("Pacman", "Ps4", 1982, "Puzzle", 3 , 30.99, "https://i.ibb.co/Fn7TBQX/pacman.png"),
                new Game("Tetris", "Nintendo", 1989, "Puzzle", 7 , 32.99 , "https://i.ibb.co/tPntP4T/tetris.jpg"),
                new Game("Minecraft", "X360", 2013, "Misc", 7 ,23.99 , "https://i.ibb.co/TtFhWLr/minicraf.png")

        ));
    }
}


