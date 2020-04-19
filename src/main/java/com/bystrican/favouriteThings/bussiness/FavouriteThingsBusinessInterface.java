package com.bystrican.favouriteThings.bussiness;

import com.bystrican.favouriteThings.JDBC.FavouriteThing;

import javax.ejb.Local;
import java.util.List;

@Local
public
interface FavouriteThingsBusinessInterface {

    public boolean delete(int id);
    public boolean insert(FavouriteThing favouriteThing);
    public boolean update(int id, FavouriteThing favouriteThing);
    public FavouriteThing read(int id);
    public List<FavouriteThing> read();
}
