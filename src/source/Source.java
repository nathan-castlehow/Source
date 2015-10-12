/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

/**
 *
 * @author Natus
 */
public class Source extends Thread{
    private Thread t;
    public void run(){
        
    }
    public void start(){
        t = new Thread(this,"T1");
        t.start();
    }
    
}
