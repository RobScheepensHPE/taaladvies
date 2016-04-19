package be.vlaanderen.sbs.s6.taaladvies.model;

import java.io.Serializable;
import be.vlaanderen.sbs.s6.taaladvies.overzicht.SearchForm;

public class SearchCacheTLV extends SearchCache implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3144287120640287220L;
    private Taalvraag[] records;

    public SearchCacheTLV(SearchForm criteria) {
        Taalvraag.buildVariableQueryPart(criteria);

        //	limits = array with limits
        //	limits[0] = count of records, to calculate the amount of data to be displayed
        //	limits[1] = id to limit our search (no newly added records during scrolling of search results)

        int[] limits = Taalvraag.findCountBySearchCriteria(criteria);

        setMaxCount(limits[0]);
        setIdLimit(limits[1]);

        if (limits[0] > 0) {
            String[] queries = Taalvraag.buildFixedQueries(criteria);

            setBackwardQuery(queries[0]);
            setForwardQuery(queries[1]);
            setForwardQueryNext(queries[2]);
            
            init(criteria);

            for (int i = 0; i < queries.length; i++) {
                String string = queries[i];
                System.err.println("xxxxxxxxxxxxxxxxxxxx query generated:");
                String categorieen="";
                if (criteria.getCategorieList().size()>0) {
                    categorieen += ", Categorieën: ";
                    for (Categorie categorie: criteria.getCategorieList()) {
                	categorieen += ", " + categorie.getNummer();
                    }
                }
                System.err.println(string + ", dSingleDate: " + criteria.getDSingleDate() + ", dEndDate: " + criteria.getDEndDate() + ", dStartDate: " + criteria.getDStartDate() + ", singleDate: " + criteria.getSingleDate() + ", endDate: " + criteria.getEndDate() + ", startDate: " + criteria.getStartDate() + ", userId: " + criteria.getUserId() + ", interval: " + criteria.getInterval() + ", relevantie: " + criteria.getRelevantie() + categorieen);
            }

            if (!criteria.getEigenTXT() && !criteria.getAlleTXT()) {
                displayAmount = displayAmount * 2;
            }
        }
    }

    protected void loadRecords() {
        records = Taalvraag.findBySearchCriteria(this);
        if (records.length>0 && records[records.length - 1]!=null) {
            	currentMaxDate = records[records.length - 1].getOproep().getOproepdatum();
        	currentMaxId = records[records.length - 1].getId();
        	currentMinDate = records[0].getOproep().getOproepdatum();
        	currentMinId = records[0].getId();
        } else {
            System.err.println(
        	"Problem with loading records to cache:"+
           	"\ndisplayAmount = "+displayAmount+
           	"\nforwardQuery = "+getForwardQuery()+
           	"\nforwardQueryNext = "+getForwardQueryNext()+
           	"\nbackwardQuery = "+getBackwardQuery()+
           	"\nidLimit = "+getIdLimit()+
           	"\nmaxCount = "+getMaxCount()+
           	"\ncurrentCount = "+currentCount+
           	"\ncurrentMinId = "+currentMinId+
           	"\ncurrentMaxId = "+currentMaxId+
           	"\ncurrentMinDat = "+getCurrentMinDate()+
           	"\ncurrentMaxDate = "+currentMaxDate+
           	"\ncategorie = "+categorie+
           	"\nrelevantie = "+getRelevantie()+
           	"\nminDate = "+getMinDate()+
           	"\nmaxDate = "+getMaxDate()+
           	"\nsingleDate = "+getSingleDate()+
           	"\nowner = "+getOwner()+
           	"\ninterval = "+interval
            );
        }
    }

    /**
     * @see <a href="SearchCache2.html#setRecords(Object[])">SearchCache2.java</a>
     */
    public void setRecords(Object[] records) throws IllegalArgumentException {
        this.records = (Taalvraag[]) records;
    }

    /**
     * @see <a href="SearchCache2.html#getRecords()">SearchCache2.java</a>
     */
    public Object[] getRecords() {
        return records;
    }

}

