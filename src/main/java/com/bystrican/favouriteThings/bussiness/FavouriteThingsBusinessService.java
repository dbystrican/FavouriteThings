package com.bystrican.favouriteThings.bussiness;

import com.bystrican.favouriteThings.JDBC.DatabaseInterface;
import com.bystrican.favouriteThings.JDBC.FavouriteThing;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.List;
@Local(FavouriteThingsBusinessInterface.class)
@Stateless
@Alternative
public class FavouriteThingsBusinessService implements FavouriteThingsBusinessInterface {
    @Inject
    DatabaseInterface favouriteThingsDatabaseService;
    @Override
    public boolean delete(int id) {
        return favouriteThingsDatabaseService.delete(id);
    }

    @Override
    public boolean insert(FavouriteThing favouriteThing) {
        return favouriteThingsDatabaseService.insert(favouriteThing);
    }

    @Override
    public boolean update(int id, FavouriteThing favouriteThing) {
        return favouriteThingsDatabaseService.update(id,favouriteThing);
    }

    @Override
    public FavouriteThing read(int id) {
        return favouriteThingsDatabaseService.read(id);
    }

    @Override
    public List<FavouriteThing> read() {
        return favouriteThingsDatabaseService.read();
    }
}
