package ticket.booking.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private User user;
    private List<User> userlist;
    private ObjectMapper objectmapper = new ObjectMapper();
    private static final String USERS_PATH = "src/main/java/ticket/booking/localDB/user.json";

    public UserBookingService() throws IOException {
        File users = new File(USERS_PATH);
        loaduser();
    }

    public List<User> loaduser() throws IOException {
        File users = new File(USERS_PATH);
        return objectmapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    public UserBookingService(User user) throws IOException {
        this.user = user;
        loaduser();
    }

    public Boolean loginUser() {
        Optional<User> foundUser = userlist.stream().filter(user -> {
            return user.getName().equalsIgnoreCase(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user.getHashpassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user) {
        try {
            userlist.add(user);
            saveUserlistToFile();
            return Boolean.TRUE;
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    public void saveUserlistToFile() throws IOException {
        File userFile = new File(USERS_PATH);
        objectmapper.writeValue(userFile, userlist);
    }

    public void fetchBookings() {
        user.printTickets();
    }

    public Boolean cancelBooking(String ticketId) {
        // todo:Complete The Function
        // User global level p hai us s fetch booking use karke usme ticketid pass karke jo v hoga usko array s
        // remove kar denge , need to see how to remove something from list and then save the same in local db
        // use saveuserlistToFile method do the same
        return Boolean.FALSE;
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (IOException ex) {
            return new ArrayList<>();
        }
    }

    public List<List<Integer>> fetchSeats(Train train) {
        return train.getSeats();
    }

    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try {
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }
}
