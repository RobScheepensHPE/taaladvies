package be.vlaanderen.sbs.s6.taaladvies.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import be.vlaanderen.sbs.s6.framework.logging.AppLogger;
import be.vlaanderen.sbs.s6.taaladvies.overzicht.SearchForm;

public class SearchCacheTXT extends SearchCache implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8975942596943961754L;
    private Tekst[] records;

    public SearchCacheTXT(SearchForm criteria) {
        AppLogger.getInstance().debug(ToStringBuilder.reflectionToString(criteria));
        Tekst.buildVariableQueryPart(criteria);
        AppLogger.getInstance().debug(ToStringBuilder.reflectionToString(criteria));

        //	limits = array with limits
        //	limits[0] = count of records, to calculate the amount of data to be displayed
        //	limits[1] = id to limit our search (no newly added records during scrolling of search results)

        int[] limits = Tekst.findCountBySearchCriteria(criteria);

        setMaxCount(limits[0]);
        setIdLimit(limits[1]);

        if (limits[0] > 0) {
            String[] queries = Tekst.buildFixedQueries(criteria);

            setBackwardQuery(queries[0]);
            setForwardQuery(queries[1]);
            setForwardQueryNext(queries[2]);

            init(criteria);

            if (!criteria.getEigenTV() && !criteria.getAlleTV()) {
                displayAmount = displayAmount * 2;
            }
        }
    }

    protected void loadRecords() {
        records = Tekst.findBySearchCriteria(this);

        currentMaxDate = records[records.length - 1].getOproep().getOproepdatum();
        currentMaxId = records[records.length - 1].getId();
        currentMinDate = records[0].getOproep().getOproepdatum();
        currentMinId = records[0].getId();
    }

    /**
     * @see SearchCache2#setRecords(Object[])
     */
    public void setRecords(Object[] records) throws IllegalArgumentException {
        this.records = (Tekst[]) records;
    }

    /**
     * @see SearchCache2#getRecords()
     */
    public Object[] getRecords() {
        return records;
    }

}

