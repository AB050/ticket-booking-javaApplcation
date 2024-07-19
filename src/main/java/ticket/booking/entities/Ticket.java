package ticket.booking.entities;

import java.util.Date;

public class Ticket {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private String dateOftravel;
    private Train train;

    public Ticket(String ticketId, String userId, String source, String destination, String dateOftravel, Train train) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOftravel = dateOftravel;
        this.train = train;
    }

    public Ticket() {
    }

    ;




    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDateOftravel() {
        return dateOftravel;
    }

    public void setDateOftravel(String dateOftravel) {
        this.dateOftravel = dateOftravel;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getTicketsinfo() {
        return String.format("Ticket ID: %s belongs to user %s from %s to %s on %s", ticketId, userId, source, destination, dateOftravel);
    }

}
