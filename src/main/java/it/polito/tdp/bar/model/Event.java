package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Event {

	public enum EventType {
		
		ARRIVO_GRUPPO_CLIENTI, TAVOLO_LIBERATO,
	}
	
	private EventType type;
	private Duration time;
	private int nPersone;
	private Duration durata;
	private double tolleranza;
	private Tavolo tavolo;
	
	public Event(EventType type, Duration time, int nPersone, Duration durata, double tolleranza, Tavolo tavolo) {
		super();
		this.type = type;
		this.time = time;
		this.nPersone = nPersone;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.tavolo = tavolo;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public Duration getTime() {
		return time;
	}

	public void setTime(Duration time) {
		this.time = time;
	}

	public int getnPersone() {
		return nPersone;
	}

	public void setnPersone(int nPersone) {
		this.nPersone = nPersone;
	}

	public Duration getDurata() {
		return durata;
	}

	public void setDurata(Duration durata) {
		this.durata = durata;
	}

	public double getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(double tolleranza) {
		this.tolleranza = tolleranza;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((durata == null) ? 0 : durata.hashCode());
		result = prime * result + nPersone;
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		long temp;
		temp = Double.doubleToLongBits(tolleranza);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (durata == null) {
			if (other.durata != null)
				return false;
		} else if (!durata.equals(other.durata))
			return false;
		if (nPersone != other.nPersone)
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (Double.doubleToLongBits(tolleranza) != Double.doubleToLongBits(other.tolleranza))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
}
