package com.huskygames.rekhid.slugger.actor.AI;

import com.huskygames.rekhid.actor.StickMan;
import com.huskygames.rekhid.slugger.input.ButtonEvent;
import com.huskygames.rekhid.slugger.util.DoublePair;

import java.util.LinkedList;
import java.util.Queue;

import static com.huskygames.rekhid.slugger.input.ButtonType.ATTACK_BUTTON;
import static com.huskygames.rekhid.slugger.input.ButtonType.JUMP_BUTTON;
import static com.huskygames.rekhid.slugger.input.ButtonType.SHIELD_BUTTON;

/**
 * Created by brian on 10/22/2016.
 */
    public class FsmProf{

        protected int difficulty;
        protected String activeState;
        protected LinkedList<String> queuedStates;
        protected StickMan meAIPlayer;
        protected StickMan firstPlayer;
        private int left = 1;
        private int right = 3;
        //TODO: Eventually change FPlayer to list of enemy players
        public FsmProf(int Difficulty, StickMan AIPlayer, StickMan FPlayer) {
            this.difficulty = Difficulty;
            this.queuedStates = new LinkedList<String>();
            this.queuedStates.add("Idle");
            activeState = "idle";
            firstPlayer = FPlayer;
            meAIPlayer = AIPlayer;
        }

        public void pushState(String addState) { queuedStates.addFirst(addState); }

        public void update() {
            double fpX = firstPlayer.getPosition().getX();
            double aiX = meAIPlayer.getPosition().getX();
            double fightorFlight = Math.random() * 10;

            if(Math.abs(fpX - aiX) > 500) {
                this.pushState("Approach");
            }else if(Math.abs(fpX - aiX) > 200){
                this.pushState("Idle");
            }else if(firstPlayer.getVelocity().getX() < 0.5 && Math.abs(fpX - aiX)<50){
                if(fightorFlight > 3) {
                    this.pushState("Attack");
                }else{
                    this.pushState("Block");
                }
            }else if(Math.abs(fpX - aiX) < 200 && firstPlayer.getDamage() > 2.0){
                if(aiX <600 || aiX > fpX ){
                    this.pushState("RetreatRight");
                }else {
                    this.pushState("RetreatLeft");
                }
            }else {
                this.pushState("Block");
            }
            popState();
        }

        public void popState(){
            Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
            String statecase = queuedStates.pop();
            activeState = statecase;

            switch(statecase) {
                case "Attack":
                    buttonEvents.add(new ButtonEvent(ATTACK_BUTTON, meAIPlayer, System.nanoTime()));
                    meAIPlayer.AIreadController(buttonEvents, -11);
                    break;
                case "Block":
                    buttonEvents.add(new ButtonEvent(SHIELD_BUTTON, meAIPlayer, System.nanoTime()));
                    meAIPlayer.AIreadController(buttonEvents, -1);
                    break;
                case "RetreatLeft":
                    //  buttonEvents.add( new ButtonEvent(JUMP_BUTTON, meAIPlayer, System.nanoTime()) );
                    meAIPlayer.AIreadController(buttonEvents, left);
                    break;
                case "RetreatRight":
                    //  buttonEvents.add( new ButtonEvent(JUMP_BUTTON, meAIPlayer, System.nanoTime()) );
                    meAIPlayer.AIreadController(buttonEvents, right);
                    break;
                case "Idle":
                    meAIPlayer.AIreadController(buttonEvents, -1);
                    break;
                case "Approach":
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
        public String activeState(){
            return activeState;
        }
}
