package fr.reminy.pokemon_discord.game.data;

import fr.reminy.pokemon_discord.game.entity.Character;
import fr.reminy.pokemon_discord.game.entity.Player;

public interface Event {
    void executeOn(Character character);
}
