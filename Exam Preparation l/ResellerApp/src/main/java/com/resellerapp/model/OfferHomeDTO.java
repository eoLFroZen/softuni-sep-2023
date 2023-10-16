package com.resellerapp.model;

import java.util.List;

public class OfferHomeDTO {

    private List<MyOfferDTO> myOffers;

    private List<BoughtOffersDTO> boughtOffers;

    private List<OtherOffersDTO> allOtherOffers;

    private long totalOtherOffers;

    public OfferHomeDTO(List<MyOfferDTO> myOffers, List<BoughtOffersDTO> boughtOffers, List<OtherOffersDTO> otherOffers) {
        this.myOffers = myOffers;
        this.boughtOffers = boughtOffers;
        this.allOtherOffers = otherOffers;
        this.totalOtherOffers = otherOffers.size();
    }

    public List<MyOfferDTO> getMyOffers() {
        return myOffers;
    }

    public void setMyOffers(List<MyOfferDTO> myOffers) {
        this.myOffers = myOffers;
    }

    public List<BoughtOffersDTO> getBoughtOffers() {
        return boughtOffers;
    }

    public void setBoughtOffers(List<BoughtOffersDTO> boughtOffers) {
        this.boughtOffers = boughtOffers;
    }

    public List<OtherOffersDTO> getAllOtherOffers() {
        return allOtherOffers;
    }

    public void setAllOtherOffers(List<OtherOffersDTO> allOtherOffers) {
        this.allOtherOffers = allOtherOffers;
    }

    public long getTotalOtherOffers() {
        return totalOtherOffers;
    }

    public void setTotalOtherOffers(long totalOtherOffers) {
        this.totalOtherOffers = totalOtherOffers;
    }
}
