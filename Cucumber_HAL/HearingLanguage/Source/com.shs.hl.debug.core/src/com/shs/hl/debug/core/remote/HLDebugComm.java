package com.shs.hl.debug.core.remote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HLDebugComm  {
  Socket input;
  Socket output;
  
  
  
  public static int getFreePort() {
		ServerSocket socket= null;
		try {
			socket= new ServerSocket(0);
			return socket.getLocalPort();
		} catch (IOException e) { 
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
		return -1;		
	}		

  
}
