package com.huskygames.rekhid.slugger.actor.AI;

import com.huskygames.rekhid.actor.StickMan;
import com.huskygames.rekhid.slugger.input.ButtonEvent;
import com.huskygames.rekhid.slugger.util.DoublePair;

import java.util.LinkedList;
import java.util.Queue;

import static com.huskygames.rekhid.slugger.actor.AI.StateTypes.*;
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
        protected long timeofLastAttack=0;
     //   protected LinkedList<>
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
            double aiY = meAIPlayer.getPosition().getY();
            double fpY = firstPlayer.getPosition().getY();
            double fightorFlight = Math.random() * 10;

            if(Math.abs(fpX - aiX) > 500) {
                this.pushState(APPROACH);

            }else if(Math.abs(fpX-aiX) <150 ){ //&&  firstPlayer.getDamage() meAIPlayer.getDamage()){

                this.pushState(CHASE);


            }else if(Math.abs(fpX - aiX) > 200){
                this.pushState(IDLE);
            }else if(firstPlayer.getVelocity().getX() < 0.5 && Math.abs(fpX - aiX)<50  ){
                if(fightorFlight > 3 && Math.abs(fpY - aiY) < 100) {
                    if(activeState!=ATTACK) {
                        this.pushState(ATTACK);
                    }
                }else{
                    this.pushState(BLOCK);
                }
            }else if(Math.abs(fpX - aiX) < 200 && firstPlayer.getDamage() > 2.0){
                if(aiX <600 || aiX > fpX ){
                    this.pushState(RETREAT_RIGHT);
                }else {
                    this.pushState(RETREAT_LEFT);
                }
            }else {
                this.pushState(BLOCK);
            }
            popState();
        }

        protected void attack(){
            if(timeofLastAttack==0){
                timeofLastAttack = System.nanoTime();
            }

            //Only attack every second
            if(System.nanoTime() - timeofLastAttack > 1000000000 ) {
                Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
                buttonEvents.add(new ButtonEvent(ATTACK_BUTTON, meAIPlayer, System.nanoTime()));
                meAIPlayer.AIreadController(buttonEvents, -1);
                timeofLastAttack = System.nanoTime();
            }

        }

        protected void block(){
            Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
            buttonEvents.add(new ButtonEvent(SHIELD_BUTTON, meAIPlayer, System.nanoTime()));
            meAIPlayer.AIreadController(buttonEvents, -1);
        }

        protected void retreat_left(){
            Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
            meAIPlayer.AIreadController(buttonEvents, left);
        }

        protected void retreat_right(){
            Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
            meAIPlayer.AIreadController(buttonEvents, right);
        }

        protected void idle() {
            Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
            meAIPlayer.AIreadController(buttonEvents, -1);
        }

        protected void approach() {
            Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
            if (meAIPlayer.getPosition().getX() > firstPlayer.getPosition().getX()){
                retreat_left();
            }else if(meAIPlayer.getPosition().getX()<firstPlayer.getPosition().getX()){
                retreat_right();
            }
        }

        public void popState(){

            StateTypes statecase = queuedStates.pop();
            activeState = statecase;

            switch(statecase) {
                case ATTACK:
                    attack();
                    break;
                case BLOCK:
                    block();
                    break;
                case RETREAT_LEFT:
                    retreat_left();
                    break;

                case CHASE:
                    approach();
                    attack();

                    break;
                case RETREAT_RIGHT:
                    retreat_right();
                    break;
                case IDLE:
                    idle();
                    break;
                case APPROACH:
                    approach();
                    break;
                default:

                    break;
            }
        }
        public StateTypes activeState(){
            return activeState;
        }
}
