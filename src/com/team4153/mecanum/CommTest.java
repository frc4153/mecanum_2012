/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4153.mecanum;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.ServerSocketConnection;
import javax.microedition.io.SocketConnection;

/**
 *
 * @author KPT
 */
class CommTest extends Thread {
    
    void doTest() {
	Thread t = new Thread(
	    new Runnable(){
		public void run(){
		    try {
			CommTest.this.socketWorker();
		    } catch (IOException ex) {
			System.out.println("CommTest::IOException in run(): " + ex.toString());
		    }
		}
	    }
	);
	t.start(); 
	System.out.println("Worker thread created. Listening on 32000.");
    }
    
    private void socketWorker() throws IOException{
	ServerSocketConnection socketCon = 
	    (ServerSocketConnection)Connector.open("socket://:32000");
	// Wait for a connection.
	SocketConnection sc = (SocketConnection) socketCon.acceptAndOpen();
	System.out.println("Got connection from: " + sc.getAddress());
	// Get the input stream of the connection.
	DataInputStream is = sc.openDataInputStream();

	// Get the output stream of the connection.
	DataOutputStream os = sc.openDataOutputStream();
	
	int inputInt = 0;
	while (inputInt<=100) {
	    inputInt = is.readInt();
	    System.out.println("read: " + inputInt);
	    os.writeInt(inputInt * -1);
	    os.flush();
	    //break;
	}
	is.close();
	os.close();
	sc.close();
	socketCon.close();
    }
}
