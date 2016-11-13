package com.huskygames.rekhid.slugger.actor.AI;

import com.huskygames.rekhid.actor.StickMan;
import com.huskygames.rekhid.slugger.input.ButtonEvent;
import com.huskygames.rekhid.slugger.util.DoublePair;

import java.util.LinkedList;
import java.util.Queue;

import static com.huskygames.rekhid.slugger.actor.AI.StateTypes.ATTACK;
import static com.huskygames.rekhid.slugger.actor.AI.StateTypes.APPROACH;
import static com.huskygames.rekhid.slugger.actor.AI.StateTypes.IDLE;
import static com.huskygames.rekhid.slugger.actor.AI.StateTypes.BLOCK;
import static com.huskygames.rekhid.slugger.actor.AI.StateTypes.RETREATLEFT;
import static com.huskygames.rekhid.slugger.actor.AI.StateTypes.RETREATRIGHT;
import static com.huskygames.rekhid.slugger.input.ButtonType.ATTACK_BUTTON;
import static com.huskygames.rekhid.slugger.input.ButtonType.JUMP_BUTTON;
import static com.huskygames.rekhid.slugger.input.ButtonType.SHIELD_BUTTON;

/**
 * Created by brian on 10/22/2016.
 */
    public class FsmProf{

        protected int difficulty;
        protected StateTypes activeState;
        protected LinkedList<StateTypes> queuedStates;
        protected StickMan meAIPlayer;
        protected StickMan firstPlayer;
        private int left = 1;
        private int right = 3;
        //TODO: Eventually change FPlayer to list of enemy players
        public FsmProf(int Difficulty, StickMan AIPlayer, StickMan FPlayer) {
            this.difficulty = Difficulty;
            this.queuedStates = new LinkedList<StateTypes>();
            this.queuedStates.add(IDLE);
            activeState = IDLE;
            firstPlayer = FPlayer;
            meAIPlayer = AIPlayer;
        }

        public void pushState(StateTypes addState) { queuedStates.addFirst(addState); }

        public void update() {
            double fpX = firstPlayer.getPosition().getX();
            double aiX = meAIPlayer.getPosition().getX();
            double fightorFlight = Math.random() * 10;

            if(Math.abs(fpX - aiX) > 500) {
                this.pushState(APPROACH);
            }else if(Math.abs(fpX - aiX) > 200){
                this.pushState(IDLE);
            }else if(firstPlayer.getVelocity().getX() < 0.5 && Math.abs(fpX - aiX)<50){
                if(fightorFlight > 3) {
                    this.pushState(ATTACK);
                }else{
                    this.pushState(BLOCK);
                }
            }else if(Math.abs(fpX - aiX) < 200 && firstPlayer.getDamage() > 2.0){
                if(aiX <600 || aiX > fpX ){
                    this.pushState(RETREATRIGHT);
                }else {
                    this.pushState(RETREATLEFT);
                }
            }else {
                this.pushState(BLOCK);
            }
            popState();
        }

        public void popState(){
            Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
            StateTypes statecase = queuedStates.pop();
            activeState = statecase;

            switch(statecase) {
                case ATTACK:
                    buttonEvents.add(new ButtonEvent(ATTACK_BUTTON, meAIPlayer, System.nanoTime()));
                    meAIPlayer.AIreadController(buttonEvents, -1);
                    break;
                case BLOCK:
                    buttonEvents.add(new ButtonEvent(SHIELD_BUTTON, meAIPlayer, System.nanoTime()));
                    meAIPlayer.AIreadController(buttonEvents, -1);
                    break;
                case RETREATLEFT:
                    //  buttonEvents.add( new ButtonEvent(JUMP_BUTTON, meAIPlayer, System.nanoTime()) );
                    meAIPlayer.AIreadController(buttonEvents, left);
                    break;
                case RETREATRIGHT:
                    //  buttonEvents.add( new ButtonEvent(JUMP_BUTTON, meAIPlayer, System.nanoTime()) );
                    meAIPlayer.AIreadController(buttonEvents, right);
                    break;
                case IDLE:
                    meAIPlayer.AIreadController(buttonEvents, -1);
                    break;
                case APPROACH:
                    if (meAIPlayer.getPosition().getX() > firstPlayer.getPosition().getX()){
                        meAIPlayer.AIreadController(buttonEvents, left);
                    }else{
                        meAIPlayer.AIreadController(buttonEvents, right);
                    }
                    break;
                default:
                    break;
            }
        }
        public StateTypes activeState(){
            return activeState;
        }
}
