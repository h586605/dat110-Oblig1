package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServerStopStub;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) {
		
		Display display;
		Sensor sensor;
		
		RPCClient displayclient,sensorclient;
		
		System.out.println("Controller starting ...");
				
		RPCServerStopStub stopdisplay = new RPCServerStopStub();
		RPCServerStopStub stopsensor = new RPCServerStopStub();
		
		displayclient = new RPCClient(Common.DISPLAYHOST,Common.DISPLAYPORT);
		sensorclient = new RPCClient(Common.SENSORHOST,Common.SENSORPORT);
		
		displayclient.connect();
		sensorclient.connect();
		
		display = new Display();
		sensor = new Sensor();
		
		display.register(displayclient);
		sensor.register(sensorclient);
		
		displayclient.register(stopdisplay);
		sensorclient.register(stopsensor);
		
		for (int i = 0; i < N; i++) {
			int temp = sensor.read();
			try {
				Thread.sleep(1500); //For � f� forskjellig resultat p� m�lingene.
			} catch (InterruptedException e) {
			}
			display.write(String.valueOf(temp));
		}
		
		stopdisplay.stop();
		stopsensor.stop();
	
		displayclient.disconnect();
		sensorclient.disconnect();
		
		System.out.println("Controller stopping ...");
		
	}
}
