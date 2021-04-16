package fr.reminy.pokemon_discord.listeners;

import com.vdurmont.emoji.EmojiParser;
import fr.reminy.pokemon_discord.game.GameManager;
import fr.reminy.pokemon_discord.game.PokemonGame;
import org.javacord.api.entity.emoji.Emoji;
import org.javacord.api.event.message.reaction.ReactionAddEvent;
import org.javacord.api.event.message.reaction.ReactionRemoveEvent;
import org.javacord.api.listener.message.reaction.ReactionAddListener;
import org.javacord.api.listener.message.reaction.ReactionRemoveListener;

public class ReactionListener implements ReactionAddListener, ReactionRemoveListener {
    @Override
    public void onReactionAdd(ReactionAddEvent event) {
        if(event.getReaction().isEmpty()
                || event.getReaction().get().getEmoji().asUnicodeEmoji().isEmpty()
                || event.getMessage().isEmpty()
                || event.getMessageAuthor().isEmpty()
                || event.getMessageAuthor().get().asUser().isEmpty()
                || event.getUser().isEmpty()
                || event.getUser().get().isBot()) {
            return;
        }

        PokemonGame playerGame = GameManager.INSTANCE.getGameByMessage(event.getMessage().get());
        if(playerGame == null) {
            return;
        }

        if(!playerGame.canPlay(event.getUser().get())) {
            return;
        }

        Emoji em = event.getReaction().get().getEmoji();
        if(eq(em, ":arrow_up:")) {
            playerGame.getPlayer().moveUp();
        }else if(eq(em, ":arrow_right:")) {
            playerGame.getPlayer().moveRight();
        }else if(eq(em, ":arrow_down:")) {
            playerGame.getPlayer().moveDown();
        }else if(eq(em, ":arrow_left:")) {
            playerGame.getPlayer().moveLeft();
        }
        playerGame.update();
    }

    @Override
    public void onReactionRemove(ReactionRemoveEvent event) {
        if(event.getReaction().isEmpty()
                || event.getReaction().get().getEmoji().asUnicodeEmoji().isEmpty()
                || event.getMessage().isEmpty()
                || event.getMessageAuthor().isEmpty()
                || event.getMessageAuthor().get().asUser().isEmpty()
                || event.getUser().isEmpty()
                || event.getUser().get().isBot()) {
            return;
        }

        PokemonGame playerGame = GameManager.INSTANCE.getGameByMessage(event.getMessage().get());
        if(playerGame == null) {
            return;
        }

        if(!playerGame.canPlay(event.getUser().get())) {
            return;
        }

        Emoji em = event.getReaction().get().getEmoji();
        if(eq(em, ":arrow_up:")) {
            playerGame.getPlayer().moveUp();
        }else if(eq(em, ":arrow_right:")) {
            playerGame.getPlayer().moveRight();
        }else if(eq(em, ":arrow_down:")) {
            playerGame.getPlayer().moveDown();
        }else if(eq(em, ":arrow_left:")) {
            playerGame.getPlayer().moveLeft();
        }
        playerGame.update();
    }

    private boolean eq(Emoji emoji, String unicode) {
        return emoji.equalsEmoji(EmojiParser.parseToUnicode(unicode));
    }
}
