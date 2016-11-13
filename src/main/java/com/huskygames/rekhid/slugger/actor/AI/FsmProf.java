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
        protected long timeofLastAttack;
        protected double fpX;
        protected double aiX;
        protected double aiY;
        protected double fpY;

        double distance = (Math.abs(fpX - aiX));

        private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger(Rekhid.class);
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
            timeofLastAttack=0;
            fpX = firstPlayer.getPosition().getX();
            aiX = meAIPlayer.getPosition().getX();
            aiY = meAIPlayer.getPosition().getY();
            fpY = firstPlayer.getPosition().getY();
            distance = (Math.abs(fpX - aiX));
        }

        public void pushState(StateTypes addState) { queuedStates.addFirst(addState); }

        public void clear(){ queuedStates = new LinkedList<StateTypes>(); }

        public void update() {
            fpX = firstPlayer.getPosition().getX();
            aiX = meAIPlayer.getPosition().getX();
            aiY = meAIPlayer.getPosition().getY();
            fpY = firstPlayer.getPosition().getY();
            double distance = (Math.abs(fpX - aiX));

            double fightorFlight = Math.random() * 10;


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
            }else {
                this.pushState(BLOCK);
            }
            popState();
        }

        protected void attack(){
            fpX = firstPlayer.getPosition().getX();
            aiX = meAIPlayer.getPosition().getX();
            aiY = meAIPlayer.getPosition().getY();
            fpY = firstPlayer.getPosition().getY();
            double distance = (Math.abs(fpX - aiX));
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
                    meAIPlayer.AIreadController(buttonEvents, left);
                    buttonEvents.add(new ButtonEvent(ATTACK_BUTTON, meAIPlayer, timeofLastAttack));
                    meAIPlayer.AIreadController(buttonEvents, left);
                //Face right
                }else{
                    Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
                    meAIPlayer.AIreadController(buttonEvents, right);
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

        protected void backstep_left() {
          //F  if( distance < 75 ) {
                Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
                meAIPlayer.AIreadController(buttonEvents, left);
           // }
        }

        protected void backstep_right(){
          //  if( distance < 75 ) {
                Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
                meAIPlayer.AIreadController(buttonEvents, right);
          //  }
        }

        protected void idle() {
            Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();
            meAIPlayer.AIreadController(buttonEvents, -1);
        }

        protected void approach() {
            Queue<ButtonEvent> buttonEvents = new LinkedList<ButtonEvent>();

            if (aiX > fpX){
                retreat_left();
            }else if(aiX < fpX){
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

            fpX = firstPlayer.getPosition().getX();
            aiX = meAIPlayer.getPosition().getX();
            aiY = meAIPlayer.getPosition().getY();
            fpY = firstPlayer.getPosition().getY();
            double distance = (Math.abs(fpX - aiX));
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
                        //Player is left of AI and facing right
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
                    }else{/**
                        if(jumporRun>1){
                            if(fpX < aiX && !firstPlayer.getFacing() ){
                                backstep_right();
                            }else{
                                backstep_left();;
                            }
                        }else if(jumporRun>9){
                            if(fpX < aiX && !firstPlayer.getFacing() ){
                                backstep_right();
                            }else{
                                backstep_left();;
                            }
                        }**/
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
