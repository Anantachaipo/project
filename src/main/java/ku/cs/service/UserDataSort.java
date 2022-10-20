package ku.cs.service;

import ku.cs.model.User;

import java.util.Comparator;

public class UserDataSort implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        if (user1.getLastLogin().compareTo(user2.getLastLogin()) > 0) return -1;
        if (user1.getLastLogin().compareTo(user2.getLastLogin()) < 0) return 1;

        return 0;
    }
}

