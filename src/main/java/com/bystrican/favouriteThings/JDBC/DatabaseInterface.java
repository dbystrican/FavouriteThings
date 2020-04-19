package com.bystrican.favouriteThings.JDBC;

import javax.ejb.Local;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Local
public interface DatabaseInterface {
    public boolean delete(int id);
    public boolean insert(FavouriteThing favouriteThing);
    public boolean update(int id, FavouriteThing favouriteThing);
    public FavouriteThing read(int id);
    public List<FavouriteThing> read();
}
