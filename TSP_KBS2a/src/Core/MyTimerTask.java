/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.util.TimerTask;

/**
 *
 * @author jelle
 */
public class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("Painting started");
        completeTask();
        System.out.println("Painting ended");
    }

    private void completeTask() {
        try {
            //assuming it takes 2 secs to complete the task
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
