package com.olivtopa.safetynetalerts.model;

public class FiresStation {

	private String address;
	private int station;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	@Override
	public String toString() {
		return "FiresStation [address=" + address + ", station=" + station + "]";
	}

}
