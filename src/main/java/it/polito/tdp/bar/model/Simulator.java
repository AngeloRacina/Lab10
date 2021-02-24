package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {

	// model of the world
	
	private List<Tavolo> tavoli ;
	
	
	// types of Events
	private int NMAX_PERSONE = 10;
	private int TMIN_ARRIVO_CLIENTI = 1;
	private int TMAX_ARRIVO_CLIENTI = 10;
	private int TMIN_PERMANENZA_CLIENTI = 60;
	private int TMAX_PERMANENZA_CLIENTI = 120;
	private EventType TAVOLO_LIBERATO;
	private Double MAX_TOLLERANZA = 0.9;
	private double MIN_OCCUPAZIONE = 0.5;
	
	private PriorityQueue<Event> queue;
	
	// Parameters of simulation
	
	private int nEventi = 2000;

	// output values 
	private Statistiche stats;
	
	
	public void inizializza() {
		
		caricaTavoli();
		
		this.queue = new PriorityQueue<>();
		this.caricaEventi();
		
		this.stats = new Statistiche();
		
	}


	private void caricaEventi() {
		
		Duration arrivo = Duration.ofMinutes(0);
		for(int i = 0; i<this.nEventi; i++) {
			
			int persone = (int) (Math.random()*this.NMAX_PERSONE);
			Duration durata = Duration.ofMinutes((long) (this.TMIN_PERMANENZA_CLIENTI+(Math.random()*this.TMIN_PERMANENZA_CLIENTI)));
			double tolleranza = Math.random()+this.MAX_TOLLERANZA;
			
			Event e = new Event(EventType.ARRIVO_GRUPPO_CLIENTI, arrivo, persone, durata, tolleranza, null);
			this.queue.add(e);
			
			arrivo.plusMinutes((long) (this.TMIN_ARRIVO_CLIENTI+Math.random()+this.TMAX_ARRIVO_CLIENTI));
		}
		
	}


	private void caricaTavoli() {
		
		this.tavoli = new ArrayList<>();
		
		tavoli.add(new Tavolo(10, false));
		tavoli.add(new Tavolo(10, false));
		tavoli.add(new Tavolo(8, false));
		tavoli.add(new Tavolo(8, false));
		tavoli.add(new Tavolo(8, false));
		tavoli.add(new Tavolo(8, false));
		tavoli.add(new Tavolo(6, false));
		tavoli.add(new Tavolo(6, false));
		tavoli.add(new Tavolo(6, false));
		tavoli.add(new Tavolo(6, false));
		tavoli.add(new Tavolo(4, false));
		tavoli.add(new Tavolo(4, false));
		tavoli.add(new Tavolo(4, false));
		tavoli.add(new Tavolo(4, false));
		tavoli.add(new Tavolo(4, false));
		
		Collections.sort(this.tavoli);
		
	}
	
	public void run() {
		
		while(this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processaEvento(e);
		}
	}


	private void processaEvento(Event e) {
		// TODO Auto-generated method stub
		switch(e.getType()) {
		case ARRIVO_GRUPPO_CLIENTI:
			
			for(Tavolo t : this.tavoli) {
				if(e.getnPersone() <= t.getnPosti()*this.MAX_TOLLERANZA && t.isOccupato()==false) {
					// satisfied
					t.setOccupato(true);
					this.stats.addnClientiTot(e.getnPersone());
					this.stats.addnClientiSoddisfatti(e.getnPersone());
					Event event = new Event(EventType.TAVOLO_LIBERATO, e.getTime(), e.getnPersone(), e.getDurata() , e.getTolleranza(), t);
					this.queue.add(event);
					
				}else {
					// try desk
					if(e.getTolleranza()<= 0.9) {
						// satisfied
						this.stats.addnClientiTot(e.getnPersone());
						this.stats.addnClientiSoddisfatti(e.getnPersone());
						

					}else {
						// non satisfied
						this.stats.addnClientiTot(e.getnPersone());
						this.stats.addnClientiInsoddisfatti(e.getnPersone());
					}
						
				}
			}
			break;
			
		case TAVOLO_LIBERATO: 
			
			e.getTavolo().setOccupato(false);
			break;
		}
		
	}
	
	public Statistiche getStatistiche() {
		return stats;
	}
}
