package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;

	// static: para que não seja intanciado um novo simpledateforma para cada
	// reservate por isso ele é estatico
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation() {

	}
	//throws DomainException delega para a principal tratar 
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if (!checkOut.after(checkIn)) {// data de checkout não for posterior a data de checkin
			throw new DomainException("Check-out date must be after che-in date");
		}
		
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

	// CALCULA DURAÇÂO EM DIA APARTIR DA ENTRADA E SAIDA
	// long: inteiro mais longo
	public long duration() {
		// diferença em milessegundo da entra e da saida
		long diff = checkOut.getTime() - checkIn.getTime();// getTime: retorna a quantidade de milisegundo da data
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		// retorna: um TimeUnit que faz a conversão dos milesegundo para dia
	}

	// Recebe duas datas novas e atualiza o checkin e o checkout
	public void updateDates(Date checkIn, Date checkOut){
		Date now = new Date();
		// before: se as datas forem antes de agora não aceita a reserva
		// tratamentos de datas: que não pode ser anteriores a data atual
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException("Reservation dates for updates must be future dates");// usada quando os																					// argumentos são
																									// invalidos
		}
		if (!checkOut.after(checkIn)) {// data de checkout não for posterior a data de checkin
			throw new DomainException("Check-out date must be after che-in date");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		return "Room " + roomNumber + ", check-in: " + sdf.format(checkIn) + ", check-out: " + sdf.format(checkOut)
				+ ", " + duration() + " nights";
	}
}
