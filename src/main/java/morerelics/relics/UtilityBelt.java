package morerelics.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.PostDrawSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class UtilityBelt extends CustomRelic implements PostDrawSubscriber {
    public static final String ID = "UtilityBelt";
    private static final String IMG = "img/relics/UtilityBelt.png";
    
    private boolean firstTurn = false;
    
    public UtilityBelt() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.COMMON, LandingSound.CLINK);
        BaseMod.subscribeToPostDraw(this);
    } 
    
    public void receivePostDraw(AbstractCard c) {
        if (firstTurn) {
            if (c.isInnate) {
                flash();
                AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractDungeon.actionManager.addToTop(new DrawCardAction(AbstractDungeon.player, 1));
            }
        }
    }
    
    @Override
    public void atPreBattle() {
        firstTurn = true;
    }
    
    @Override
    public void onPlayerEndTurn() {
        firstTurn = false;
    }
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
   
    @Override
    public AbstractRelic makeCopy() {
        return new UtilityBelt();
    }
}