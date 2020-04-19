package com.bystrican.favouriteThings.controller;

import com.bystrican.favouriteThings.JDBC.FavouriteThing;
import com.bystrican.favouriteThings.bussiness.FavouriteThingsBusinessInterface;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
@SessionScoped
public class FavouiriteThingController {
    @Inject
    FavouriteThingsBusinessInterface favouriteThingsBussinessInterface;
    @Inject
    FavouriteThing favouriteThing;

    public String onEdit(FavouriteThing favouriteThing){
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("favouriteThing",favouriteThing);
        System.out.println("onEditId:"+FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(),"#{favouriteThing}",FavouriteThing.class).getId());
        return "edit_form.xhtml";
    }
    public String onSubmitEdit(){
        FavouriteThing favouriteThing = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(),"#{favouriteThing}",FavouriteThing.class);
        favouriteThingsBussinessInterface.update(favouriteThing.getId(),favouriteThing);
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("favouriteThing",favouriteThing);
        return "response_page.xhtml";
    }

    public String onCreate(){
        System.out.println("You clicked");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FavouriteThing favouriteThing = facesContext.getApplication().evaluateExpressionGet(facesContext,"#{favouriteThing}",FavouriteThing.class);
        System.out.println(favouriteThing);
        favouriteThingsBussinessInterface.insert(favouriteThing);
        return "response_page.xhtml";
    }
    public String onDelete(FavouriteThing favouriteThing){
        System.out.println("Clicked on delete button");
        System.out.println("Deleting "+favouriteThing.getName());
        favouriteThingsBussinessInterface.delete(favouriteThing.getId());
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("favouriteThing",favouriteThing);
        return "response_page.xhtml";
    }

    public FavouriteThingsBusinessInterface getFavouriteThingsBussinessInterface() {
        return favouriteThingsBussinessInterface;
    }
}
