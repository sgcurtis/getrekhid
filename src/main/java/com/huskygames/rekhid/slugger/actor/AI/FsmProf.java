package com.huskygames.rekhid.slugger.actor.AI;

import com.huskygames.rekhid.Rekhid;
import com.huskygames.rekhid.actor.StickMan;
import com.huskygames.rekhid.slugger.input.ButtonEvent;
import com.huskygames.rekhid.slugger.util.DoublePair;
import com.sun.media.jfxmedia.logging.Logger;
import org.apache.logging.log4j.LogManager;

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
        private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger(Rekhid.class);
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

        public void clear(){ queuedStates = new LinkedList<StateTypes>(); }

        public void update() {

            double fpX = firstPlayer.getPosition().getX();
            double aiX = meAIPlayer.getPosition().getX();
            double aiY = meAIPlayer.getPosition().getY();
            double fpY = firstPlayer.getPosition().getY();
            double fightorFlight = Math.random() * 10;

            double distance = (Math.abs(fpX - aiX));

            if(distance > 500) {
                this.pushState(APPROACH);
            }else if( distance < 400 ) { // &&  firstPlayer.getDamage() meAIPlayer.getDamage()){

                if( distance < 70 ){
                    this.pushState(COMBAT);
                }else if( distance > 50 ){
                    this.pushState(CHASE);
                }else{
                    this.pushState(IDLE);
                }

            }else if(distance > 200){
                this.pushState(IDLE);

                /**    }else if(firstPlayer.getVelocity().getX() < 0.5 && Math.abs(fpX - aiX)<50  ){
                if(fightorFlight > 3 && Math.abs(fpY - aiY) < 30) {
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
                }  **/

            }else {
                this.pushState(BLOCK);
            }
            popState();
        }

        /**
        protected void down_attack(){
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
        }**/

        protected void attack(){
            double fpX = firstPlayer.getPosition().getX();
            double aiX = meAIPlayer.getPosition().getX();
            double fpY = firstPlayer.getPosition().getY();
            double aiY = meAIPlayer.getPosition().getY();

            if(timeofLastAttack==0){
                timeofLastAttack = System.nanoTime();
            }

            //Only attack every second
            if(System.nanoTime() - timeofLastAttack > 1000000000 ) {
                timeofLastAttack = System.nanoTime();

                if( Math.abs(fpY - aiY) < 50 )
                //Face left
                if(fpX < aiX){
                    Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
                    buttonEvents.add(new ButtonEvent(ATTACK_BUTTON, meAIPlayer, timeofLastAttack));
                    meAIPlayer.AIreadController(buttonEvents, left);
                //Face right
                }else{
                    Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
                    buttonEvents.add(new ButtonEvent(ATTACK_BUTTON, meAIPlayer, timeofLastAttack));
                    meAIPlayer.AIreadController(buttonEvents, right);
                }
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

        protected void backstep_left(){
            Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
                meAIPlayer.AIreadController(buttonEvents, left);
        }

        protected void backstep_right(){
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
            }else if(meAIPlayer.getPosition().getX() < firstPlayer.getPosition().getX()){
                retreat_right();
            }
        }
        protected void jump() {
            Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
            buttonEvents.add(new ButtonEvent(JUMP_BUTTON, meAIPlayer, System.nanoTime()));
            meAIPlayer.AIreadController(buttonEvents, -1);
        }

        protected void left_jump() {
            if(meAIPlayer.getVelocity().getY()==0) {
                Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
                buttonEvents.add(new ButtonEvent(JUMP_BUTTON, meAIPlayer, System.nanoTime()));
                meAIPlayer.AIreadController(buttonEvents, left);
            }
        }
        protected void right_jump() {
            if(meAIPlayer.getVelocity().getY()==0) {
                Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
                buttonEvents.add(new ButtonEvent(JUMP_BUTTON, meAIPlayer, System.nanoTime()));
                meAIPlayer.AIreadController(buttonEvents, right);
            }
        }

        public void popState(){
            double fpX = firstPlayer.getPosition().getX();
            double aiX = meAIPlayer.getPosition().getX();
            double aiY = meAIPlayer.getPosition().getY();
            double fpY = firstPlayer.getPosition().getY();
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
                    break;

                case COMBAT:
                    double jumporRun = Math.random() * 10;


                    if(firstPlayer.attacking()){
                        //Player is left of AI
                        if(fpX < aiX && !firstPlayer.getFacing() ){
                            if(jumporRun<1){
                                right_jump();
                            }else {
                                backstep_right();
                            }

                        }else if(fpX> aiX && firstPlayer.getFacing() ){
                            if(jumporRun<1) {
                                left_jump();
                            }else{
                                backstep_left();
                            }
                        }
                    }else{
                        attack();
                    }
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
