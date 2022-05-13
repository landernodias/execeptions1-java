package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	//static: para que não seja intanciado um novo simpledateforma para cada reservate por isso ele é estatico
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
		
	}
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	public Integer getRoomNumber() {
		return roomNumber;
	}
	
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public Date getCheckIn() {
		return checkIn;
	}
	
	public Date getCheckOut() {
		return checkOut;
	}
	//CALCULA DURAÇÂO EM DIA APARTIR DA ENTRADA E SAIDA
	//long: inteiro mais longo
	public long duration() {
		//diferença em milessegundo da entra e da saida
		long diff = checkOut.getTime() - checkIn.getTime();//getTime: retorna a quantidade de milisegundo da data
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		//retorna: um TimeUnit que faz a conversão dos milesegundo para dia
	}
	
	//Recebe duas datas novas e atualiza o checkin e o checkout
	public String updateDates(Date checkIn, Date checkOut) {
		Date now = new Date();
		// before: se as datas forem antes de agora não aceita a reserva
		// tratamentos de datas: que não pode ser anteriores a data atual
		if (checkIn.before(now) || checkOut.before(now)) {
			return "Reservation dates for updates must be future dates";
		} 
		if (!checkOut.after(checkIn)) {// data de checkout não for posterior a data de checkin
			return "Check-out date must be after che-in date";
		}
			this.checkIn = checkIn;
			this.checkOut = checkOut;
			return null;//criterio para retorna que não deu nenhum erro
	}
	
	@Override
	public String toString() {
		return "Room " 
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}
}
