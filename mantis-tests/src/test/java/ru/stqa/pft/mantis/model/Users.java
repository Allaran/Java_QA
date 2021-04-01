package ru.stqa.pft.mantis.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users {
    private Set<UserData> delegate;

    public Users(Users users) {
        this.delegate = new HashSet<UserData>(users.delegate);
    }

    public Users(Collection<UserData> users) {
        this.delegate=new HashSet<UserData>(users);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }
    public Users withAdded(UserData user) {
        Users users = new Users(this);
        users.add(user);
        return users;
    }
    public Users without(UserData user) {
        Users users = new Users(this);
        users.remove(user);
        return users;
    }
}
