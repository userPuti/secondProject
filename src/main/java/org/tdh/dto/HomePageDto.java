package org.tdh.dto;

import org.tdh.domain.CkCkdx;

/**
 * @author Puti
 * @date 2022/5/7 10:48
 */
public class HomePageDto extends CkCkdx {
    private int start;
    private int limit;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
