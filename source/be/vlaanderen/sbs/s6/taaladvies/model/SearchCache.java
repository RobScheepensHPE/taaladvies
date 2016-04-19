package be.vlaanderen.sbs.s6.taaladvies.model;

import java.io.Serializable;
import java.util.Date;
import be.vlaanderen.sbs.s6.taaladvies.overzicht.SearchForm;


public abstract class SearchCache implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2677288349116575976L;

    protected int displayAmount;

    // Queries

    private String forwardQuery;
    private String forwardQueryNext;
    private String backwardQuery;

    // Id to limit to the interval of records at the time of search
    private int idLimit;

    // The count of records in the interval at the time of search
    // (Page x of y)
    private int maxCount;

    //
    protected int currentCount = 1;

    //
    protected int currentMinId;

    //
    protected int currentMaxId;

    //
    protected Date currentMinDate;

    //
    protected Date currentMaxDate;

    //
    protected boolean categorie;

    // Gemeenschappelijk
    private int relevantie = 0;

    // Gemeenschappelijk
    private Date minDate;

    // Gemeenschappelijk
    private Date maxDate;

    // Gemeenschappelijk
    private Date singleDate;

    // Gemeenschappelijk
    private boolean owner;

    int interval = 0;

    /**
     * Gets the interval
     *
     * @return Returns a int
     */
    public int getInterval() {
        return interval;
    }

    /**
     * Sets the interval
     *
     * @param interval The interval to set
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }


    /**
     * Gets the forwardQuery
     *
     * @return Returns a String
     */
    public String getForwardQuery() {
        return forwardQuery;
    }

    /**
     * Sets the forwardQuery
     *
     * @param forwardQuery The forwardQuery to set
     */
    public void setForwardQuery(String forwardQuery) {
        this.forwardQuery = forwardQuery;
    }

    /**
     * Gets the backwardQuery
     *
     * @return Returns a String
     */
    public String getBackwardQuery() {
        return backwardQuery;
    }

    /**
     * Sets the backwardQuery
     *
     * @param backwardQuery The backwardQuery to set
     */
    public void setBackwardQuery(String backwardQuery) {
        this.backwardQuery = backwardQuery;
    }

    /**
     * Gets the forwardQueryNext
     *
     * @return Returns a String
     */
    public String getForwardQueryNext() {
        return forwardQueryNext;
    }

    /**
     * Sets the forwardQueryNext
     *
     * @param forwardQueryNext The forwardQueryNext to set
     */
    public void setForwardQueryNext(String forwardQueryNext) {
        this.forwardQueryNext = forwardQueryNext;
    }

    /**
     * Gets the maxDate
     *
     * @return Returns a Date
     */
    public Date getMaxDate() {
        return maxDate;
    }

    /**
     * Sets the maxDate
     *
     * @param maxDate The maxDate to set
     */
    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    /**
     * Gets the relevantie
     *
     * @return Returns a int
     */
    public int getRelevantie() {
        return relevantie;
    }

    /**
     * Sets the relevantie
     *
     * @param relevantie The relevantie to set
     */
    public void setRelevantie(int relevantie) {
        this.relevantie = relevantie;
    }

    /**
     * Gets the minDate
     *
     * @return Returns a Date
     */
    public Date getMinDate() {
        return minDate;
    }

    /**
     * Sets the minDate
     *
     * @param minDate The minDate to set
     */
    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    /**
     * Gets the idLimit
     *
     * @return Returns a int
     */
    public int getIdLimit() {
        return idLimit;
    }

    /**
     * Sets the idLimit
     *
     * @param idLimit The idLimit to set
     */
    public void setIdLimit(int idLimit) {
        this.idLimit = idLimit;
    }

    /**
     * Gets the currentCount
     *
     * @return Returns a int
     */
    public int getCurrentCount() {
        return currentCount;
    }

    /**
     * Sets the currentCount
     *
     * @param currentCount The currentCount to set
     */
    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    /**
     * Gets the currentMaxDate
     *
     * @return Returns a Date
     */
    public Date getCurrentMaxDate() {
        return currentMaxDate;
    }

    /**
     * Sets the currentMaxDate
     *
     * @param currentMaxDate The currentMaxDate to set
     */
    public void setCurrentMaxDate(Date currentMaxDate) {
        this.currentMaxDate = currentMaxDate;
    }

    /**
     * Gets the currentMaxId
     *
     * @return Returns a int
     */
    public int getCurrentMaxId() {
        return currentMaxId;
    }

    /**
     * Sets the currentMaxId
     *
     * @param currentMaxId The currentMaxId to set
     */
    public void setCurrentMaxId(int currentMaxId) {
        this.currentMaxId = currentMaxId;
    }

    /**
     * Gets the currentMinDate
     *
     * @return Returns a Date
     */
    public Date getCurrentMinDate() {
        return currentMinDate;
    }

    /**
     * Sets the currentMinDate
     *
     * @param currentMinDate The currentMinDate to set
     */
    public void setCurrentMinDate(Date currentMinDate) {
        this.currentMinDate = currentMinDate;
    }

    /**
     * Gets the currentMinId
     *
     * @return Returns a int
     */
    public int getCurrentMinId() {
        return currentMinId;
    }

    /**
     * Sets the currentMinId
     *
     * @param currentMinId The currentMinId to set
     */
    public void setCurrentMinId(int currentMinId) {
        this.currentMinId = currentMinId;
    }

    /**
     * Gets the maxCount
     *
     * @return Returns a int
     */
    public int getMaxCount() {
        return maxCount;
    }

    /**
     * Sets the maxCount
     *
     * @param maxCount The maxCount to set
     */
    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    /**
     * Gets the owner
     *
     * @return Returns a boolean
     */
    public boolean getOwner() {
        return owner;
    }

    /**
     * Sets the owner
     *
     * @param owner The owner to set
     */
    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public int getPageCount() {
        int pageCount = maxCount / displayAmount;

        if (maxCount % displayAmount > 0)
            pageCount++;

        return pageCount;
    }

    public boolean isLastPage() {
        return getPageCount() == currentCount;
    }

    public void loadPreviousRecords() {
        interval = 2;
        currentCount--;
        loadRecords();
    }

    public void loadNextRecords() {
        interval = 1;
        currentCount++;
        loadRecords();
    }

    public void loadFirstRecords() {
        interval = 0;
        loadRecords();
    }

    protected abstract void loadRecords();

    protected abstract Object[] getRecords();

    protected abstract void setRecords(Object[] records);

    protected void init(SearchForm criteria) {
        if (criteria.getDateSearch() == 1) {
            setMinDate(criteria.getDStartDate());
            setMaxDate(criteria.getDEndDate());
        } else {
            setSingleDate(criteria.getDSingleDate());
        }

        setRelevantie(criteria.getRelevantie());
        setCategorie(criteria.isCategorie());

        displayAmount = SearchForm.DISPLAY_AMOUNT;
    }

    /**
     * Gets the categorie
     *
     * @return Returns a boolean
     */
    public boolean getCategorie() {
        return categorie;
    }

    /**
     * Sets the categorie
     *
     * @param categorie The categorie to set
     */
    public void setCategorie(boolean categorie) {
        this.categorie = categorie;
    }

    /**
     * Gets the displayAmount
     *
     * @return Returns a int
     */
    public int getDisplayAmount() {
        return displayAmount;
	}
	/**
     * Sets the displayAmount
     *
     * @param displayAmount The displayAmount to set
     */
    public void setDisplayAmount(int displayAmount)
	{
		this.displayAmount = displayAmount;
	}

	/**
     * Gets the singleDate
     *
     * @return Returns a Date
     */
    public Date getSingleDate() {
		return singleDate;
	}
	/**
     * Sets the singleDate
     *
     * @param singleDate The singleDate to set
     */
    public void setSingleDate(Date singleDate)
	{
		this.singleDate = singleDate;
	}

}

